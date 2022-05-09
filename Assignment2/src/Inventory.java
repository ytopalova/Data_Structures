import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Assignment #2
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class allows us to create a new item in array inventory, 
 * update quantity or display current inventory.
 */
public class Inventory{
	
		/**
		 * ArrayList of FoodItems that represents our inventory
		 */
		private ArrayList<FoodItem> inventory;;
	
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
		
		@Override
		public String toString() {
			String returnString = "Inventory:\n";
			for(int i = 0; i < numItems; i++)
				returnString += inventory.get(i) + "\n";
			return returnString;
		}
		
		/**
		 * Search for the index of a FoodItem in the inventory array with the same itemCode as the FoodItem object
		 * and see if it is already stored in the inventory
		 * @param item - FoodItem 
		 * @return - index of a FoodItem if it is found, -1 otherwise
		 */
		public int alreadyExists(FoodItem item) {
			for(int i = 0; i < numItems; i++){
				if(inventory.get(i).itemCode == item.itemCode)
					return i;
			}
			return -1;
		}

		/**
		 * Reads from the Scanner and adds an item to the inventory array
		 * @param scanner - Scanner to use for input
		 * @return <code>true</code> if all data members were successfully populated, <code>false</code> otherwise
		 */
		public boolean addItem(Scanner scanner) {

			if(numItems == 20){
				System.out.println("Inventory full");
				return false;
			}
			System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
	        String choice = scanner.next();
	        choice = choice.toUpperCase();
	        //FoodItem item;
				switch(choice){
					case "F":
						FoodItem fruit = new Fruit();
			            fruit.inputCode(scanner);
			            if (alreadyExists(fruit) != -1) {
			                System.out.print("Item already exists");
			                return false;
			            }
			            fruit.addItem(scanner);
			            inventory.add(binarySearch(fruit), fruit);
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
			            inventory.add(binarySearch(vegetable), vegetable);
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
			            inventory.add(binarySearch(preserve), preserve);
						numItems++;
			            return true;
					default:
	                    System.out.println("Invalid entry");
	                    break;					
					}return false;			
		}
			
	
		/**
		 * Method reads in an itemCode to update and quantity to update 
		 * by and updates that item by the input quantity in the inventory array.
		 * Update the quantity stored in the food item
		 * @param scanner - Input device to use 
		 * @param buyOrSell - is used to denote whether buying operation (true) or selling operation (false) is occurring
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
						if (inventory.get(i).itemCode == itemCode) {
							if (buyOrSell) {
								System.out.print("Enter amount to buy: ");
								amount = scanner.nextInt();
								inventory.get(i).itemQuantityInStock += amount;
							} else {
								System.out.print("Enter amount to sell: ");
								amount = scanner.nextInt();
								if (amount > inventory.get(i).itemQuantityInStock) {
									System.out.println("You do not have that many items to sell");
								} else {
									inventory.get(i).itemQuantityInStock -= amount;
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
		 * This method saves current inventory to file
		 * name of file reads from input 
		 * @param scan the Scanner
		 * @return true if reading was successful, false if not
		 */
		public boolean saveToFile(Scanner scan) {
			if (inventory.isEmpty()) {
				return false;
			} else {
				String filePath = new String();
				FileWriter outFile = null;
				char option = '\u0000';
				
				System.out.print("\nEnter name of file to write to: ");
				filePath = scan.next();
				File file = new File(filePath);
				try {
					while (file.exists()) {
						System.out.println("File name already exists. Would you like to overwrite the file? (y or n)");
						option = scan.next().charAt(0);
						if (option == 'y' || option == 'Y') {
							break;
						} else {
							System.out.println("Please enter a new file name to write to: ");
							filePath = scan.next();
							file = new File(filePath);
						}
					}
					outFile = new FileWriter(filePath);
					for (int i = 0; i < inventory.size(); i++) {
						outFile.append(inventory.get(i).toWriteFile() + "\n");
					}
					outFile.close();
					return true;
				} catch (IOException e) {
					return false;
				}
			}
		}
		
		/**
		 * This method is opening file
		 * @param scanner the Scanner
		 * @return true if reading was successful, false if not
		 */
		public boolean openFile(Scanner scanner) {
			Scanner reader = null;
			String filePath = new String();
			System.out.print("\nEnter the filename to read from: ");
			filePath = scanner.next();
			try {
				File file = new File(filePath);
				if (file.exists()) {
					reader = new Scanner(file);
					if (reader.hasNext()) {
						return readFile(reader); 
					} else {
						System.out.println("File is empty, no items to load in\n");
						reader.close();
						return false;
					}
				} else {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		}
		
		/**
		 * This method is used with openFile()
		 * it reads line by line and adds all valid items into inventory
		 * @param reader the Scanner
		 * @return true if reading was successful, false if not
		 */
		private boolean readFile(Scanner reader) {
			FoodItem newItem = null;
			char itemType = ' ';
			int itemCode = 0;
			String itemName = null;
			int itemQuantity = 0;
			float itemPrice = 0;
			float itemCost = 0;
			
			while (reader.hasNextLine()) {
				String fromFile = " ";
				try {
					fromFile = reader.nextLine();
					itemType = fromFile.charAt(0);
					if (itemType != 'f' && itemType != 'v' && itemType != 'p') {
						return false;
					}
				} catch (InputMismatchException e) {
					return false;
				} catch (NumberFormatException e) {
					return false;
				}

				try {
					fromFile = reader.nextLine();
					itemCode = Integer.parseInt(fromFile);
				} catch (InputMismatchException e) {
					return false;
				} catch (NumberFormatException e) {
					return false;
				}
				
				itemName = reader.nextLine();
				
				try {
					fromFile = reader.nextLine();
					itemQuantity = Integer.parseInt(fromFile);
				} catch (InputMismatchException e) {
					return false;
				} catch (NumberFormatException e) {
					return false;
				}
				
				try {
					fromFile = reader.nextLine();
					itemCost = Float.parseFloat(fromFile);
				} catch (InputMismatchException e) {
					return false;
				} catch (NumberFormatException e) {
					return false;
				}
				
				try {
					fromFile = reader.nextLine();
					itemPrice = Float.parseFloat(fromFile);
				} catch (InputMismatchException e) {
					return false;
				} catch (NumberFormatException e) {
					return false;
				}
				
				if (itemType == 'f' || itemType == 'F') {
					String supplierName = reader.nextLine();
					newItem = new Fruit(itemCode, itemName, itemQuantity, itemPrice, itemCost, supplierName);
				} else if (itemType == 'v' || itemType == 'V') {
					String farmName = reader.nextLine();
					newItem = new Vegetable(itemCode, itemName, itemQuantity, itemPrice, itemCost, farmName);
				} else if (itemType == 'p' || itemType == 'P') {
					int jarSize = 0;
					try {
						fromFile = reader.nextLine();
						jarSize = Integer.parseInt(fromFile);
					} catch (InputMismatchException e) {
						return false;
					} catch (NumberFormatException e) {
						return false;
					}
					newItem = new Preserve(itemCode, itemName, itemQuantity, itemPrice, itemCost, jarSize);
				} else {
					return false;
				}
				
				if (alreadyExists(newItem) == -1) {
					insertItem(newItem);
				} else {
					System.out.println("Item did not get added... already exists in inventory.\n");
				}
			}
			return true;
		}
		
		/**
		 * This method is for inserting new item into the inventory
		 * @param item FoodItem
		 */
		private void insertItem(FoodItem item) {
			if (inventory.isEmpty()) {
				inventory.add(item);
				numItems++;
			} else {
				inventory.add(binarySearch(item), item);
				numItems++;
			}
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
				if (inventory.get(i).getCode() == code) {
					System.out.println(inventory.get(i).toString());
					return true;
				}
			}
			System.out.println("Code not found in inventory...");
			return false;
		}
		
		/**
		 * This method is searching for correct index in inventory 
		 * in order to insert new item
		 * @param item FoodItem
		 * @return lowIndex
		 */
		private int binarySearch(FoodItem item) {
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
		
}
		
