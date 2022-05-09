import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Assignment #3
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class allows us to create a new item in array inventory, 
 * update quantity or display current inventory.
 */
public class Inventory {

	/**
	 * ArrayList of FoodItems that represents our inventory
	 */
	private ArrayList<InventoryItem> inventory;

	/**
	 * Number of items that a user has entered
	 */
	private int numItems;

	/**
	 * Default Constructor
	 */
	public Inventory() {
		this.inventory = new ArrayList<>();
		this.numItems = 0;
	}

	/**
	 * Displays the all data members in the class.
	 */
	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		for (int i = 0; i < numItems; i++)
			returnString += inventory.get(i) + "\n";
		return returnString;
	}

	/**
	 * Search for the index of a FoodItem in the inventory array with the same
	 * itemCode as the FoodItem object and see if it is already stored in the
	 * inventory
	 * @param item - FoodItem
	 * @return - index of a FoodItem if it is found, -1 otherwise
	 */
	public int alreadyExists(FoodItem item) {
		for (int i = 0; i < numItems; i++) {
			if (inventory.get(i).getItemCode() == item.itemCode)
				return i;
		}
		return -1;
	}

	/**
	 * Reads from the Scanner and adds an item to the inventory array 
	 * @param scanner - Scanner to use for input
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner) {

		if (numItems == 20) {
			System.out.println("Inventory full");
			return false;
		}
		System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
		String choice = scanner.next();
		choice = choice.toUpperCase();
		// FoodItem item;
		switch (choice) {
		case "F":
			FoodItem fruit = new Fruit();
			fruit.inputCode(scanner);
			if (alreadyExists(fruit) != -1) {
				System.out.print("Item already exists");
				return false;
			}
			fruit.addItem(scanner);
			InventoryItem newFruit = new InventoryItem(fruit);
			newFruit.addItem(scanner);
			inventory.add(binarySearch(newFruit), newFruit);
			numItems++;
			return true;
		case "V":
			FoodItem vegetable = new Vegetable();
			vegetable.inputCode(scanner);
			if (alreadyExists(vegetable) != -1) {
				System.out.print("Item already exists");
				return false;
			}
			vegetable.addItem(scanner);
			InventoryItem newVegetable = new InventoryItem(vegetable);
			newVegetable.addItem(scanner);
			inventory.add(binarySearch(newVegetable), newVegetable);
			numItems++;
			return true;

		case "P":
			FoodItem preserve = new Preserve();
			preserve.inputCode(scanner);
			if (alreadyExists(preserve) != -1) {
				System.out.print("Item already exists");
				return false;
			}
			preserve.addItem(scanner);
			InventoryItem newPreserve = new InventoryItem(preserve);
			newPreserve.addItem(scanner);
			inventory.add(binarySearch(newPreserve), newPreserve);
			numItems++;
			return true;
		default:
			System.out.println("Invalid entry");
			break;
		}
		return false;
	}

	/**
	 * Method reads in an itemCode to update and quantity to update by and updates
	 * that item by the input quantity in the inventory array. Update the quantity
	 * stored in the food item
	 * @param scanner   - Input device to use
	 * @param buyOrSell - is used to denote whether buying operation (true) or
	 *                  selling operation (false) is occurring
	 * @return true/false on whether update was successful or not
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		int amount = 0;
		int itemCode = 0;
		if (numItems == 0)
			return false;
		System.out.print("Enter a valid item code: ");
		String tempCode = scanner.next();
		try {
			itemCode = Integer.parseInt(tempCode);
			if (itemCode < 0) {
				System.out.println("Invalid Input, must be positive");
			} else {
				for (int i = 0; i < numItems; i++) {
					if (inventory.get(i).getItemCode() == itemCode) {
						if (buyOrSell) {
							System.out.print("Enter valid quantity to buy: ");
							amount = scanner.nextInt();
							inventory.get(i).updateQuantity(scanner, amount);
						} else {
							System.out.print("Enter valid quantity to sell: ");
							amount = scanner.nextInt();
							if (amount > inventory.get(i).itemQuantityInStock) {
								System.out.println("You do not have that many items to sell");
							} else {
								inventory.get(i).updateQuantity(scanner, -amount);
							}
						}
						return true;
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
		}
		return false;
	}

	/**
	 * This method searches for an item itemCode of which is reading from input 
	 * @param scan the Scanner
	 * @return true if successful, false if not
	 */
	public boolean searchForItem(Scanner scan) {
		System.out.print("Enter the code for the item: ");

		String reader = "";
		int code = 0;
		do {
			try {
				reader = scan.next();
				code = Integer.parseInt(reader);
			} catch (InputMismatchException e) {
				System.out.println("Invalid Code");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Code");
			}
		} while (code == 0);

		for (int i = 0; i < numItems; i++) {
			if (inventory.get(i).getItemCode() == code) {
				System.out.println(inventory.get(i).toString());
				return true;
			}
		}
		System.out.println("Code not found in inventory...");
		return false;
	}

	/**
	 * This method is searching for correct index in inventory in order to insert
	 * new item
	 * @param item FoodItem
	 * @return lowIndex
	 */
	private int binarySearch(InventoryItem item) {
		int lowIndex = 0;
		int highIndex = inventory.size() - 1;
		int midIndex = highIndex / 2;

		while (lowIndex <= highIndex) {
			if (item.compareTo(inventory.get(midIndex)) < 0) {
				highIndex = midIndex - 1;
			} else if (item.compareTo(inventory.get(midIndex)) > 0) {
				lowIndex = midIndex + 1;
			} else {
				return midIndex;
			}
			midIndex = (lowIndex + highIndex) / 2;
		}
		return lowIndex;
	}

	/**
	 * This method is prints the expiry date, asks for user input, if it's valid it
	 * prints that item's printExpirySummary method
	 * @param scan the Scanner
	 * @return true if successful, false if not
	 */
	public boolean printExpiry(Scanner scan) {
		System.out.print("Enter the code for the item: ");
		String temp = "";
		int code = 0;
		try {
			temp = scan.nextLine();
			code = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			return false;
		}
		for (int i = 0; i < numItems; i++) {
			if (inventory.get(i).getItemCode() == code) {
				inventory.get(i).printExpirySummary();
				return true;
			}
		}
		return false;
	}

	/**
	 * This method removs expired items from the system loops through the arraylist
	 * of inventory items and runs each removeExpiredItems method to remove items of
	 * that object that are expired
	 * @param today the LocalDate
	 */
	public void removeExpiredItems(LocalDate today) {
		for (int i = 0; i < numItems; i++) {
			inventory.get(i).removeExpiredItems(today);
		}
	}

}
