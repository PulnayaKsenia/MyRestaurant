
insert into position values (1, 'ADMINISTRATOR');
insert into position values (2, 'WAITER');
insert into position values (3, 'COOK');

insert into employee (id, first_name, position_id)
values (1, 'Kate', 1);
insert into employee (id, first_name, position_id)
values (2, 'Rita', 2);
insert into employee (id, first_name, position_id)
values (3, 'Stasy', 2);
insert into employee (id, first_name, position_id)
values (4, 'Egor', 3);
insert into employee (id, first_name, position_id)
values (5, 'Kirill', 3);

insert into ingredient (id, name) values (1, 'dough');
insert into ingredient (id, name) values (2, 'tomato');
insert into ingredient (id, name) values (3, 'beef');
insert into ingredient (id, name) values (4, 'pork');
insert into ingredient (id, name) values (5, 'cheese');
insert into ingredient (id, name) values (6, 'oregano');

insert into dish (id, name, weight, price) values (1, 'margarita', 400, 55);
insert into dish (id, name, weight, price) values (2, 'pepperoni', 350, 70);

insert into list_of_ingredients (dish_id, ingredient_id) values (1, 1);
insert into list_of_ingredients (dish_id, ingredient_id) values (1, 2);
insert into list_of_ingredients (dish_id, ingredient_id) values (1, 5);
insert into list_of_ingredients (dish_id, ingredient_id) values (1, 6);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 1);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 2);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 3);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 4);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 5);
insert into list_of_ingredients (dish_id, ingredient_id) values (2, 6);

insert into storage (id, ingredient_id, amount) values (1, 1, 5850);
insert into storage (id, ingredient_id, amount) values (2, 2, 2080);
insert into storage (id, ingredient_id, amount) values (3, 3, 1560);
insert into storage (id, ingredient_id, amount) values (4, 4, 990);
insert into storage (id, ingredient_id, amount) values (5, 5, 2845);
insert into storage (id, ingredient_id, amount) values (6, 6, 375);