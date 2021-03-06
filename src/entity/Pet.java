package entity;
import entity.PetBreed;
import java.sql.SQLException;

public class Pet {
	private int pet_id;
	private int pet_type_id;
	private int pet_breed_id;
	//private int pet_costume_id;
	private String pet_name;
	private String pet_type_name;
	private String pet_breed_name;
	private String pet_gender;
	private String pet_birthday;
	private PetBreed PetBreed = new PetBreed(pet_breed_id, pet_birthday);
	
	public Pet(
			int pet_id_input,
			int pet_type_id_input, 
			int pet_breed_id_input, 
			String pet_name_input,
			String pet_gender_input,
			String pet_birthday_input
			) {
		// Primary Pet Information Input
		this.setPetID(pet_id_input);
		this.setPetName(pet_name_input);
		this.setPetGender(pet_gender_input);
		this.setPetBirthday(pet_birthday_input);
		// Pet Type (Dog, Cat, Bird, etc.)
		this.setPetTypeID(pet_type_id_input);
		// Set Pet Breed ID & Type
		this.setPetBreedID(pet_breed_id_input);
		//this.setPetBreedName(pet_breed_name_input);
	}

	private void setPetGender(String pet_gender_input) {
		this.pet_gender = pet_gender_input;
	}

	private void setPetBirthday(String pet_birthday_input) {
		this.pet_gender = pet_birthday_input;
	}

	private void setPetBreedName(String pet_breed_name_input) {
		this.pet_breed_name = pet_breed_name_input;
	}

	private void setPetBreedID(int pet_breed_id_input) {
		this.pet_breed_id = pet_breed_id_input;
	}

	private void setPetTypeName(String pet_type_name_input) {
		this.pet_type_name = pet_type_name_input;
	}

	private void setPetTypeID(int pet_type_id_input) {
		this.pet_type_id = pet_type_id_input;
	}

	private void setPetName(String pet_name_input) {
		this.pet_name = pet_name_input;
	}

	//private void setPetCostume(int pet_costume_id_input) {
	//	this.pet_costume_id = pet_costume_id_input;
	//}

	private void setPetID(int pet_id_input) {
		this.pet_id = pet_id_input;
	}
	
	public int getPetID() {
		return this.pet_id;
	}
	
	public String getPetName() {
		return this.pet_name;
	}
	
	public String getPetTypeName() {
		return this.pet_type_name;
	}
	
	public int getPetTypeID() {
		return this.pet_type_id;
	}
	
	public String getPetBreedName() throws SQLException {
		int id = this.pet_breed_id;
		this.pet_breed_name = entity.PetBreed.getPetBreedName(id);
		return this.pet_breed_name;
	}
	
	public String getPetGender() {
		return this.pet_gender;
	}
	
	public String getPetBirthday() {
		return this.pet_birthday;
	}
	
	public int getPetBreedID() {
		return this.pet_breed_id;
	}
}
