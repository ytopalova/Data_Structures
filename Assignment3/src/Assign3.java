import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Assignment #3
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class test our program.
 */

public class Assign3 {
	private static LocalDate localDate = LocalDate.now();

	/**
	 * Main method of the program creates an object and runs all cases on it.
	 * @param args String
	 */
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Inventory inventory = new Inventory();
		String choice = "";
		while (choice != "9") {
			displayMainMenu();
			System.out.print("> ");
			if (input.hasNext()) {
				choice = input.next();
				switch (choice) {
				case "1":
					if (!inventory.addItem(input))
						System.out.println("Error...could not add item");
					break;
				case "2":
					System.out.println(inventory);
					break;
				case "3":
					if (!inventory.updateQuantity(input, true))
						System.out.println("Error...could not buy item");
					break;
				case "4":
					if (!inventory.updateQuantity(input, false))
						System.out.println("Error...could not sell item");
					break;
				case "5":
					inventory.searchForItem(input);
					break;
				case "6":
					inventory.removeExpiredItems(localDate);
					break;
				case "7":
					input.nextLine();
					if (!inventory.printExpiry(input)) {
						System.out.println("Error, could not print expiry");
					}
					break;
				case "8":
					System.out.print("Please enter today's date (yyyy-mm-dd): ");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					input.nextLine();
					String date = input.nextLine();

					try {
						localDate = LocalDate.parse(date, formatter);
					} catch (DateTimeParseException e) {
						System.out.println(e.getLocalizedMessage());
					}
					break;
				default:
					System.out.println("Something went wrong...");
					break;
				}
			} else {
				System.out.println("Incorrect value entered");
				input.next();
			}
		}
	}

	/**
	 * Displays the main menu to the console
	 */
	public static void displayMainMenu() {
		System.out.println("Please select one of the following:\n" 
				+ "1: Add Item to Inventory\n"
				+ "2: Display Current Inventory\n" 
				+ "3: Buy Item(s)\n" + "4: Sell Item(s)\n" 
				+ "5: Search for Item\n"
				+ "6: Remove Expired Item(s)\n" 
				+ "7: Print Expiry\n" 
				+ "8: Change Today's Date\n" 
				+ "9: To Exit\n");

	}

}
