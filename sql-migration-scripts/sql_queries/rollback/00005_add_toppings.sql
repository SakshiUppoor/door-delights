BEGIN TRANSACTION;
    DELETE FROM topping 
        WHERE name='Mushroom' 
        OR name='Pepperoni' 
        OR name='Marinara Sauce' 
        OR name='Basil' 
        OR name='Mozarella Cheese';
COMMIT;