package entity;

public class PetBreed {
	private int pet_breed_id;
	private String pet_breed_name;
	
	public PetBreed(int pet_breed_id_input, String pet_breed_name_input) {
		this.setPetTypeID(pet_breed_id_input);
		this.SetPetTypeName(pet_breed_name_input);
	}

	private void SetPetTypeName(String pb_name) {
		this.pet_breed_name = pb_name;
	}

	private void setPetTypeID(int pb_id) {
		this.pet_breed_id = pb_id;
	}

	private String getPetTypeName() {
		return this.pet_breed_name;
	}

	private int getPetTypeID() {
		return this.pet_breed_id;
	}
}
