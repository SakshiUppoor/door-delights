BEGIN TRANSACTION;
    ALTER TABLE menu_item
    ADD CONSTRAINT unique_menu_item_name UNIQUE (name);
    ALTER TABLE topping
    ADD CONSTRAINT unique_topping_name UNIQUE (name);
COMMIT;
