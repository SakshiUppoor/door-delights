BEGIN TRANSACTION;
	DROP TABLE item_topping_pair;
	DROP TABLE topping;
	DROP TABLE price;
	DROP TABLE menu_item;
	DROP TYPE meal_types;
COMMIT;

