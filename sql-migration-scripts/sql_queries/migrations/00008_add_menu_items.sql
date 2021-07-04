BEGIN TRANSACTION;
    INSERT INTO menu_item (name, description, photo, meal_type)
    VALUES ('Bread Sticks', 'Breadsticks are light, buttery, and oh so delicious!', 'https://media.olivegarden.com/en_us/images/product/Breadsticks-dvp-1180x730.jpg', 'single');
    INSERT INTO item_topping_pair
    VALUES (
        (SELECT id FROM menu_item WHERE name='Bread Sticks'),
        (SELECT id from topping WHERE name='Marinara Sauce')
    );
    INSERT INTO menu_item (name, description, photo, meal_type)
    VALUES ('Pizza Heaven', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Aliquam etiam erat velit scelerisque in dictum. A diam sollicitudin tempor id eu nisl nunc mi ipsum.', 
    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCnAm5soYsrXca8oQ5xo8i1iz3wFkYF8HQ9i5gRElhcCa8IBC0UGg-I2tBhjNtY_CUdm4&usqp=CAU', 'combo');
    INSERT INTO combo_meal_pair VALUES
    (
        (SELECT id FROM menu_item WHERE name='Pizza Heaven'),
        (SELECT id FROM menu_item WHERE name='Margherita Pizza')
    ),
    (
        (SELECT id FROM menu_item WHERE name='Pizza Heaven'),
        (SELECT id FROM menu_item WHERE name='Mushroom Pizza')
    );
COMMIT;

