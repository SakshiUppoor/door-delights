BEGIN TRANSACTION;
    CREATE TABLE IF NOT EXISTS delivery (
        id SERIAL,
        delivery_person_id INT NOT NULL,
        address_id INT NOT NULL,
        total_amount REAL NOT NULL,
        delivery_instructions VARCHAR(255),
    	is_ordered BOOLEAN,
    	is_delivered BOOLEAN,
		entry_time TIMESTAMP DEFAULT NOW(),
        FOREIGN KEY (delivery_person_id) REFERENCES user_account(id)
        ON DELETE CASCADE,
        FOREIGN KEY (address_id) REFERENCES address(id)
        ON DELETE CASCADE,
        PRIMARY KEY (id)
    );
    CREATE TABLE IF NOT EXISTS order_item (
        id SERIAL,
        delivery_id INT NOT NULL,
        menu_item_id INT NOT NULL,
		quantity INT NOT NULL,
        customer_id INT NOT NULL,
        FOREIGN KEY (delivery_id) REFERENCES delivery(id)
        ON DELETE CASCADE,
        FOREIGN KEY (menu_item_id) REFERENCES menu_item(id)
        ON DELETE CASCADE,
        FOREIGN KEY (customer_id) REFERENCES user_account(id)
        ON DELETE CASCADE,
        PRIMARY KEY (id)
    );
COMMIT;

