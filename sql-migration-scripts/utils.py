import psycopg2
import os
import argparse
from datetime import datetime
from dotenv import load_dotenv

load_dotenv()

"""
For parsing arguments provided from the command line 
and displaying the help menu

Arguments:
-

Returns:
    - release_no: str 
        Release no. provided from the command line. This is used for 
        determining which migrations have to be applied
    - rollback: bool
        indicates whether rollback option is selected or not
"""


def parse_args():
    parser = argparse.ArgumentParser(
        description="Migrate DB changes between environments."
    )

    parser.add_argument(
        "release_no",
        help="Release number of the migrations you want to apply",
        type=str,
    )

    parser.add_argument(
        "--rollback",
        action="store_true",
        help="Rollback migrations applied in this release",
    )

    # Return release number and optional rollback option from argument
    args = parser.parse_args()
    return args.release_no, args.rollback


"""
For maintaining log of applied/failed migrations and errors
Append message along with timestamp to log files

Arguments: 
    - filename: str 
        Name of log file in which the message is to be appended.
        for eg: "db.log", "error.log"
    - message: str 
        Message to be appended in the log file
        for eg: "Migration applied successfully"

Returns:
-
"""


def write_to_log_file(filename, message):
    now = datetime.now()
    with open(filename, "a") as log_file:
        log_file.write(f"[{now}] {message} \n")


"""
Once a migration is applied successfully, it's appended 
in the message and db log files

Arguments: 
    - release_no: str 
        Release number
    - migration_file_name: str 
        Name of the SQL file which was migrated

Returns:
-
"""


def log_success(release_no, migration_file_name, rollback):
    if rollback:
        print("Rolled back.")
        message = f"Release-{release_no} Succesfully rolled back migration {migration_file_name}"
    else:
        print("Applied.")
        message = (
            f"Release-{release_no} Succesfully applied migration {migration_file_name}"
        )
    write_to_log_file("db.log", message)
    write_to_log_file("message.log", message)


"""
If a migration fails, the error is logged in 
messages and error log files

Arguments: 
    - release_no: str 
        Release number
    - migration_file_name: str 
        Name of the SQL file which was migrated
    - error: str 
        Desciption of the error that occured

Returns:
-
"""


def log_error(release_no, migration_file_name, error, rollback):
    print("Failed.")
    if rollback:
        message = f"Release-{release_no} Failed to roll back migration {migration_file_name}. Check error.log for details."
        error_message = (
            f"Error while rolling back migration: {error}".replace("\n", ""),
        )
    else:
        message = f"Release-{release_no} Failed to apply migration {migration_file_name}. Check error.log for details."
        error_message = (f"Error while applying migration: {error}".replace("\n", ""),)

    print(message)

    write_to_log_file("message.log", message)
    write_to_log_file("error.log", error_message)


"""
For creating a connection with the database server
Arguments: 
-

Returns: 
    - connection
"""


def create_server_connection():
    connection = None
    try:
        connection = psycopg2.connect(
            host=os.getenv("HOST_NAME"),
            database=os.getenv("DATABASE_NAME"),
            user=os.getenv("USER_NAME"),
            password=os.getenv("USER_PASSWORD"),
        )

    except (Exception, psycopg2.DatabaseError) as error:
        write_to_log_file(
            "db.log", "Error while connecting to database. Check error.log for details"
        )
        write_to_log_file("error.log", f"Error while connecting to database: {error}")
        print(f"Error while connecting to database. Check error.log for details \n")
        exit()

    return connection
