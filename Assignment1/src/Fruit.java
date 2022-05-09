import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Assignment #1
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class inherits FoodItem and add own specifications
 */
public class Fruit extends FoodItem  {
	
	private String orchardName;

		/**
		 * Default Constructor
		 */
		public Fruit() {
			super();
			orchardName = "";
		}

		/**
		  * Displays the all data members in the class.
		  */
	   @Override
		public String toString() {
			return super.toString()+" orchard supplier: "+orchardName;
		}
	   
	   /**
		 * Reads from the Scanner object passed in and fills the data 
		 * member fields of the class with valid data
		 * @param scan the Scanner
		 * @return true if program successfully reads in all fields, otherwise returns false
		 */
	   @Override
	   	public boolean addItem(Scanner scan) {
		   super.addItem(scan);
		   
		   System.out.println("Enter the name of the orchard supplier: ");
		   try {
	            scan.nextLine();
	            orchardName = scan.nextLine();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid Input");
	        }
           
		   return true;
		   
	   }
}
