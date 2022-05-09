import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Assignment #2
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * 
 * This class inherits FoodItem and add own specifications
 */
public class Vegetable extends FoodItem {
	
	private String farmName;

	/**
	 * Default Constructor and Initial Constructor
	 */
	public Vegetable() {
		super();
		farmName = "";
	}
	public Vegetable(int itemCode, String itemName, int itemQuantityInStock, Float itemPrice, Float itemCost, String farmName) {
        super.itemCode = itemCode;
        super.itemName = itemName;
        super.itemQuantityInStock = itemQuantityInStock;
        super.itemPrice = itemPrice;
        super.itemCost = itemCost;
		this.farmName = farmName;
	}

	/**
	  * Displays the all data members in the class.
	  */
	@Override
	public String toString() {
		return super.toString()+" farm supplier: "+farmName;
	}
	
	/**
	 * This method reads from the Scanner object passed in and fills the data 
	 * member fields of the class with valid data
	 * @param scan the Scanner
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scan) {
		super.addItem(scan);
		
		System.out.println("Enter the name of the farm supplier: ");
		 try {
	         scan.nextLine();
	         farmName = scan.nextLine();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid Input");
	        }
     
		return true;
		   
	   }

}