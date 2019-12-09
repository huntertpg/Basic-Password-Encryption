/* This is the main class for the program. This class class displays a menu to the user
 * then the user picks from the menu to either create a password, login, or exit the program.
 * After the user enters a password the program checks to see if it's a valid password, if not it prompts
 * the user to enter a proper password. After the password returns true for valid it encrypts itself and stores
 * the encrypted version of the password as the original password. The user also has the ability to login with this created
 * password as well as exit the program. The login functionality is explained in the password class.
 * 
 */

import java.util.Scanner;

public class Main {

	// Create a static password object
	private static Password password;

	// Call the main Java function
	public static void main(String[] args) {

		// Create a scanner for taking in user inputs
		Scanner input = new Scanner(System.in);

		// Create a boolean for weather the program should exit or not
		boolean exit = false;

		// Create a boolean to see if there has been a password set
		boolean passwordSet = false;

		// Create a userInput string so that there aren't multiple input calls clogging
		// up user input
		String userInput;

		// Loop until exit is true
		while (!exit) {

			// Prompt the user to enter a numeric value and display the options
			System.out.println("Please Choose an option from below by entering its numeric value:");
			System.out.println("1. Set Password");

			// if the password is set prompt the user that logging in will be locked until a
			// password is set
			if (!passwordSet) {
				System.out.println("2. Password Login(Locked until password is set)");

				// If a password is set get rid of the locked message
			} else {
				System.out.println("2. Password Login");
			}

			// Print out the exit option
			System.out.println("3. Exit");

			// Grab user input and set it to the userInput variable
			userInput = input.next();

			// If the user input is 1 go into the password set dialog
			if (userInput.equals("1")) {

				// Print out to the user the requirements for a valid password
				System.out.println("Please enter a password that contains at least an upercase and lowercase letter"
						+ " as well as a number 0-9, and one of the following symbols: !, -, +, with at least 8 characters");

				// Set the password so it can be called to see if it's a valid password
				password = new Password(input.next());
				
				// While the password isn't valid prompt the user to enter a new one
				while (!password.isValid(password.getPassword())) {
					System.out.println("Please enter a valid password");

					// Have a user input a new password and set it to the password object
					password = new Password(input.next());
				}

				// Once Validation is complete prompt the user that the password has been set
				System.out.println("Successfully Set Password!");
				// Set the passwordSet boolean to true so that the login dialog is unlocked
				passwordSet = true;

				// If the user inputs 2 and a password has been set go into the login dialog
			} else if (userInput.equals("2") && passwordSet == true) {

				// Prompt the user to input a password
				System.out.println("Please Enter Password:");

				// Check to see if the password matches the encrypted version of the password
				if (password.match(input.next())) {

					// If it matches print to the user that they have logged in
					System.out.println("Login Success!");
				} else {

					// If it doesn't match prompt them that it has failed
					System.out.println("Login Failed!");
				}

				// If the user enters 2 to log in but a password isn't set prompt the user to
				// set a password
			} else if (userInput.equals("2") && !passwordSet) {

				System.out.println("Password is not set! Please set password before trying to login.");

				// If the user inputs 3 set exit to true to exit the programs main loop
			} else if (userInput.equals("3")) {
				exit = true;
			}
		}

	}

}
