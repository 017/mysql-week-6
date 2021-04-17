DELIMITER $$
DROP PROCEDURE add_pet;
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