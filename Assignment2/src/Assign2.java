import java.util.Scanner;
/**
 * Assignment #2
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class test our program.
 */

public class Assign2 {
	
		/** Main method of the program creates an object and runs 
		 * all cases on it.
		 * @param args String
		 */
	   public static void main(String[] args) {
	      
	       	Scanner input = new Scanner(System.in);
	       	Inventory inventory = new Inventory();
	       	String choice = "";
	    		while (choice != "8") {
	    				displayMainMenu();
	    	            System.out.print("> ");
	    				if (input.hasNext()) {
	    					choice = input.next();
	    					switch(choice)
	    					{
	    					case "1": 
	    						if(!inventory.addItem(input))
	    							System.out.println("Error...could not add item");
	    						break;
	    					case "2": 
	    						System.out.println(inventory);
	    						break;
	    					case "3": 
	    						if(!inventory.updateQuantity(input, true))
	    							System.out.println("Error...could not buy item");
	    						break;
	    					case "4": 
	    						if(!inventory.updateQuantity(input, false))
	    							System.out.println("Error...could not sell item");
	    						break;
	    					case "5":
	    						inventory.searchForItem(input);
	    						break;
	    					case "6":
	    						if (!inventory.saveToFile(input)) {
	    							System.out.println("Error, could not save inventory");
	    						}
	    						break;
	    					case "7":
	    						if (!inventory.openFile(input)) {
	    							System.out.println("Error, could not read from file");
	    						}
	    						break;
	    					case "8": 
	    						System.out.println("Exiting...");
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
	  
    	
	   
	   /** Displays the main menu to the console
		 */
	   public static void displayMainMenu() {
	        System.out.println("Please select one of the following:\n" +
	                "1: Add Item to Inventory\n" +
	                "2: Display Current Inventory\n" +
	                "3: Buy Item(s)\n" +
	                "4: Sell Item(s)\n" +
	                "5: Search for Item\n" + 
	                "6: Save Inventory to File\n"+
	                "7: Read Inventory from File\n"+
	                "8: To Exit\n");
	      
	    }	   
	
}


