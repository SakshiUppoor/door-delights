DELETE FROM item_topping_pair WHERE
item_id=(SELECT id FROM menu_item WHERE name='Margherita Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Marinara Sauce')
OR item_id=(SELECT id FROM menu_item WHERE name='Margherita Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Basil')
OR item_id=(SELECT id FROM menu_item WHERE name='Margherita Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Mozarella Cheese')
OR item_id=(SELECT id FROM menu_item WHERE name='Mushroom Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Mushroom')
OR item_id=(SELECT id FROM menu_item WHERE name='Mushroom Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Basil')
OR item_id=(SELECT id FROM menu_item WHERE name='Mushroom Pizza') AND topping_id=(SELECT id FROM topping WHERE name='Mozarella Cheese');

