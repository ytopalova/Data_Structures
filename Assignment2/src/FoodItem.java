import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Assignment #2
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * Parent class foodItem stores variables and methods, allow us 
 * optimize memory and give ability not to right same code multiple times.
 * 
 */


/**
* The Class FoodItem.
*/
public class FoodItem implements Comparable<FoodItem> {

   /** The item code. */
   protected int itemCode;

   /** The item name. */
   protected String itemName;

   /** The item price. */
   protected float itemPrice;

   /** The item quantity. */
   protected int itemQuantityInStock;

   /** The item cost. */
   protected float itemCost;

   /**
	 * Default Constructor
	 */
	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
	}
	/**
	  * Displays the all data members in the class.
	  */										 
	@Override
	public String toString() {
		return "Item: "+itemCode+" "+itemName+" "+itemQuantityInStock+" price: $"+String.format("%.2f", itemPrice)+" cost: $"+String.format("%.2f", itemCost);
	}
	
	/**
	 * This method updates the quantity field by amount
	 * (note amount could be positive or negative)
	 * @param amount - what to update by, either can be positive or negative
	 * @return true if successful, otherwise returns false
	 */
	public boolean updateItem(int amount) {
		if(amount< 0 && itemQuantityInStock > amount) {
			return false;
		}
		itemQuantityInStock += amount;
		return true;
	}
	
	/**
	* This method returns true if the itemCode of the object being acted 
	* on and the item object parameter are the same value    
	* @param item FoodItem
	* @return true if equal, otherwise returns false
	*/
	
	public boolean isEqual(FoodItem item) {
		if (this.itemCode == item.itemCode) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method reads from the Scanner object passed in and fills the data 
	 * member fields of the class with valid data
	 * @param scanner the Scanner
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {	
		do {
			System.out.print("Enter the name of the item: ");
			try {
				scanner.nextLine();
				itemName=scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			}
		} while(itemName=="");
		
		String tempQuantity = "";
		do {
			System.out.print("Enter the quantity for the item: ");
			try {
				tempQuantity = scanner.next();
				itemQuantityInStock = Integer.parseInt(tempQuantity);
				if (itemQuantityInStock < 0) {
					System.out.println("Invalid Input, Must be positive");
					itemQuantityInStock=0;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		} while(itemQuantityInStock==0);
		
		String tempCost = "";
		do {
			System.out.print("Enter the cost of the item: ");
			try {
				tempCost = scanner.next();
				itemCost = Float.parseFloat(tempCost);
				if (itemCost < 0) {
					System.out.println("Invalid Input, Must be positive");
					itemCost=0;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		} while(itemCost==0);
		
		String tempPrice = "";
		do {
			System.out.print("Enter the sales price of the item: ");
			try {
				tempPrice = scanner.next();
				itemPrice = Float.parseFloat(tempPrice);
				if (itemPrice < 0) {
					System.out.println("Invalid Input, Must be positive");
					itemPrice=0;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		} while(itemPrice==0);
		
		return true;
	}
  
   /**
   * This method reads a valid itemCode from the Scanner input 
   * @param scanner the Scanner
   * @return returns true/false if successful
   */ 
	public boolean inputCode(Scanner scanner) {
		boolean validInput = false;
		while(!validInput) {
			System.out.print("Enter valid item code: ");
			if(scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
					validInput = true;
				
			}else {
				System.out.println("Invalid code");
				scanner.next();
			}
		}return validInput;
	}
	
	/**
	 * This method  returns the item's code
	 * @return itemCode
	 */
	public int getCode() {
		return itemCode;
	}
	
	/**
	 * This method is used to compare FoodItems based on their itemCode
	 * @param otherItem FoodItem 
	 * @return compare of codes
	 */
	public int compareTo(FoodItem otherItem) {
		return (itemCode - otherItem.getCode());
	}
	
	/**
	 * This method create format that prepares it for saving to file
	 * @return String of FoodItem's info
	 */
	public String toWriteFile() {
		return this.itemCode + "\n" + this.itemName + "\n" + this.itemQuantityInStock + "\n" + this.itemCost + "\n" + this.itemPrice; 
	}
	

}