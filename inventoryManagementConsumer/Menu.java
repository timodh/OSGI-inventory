package inventorymanagementconsumer;

import java.util.IllegalFormatException;
import java.util.Scanner;

import com.inventorymanagementPublisher.Dao;
import com.inventorymanagementPublisher.ItemDTO;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	Dao dao;
	Boolean menuLoop = true;
	
	public Menu(Dao dao) {
		this.dao = dao;
	}
	
	void displayMenu() {
		while(menuLoop) {
			System.out.println("=".repeat(30));
			System.out.println("Inventory Management Service");
			System.out.println("Select an Option");
			System.out.println("");
			System.out.println("[1] View Current Inventory");
			System.out.println("[2] View Specific Item");
			System.out.println("[3] Add an Item ");
			System.out.println("[4] Update an Item");
			System.out.println("[5] Delete an Item");
			System.out.println("[6] Exit Service");
			String line = scanner.nextLine();
			
			switch (line) {
			case "1" -> displayInventory();
			case "2" -> displayItem();
			case "3" -> displayAddItem();
			case "4" -> displayUpdateItem();
			case "5" -> displayDeleteItem();
			case "6" -> displayExit();
			default -> System.out.println("Invalid Option");
			}
			
			
		}
	}
	
	void displayInventory() {
		
		System.out.println("=".repeat(30));
		System.out.println("Inventory");
		System.out.println("");
		System.out.println("%-10s %-10s %-10s %-10s".formatted("ID", "Name", "Quantity", "Price"));
		System.out.println("");
		
		
		for(ItemDTO item : dao.getAll()) {
			System.out.println("%-10d %-10s %-10d %-10.2f".formatted(item.getId(), item.getName(), item.getQuantity(), item.getPrice()));
		}
		
		
	}
	
	void displayItem() {
		//handle null
		//handle displaying too lol
		
		ItemDTO item;
		int intResult;
		
		System.out.println("=".repeat(30));
		while(true) {
			System.out.println("Enter Item ID");
			String id = scanner.nextLine();
			
			try {
				intResult = Integer.parseInt(id);
				
				if(dao.check(intResult)) { //check if item id exists within the table
					break;
				} else {
					System.out.println("Invalid Item ID");
					return; //exists subroutine because i can't be bothered to implement a quit button
				}
			} catch (Exception e) {
				System.out.println("Enter Valid ID");
				System.out.println("");
			}
			
		}
		
		item = dao.get(intResult);
		
		System.out.println("");
		System.out.println("%-10s %-10s %-10s %-10s".formatted("ID", "Name", "Quantity", "Price"));
		System.out.println("");
		System.out.println("%-10d %-10s %-10d %-10.2f".formatted(item.getId(), item.getName(), item.getQuantity(), item.getPrice()));
		}
	
	void displayAddItem() {
		
		int intResult;
		double doubleResult;
		
		System.out.println("=".repeat(30));
		System.out.println("Add an Item");
		System.out.println("");
		
		System.out.println("Enter Name");
		String name = scanner.nextLine();
		
		System.out.println(""); //Separating space
		
		while(true) {
		System.out.println("Enter Quantity");
		String quantity = scanner.nextLine();
		try {
			intResult = Integer.parseInt(quantity);
			break;
		} catch (Exception e) {
			System.out.println("Enter Valid Quantity");
			System.out.println("");
		}
		}
		
		System.out.println(""); //Separating space
		
		while(true) {
		System.out.println("Enter Price");
		String price = scanner.nextLine();
		
		try {
			doubleResult = Double.parseDouble(price);
			break;
		} catch (Exception e) {
			System.out.println("Enter Vaslid Price");
			System.out.println("");
		}
		
		}
		
		ItemDTO item = new ItemDTO(name, intResult, doubleResult);
		dao.create(item);
		
		System.out.println(""); //Separating space
		
		System.out.println("Item Added!");
		
	}
	
	void displayUpdateItem() {
		
		//Annoying menu
		
		ItemDTO item = null;
		String input;
		Boolean loop = true;
		
		int intResult;
		double doubleResult;
		
		displayInventory();
		System.out.println("=".repeat(30));
		
		
		while(true) {
			
			System.out.println("Enter ID of Item You Wish to Update");
			input = scanner.nextLine();
			
			try {
				intResult = Integer.parseInt(input);
				
				if(dao.check(intResult)) { //check if item id exists within the table
					break;
				} else {
					System.out.println("Invalid Item ID");
					return; //exists subroutine because i can't be bothered to implement a quit button
				}
			} catch (Exception e) {
				System.out.println("Enter Valid ID");
				System.out.println("");
			}
			
		}
		
		item = dao.get(intResult);
		
		while(loop) {
			System.out.println("=".repeat(30));
			System.out.println("%-10s %-10s %-10s %-10s".formatted("ID", "Name", "Quantity", "Price"));
			System.out.println("%-10d %-10s %-10d %-10.2f".formatted(item.getId(), item.getName(), item.getQuantity(), item.getPrice()));
			System.out.println("=".repeat(30));
			System.out.println("Which Value Do You Wish To Modify?");
			System.out.println("");
			System.out.println("[1] Name");
			System.out.println("[2] Quantity");
			System.out.println("[3] Price");
			System.out.println("[4] Commit Changes");
			input = scanner.nextLine();
			switch(input) {
			case "1" -> {
				System.out.println("");
				System.out.println("Enter New Name");
				input = scanner.nextLine(); //String so don't know if I can validate
				item.setName(input);
			}
			case "2" -> {
				System.out.println("");
				while(true) {
					System.out.println("Enter New Quantity");
					input = scanner.nextLine();
					
					try {
						intResult = Integer.parseInt(input);
						break;
					}catch(Exception e) {
						System.out.println("Enter Valid Quantity");
						System.out.println("");
					}
					
				}
				item.setQuantity(intResult);
			}
			case "3" -> {
				System.out.println("");
				while(true) {
					System.out.println("Enter New Price");
					input = scanner.nextLine();
					
					try {
						doubleResult = Double.parseDouble(input);
						break;
					} catch (Exception e) {
						System.out.println("Enter Valid Price");
						System.out.println("");
					}
					
					}
				item.setPrice(doubleResult);
			}
			case "4" -> {
				dao.update(item);
				System.out.println("");
				System.out.println("Item Updated!");
				loop = false;
			}
			default -> {
				System.out.println("Invalid Option");
			}
			}
		}
		}
	
	
	void displayDeleteItem() {
		String input;
		int intResult;
		displayInventory();
		System.out.println("=".repeat(30));
		while(true) {
		System.out.println("Enter ID of Item You Wish to Delete");
		input = scanner.nextLine();
		
		try {
			intResult = Integer.parseInt(input);
			
			if(dao.check(intResult)) { //check if item id exists within the table
				break;
			} else {
				System.out.println("Invalid Item ID");
				return; //exists subroutine because i can't be bothered to implement a quit button
			}
		} catch (Exception e) {
			System.out.println("Enter Valid ID");
			System.out.println("");
		}
		}
		dao.delete(intResult);
		System.out.println("Item Deleted!");
		}
	
	void displayExit() {
		String input;
		System.out.println("=".repeat(30));
		System.out.println("Do You Wish to Exit?");
		System.out.println("[1] Yes");
		System.out.println("[2] No");
		
		input = scanner.nextLine();
		switch (input) {
		case "1" -> menuLoop = false;
		case "2" -> {}
		default -> System.out.println("Enter Valid Option");
		}
		
	}
	
	
}
