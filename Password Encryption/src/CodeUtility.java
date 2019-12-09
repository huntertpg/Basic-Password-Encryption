/* This class is used to mainly encrypt passwords using a rotation algorithm based on the
 * passwords length. The passwords valid char set is in octal value of 33-126. As stated
 * before the rotation value is based on how long the password is and if the rotation value
 * is larger than 126 or smaller than 33 the values will be set to the other end of the charset
 * to not throw any errors or unknown char. It takes in a string and converts the string into an array
 * of chars and then rotating each character byte by the rotation value
 * 
 */
public class CodeUtility {
	
	//Create a static function that takes in plain text to be encrypted
	public static String encrypt(String plainText) {
		
		//Declare and initialize a string builder
		StringBuilder sb = new StringBuilder();
		
		//Declare and init a array of characters and set it to each character in the plain text using the stringbuilder
		char[] password = plainText.toCharArray();
		
		//Set the rotation value to the amount of characters in plain text
		int rot = plainText.length();
		
		//Declare a overflow variable for when the rotation value is larger than 126 or less than 33
		int overflow;
		
		//Loop through the array of characters in the password
		for (char ch : password) {
			
			//if the character byte plus the rotation value is larger then 126
			if (ch + rot > 126) {
				
				//add the rotation value to the character byte value and set the overflow value to that
				overflow = ch + rot;
				
				//subtract 126 from the overflow
				overflow -= 126;
				
				//set the character byte to a cast of that start of the ASCII char set (33) plus the overflow
				ch = (char) (33 + overflow);
				
				//add the modified character value to the stringbuilder
				sb.append(ch);
				
			} else {
				//if the rotation value isn't larger than 126 than simply add it to the character
				ch += rot;
				
				//Append the modified character to the string builder
				sb.append(ch);
			}

		}
		
		//return the encrypted password
		return sb.toString();
	}
	
	//Create a public function that decrypts the password that takes in the encrypted text
	public static String decrypt(String cipherText) {
		
		//Create a string builder to help with taking apart the text
		StringBuilder sb = new StringBuilder();
		
		//create an array of characters for each character in the encrypted password
		char[] encryptedPassword = cipherText.toCharArray();
		
		//Create a rotation value thats equal to the length of the encrypted password
		int rot = cipherText.length();
		
		//Create an overflow variable to handle values under 33
		int overflow;
		
		//Loop through the characters in the encryptedPassword
		for (char ch : encryptedPassword) {
			
			//if the character byte minus the rotation value is less than 33 start at 126
			if (ch - rot < 33) {
				
				//Set the overflow variable to the value of the character value minus the rotation value
				overflow = (ch - 1) - rot;
				
				//Subtract the overflow value to 33 minus overflow to get the amount that needs to be rotated
				overflow = 33 - overflow;
				
				//set the character value to a cast of 127 minus the rotation value
				ch = (char) (127 - overflow);
				
			//If the rotation value is within the char set range simply minus the character by the rotation value
			} else {
				ch -= rot;
			}
			
			//add the character to the string builder
			sb.append(ch);

		}
		
		//return the dectypted password
		return sb.toString();
	}

}
