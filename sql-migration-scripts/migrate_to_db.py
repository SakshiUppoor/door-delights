import json
import psycopg2
from datetime import datetime
import os
from pathlib import Path
from dotenv import load_dotenv
from utils import (
    parse_args,
    log_success,
    log_error,
    create_server_connection,
)

load_dotenv()

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

"""
Get migrations which are already applied on the machine
"""
applied_migrations = []
rolled_back_migrations = []

local_migrations = Path('local_migrations.json')
local_migrations.touch(exist_ok=True)

with open("local_migrations.json") as json_file:
    try:
        applied_migrations = json.load(json_file).get("migrations")
    except Exception as e:
        applied_migrations = []

if not applied_migrations:
    applied_migrations = []

if rollback:
    migrations = [element for element in migrations if element in applied_migrations]
else:
    migrations = [element for element in migrations if element not in applied_migrations]

if len(migrations) == 0:
    print("No migrations to apply.")

failed_flag = 0
for migration in migrations:

    with open(f"{path}/{migration}", "r") as file:
        # Fetch SQL queries from the file
        query = file.read()

        try:

            # TODO Get appropriate permissions
            if rollback:
                print(f"Rolling back {migration}.........", end="")
            else:
                print(f"Applying {migration}.........", end="")
            cursor.execute(query)
            if rollback:
                rolled_back_migrations.append(migration)
            else:
                applied_migrations.append(migration)
            log_success(release_no, migration, rollback)

            # TODO Revoke access permissions

        except (Exception, psycopg2.Error) as error:
            failed_flag = 1
            log_error(release_no, migration, error, rollback)

            break
    
# Update realeases.json
db_server = os.getenv("DATABASE_SERVER").lower()
if rollback:
    if db_server == "prod":
        releases[release_id]["dateReleasedOnProd"] = None
else:
    if db_server == "prod":
        now = datetime.now()
        releases[release_id]["dateReleasedOnProd"] = now.isoformat()
data = {"releases": releases}

with open("releases.json", "w") as file:
    json.dump(data, file, indent=4)

# Update Local Migration JSON
with open("local_migrations.json", "w") as file:
    if rollback:
        migrations = [element for element in applied_migrations if element not in rolled_back_migrations]
    else:
        migrations = applied_migrations
    data = {"migrations":migrations}
    json.dump(data, file, indent=4)

if connection:
    cursor.close()
    connection.close()

if len(migrations) != 0 and not failed_flag:
    if rollback:
        print("Rolled back successfully. ")
    else:
        print("Migrations applied succesfully. ")
