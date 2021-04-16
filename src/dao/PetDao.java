package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetDao {

	private Connection connection;
	private final String GET_PETS_QUERY = "SELECT * FROM pets";
	private final String GET_PET_BY_ID_QUERY = "SELECT * FROM pizzas WHERE pet_id = ?";
	private final String DELETE_PET_BY_ID_QUERY = "DELETE FROM pizzas WHERE pet_id = ?";
	
	//TODO: make a SQL procedure for editing pets
	private final String EDIT_PET_BY_ID_QUERY = "";
	
	//TODO: make a SQL procedure for entering new pets
	private final String CREATE_NEW_PET_QUERY = "";
	
	public PetDao() {
		connection = DBConnection.getConnection();
	}
	
	public void deletePetByID(int pet_target_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PET_BY_ID_QUERY);
		ps.setInt(1, pet_target_id);
		ps.executeUpdate();
	}
	
	public void editPetByID(
			int pet_target_id, 
			String pet_name, 
			String pet_type, 
			String pet_breed
			) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_PET_BY_ID_QUERY);
		ps.setInt(1, pet_target_id);
		ps.setInt(2, pet_name);
		ps.setInt(3, pet_type);
		ps.setInt(4, pet_breed);
		ps.executeUpdate();
	}
}
