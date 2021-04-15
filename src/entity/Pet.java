package entity;

public class Pet {
	private int pet_id;
	private int pet_type_id;
	private int pet_breed_id;
	private String pet_name;
	private String pet_type_name;
	private String pet_breed_name;
	
	public Pet(
			int pet_id_input,
			int pet_type_id_input, 
			int pet_breed_id_input, 
			String pet_name_input, 
			String pet_type_name_input, 
			String pet_breed_name_input
			) {
		// Primary Pet Information Input
		this.setPetID(pet_id_input);
		this.setPetName(pet_name_input);
		// Pet Type (Dog, Cat, Bird, etc.)
		this.setPetTypeID(pet_type_id_input);
		this.setPetTypeName(pet_type_name_input);
		// Set Pet Breed ID & Type
		this.setPetBreedID(pet_breed_id_input);
		this.setPetBreedName(pet_breed_name_input);
		
	}

	private void setPetBreedName(String pet_breed_name_input) {
		// TODO Auto-generated method stub
		
	}

	private void setPetBreedID(int pet_breed_id_input) {
		// TODO Auto-generated method stub
		
	}

	private void setPetTypeName(String pet_type_name_input) {
		// TODO Auto-generated method stub
		
	}

	private void setPetTypeID(int pet_type_id_input) {
		// TODO Auto-generated method stub
		
	}

	private void setPetName(String pet_name_input) {
		// TODO Auto-generated method stub
		
	}

	private void setPetID(int pet_id_input) {
		// TODO Auto-generated method stub
		
	}
}
