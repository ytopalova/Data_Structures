import java.util.Arrays;
import java.util.Collections;
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
public class Numbers {
	/**
	 * Stores Float values.
	 */
	private Float[] numbers;
	
	/**
	 * Store the number of items currently in the array.
	 */
	private int numItems;
	private int size;

	/**
	 * Default Constructor
	 */
	public Numbers() {
		// TODO Write code here to initialize a "default" array since this is the default constructor
        numbers = new Float[0];
        size = 0;
        numItems = 0;
	}

	/**
	 * Constructor that initializes the numbers array.
	 * @param size - Max size of the numbers array
	 */
	public Numbers(int size) {
		// TODO Write code here to initialize the numbers array of max 'size'
		this.size = size;
        numbers = new Float[size];
        numItems = 0;
	}
	
	/**
	 * Adds a value in the array
	 * @param keyboard - Scanner object to use for input
	 */
	public void addValue(Scanner keyboard) {
		// TODO Write code here to add the values in the array
		
		System.out.print("Enter value: ");
		try {
	    numbers[numItems] = keyboard.nextFloat();
		numItems++;
		}catch(ArrayIndexOutOfBoundsException e) { 
            System.out.println("Array is full");
        }catch(InputMismatchException e) { 
            System.out.println("Invalid Input");
		}
    }
	
	/**
	 * Calculates the average of all the values in the numbers array.
	 * @return float value that represents the average
	 */
	public float calcAverage() {
		// TODO Write code to return the average of the values in the array
		float avr = 0;
		if(numItems != 0) {
	    for (int i = 0; i < numItems; i++) {
	           avr += numbers[i];//calculating sum of the numbers in the array
	       }
	       return (float) + avr / numItems;//calculating aver
	 }return 0.0f;
	}
	
	public String createYourOwnMethod() {
		Float[] sortedArray = numbers.clone();
		Arrays.sort(sortedArray);
		
		return "Sorted values: " + convertToString(sortedArray);
	}
	
	@Override
	public String toString() {
		// TODO Write code for an appropriate toString method
        return "Numbers are: " + convertToString(numbers); //return the string if not empty
    }
	
	private String convertToString(Float[] list) {
		String values = ""; //Empty String to store the elements of the list
        try {
            for (int i = 0; i < numItems; i++) {
                values += "\n" + list[i].toString(); //Add all elements to the string
            }
            values += "\n";
            return values;
        } catch(NullPointerException e) { //return empty string if empty
            return "";
        }
		
	}

}
