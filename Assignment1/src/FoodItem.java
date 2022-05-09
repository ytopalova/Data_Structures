import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Assignment #1
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
public class FoodItem {

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
	 * Method updates the quantity field by amount
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
	* Method returns true if the itemCode of the object being acted 
	* on and the item object parameter are the same value    
	* @param item FoodItem
	* @return true if equal, otherwise returns false
	*/
	
	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}
	
	/**
	 * Reads from the Scanner object passed in and fills the data 
	 * member fields of the class with valid data
	 * @param scanner the Scanner
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scanner) {
		boolean validation = false;

		System.out.println("Enter the name for the item: ");
		try {
            scanner.nextLine();
            itemName=scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
		
		while(!validation){
			System.out.println("Enter the quantity for the item: ");
			if(scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if(itemQuantityInStock < 0) {
					validation = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				}else
					validation = true;				
			}else {
				System.out.println("Invalid input");
				scanner.next();
				validation = false;
			}
		}

		validation = false;
		while(!validation){
			System.out.print("Enter the cost of the item: ");
			if(scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				if(itemCost < 0) {
					validation = false;
					System.out.println("Invalid input");
					itemCost = 0;
				}else
					validation = true;
			}else {
				System.out.println("Invalid input");
				scanner.next();
				validation = false;
			}
		}

		validation = false;
		while(!validation) {
			System.out.print("Enter the sales price of the item: ");
			if(scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				if(itemPrice < 0) {
					validation = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				}else
					validation = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				validation = false;
			}
		}return true;
	}

  
   /**
   * Reads a valid itemCode from the Scanner object 
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

}