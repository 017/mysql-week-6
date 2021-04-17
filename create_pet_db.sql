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