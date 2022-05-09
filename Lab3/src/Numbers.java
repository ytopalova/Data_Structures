import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
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

	/**
	 * Sorting array from smallest to biggest value.
	 * @return sorted String of numbers
	 */
	public String sortingAttay() {
		Float[] sortedArray = numbers.clone();
		Arrays.sort(sortedArray);
		
		return "Sorted values: " + convertToString(sortedArray);
	}
	/**
	 * print out the String of float numbers.
	 * @return String of numbers
	 */
	@Override
	public String toString() {
		// TODO Write code for an appropriate toString method
        return "Numbers are: " + convertToString(numbers); //return the string if not empty
    }
	/**
	 * Converting our array of float numbers to String.
	 * @return String of numbers
	 */
	private String convertToString(Float[] list) {
		String values = ""; //Empty String to store the elements of the list
        try {
            for (int i = 0; i < numItems; i++) {
                values += "\n"; 
                values += list[i].toString(); //Add all elements to the string
            }
            values += "\n";
            return values;
        } catch(NullPointerException e) { //return empty string if empty
            return "";
        }	
	}
	/**
	 * This method allow insert multiple values to array.
	 */
	public void addMultiple(Scanner keyboard, boolean fromKeyboard) {

	       if (fromKeyboard) { 
	           System.out.print("How many values do you wish to add? ");
	       }
	       float n = keyboard.nextInt();
	       if((n + numItems) > size ) {
	    	   System.out.print("No room in array to add all values");
		   } else {
		       for (int i = 0; i < n; i++) {
		           addValue(keyboard);
		       }
		   }
	}
	/**
	 * This method read users input with name of the file and 
	 * then read the data in this file.
	 */
	 public void loadDataFromFile(Scanner scanner) {

		   System.out.println("Name of the file to read from:");
           String name = scanner.next();
           try {
               Scanner fileScanner = new Scanner(new File(name));
               int i = fileScanner.nextInt();
               while (fileScanner.hasNextFloat()) {
            	   try {
           				numbers[numItems] = fileScanner.nextFloat();
           				numItems++;
           		   } catch(ArrayIndexOutOfBoundsException e) { 
                       System.out.println("Array is full");
                   } catch(InputMismatchException e) { 
                       System.out.println("Invalid Input");
           		   }
               }
           } catch (FileNotFoundException e) {
                 System.out.println("File not found!");
           } catch (Exception e) {
                 System.out.println("Invalid file format!");
           }
     }
	 /**
		 * This method saves data to the file that user provided. 
		 */
	 public void saveValuesToFile(Scanner scanner) {

	       System.out.println("Name of the file to save to:");
	       String name = scanner.next();
	       try {
	           PrintWriter writer = new PrintWriter(new File(name));
	           writer.println(numItems);
	           for (int i = 0; i < numItems; i++) {
	               writer.println(numbers[i]);
	           }
	           writer.close();
	       } catch (FileNotFoundException e) {
	           System.out.println("File cant be opened!");
	       }
	  }
	 /**
		 * This method clone our array and apply insertion sort on it. 
		 * @return String of sorted numbers
		 */
	 public String InsertionSort() {
		 Float[] InsertionSort = numbers.clone();
		 for (int i=1; i < InsertionSort.length; i++)
		 {
			 float index = InsertionSort[i]; 
			 int j = i - 1;
			 while (j >= 0 && InsertionSort[j] > index)
			 {
				 InsertionSort[j + 1] = InsertionSort[j];				
				 j--;
			 }
			 InsertionSort[j+1] = index;
		 } 
		      
		return "Insertion Sort values: " + convertToString(InsertionSort);
	 }
	
	 /**
		 * This method clone our array and apply selection sort on it. 
		 * @return String of sorted numbers
		 */
	 public String SelectionSort() {
		 Float[] SelectionSort = numbers.clone();
		 
		 for (int i = 0; i < SelectionSort.length-1; i++)
		   {
		      int min = i;
		      for (int j = i+1; j < SelectionSort.length; j++)
		            if (SelectionSort[j] < SelectionSort[min]) min = j;
		      float temp = SelectionSort[i];
		      SelectionSort[i] = SelectionSort[min];
		      SelectionSort[min] = temp;
		} 
		 
		 return "Selection Sort values: " + convertToString(SelectionSort);
	 }
	 
	 /**
		 * This method clone our array and apply bubble sort on it. 
		 * @return String of sorted numbers
		 */
	 public String BubbleSort() {
		Float[] BubbleSort = numbers.clone();
		
		for (int i = (BubbleSort.length - 1); i >= 0; i--)
		   {
		      for (int j = 1; j <= i; j++)
		      {
		         if (BubbleSort[j-1] > BubbleSort[j])
		         {
		              float temp = BubbleSort[j-1];
		              BubbleSort[j-1] = BubbleSort[j];
		              BubbleSort[j] = temp;
		   } } } 
		
		return "Bubble Sort values: " + convertToString(BubbleSort);
	}

	 
}