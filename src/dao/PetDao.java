package dao;

import java.sql.Connection;

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
}
