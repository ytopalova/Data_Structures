import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Assignment #3
 * @author Yuliia Topalova
 * 040-981-104
 * 
 * This class test our program.
 */

public class InventoryItem implements Comparable<InventoryItem>, Iterator<LocalDate> {
	/**
	 * Stores the quontity of items in stock
	 */
	protected int itemQuantityInStock; 
	
	/**
	 * Stores the FoodItem Object item
	 */
	private FoodItem item; 
	
	/**
	 * Stores the expiry date for each item in stock
	 */
	private Deque<LocalDate> expiries; 
	
	/**
	 * Paramaterized Constructor
	 * @param item FoodItem
	 */
	InventoryItem(FoodItem item) {
		this.itemQuantityInStock = 0;
		this.item = item;
		this.expiries = new LinkedList<>();
	}
	
	/**
	 * This method uses the implementation of Comparable to compare 
	 * current itemCode to passed-in itemCode
	 * @param otherItem  item from Inventory
	 * @return itemCode
	 */
	public int compareTo(InventoryItem otherItem) {
		return (this.item.itemCode - otherItem.getItemCode());
	}
	
	/**
	 * This method adds item to the system
	 * @param scan the Scanner
	 * @return true if successful, false if not
	 */
	public boolean addItem(Scanner scan) {
		String temp = "";

		do {
			System.out.print("Enter the quantity for the item: ");
			try {
				temp = scan.next();
				itemQuantityInStock = Integer.parseInt(temp);
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
		
		do {
			System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				scan.nextLine();
				String date = scan.nextLine();
				
				if (date.equalsIgnoreCase("None")) {
					for (int i = 0; i < itemQuantityInStock; i++) {
						expiries.add(LocalDate.MAX);
					}
					break;
				} else {
					try {
						for (int i = 0; i < itemQuantityInStock; i++) {
							expiries.add(LocalDate.parse(date, formatter));
						}
						break;
					} catch (DateTimeParseException e) {
						System.out.println(e.getLocalizedMessage());
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		} while(true);
		
		return true;
	}
	
	/**
	 * This method gets the item code
	 * @return itemCode
	 */
	public int getItemCode() {
		return item.getCode();
	}
	
	/**
	 * This method inputs item code
	 * calls for FoodItem.inputCode(scan);
	 * @param scan the Scanner
	 * @return item code
	 */
	public boolean inputCode(Scanner scan) {
		return item.inputCode(scan);
	}
	
	/**
	 * This method prints the expiry details for the item
	 */
	public void printExpirySummary() {
		System.out.println(this.toString());
		System.out.println("Expiry Details: ");
		
		Iterator<LocalDate> dates = expiries.descendingIterator();
		
		LocalDate temp = dates.next();
		LocalDate temp2 = temp;
		
		System.out.println(temp + ": " + Collections.frequency(expiries,  temp));
		
		while (dates.hasNext()) {
			temp = dates.next();
			if (!temp.equals(temp2)) {
				System.out.println(temp + ": " + Collections.frequency(expiries, temp));
				temp2 = temp;
			}
		}
	}
	
	/**
	 * This method removes all expired items in the class
	 * @param today LocalDate
	 */
	public void removeExpiredItems(LocalDate today) {
		Iterator<LocalDate> dates = expiries.iterator();
		LocalDate temp;
		
		while (dates.hasNext()) {
			temp = dates.next();
			if (temp.compareTo(today) < 0) {
				dates.remove();
				itemQuantityInStock--;
			}
		}
	}
	
	/**
	 * This method updates the items quantity when item 
	 * was bought or sold
	 * @param amount int
	 * @param scan the Scanner
	 * @return true if successful, false if not
	 */
	public boolean updateQuantity(Scanner scan, int amount) {
		itemQuantityInStock += amount;
		if (itemQuantityInStock < 0) itemQuantityInStock = 0;
		
		if (amount < 0) {
			for (int i = 0; i < -1*amount; i++) {
				expiries.removeFirst();
			}
		} else {	
			do {
				System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String date = scan.next();
				
				if (date.equalsIgnoreCase("None")) {
					for (int i = 0; i < amount; i++) {
						expiries.add(LocalDate.MAX);
					}
					break;
				} else {
					try {
						for (int i = 0; i < amount; i++) {
							expiries.add(LocalDate.parse(date, formatter));
						}
						break;
					} catch (DateTimeParseException e) {
						System.out.println(e.getLocalizedMessage());
					}
				}
			} while (true);
		}
		
		return true;
	}
	
	/**
	 * This method converts inventoryItem to string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(item.toString()).append(" qty: ").append(itemQuantityInStock);
		return sb.toString();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LocalDate next() {
		// TODO Auto-generated method stub
		return null;
	}
}
