package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PetDao;
import entity.Pet;
//import entity.Type;
//import entity.Breed;
//import entity.Outfit;

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
					this.displayallPets();
				} else if (selection.equals("2")) {
					this.displayPetsByType();
				} else if (selection.equals("3")) {
					this.displayPetsByBreed();
				} else if (selection.equals("4")) {
					this.displayPetByID();
				} else if (selection.equals("5")) {
					this.createPet();
				} else if (selection.equals("6")) {
					this.deletePet();
				} else if (selection.equals("7")) {
					this.editPet();
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

	private void createPet() throws SQLException {
		System.out.print("Enter Pet ID: ");
		int petID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Pet Type ID: ");
		int typeID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Pet Breed ID: ");
		int breedID = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter Pet Name: ");
		String petName = scanner.nextLine();
		System.out.print("Enter Pet Type: ");
		String petType = scanner.nextLine();
		System.out.print("Enter Pet Breed: ");
		String petBreed = scanner.nextLine();
		System.out.print("Enter Pet Gender: ");
		String petGender = scanner.nextLine();
		System.out.print("Enter Pet Birthday: ");
		String petBirthday = scanner.nextLine();
		petDao.createPet(petID, typeID, breedID, petName, petType, petBreed, petGender, petBirthday);
	}

	private void displayPetByID() throws SQLException {
		System.out.print("Enter Pet ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Pet pet = petDao.getPetByID(id);
		System.out.println(pet.getPetID() + ": " + pet.getPetName());
		System.out.println("\tPet ID: " + pet.getPetID() + " Pet Name:" + pet.getPetName() + " Pet Type: " + pet.getPetTypeName());
	}

	private void displayPetsByBreed() throws SQLException {
		
	}

	private void displayPetsByType() throws SQLException{
		
	}

	private void displayallPets() throws SQLException {
		// Exception in thread "main" java.lang.NullPointerException
		// at mysql_week_6_petDB/application.Menu.displayallPets (here)
		// null pointer though getPets returns a Pet?
		List<Pet> pets = petDao.getPets();
		for (Pet pet : pets) {
			System.out.println(
			pet.getPetID() + ": Name: " + 
			pet.getPetName() + " | Type: " + 
			pet.getPetTypeName() + " | Breed: " + 
			pet.getPetBreedName());
		}
	}

	private void editPet() throws SQLException {
		System.out.print("Enter Pet ID to Edit: ");
		int id = Integer.parseInt(scanner.nextLine());
		petDao.editPetByID(id);
	}

	private void printMenu() {
		System.out.println("Select an Option: \n");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + "] " + options.get(i));
		}
	}
}
