BEGIN TRANSACTION;
    DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'meal_types') THEN
            CREATE TYPE meal_types AS ENUM('single', 'combo');
        END IF;
    END$$;
	CREATE TABLE IF NOT EXISTS menu_item (
        id SERIAL,
        name VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
    	photo varchar(255) NOT NULL,
        meal_type meal_types NOT NULL,
        PRIMARY KEY (id)
    );
    CREATE TABLE IF NOT EXISTS price (
        id SERIAL,
		rate REAL NOT NULL,
        menu_item_id INT NOT NULL,
		entry_time TIMESTAMP DEFAULT NOW(),
		entry_user VARCHAR(255) NOT NULL,
		update_time TIMESTAMP DEFAULT NOW(),
		update_user VARCHAR(255) NOT NULL,
        FOREIGN KEY (menu_item_id) REFERENCES menu_item(id)
        ON DELETE CASCADE,
        PRIMARY KEY (id)
    );
    CREATE TABLE IF NOT EXISTS topping (
        id SERIAL,
        name VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
    	photo varchar(255) NOT NULL,
        PRIMARY KEY (id)
    );
    CREATE TABLE IF NOT EXISTS item_topping_pair (
        item_id INT NOT NULL,
        topping_id INT NOT NULL,
        FOREIGN KEY (item_id) REFERENCES menu_item(id)
        ON DELETE CASCADE,
        FOREIGN KEY (topping_id) REFERENCES topping(id)
        ON DELETE CASCADE,
        PRIMARY KEY (item_id, topping_id)
    );
COMMIT;

