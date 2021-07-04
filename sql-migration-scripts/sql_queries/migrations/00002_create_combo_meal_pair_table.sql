BEGIN TRANSACTION;
    CREATE TABLE IF NOT EXISTS combo_meal_pair (
        combo_meal_id INT NOT NULL,
        single_item_id INT NOT NULL,
        FOREIGN KEY (combo_meal_id) REFERENCES menu_item(id)
        ON DELETE CASCADE,
        FOREIGN KEY (single_item_id) REFERENCES menu_item(id)
        ON DELETE CASCADE,
        PRIMARY KEY (combo_meal_id, single_item_id)
    );
COMMIT;

