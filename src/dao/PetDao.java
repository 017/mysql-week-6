package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Pet;

public class PetDao {
	private Connection connection;
	private Scanner scanner = new Scanner(System.in);
	private final String GET_PETS_QUERY = "SELECT * FROM pets";

	private final String GET_PETS_BY_TYPE_QUERY = null;

	private final String GET_PETS_BY_BREED_QUERY = null;
	
	private final String GET_PET_BY_ID_QUERY = "SELECT * FROM pets WHERE pet_id = ?";
	private final String DELETE_PET_BY_ID_QUERY = "DELETE FROM pets WHERE pet_id = ?";
	
	//TODO: make a SQL procedure for editing pets
	private final String EDIT_PET_BY_ID_QUERY = "";
	private final String CREATE_NEW_PET_QUERY = "CALL add_pet(@?, @?, @?, @?, @?, @?, @?);"; //MySQL variables need @ in front apparently
	
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
	
	// might not work yet
	public Pet editPetByID(int pet_target_id) throws SQLException {
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
		ps.setInt(1, pet_target_id);
		ps.setInt(2, new_pet_type_id);
		ps.setInt(3, new_pet_breed_id);
		ps.setString(4, new_pet_name);
		ps.setString(5, new_pet_type);
		ps.setString(6, new_pet_breed);
		ps.setString(7, new_pet_gender);
		ps.setString(8, new_pet_birthday);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) return populatePet(
			rs.getInt(1),
			rs.getInt(2),
			rs.getInt(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6),
			rs.getString(7),
			rs.getString(8));
		throw new RuntimeException("Result set is empty");
	}

	private final String GET_PET_BREED_BY_NAME = "SELECT pet_breed_id FROM breeds WHERE pet_breed_name = ?";
	private int convertBreedNameToID(String input_breed_name) throws SQLException {
		int result_id = 0;
		
		PreparedStatement ps = connection.prepareStatement(GET_PET_BREED_BY_NAME);
		ps.setString(1, input_breed_name);
		ResultSet rs = ps.executeQuery();
		result_id = rs.getInt(1);
		
		if (result_id > 0) {
			return result_id;
		} else {
			return breedAdd();
		}
	}

	private final String GET_PET_TYPE_BY_NAME = "SELECT pet_type_id FROM pet_types WHERE pet_type_name = ?";
	private int convertTypeNameToID(String inputted_name) throws SQLException {
		// Find out from the Database what the type ID is based on the name if it exists already
		int result_id = 0;
		
		PreparedStatement ps = connection.prepareStatement(GET_PET_TYPE_BY_NAME);
		ps.setString(1, inputted_name);
		ResultSet rs = ps.executeQuery();
		result_id = rs.getInt(1);
		
		if (result_id > 0) {
			return result_id;
		} else {
			// If it doesn't find anything, default the ID to the type table length +1, inserting it as the next ID.
			return typeAdd();
		}
	}

	// COUNT(*) query the types table and get a number from that, +1 to add the next numeric ID.
	private final String GET_TYPE_LENGTH = "SELECT COUNT(*) FROM pet_types";
	private int typeAdd() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_LENGTH);
		ResultSet rs = ps.executeQuery();
		int result = rs.getInt(1) +1;
		return result;
	}

	private final String GET_BREED_LENGTH = "SELECT COUNT(*) FROM breeds";
	private int breedAdd() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_LENGTH);
		ResultSet rs = ps.executeQuery();
		int result = rs.getInt(1) +1;
		return result;
	}
	
	public Pet getPetByID(int pet_target_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PET_BY_ID_QUERY);
		ps.setInt(1, pet_target_id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) return populatePet(
			rs.getInt(1),
			rs.getInt(2),
			rs.getInt(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6),
			rs.getString(7),
			rs.getString(8));
		throw new RuntimeException("Result set is empty");
	}
	
	public void createPet(
			int pet_id, 
			int pet_type_id,
			int pet_breed_id,
			String pet_name, 
			String pet_type_name, 
			String pet_breed_name,
			String pet_gender,
			String pet_birthday
			) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_PET_BY_ID_QUERY);
		ps.setInt(1, pet_id);
		ps.setInt(2, pet_type_id);
		ps.setInt(3, pet_breed_id);
		ps.setString(4, pet_name);
		ps.setString(5, pet_type_name);
		ps.setString(6, pet_breed_name);
		ps.setString(7, pet_gender);
		ps.setString(8, pet_birthday);
		ps.executeUpdate();
	}
	
	public List<Pet> getPets() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_PETS_QUERY).executeQuery();
		List<Pet> pets = new ArrayList<Pet>();
		while (rs.next()) {
			pets.add(populatePet(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8)));
		}
		if (rs.next()) return pets;
		throw new RuntimeException("Result set is empty");
	}
	
	public List<Pet> getPetsByType(String typeName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PETS_BY_TYPE_QUERY);
		ps.setString(1, typeName);
		ResultSet rs = ps.executeQuery();
		List<Pet> pets_by_type = new ArrayList<Pet>();
		
		while (rs.next()) {
			pets_by_type.add(new Pet(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8)));
		}
		
		if (rs.next()) return pets_by_type;
		throw new RuntimeException("Result set is empty");
	}
	
	private Pet populatePet(
			int pet_id,
			int pet_type_id, 
			int pet_breed_id, 
			String pet_name, 
			String pet_type_name, 
			String pet_breed_name,
			String pet_gender,
			String pet_birthday) {
		
		return new Pet(pet_id, pet_type_id, pet_breed_id, pet_name, pet_type_name, pet_breed_name, pet_gender, pet_birthday);
	}

}
