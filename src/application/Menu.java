package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PetDao;

public class Menu {
	private PetDao petDao;
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display All Pets",
			"Display Pets by Type",
			"Display Pets by Breed",
			"Display a Pet by ID",
			"Create A Pet",
			"Delete A Pet",
			"Edit a Pet");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {

				if (selection.equals("1")) {
					displayallPets();
				} else if (selection.equals("2")) {
					displayPetsByType();
				} else if (selection.equals("3")) {
					displayPetsByBreed();
				} else if (selection.equals("4")) {
					displayPetByID();
				} else if (selection.equals("5")) {
					createPet();
				} else if (selection.equals("6")) {
					deletePet();
				} else if (selection.equals("7")) {
					editPet();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue.");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}

	private void deletePet() throws SQLException {
		System.out.print("Enter Pet ID to Delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		petDao.deletePetByID(id);
	}

	private void createPet() {
		// TODO Auto-generated method stub
		
	}

	private void displayPetByID() {
		// TODO Auto-generated method stub
		
	}

	private void displayPetsByBreed() {
		// TODO Auto-generated method stub
		
	}

	private void displayPetsByType() {
		// TODO Auto-generated method stub
		
	}

	private void displayallPets() {
		// TODO Auto-generated method stub
		
	}
}
