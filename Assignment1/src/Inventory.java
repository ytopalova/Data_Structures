import java.util.Scanner;
/**
 * Assignment #1
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class allows us to create a new item in array inventory, 
 * update quantity or display current inventory.
 */
public class Inventory{
	
		/**
		 * List of FoodItems that represents our inventory
		 */
		private FoodItem[] inventory;
	
		/**
		 * Number of items that a user has entered
		 */
		private int numItems;
	
		/**
		 * Default Constructor
		 */
		public Inventory() {
				inventory = new FoodItem[20];
			}
		
		@Override
		public String toString() {
			String returnString = "Inventory:\n";
			for(int i = 0; i < numItems; i++)
				returnString += inventory[i].toString() + "\n";
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
				if(inventory[i].isEqual(item))
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
	        FoodItem item;
				switch(choice){
					case "F":
			            inventory[numItems] = new Fruit();
			            inventory[numItems].inputCode(scanner);
			            if (alreadyExists(inventory[numItems]) != -1) {
			                System.out.print("Item already exists");
			                return false;
			            }
			            inventory[numItems++].addItem(scanner);
			            return true;
					case "V":
			            inventory[numItems] = new Vegetable();
			            inventory[numItems].inputCode(scanner);
			            if (alreadyExists(inventory[numItems]) != -1) {
			                System.out.print("Item already exists");
			                return false;
			            }
			            inventory[numItems++].addItem(scanner);
			            return true;
						
					case "P":
			            inventory[numItems] = new Preserve();
			            inventory[numItems].inputCode(scanner);
			            if (alreadyExists(inventory[numItems]) != -1) {
			                System.out.print("Item already exists");
			                return false;
			            }
			            inventory[numItems++].addItem(scanner);
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
	      
	        if (numItems == 0)
	            return false;
	        System.out.print("Enter a valid item code: ");
	        int itemCode = scanner.nextInt();
	        for (int i = 0; i < numItems; i++) {
	            if (inventory[i].itemCode == itemCode) {
	                if (buyOrSell) {
	                    System.out.print("Enter amount to buy: ");
	                    int amount = scanner.nextInt();
	                    if (amount > 0) {
	                    inventory[i].itemQuantityInStock += amount;
	                    }else {
	                    	System.out.print("Invalid quantity...");
	                    }
	                } else {
	                    System.out.print("Enter amount to sell: ");
	                    int amount = scanner.nextInt();
	                    if (amount > 0 && amount < inventory[i].itemQuantityInStock) {
	                    inventory[i].itemQuantityInStock -= amount;
	                    }else {
	                    	System.out.print("Invalid quantity...");
	                    }
	                }
	                return true;
	            }
	        }
	        return false;
	    }

		
}
		
