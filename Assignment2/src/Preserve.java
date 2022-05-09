import java.util.Scanner;
/**
 * Assignment #2
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class inherits FoodItem and add own specifications
 */
public class Preserve extends FoodItem {
	
	private int jarSize;

	/**
	 * Default Constructor and Initial Constructor
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}
	public Preserve(int itemCode, String itemName, int itemQuantityInStock, Float itemPrice, Float itemCost, int jarSize) {
		super.itemCode = itemCode;
        super.itemName = itemName;
        super.itemQuantityInStock = itemQuantityInStock;
        super.itemPrice = itemPrice;
        super.itemCost = itemCost;
        this.jarSize = jarSize;
	}
	
	/**
	  * Displays the all data members in the class.
	  */
	@Override
	public String toString() {
		return super.toString()+" size: "+jarSize+"mL";
	}
	
	/**
	 * This method reads from the Scanner object passed in and fills the data 
	 * member fields of the class with valid data
	 * @param scan the Scanner
	 * @return true if program successfully reads in all fields, otherwise returns false
	 */
	public boolean addItem(Scanner scan) {
		super.addItem(scan);
//		System.out.print("Enter the size of the jar in millilitres: ");
//		jarSize = scan.nextInt();
		while(!false){
			System.out.print("Enter the size of the jar in millilitres: ");
			if(scan.hasNextInt()){
				jarSize = scan.nextInt();
				if(jarSize < 0){
					System.out.println("Invalid input");
					jarSize = 0;
				}else 
					return true;							
				}
			}
   
		}
}
