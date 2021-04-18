create database if not exists pet_db;

use pet_db;

drop table if exists pets;
drop table if exists pet_types;
drop table if exists breeds;
drop table if exists costumes; 

);

create table pet_types (
    pet_type_id int(11) not null auto_increment,
    pet_type_name varchar(30),
    primary key (pet_type_id)
);

create table breeds (
    pet_breed_id int(11) not null auto_increment,
    pet_breed_name varchar(30),
    pet_min_lifespan int(11),
    pet_max_lifespan int(11),
    primary key (pet_breed_id)
);

create table pets (
    pet_id int(11) not null auto_increment,
    pet_type_id int(11),
    pet_breed_id int(11),
    pet_name varchar(30),
    pet_gender char(1),
    pet_birthday date,
    primary key (pet_id)
);

CREATE VIEW `pet_tables_combined` AS SELECT p.pet_id, p.pet_name, p.pet_gender, p.pet_birthday FROM `pets` AS p
INNER JOIN `breeds` AS b ON p.pet_breed_id = b.pet_breed_id INNER JOIN `pet_types` AS pt ON p.pet_type_id = pt.pet_type_id;
Insert Into pets values (pet_id, 1, 3, 'Bean', 'M', '2020-03-27');

Insert Into pets values (pet_id, 2, 5, 'Bella', 'F', '2019-01-14');

Insert Into pets values (pet_id, 3, 7, 'Jasper', 'M', '2021-04-15');

Insert Into pets values (pet_id, 2, 4, 'Oliver', 'M', '2007-06-05');

Insert Into pets values (pet_id, 1, 2, 'Sadie', 'F', '2001-08-22');

Insert Into pets values (pet_id, 3, 8, 'Max', 'M', '2019-01-14');



Insert Into pet_types values (1, 'Dog');

Insert Into pet_types values (2, 'Cat');

Insert Into pet_types values (3, 'Bird');



Insert Into breeds values (1, 'GOLDEN RETRIVER', 0, 12);

Insert Into breeds values (2, 'GERMAN SHEPHERD', 0, 13);

Insert Into breeds values (3, 'SIBERIAN HUSKY', 0, 15);

Insert Into breeds values (4, 'SIAMESE', 0, 12);

Insert Into breeds values (5, 'NORWEGIAN FOREST CAT', 0, 14);

Insert Into breeds values (6, 'TURKISH ANGORA', 0, 18);

Insert Into breeds values (7, 'PARROT', 40, 80);

Insert Into breeds values (8, 'COCKATIEL', 10, 14);


DELIMITER $$
-- DROP PROCEDURE add_pet;
CREATE PROCEDURE add_pet(
    IN pet_id_input INT,
    IN pet_type_id_input INT,
    IN pet_breed_id_input INT,
    IN pet_name_input VARCHAR(30),
    IN pet_gender_input CHAR(1), 
    IN pet_birthday_input DATE)
BEGIN
INSERT INTO pets (pet_id, pet_type_id, pet_breed_id, pet_name, pet_gender, pet_birthday)
VALUES (pet_id_input, pet_type_id_input, pet_breed_id_input, pet_name_input, pet_gender_input, pet_birthday_input);
END$$
DELIMITER ;
