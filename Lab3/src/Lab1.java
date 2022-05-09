import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Yuliia Topalova
 * Student Number: 040-981-104
 * Course: CST8130 - Data Structures     :  CET-CS-Level 3
 * @author/Professor James Mwangi PhD. 
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
public class Lab1 {

	/**
	 * Main method runs the program. Creates new object in 
	 * which we testing all our methods and throw exception
	 * if something is wrong.
	 * @param String args
	 */
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Numbers numbers = new Numbers();
        String choice = "";
        while (!choice.equalsIgnoreCase("10")) {
            displayMainMenu();
            System.out.print("> ");
            choice = input.next();

            switch (choice) {
                case "1":
                    numbers = new Numbers();
                    break;
                case "2":
                    System.out.print("Enter new size of array: ");
                    try{ 
                   int size = input.nextInt();
                    numbers = new Numbers(size);
                    input.nextLine();
                    }catch(NegativeArraySizeException e) {
                    	System.out.println("Size can not be negative");
                    }catch(InputMismatchException e) {
                            System.out.println("Invalid Input");
                    }
            		break;
                case "3":
                    numbers.addValue(input);
                    break;
                case "4":
                    System.out.println(numbers);
                    break;
                case "5":
                     System.out.println("Average is: "+ numbers.calcAverage());
                     System.out.println(numbers.sortingAttay());
                    break;
                case "6": 
                	numbers.addMultiple(input, true);
                	 break;
                case "7":
                	numbers.loadDataFromFile(input);
                	break;
                case "8": 
                	numbers.saveValuesToFile(input);
                	break;
                case "9":
                	System.out.println(numbers.InsertionSort());
                	System.out.println(numbers.SelectionSort());
                	System.out.println(numbers.BubbleSort());
                	break;
                case "10":
                    System.out.println("Thanks for using this App.");
                    break; 
                default:
                    System.err.println("Entered wrong choice...Try Again!");
                    break;
            }
        }
    }
	
	/**
	 * This method displays main menu for the user.
	 */
    public static void displayMainMenu() {
        System.out.println("Please select one of the following:\n" +
                "1: Initialize a default array\n" +
                "2: To specify the max size of the array\n" +
                "3: Add value to the array\n" +
                "4: Display values in the array\n" +
                "5: Display the average of the values\n" +
                "6: Enter multiple values\n" +
                "7: Read values from file\n" +
                "8: Save values to file\n" +
                "9: Sort the array\n"+
    			"10: To Exit");
    }
}
