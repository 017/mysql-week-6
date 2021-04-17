drop database if exists pet_db;
create database if not exists pet_db;

use pet_db;

drop table if exists pets;
drop table if exists pet_types;
drop table if exists breeds;
drop table if exists costumes;

create table costumes (
    costume_id int(11) not null auto_increment,
    costume_name varchar(30),
    costume_desc varchar(120),
    primary key (costume_id)
);

create table pets (
    pet_id int(11) not null auto_increment,
    pet_type_id int(11),
    pet_breed_id int(11),
    pet_costume_id int(11),
    pet_name varchar(30),
    pet_gender char(1),
    pet_birthday date,
    primary key (pet_id),
    foreign key (pet_costume_id) references costumes(costume_id)
);

create table pet_types (
    pet_type_id int(11) not null auto_increment,
    pet_breed_id int(11),
    pet_type_name varchar(30),
    primary key (pet_type_id)
);

create table breeds (
    pet_breed_id int(11) not null auto_increment,
    pet_type_id int(11),
    pet_breed_name varchar(30),
    pet_min_lifespan int(11),
    pet_max_lifespan int(11),
    primary key (pet_breed_id),
    foreign key (pet_type_id) references pet_types(pet_type_id)
);

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Bean', 'M', '2020-03-27');

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Bella', 'F', '2019-01-14');

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Jasper', 'M', '2021-04-15');

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Oliver', 'M', '2007-06-05');

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Sadie', 'F', '2001-08-22');

Insert Into pets values (pet_id, NULL, NULL, NULL, 'Max', 'M', '2019-01-14');



Insert Into pet_types values (NULL, NULL, 'Dog');

Insert Into pet_types values (NULL, NULL, 'Cat');

Insert Into pet_types values (NULL, NULL, 'Bird');



Insert Into breeds values (NULL, NULL, 'GOLDEN RETRIVER', 0, 12);

Insert Into breeds values (NULL, NULL, 'GERMAN SHEPHERD', 0, 13);

Insert Into breeds values (NULL, NULL, 'SIBERIAN HUSKY', 0, 15);

Insert Into breeds values (NULL, NULL, 'SIAMESE', 0, 12);

Insert Into breeds values (NULL, NULL, 'NORWEGIAN FOREST CAT', 0, 14);

Insert Into breeds values (NULL, NULL, 'TURKISH ANGORA', 0, 18);

Insert Into breeds values (NULL, NULL, 'PARROT', 40, 80);

Insert Into breeds values (NULL, NULL, 'COCKATIEL', 10, 14);
