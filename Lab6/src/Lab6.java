import java.util.LinkedList;
import java.util.Scanner;


public class Lab6 {
	/**
	 * The main method of our program tests the code calling checkBalanced
	 * method and output if our expression is balanced.
	 * If input exit program will quit.
	 */
	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);      //initialize the scanner
		String expression = "";						//initialize the user input 
		do {										//start the loop
			System.out.print("Enter an expression(or exit to terminate): ");
			expression = scan.nextLine().trim();	//read input
				if(expression.equalsIgnoreCase("Exit")) {
					System.out.println("\nTerminating...\n");
					System.exit(0);					//quit if exit with exit output
				}else {								//check expression balanced or not
					if(checkBalanced(expression))
						System.out.println("The expression is balanced.");
					else
						System.out.println("The expression is NOT balanced.");
					}
					System.out.println();
		}while(!expression.equalsIgnoreCase("Exit"));
	}
	/**
	 * Method checks mismatch
	 * @param c1
	 * @param c2
	 * @return true or false
	 */
	private static boolean isMatchedPair(char c1, char c2) {
		
		if (c1 == '(' && c2 == ')') {
			return true; //return true is open "(" has closed one
		}else if (c1 == '{' && c2 == '}') {
			return true; //return true is open "{" has closed one
		}else{
			return false; //return false in other cases
		}
	}
	  
	/**
	 * Method checks if expression is balanced
	 * @param String expression
	 * @return true if String input is balanced
	 * and false if String input is not balanced
	 */
	public static boolean checkBalanced(String expression) {
	
		LinkedList<String> llist = new LinkedList<>();	   //initialize LinkedList
		for(int i = 0; i < expression.length(); i++){		//looping every character of the string
			if(expression.charAt(i) == '(' || expression.charAt(i) == '{') {
				llist.addFirst(expression.charAt(i) + ""); ///if open bracket is scanned, add it to the linklist
				continue;
			}else if(expression.charAt(i) == ')' || expression.charAt(i) == '}'){ ////if  close bracket is scanned
				if(llist.isEmpty())							//if the list is empty check
					return false;
				else if(!isMatchedPair(llist.removeFirst().charAt(0), expression.charAt(i)))
					return false;
			}
		}
	  
		if(llist.isEmpty()) //If no imbalances are found, return true if it's not empty - return false
			return true;
		else
			return false;
	}

}
