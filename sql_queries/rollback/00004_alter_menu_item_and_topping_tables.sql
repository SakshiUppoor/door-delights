BEGIN TRANSACTION;
    ALTER TABLE menu_item
    DROP CONSTRAINT unique_menu_item_name;
    ALTER TABLE topping
    DROP CONSTRAINT unique_topping_name;
COMMIT;
