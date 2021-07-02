BEGIN TRANSACTION;
    INSERT INTO topping (name, description, photo)
    VALUES
        ('Mushroom', 'Adds a savory earthiness to the pizza', 'https://lotsa.com/wp-content/uploads/2017/10/mushrooms.png'),
        ('Pepperoni', 'An American extrapolation of Calabrese salami tradition', 'https://5.imimg.com/data5/SELLER/Default/2021/5/YX/KV/VS/1432671/spanish-pepperoni-sliced-repacked--500x500.png'),
        ('Marinara Sauce', 'A traditional Italian tomato sauce that combines crushed tomatoes, garlic and a variety of other ingredients', 'https://simpleveganblog.com/wp-content/uploads/2018/07/Homemade-marinara-sauce.jpg'),
        ('Basil', 'An herb from the mint family', 'https://image.freepik.com/free-photo/close-up-fresh-green-basil-herb-leaves-isolated-white-background-sweet-genovese-bas_33736-569.jpg'),
        ('Mozarella Cheese', 'A traditionally southern Italian cheese', 'https://bestaabtrading.com/wp-content/uploads/2020/02/mozarella.jpg');
COMMIT;