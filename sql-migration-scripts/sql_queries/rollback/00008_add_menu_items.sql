BEGIN TRANSACTION;
    DELETE FROM menu_item WHERE name='Pizza Heaven';
    DELETE FROM menu_item WHERE name='Bread Sticks';
COMMIT;

