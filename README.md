# Door Delights

An online food ordering app. The purpose of this project was to study and implement DBMS in a production environment. 

Sections:
- [DB schema design](#db-schema-design)
- [Script for migrating data between 2 databases](#script-for-migrating-data-between-databases)
- [Integrating the DB with a Java backend](#integrating-the-db-with-a-java-backend)

## DB schema design
<p align="center"><img src="https://github.com/SakshiUppoor/door-delights/blob/master/FoodOrderingSchemaDesign.png" width="90%"></p>

## Script for migrating data between databases
<details>
<summary> Need for migration scripts </summary>

#### The Problem
There are multiple environments with their own version of the database. One in the production server, one in QA, and development servers at each workstation. 

So, when there is any changes in the database such as a new table is adding to the database or a new column is added to an existing table or new rows are added to an existing table that contains static data, then these changes need to migrated and applied to all the other database servers too so that all the databases are in a consistent manner and it could be migrated to the production server.

</details>

<details>
<summary> Solution & workflow </summary>

#### Solution
A python script to migrate the changes to other databases and a json file to maintain history of the migrations applied in production.

### Workflow 
The workflow implemented in this project for migrating changes from one database to another is as follows:

1. Run `create_migration_file.py` which will make JSON file for the change containing:
  - SQL queries 
  - corresponding SQL query to rollback the aforementioned queries
  - Comments or reason for the change
  - And also add this migration to the latest scheduled release or create a new release
2. Push these files to Github
3. Create a new release which includes all the unapplied migration files
4. Pull the files on the server where the changes are to be migrated
5. Run `migrate_to_db.py` which will execute all the SQL queries included the given release number in the database server
- This script can also rollback the queries from the given release number

</details>

### Running the scripts 
To run this project, you will need to add the following environment variables to your .env file
`HOST_NAME`, 
`DATABASE_NAME`, 
`USER_NAME`, 
`USER_PASSWORD`, 
`DATABASE_SERVER` (prod or qa or dev)

Scripts:
1. `create_migration_file.py`
Create a JSON file for the change containing the SQL queries, corresponding SQL query to rollback the aforementioned queries, any comments or reason for the change
Add this migration to the next scheduled release or create a new release (releases.json)

* Sample migration file:
    ```
    {
        "Name": "Add toppings",
        "Reason for the migration": "Updating menu",
        "SQL script": "INSERT INTO toppings (name) VALUES ('Mushroom');",
        "Rollback script": "DELETE FROM topping\nWHERE name='Mushroom';\n\n",
        "Comments": "-"
    }
    ```

2. `migrate_to_db.py`
Running this script migrates all the migrations linked in the given release no.
In case the server is production or QA, the releases.json is updated accordingly.
This script can also be used to rollback migrations in a particular release.

Usage:
```
migrate_to_prod.py [-h] [--rollback] release_no
```

### Files

`releases.json`
Maintains history of all the past and future releases and the migrations included in them
Sample release.json:
```
{
    "releases": [
        {
            "number": "0.0.1",
            "dateReleasedOnProd": null,
            "migrations": [
                "00002_create_combo_meal_pair_table.sql",
                "00003_create_order_tables.sql",
                "00004_alter_menu_item_and_topping_tables.sql"
            ]
        },
        {
            "number": "0.0.0",
            "dateReleasedOnProd": "2021-07-04T12:57:28.324466",
            "migrations": [
                "00000_create_user_and_address_tables.sql",
                "00001_create_menu_items_tables.sql"
            ]
        }
    ]
}

```

`local_migrations.json`
Maintains track of all the applied migrations at the local server (For development)

`message.log` Applied or rollback migrations and brief description about any errors occurred

`error.log` Detailed description of all the erors

`db.log` Log of all the successfully applied migrations and rollbacks


## Integrating the DB with a Java backend
**Backend Framework:** Spring Boot

#### File structure:
```
├───controller
├───dao
├───entity
├───mapper
└───service
```
