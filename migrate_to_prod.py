import json
import psycopg2
from utils import (
    parse_args,
    log_success,
    log_error,
    create_server_connection,
)

# Get release_no and whether the rollback feature is called
release_no, rollback = parse_args()

# Credentials fetched from environment variables
connection = create_server_connection()
cursor = connection.cursor()

"""
If rollback option is selected, then run sql files from rollback directory
Else run sql files from migrations directory
"""
directory = "rollback" if rollback else "migrations"
path = f"sql_queries/{directory}"

# Get list of all SQL files to be executed
release_id = -1
releases = []
migrations = []

with open("releases.json") as json_file:
    releases = json.load(json_file).get("releases")
    for i in range(len(releases)):
        if releases[i]["number"] == release_no:
            release_id = i
            break

if release_id != -1:
    migrations = releases[release_id]["migrations"]
    if rollback:
        migrations = migrations[::-1]

else:
    print("No migrations found.")

failed_flag = 0
for migration in migrations:

    with open(f"{path}/{migration}", "r") as file:
        # Fetch SQL queries from the file
        query = file.read()

        try:

            # TODO Get appropriate permissions

            cursor.execute(query)
            log_success(release_no, migration, rollback)

            # TODO Revoke access permissions

        except (Exception, psycopg2.Error) as error:
            failed_flag = 1
            log_error(release_no, migration, error, rollback)

            break

    releases[release_id]["released"] = True
    data = {"releases": releases}

    with open("releases.json", "w") as file:
        json.dump(data, file, indent=4)

if connection:
    cursor.close()
    connection.close()

if migrations and not failed_flag:
    print("Migrations applied succesfully. ")
