create database restaurant;

create table position(
  id int primary key,
  name text not null
);

create table employee(
  id int primary key,
  first_name text not null,
  position_id int references position(id),
  photo_url text
);

create table ingredient(
  id int not null primary key,
  name text not null
);

create table dish(
  id int not null primary key,
  name text,
  weight int,
  price real
);

create table list_of_ingredients(
  dish_id int references dish(id),
  ingredient_id int references ingredient(id)
);

create table storage(
  id int not null primary key,
  ingredient_id int references ingredient(id),
  amount int not null
);

create table orders(
  id int primary key,
  employee_id int references employee(id),
  dish_to_order int,
  table_number int not null,
  date date not null
);

create table dish_to_order(
  dish_id int references dish(id),
  order_id int references orders(id)
);

create table cooked_dish(
  id int primary key,
  dish_id int references dish(id),
  employee_id int references employee(id),
  order_id int references orders(id),
  date date not null
);

