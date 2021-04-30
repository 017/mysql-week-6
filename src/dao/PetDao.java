package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Pet;
import entity.PetBreed;
import entity.PetType;

public class PetDao {
	private Connection connection;
	private Scanner scanner = new Scanner(System.in);
	private final String DELETE_PET_BY_ID_QUERY = "DELETE FROM pets WHERE pet_id = ?";
	

		
    private final String INEXISTENT_COLUMN_PATTERN = "?";
    private final String DUPLICATE_DATA_PATTERN = "?";
	
	public PetDao() {
		connection = DBConnection.getConnection();
	}
	
	public void deletePetByID(int pet_target_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PET_BY_ID_QUERY);
		ps.setInt(1, pet_target_id);
		ps.executeUpdate();
	}
	
	// 5 Parameters + Target ID
	private final String EDIT_PET_BY_ID_QUERY = "UPDATE pets"
		+ "SET pet_type_id = ?, "
		+ "pet_breed_id = ? "
		+ "pet_name = ? "
		+ "pet_gender = ? "
		+ "pet_birthday = ? "
		+ "WHERE pet_id = ?";
	
	public void editPetByID(int pet_target_id) throws SQLException {
		System.out.println("Pet Current Data: ");
		Pet current = this.getPetByID(pet_target_id);
		System.out.println("Name: " + current.getPetName());
		System.out.println("Gender: " + current.getPetGender());
		System.out.println("Birthday: " + current.getPetBirthday());
		System.out.println("Type: " + current.getPetTypeName());
		System.out.println("Breed: " + current.getPetBreedName());
		System.out.println("END Current Data");
		PreparedStatement ps = connection.prepareStatement(EDIT_PET_BY_ID_QUERY);
		System.out.print("Enter New Pet Name: ");
		String new_pet_name = scanner.nextLine();
		System.out.print("Enter New Pet Gender: ");
		String new_pet_gender = scanner.nextLine();
		System.out.print("Enter New Pet Birthday: ");
		String new_pet_birthday = scanner.nextLine();
		System.out.print("Enter New Pet Type: (Dog, Cat, Bird, Fish, etc)");
		String new_pet_type = scanner.nextLine();
		int new_pet_type_id = convertTypeNameToID(new_pet_type);
		System.out.print("Enter New Pet Breed: (Bulldog, Shiba Inu, Pug, Bombay, etc.)");
		String new_pet_breed = scanner.nextLine();
		int new_pet_breed_id = convertBreedNameToID(new_pet_breed);
		ps.setInt(6, pet_target_id);
		ps.setInt(1, new_pet_type_id);
		ps.setInt(2, new_pet_breed_id);
		ps.setString(3, new_pet_name);
		ps.setString(4, new_pet_gender);
		ps.setString(5, new_pet_birthday);
		ps.executeUpdate();
	}

	private final String GET_PET_BREED_BY_NAME = "SELECT pet_breed_id FROM pet_breeds WHERE pet_breed_name = ?";
	public int convertBreedNameToID(String input_breed_name) throws SQLException {
		int result_id = 0;
		
		PreparedStatement ps = connection.prepareStatement(GET_PET_BREED_BY_NAME);
		ps.setString(1, input_breed_name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result_id = rs.getInt(1);
		}
		
		if (result_id > 0) {
			return result_id;
		} else {
			return this.breedAdd(input_breed_name);
		}
	}

	private final String GET_PET_TYPE_BY_NAME = "SELECT pet_type_id FROM pet_types WHERE pet_type_name = ?";
	public int convertTypeNameToID(String inputted_name) throws SQLException {
		// Find out from the Database what the type ID is based on the name if it exists already
		int result_id = 0;
		
		PreparedStatement ps = connection.prepareStatement(GET_PET_TYPE_BY_NAME);
		ps.setString(1, inputted_name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result_id = rs.getInt(1);
		}
		
		if (result_id > 0) {
			return result_id;
		} else {
			// If it doesn't find anything, default the ID to the type table length +1, inserting it as the next ID.
			return this.typeAdd(inputted_name);
		}
	}

	private void checkTypeID(int result_id) throws SQLException {
		// TODO write SQL to check result ID in DB, if ID 
		
	}

	// COUNT(*) query the types table and get a number from that, +1 to add the next numeric ID.
	private final String GET_TYPE_LENGTH = "SELECT COUNT(*) FROM pet_types";
	private int typeAdd(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_LENGTH);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int id = rs.getInt(1) + 1;
			System.out.print("[" + name + "] Creating Pet Type.");
			this.createPetType(name);
			int res = rs.getInt(1);
			return res;
		} else return 0;
	}

	private final String GET_BREED_LENGTH = "SELECT COUNT(*) FROM pet_breeds";
	private int breedAdd(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_BREED_LENGTH);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int id = rs.getInt(1) + 1;
			System.out.print("[" + name + "] Creating Pet Breed.");
			this.createPetBreed(name);
			int res = rs.getInt(1);
			return res;
		} else return 0;
	}
	
	private final String GET_PET_BY_ID_QUERY = "SELECT * FROM pets WHERE pet_id = ?";
	public Pet getPetByID(int pet_target_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PET_BY_ID_QUERY);
		ps.setInt(1, pet_target_id);
		ResultSet rs = ps.executeQuery();
		Pet result = null;
		if (rs.next()) {
			result = populatePet(
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6));
		} else {
			result = populatePet(-1, -1, -1, "No Data", "No Data", "No Data");
		}
		return result;
	}
	
	public void createPet(
				int pet_type_id_in,
				int pet_breed_id_in,
				String pet_name_in, 
				String pet_gender_in,
				String pet_birthday_in
			) throws SQLException {
		String query = "{call add_pet(?, ?, ?, ?, ?)}";
		CallableStatement cs = connection.prepareCall(query);
		cs.setInt(1, pet_type_id_in);
		cs.setInt(2, pet_breed_id_in);
		cs.setString(3, pet_name_in);
		cs.setString(4, pet_gender_in);
		cs.setString(5, pet_birthday_in);
		cs.executeQuery();
		
	}

	private final String CREATE_PET_TYPE = "{call add_pet_type(5?)}";
	public void createPetType(String name) throws SQLException {
		CallableStatement cs = connection.prepareCall(CREATE_PET_TYPE);
		cs.setString(1, name);
		cs.executeQuery();
	}

	private final String CREATE_PET_BREED = "{call add_pet_breed(?)}";
	public void createPetBreed(String name) throws SQLException {
		CallableStatement cs = connection.prepareCall(CREATE_PET_BREED);
		cs.setString(1, name);
		cs.executeQuery();
	}

	private final String GET_PETS_QUERY = "SELECT * FROM pets";
	public List<Pet> getPets() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PETS_QUERY);
		ResultSet rs = ps.executeQuery();
		List<Pet> pets = new ArrayList<Pet>();
		while (rs.next()) {
			pets.add(populatePet(
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6)));
		}
		return pets;
	}

	public final String GET_BREED_BY_ID_QUERY = "SELECT 1 FROM pet_breeds WHERE pet_breed_id = ?";
	public String getBreedNameByID(int target) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_BREED_BY_ID_QUERY);
		ps.setInt(1, target);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString(1);
			
			return name;
		}
		
		return "404";
	}

	public final String GET_TYPE_BY_ID_QUERY = "SELECT 1 FROM pet_types WHERE pet_type_id = ?";
	public String getTypeNameByID(int target) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_BY_ID_QUERY);
		ps.setInt(1, target);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString(1);
			
			return name;
		}
		return "404";
	}

	public final String GET_PETS_BY_BREED_QUERY = "SELECT * FROM pets WHERE pet_breed_id = ?";
	public List<Pet> getPetsByBreed(int target) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PETS_BY_BREED_QUERY);
		ps.setInt(1, target);
		ResultSet rs = ps.executeQuery();
		List<Pet> pets_by_breed = new ArrayList<Pet>();
		
		// TODO: change pets by type query to join pets with type table so we can retrieve data to add to the list here.
		while (rs.next()) {
			pets_by_breed.add(new Pet(
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6)));
		}
		
		return pets_by_breed;
	}

	public final String GET_PETS_BY_TYPE_QUERY = "SELECT * FROM pets WHERE pet_type_id = ?";
	public List<Pet> getPetsByType(int typeID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PETS_BY_TYPE_QUERY);
		ps.setInt(1, typeID);
		ResultSet rs = ps.executeQuery();
		List<Pet> pets_by_type = new ArrayList<Pet>();
		
		// TODO: change pets by type query to join pets with type table so we can retrieve data to add to the list here.
		while (rs.next()) {
			pets_by_type.add(new Pet(
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6)));
		}
		
		return pets_by_type;
	}
	
	private Pet populatePet(
		int pet_id,
		int pet_type_id, 
		int pet_breed_id, 
		String pet_name,
		String pet_gender,
		String pet_birthday) {
		
		return new Pet(pet_id, pet_type_id, pet_breed_id, pet_name, pet_gender, pet_birthday);
	}
	
	private PetType populatePetType(
		int pet_type_id,
		String pet_type_name) {
		
		return new PetType(pet_type_id, pet_type_name);
	}
	
	private PetBreed populatePetBreed(
		int pet_breed_id,
		String pet_breed_name) {
		
		return new PetBreed(pet_breed_id, pet_breed_name);
	}


}
