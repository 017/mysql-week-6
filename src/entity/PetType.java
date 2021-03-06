package entity;
import dao.PetDao;
import java.sql.SQLException;

public class PetType {
	private int pet_type_id;
	private String pet_type_name;
	private PetDao petDao = new PetDao();
	
	public PetType(int pet_type_id_input, String pet_type_name_input) {
		this.setPetTypeID(pet_type_id_input);
		this.SetPetTypeName(pet_type_name_input);
	}

	private void SetPetTypeName(String pt_name) {
		this.pet_type_name = pt_name;
	}

	private void setPetTypeID(int pt_id) {
		this.pet_type_id = pt_id;
	}

	private String getPetTypeName(int id) throws SQLException {
		this.pet_type_name = petDao.getTypeNameByID(id);
		return this.pet_type_name;
	}

	private int getPetTypeID() {
		return this.pet_type_id;
	}
}
