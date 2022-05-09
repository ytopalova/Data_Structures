import java.util.ArrayList;
import java.util.Scanner;

public class Lab8 {

	private static Scanner scan;

	public static void main(String[] args) {
		
		//creating an arraylist of size 100
		ArrayList<String> dataitems = new ArrayList<String>(100);
		for (int i = 1; i <= 100; ++i)
			dataitems.add("");
		
		char input;
		scan = new Scanner(System.in);
		while (true) { //infinite loop with menu
			System.out.println("1. Add ");
			System.out.println("2. Search ");
			System.out.println("3. Exit ");
			System.out.print(">");
			input = scan.next().charAt(0);
			if (input == '3')
				break;					//exit the program
			// System.out.println(input);
			switch (input) {
			case '1':
				System.out.println("Enter string :"); //inserting string to list
				String string = scan.next();
				int a, b = 0;						//initialize int values
				a = (int) (string.charAt(0)); 		//the first letter of the string to be added and converted
				if (string.length() >= 2)			//if the string's length is longer then or equal to 2 (greater then 1 char)
					b = (int) (string.charAt(1));	//added to the second letter of the input and converted
				int index = (a + b) % 100;
				if (dataitems.get(index) == "") {		//if the index chosen is empty, add newString to it
					dataitems.add(index, string);
					System.out.println("Entered at " + index);
				} else {
					int c = 1;
					for (int i = index; i <= 99; ++i) {	//make sure the result is a number between 0 and 99
						if (dataitems.get(i) == "") {
							dataitems.add(i, string);
							c = 0;
							break;
						}
					}
					if (c == 1)				//error message that string can not be added
						System.out.println("String cannot be added");
				}
				break;
			case '2':
				System.out.println("Enter string: ");
				String string1 = scan.next();
				int a1, b1 = 0;						//initialize int values
				a1 = (int) (string1.charAt(0));		//the first letter of the string to be added
				if (string1.length() >= 2)			//if it's longer then 1 character
					b1 = (int) (string1.charAt(1)); //added to the second letter of the input
				
				int index1 = (a1 + b1) % 100;		
				if (string1.equals(dataitems.get(index1))) //search for a string in the array dataItems
					System.out.println("String found at index " + index1 + ".");
				else {
					int c1 = 1;
					for (int i = index1; i <= 99; ++i) { 	////make sure the result is a number between 0 and 99
						if (string1.equals(dataitems.get(i))) {
							System.out.println("String found at index " + index1 + ".");
							c1 = 0;
							break;
						}
					}
					if (c1 == 1)			//error message string not found
						System.out.println("String not found.");
				}
				break;
			default:
				System.out.println("Incorrect Choice ");
			}
		}
	}
}
