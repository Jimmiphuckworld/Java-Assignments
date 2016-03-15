/** 
 * Student Name: Gemeng Yang
 * Student ID:   2204409
 */

/**
 * Programming AE2
 * Contains monoalphabetic cipher and methods to encode and decode a character.
 */

public class MonoCipher {
	
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;
	
	/** The cipher array. */
	private char [] cipher;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	public MonoCipher(String keyword) {
		
		//create alphabet & cipher
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			
			alphabet[i] = (char)('A' + i);
			
		// create first part of cipher from keyword
		cipher = new char [SIZE];
		
		for(int i = 0; i < keyword.length(); i++) {
			
			cipher[i] = keyword.charAt(i);
			
		}

		// create remainder of cipher from the remaining characters of the alphabet
		
		boolean flag;										//check the letter is the same with the keyword or not 
		int count = keyword.length();						//set the letters initial position
		
		for(int i = 0; i < SIZE; i++) {						//loop 26 alphabets
			
			flag = true;									//set flag is true 
			
			for(int j = 0; j < keyword.length(); j++) {		//loop keyword letters
				
				if(keyword.charAt(j) == ((char)('Z' - i))) {
					
					flag = false;							//if the keyword letter is the same with new alphabet,change the flag to false
					
				}
				
			}
			
			if(flag) {										//add all letters to cipher
				
				cipher[count] = (char)('Z' - i);
				count++;
				
			}
			
		}

		// print cipher array for testing and tutors
		print(keyword);
		
	}
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch) {
		
		for(int i = 0; i < SIZE; i++) {
			
			if(ch == alphabet[i]) {
				
				return cipher[i];
				
			}
			
		}
		
		return ' ' ;
		
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch) {
		
		for(int i = 0; i < SIZE; i++) {
			
			if(ch == cipher[i]) {
				
				return alphabet[i];
				
			}
			
		}
		
		return ' ' ; 
		
	}
	
	//print the cipher
	public void print(String k) {
		
		System.out.println("--The Monalphabetic cipher array for keyword \"" + k + "\"" + "--");
		
		for(int i = 0; i < SIZE; i++) {
			
			System.out.print(cipher[i] + " ");
			
		}
		
		System.out.println();
		
	}
	
}
