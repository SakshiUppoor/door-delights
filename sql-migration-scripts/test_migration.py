"""
This script allows you to test 
if your individual migration files
are correct
"""

import psycopg2
from utils import (
    create_server_connection,
)

filename = input("Enter migration name: ").replace(".sql", "")

# Credentials fetched from environment variables
connection = create_server_connection()
cursor = connection.cursor()

with open(f"sql_queries/migrations/{filename}.sql", "r") as file:
    try:
        query = file.read()
        print(f"Applying migration {filename}.......", end="")
        cursor.execute(query)
        print("Applied succefully.")
    except (Exception, psycopg2.Error) as error:
        print(error)

with open(f"sql_queries/rollback/{filename}.sql", "r") as file:
    try:
        query = file.read()
        print(f"Rolling back migration {filename}.......", end="")
        cursor.execute(query)
        print("Rolled back succefully.")
    except (Exception, psycopg2.Error) as error:
        print(error)
