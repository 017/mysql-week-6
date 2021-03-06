package entity;
import dao.PetDao;
import java.sql.SQLException;

public class PetBreed {
	private int pet_breed_id;
	private static String pet_breed_name;
	private static PetDao petDao = new PetDao();
	private int minLifeSpan = 12;
	private int maxLifeSpan = 16;
	
	public PetBreed(int pet_breed_id_input, String pet_breed_name_input) {
		this.setPetBreedID(pet_breed_id_input);
		this.SetPetBreedName(pet_breed_name_input);
	}

	private void SetPetBreedName(String pb_name) {
		this.pet_breed_name = pb_name;
	}

	private void setPetBreedID(int pb_id) {
		this.pet_breed_id = pb_id;
	}

	public static String getPetBreedName(int id) throws SQLException {
		pet_breed_name = petDao.getBreedNameByID(id);
		return pet_breed_name;
	}

	private int getPetBreedID() {
		return this.pet_breed_id;
	}
	
	private void setPetBreedMinLife(int value) {
		this.minLifeSpan = value;
	}
	
	private int getPetBreedMinLife() {
		return this.minLifeSpan;
	}
	
	private void setPetBreedMaxLife(int value) {
		this.maxLifeSpan = value;
	}
	
	private int getPetBreedMaxLife() {
		return this.maxLifeSpan;
	}
}
