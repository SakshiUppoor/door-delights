"""
This script allows you to automatically adds your SQL scripts
and corresponding rollback scripts to the appropriate directories
and append it to the latest unrealeased version in releases.json

- Creates a .sql file from the given migration SQL script
in sql_queries/migrations/ (for eg: sql_queries/migrations/00023_add_new_menu_item.sql)
- Creates a .sql file from the given rollback SQL script
in sql_queries/rollback/ (for eg: sql_queries/rollback/00023_add_new_menu_item.sql)
- Updates releases.json by finding the latest unrealeased version and 
appending the SQL file name in it's migration
"""

from os import listdir
from pathlib import Path
import json


def take_input():
    print("Details for the migration file: ")
    sql_script = ""
    migration_name = input("Name: ")
    migration_reason = input("Reason for creating the migration: ")
    line = input("SQL script: ")
    sql_script += line + "\n"

    while line != "":
        line = input()
        sql_script += line + "\n"

    # permissions = []
    # print("Which permissions are needed to run this script?\n (Leave blank if no)\n")
    # permissions.append("create") if input("Create? [-/y] ")!="" else None
    # permissions.append("alter") if input("Alter? [-/y] ")!="" else None
    # permissions.append("insert") if input("Insert? [-/y] ")!="" else None
    # permissions.append("drop") if input("Drop? [-/y] ")!="" else None

    rollback_script = ""
    line = input("SQL script for rollback: ")
    rollback_script += line + "\n"

    while line != "":
        line = input()
        rollback_script += line + "\n"

    # permissions_for_rollback = []
    # print("Which permissions are needed to run this script?\n (Leave blank if no)\n")
    # permissions_for_rollback.append("create") if input("Create? [-/y] ")!="" else None
    # permissions_for_rollback.append("alter") if input("Alter? [-/y] ")!="" else None
    # permissions_for_rollback.append("insert") if input("Insert? [-/y] ")!="" else None
    # permissions_for_rollback.append("drop") if input("Drop? [-/y] ")!="" else None

    migration_comments = input("Comments: ")
    return migration_name, migration_reason, migration_comments, sql_script, rollback_script


def get_migration_number():
    MIGRATION_NO_DIGITS = 5
    path = "migrations"
    try:
        n = len(listdir(path))
        return "0" * (MIGRATION_NO_DIGITS - len(str(n))) + str(n)
    except OSError:
        Path(path).mkdir(parents=True, exist_ok=True)
        return "0" * MIGRATION_NO_DIGITS


def create_migration_files(migration_name, migration_reason, migration_comments, sql_script, rollback_script):
    migration_no = get_migration_number()
    filename = migration_no + "_" + migration_name.lower().replace(" ", "_") + ".json"

    content = {
        "Name": migration_name,
        "Reason for the migration": migration_reason,
        "SQL script": sql_script,
        "Rollback script": rollback_script,
        "Comments": migration_comments
    }
    
    with open(f"migrations/{filename}", "w") as file:
        json.dump(content, file, indent=4)

    return filename


def include_in_releases_json(file_name):
    latest_release = None
    releases = []
    with open("releases.json") as json_file:
        releases = json.load(json_file).get("releases")

        for i in range(len(releases)):
            if not releases[i]["dateReleasedOnProd"]:
                latest_release = releases[i]["number"]
                releases[i]["migrations"].append(file_name)
                break

    if not latest_release:
        if len(releases) == 0:
            latest_release = "0.0.0"
        else:
            last_release = releases[0]["number"]
            latest_release = input(
                f"No future released found. (Last release no.: { last_release }) \nPlease enter release number: "
            )
        releases.insert(
            0,
            (
                {
                    "number": latest_release,
                    "dateReleasedOnProd": None,
                    "migrations": [file_name],
                }
            ),
        )

    data = {"releases": releases}

    with open("releases.json", "w") as file:
        json.dump(data, file, indent=4)


migration_name, migration_reason, migration_comments, sql_script, rollback_script = take_input()

file_name = create_migration_files(migration_name, migration_reason, migration_comments, sql_script, rollback_script)

include_in_releases_json(file_name)

print(f"Successfully added migration files ({file_name})")
