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
	
	//TODO: make a SQL procedure for entering new pets
	private final String CREATE_NEW_PET_QUERY = "";
	
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
		System.out.print("Enter New Pet Type: (Dog, Cat, Bird, Fish, etc)");
		String new_pet_type = scanner.nextLine();
		System.out.print("Enter New Pet Breed: (Bulldog, Shiba Inu, Pug, Bombay, etc.)");
		String new_pet_breed = scanner.nextLine();
		ps.setInt(1, pet_target_id);
		//ps.setInt(2, pet_type_id);
		//ps.setInt(3, pet_breed_id);
		ps.setString(4, new_pet_name);
		ps.setString(5, new_pet_type);
		ps.setString(6, new_pet_breed);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) return populatePet(
			rs.getInt(1),
			rs.getInt(2),
			rs.getInt(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6));
		throw new RuntimeException("Result set is empty");
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
			rs.getString(6));
		throw new RuntimeException("Result set is empty");
	}
	
	public void createPet(
			int pet_id, 
			int pet_type_id,
			int pet_breed_id,
			String pet_name, 
			String pet_type_name, 
			String pet_breed_name
			) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_PET_BY_ID_QUERY);
		ps.setInt(1, pet_id);
		ps.setInt(2, pet_type_id);
		ps.setInt(3, pet_breed_id);
		ps.setString(4, pet_name);
		ps.setString(5, pet_type_name);
		ps.setString(6, pet_breed_name);
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
					rs.getString(6)));
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
					rs.getString(6)));
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
			String pet_breed_name) {
		
		return new Pet(pet_id, pet_type_id, pet_breed_id, pet_name, pet_type_name, pet_breed_name);
	}

}
