create database if not exists pet_db;

use pet_db;

drop table if exists pets;
drop table if exists pet_breeds;
drop table if exists pet_types;
drop table if exists costumes; 
SET FOREIGN_KEY_CHECKS=0;

create table pet_types (
    pet_type_id int(4) not null auto_increment,
    pet_type_name varchar(30),
    primary key (pet_type_id)
);

create table pet_breeds (
    pet_breed_id int(4) not null auto_increment,
    pet_breed_name varchar(30),
    pet_min_lifespan int(4),
    pet_max_lifespan int(4),
    primary key (pet_breed_id),
    foreign key (pet_breed_id) references pet_types(pet_type_id)
);

create table pets (
    pet_id int(4) not null auto_increment,
    pet_type_id int(4),
    pet_breed_id int(4),
    pet_name varchar(30),
    pet_gender char(1),
    pet_birthday date,
    primary key (pet_id),
    foreign key (pet_type_id) references pet_types (pet_type_id),
    foreign key (pet_breed_id) references pet_breeds(pet_breed_id)

);


DELIMITER $$
CREATE PROCEDURE add_pet(
    IN pet_type_id_input INT,
    IN pet_breed_id_input INT,
    IN pet_name_input VARCHAR(30),
    IN pet_gender_input CHAR(1), 
    IN pet_birthday_input DATE)
BEGIN
INSERT INTO pets (pet_type_id, pet_breed_id, pet_name, pet_gender, pet_birthday)
VALUES (pet_type_id_input, pet_breed_id_input, pet_name_input, pet_gender_input, pet_birthday_input);
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE add_pet_type(
    IN pet_type_name_input VARCHAR(30))
BEGIN
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO pet_types (pet_type_id, pet_type_name)
VALUES (pet_type_id, pet_type_name_input);
SET FOREIGN_KEY_CHECKS=1;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE add_pet_breed(
    IN pet_breed_name_input VARCHAR(30))
BEGIN
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO pet_breeds (pet_breed_id, pet_breed_name)
VALUES (pet_breed_id, pet_breed_name_input);
SET FOREIGN_KEY_CHECKS=1;
END$$
DELIMITER ;

Insert Into pet_types values (1, 'Dog');

Insert Into pet_types values (2, 'Cat');

Insert Into pet_types values (3, 'Bird');

Insert Into pet_breeds values (1, 'GOLDEN RETRIVER', 0, 12);

Insert Into pet_breeds values (2, 'GERMAN SHEPHERD', 0, 13);

Insert Into pet_breeds values (3, 'SIBERIAN HUSKY', 0, 15);

Insert Into pet_breeds values (4, 'SIAMESE', 0, 12);

Insert Into pet_breeds values (5, 'NORWEGIAN FOREST CAT', 0, 14);

Insert Into pet_breeds values (6, 'TURKISH ANGORA', 0, 18);

Insert Into pet_breeds values (7, 'PARROT', 40, 80);

Insert Into pet_breeds values (8, 'COCKATIEL', 10, 14);

Insert Into pets values (pet_id, 1, 3, 'Bean', 'M', '2020-03-27');

Insert Into pets values (pet_id, 2, 5, 'Bella', 'F', '2019-01-14');

Insert Into pets values (pet_id, 3, 7, 'Jasper', 'M', '2021-04-15');

Insert Into pets values (pet_id, 2, 4, 'Oliver', 'M', '2007-06-05');

Insert Into pets values (pet_id, 1, 2, 'Sadie', 'F', '2001-08-22');

Insert Into pets values (pet_id, 3, 8, 'Max', 'M', '2019-01-14');


CREATE VIEW `pet_tables_combined` AS SELECT p.pet_id, p.pet_name, p.pet_gender, p.pet_birthday FROM `pets` AS p
INNER JOIN `pet_breeds` AS b ON p.pet_breed_id = b.pet_breed_id INNER JOIN `pet_types` AS pt ON p.pet_type_id = pt.pet_type_id;