import java.util.Scanner;

public class Lab4 {
	
	private static Scanner scan;

	/**
	 * isPalindrome method check if a string is a palindrome or not.
	 * @param string String
	 */
	public static boolean isPalindrome(String string) {
		//function to detect isPalindrome
	
	if(string.length() == 0 || string.length() == 1) {
		return true; //it is palindrome
	}
	if(string.charAt(0) == string.charAt(string.length()-1)) {
		return isPalindrome(string.substring(1, string.length()-1));
	}return false; // if there is mismatch, it is not palindrome
	}

	/**
	 * Main method of the class reads input from Scanner
	 * and outputs if word palindrome or not.
	 * @param args String
	 */
	public static void main(String[]args){
	scan = new Scanner(System.in);
	System.out.println("Please enter a palindrome or exit to terminate the program:");
	String word;
	do {
		word = scan.nextLine();
		if(word.equalsIgnoreCase("exit")==false) { 
			//program runs until exit not entered
			if(isPalindrome(word)){
				System.out.println("The word "+word + " IS a palindrome");
			}else{
				System.out.println("The word "+word + " IS NOT a palindrome");
			}
		}
	}
	while(word.equalsIgnoreCase("exit")==false);
	//program exits if exit entered
	}
}