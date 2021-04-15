create database if not exists pet_db;

use pet_db;

drop table if exists pets;

create table pets (
    pet_id int(11) not null auto_increment,
    pet_type_id int(11) not null auto_increment,
    pet_breed_id int(11) not null auto_increment,
    pet_name varchar(30) not null,
    primary key (pet_id)
);

create table pet_types (
    pet_type_id int(11) not null auto_increment,
    pet_breed_id int(11) not null auto_increment,
    pet_type_name varchar(30) not null,
    primary key (pet_type_id),
    foreign key (pet_type_id) references pets(pet_type_id),
    foreign key (pet_breed_id) references breeds(pet_breed_id)
);

create table breeds (
    pet_breed_id int(11) not null auto_increment,
    pet_type_id int(11) not null auto_increment,
    pet_breed_name varchar(30),
    pet_min_lifespan int(11),
    pet_max_lifespan int(11),
    primary key (pet_breed_id),
    foreign key (pet_breed_id) references pet_types(pet_breed_id)
);

create table traits (
    pet_trait_id int(11) not null auto_increment,
    pet_trait_type int(11) not null auto_increment,
    pet_trait_name varchar(30),
    primary key (pet_trait_id),
    foreign key (pet_trait_type) references pet_types(pet_type_id)
)