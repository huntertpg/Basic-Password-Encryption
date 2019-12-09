/* The main function of this class is to create a password object that not only stores the password
 * but users the CodeUtil class to encrypt it and see if the user has entered a valid password.
 * This also has a function that checks to see if the password a user enters to login matches the 
 * Encrypted version of the password.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
	
	//Create a private string to store the password
	private String pword;
	
	//Create an instance of CodeUtility for later use
	private CodeUtility cu;

	//Constructor of the Password Object that takes in a user password
	public Password(String pw) {
		
		//Set the global password variable to the entered password
		this.pword = pw;
		
		//Declare CodeUtility
		cu = new CodeUtility();
	}
	
	//Create a function that checks if a password is valid or not that takes in a password
	public boolean isValid(String pw) {
		
		//Create a pattern containing a valid set of numbers
		Pattern numberList = Pattern.compile("[0-9]");
		
		//Create a matcher that takes in the numbers for later use
		Matcher matcher = numberList.matcher(pw);
		
		//check if the password contains a !, -, or +, as well as if it contains both upper and lower case letters 
		//and at least 8 characters but no more than 15
		if (pw.contains("!") | pw.contains("-") | pw.contains("+") && matcher.find() && !pw.equals(pw.toLowerCase())
				&& !pw.equals(pw.toUpperCase()) && pw.length() >= 8 && pw.length() <= 15) {
			
			//if the password is valid encrypt the password using the CodeUtility class
			this.pword = cu.encrypt(pw);
			
			//Return true stating the password is valid
			return true;
		//If the password isn't valid return falses
		} else {
			return false;
		}

	}
	
	//Create a function that returns true or false if the user enters a password to login matches the password stored
	public boolean match(String attempt) {
		
		//if the password they entered encrypted matches the password stored return true
		if (cu.encrypt(attempt).equals(pword)) {
			return true;
		
		//If the password entered doesn't match return false
		} else {
			return false;
		}

	}

	//This simply retrieves the password
	public String getPassword() {
		return this.pword;
	}

}
