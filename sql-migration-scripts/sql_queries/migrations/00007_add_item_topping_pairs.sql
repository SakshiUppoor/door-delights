INSERT INTO item_topping_pair
VALUES
(
    (SELECT id FROM menu_item WHERE name='Margherita Pizza'), 
    (SELECT id FROM topping WHERE name='Marinara Sauce')
),
(
    (SELECT id FROM menu_item WHERE name='Margherita Pizza'), 
    (SELECT id FROM topping WHERE name='Basil')
),
(
    (SELECT id FROM menu_item WHERE name='Margherita Pizza'), 
    (SELECT id FROM topping WHERE name='Mozarella Cheese')
),
(
    (SELECT id FROM menu_item WHERE name='Mushroom Pizza'), 
    (SELECT id FROM topping WHERE name='Mushroom')
),
(
    (SELECT id FROM menu_item WHERE name='Mushroom Pizza'), 
    (SELECT id FROM topping WHERE name='Basil')
),
(
    (SELECT id FROM menu_item WHERE name='Mushroom Pizza'), 
    (SELECT id FROM topping WHERE name='Mozarella Cheese')
);

