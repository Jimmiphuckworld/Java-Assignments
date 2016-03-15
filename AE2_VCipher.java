/** 
 * Student Name: Gemeng Yang
 * Student ID:   2204409
 */

/**
 * Programming AE2
 * Class contains Vigenere cipher and methods to encode and decode a character
 */

public class VCipher {
	
	private char [] alphabet;   //the letters of the alphabet
	private final int SIZE = 26;
	private char[][] cipher;
	
	/** 
	 * The constructor generates the cipher
	 * @param keyword the cipher keyword
	 */
	public VCipher(String keyword) {
		
		//create alphabet
		alphabet = new char [SIZE];
		
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		//create cipher
		cipher = new char[keyword.length()][SIZE];					//initialize the cipher array;
		
		for(int i = 0; i < keyword.length(); i++) {
			
			int count = 0;	
			
			for(int j = 0; j < SIZE; j++) {
				
				if(j < ('Z'-keyword.charAt(i) + 1)) {		
					
					cipher[i][j] = (char)(keyword.charAt(i)+ j);	//create first part of the cipher letter
					
				}
				
				else {												//create the second part of the cipher letter
					
					cipher[i][j] =(char)('A' + count);
					count++;
					
				}
				
			}
				
		}
		
		print(keyword);
		
	}

	//print the cipher array ;
	public void print(String k) {
		
		System.out.println("----The Vigenere cipher array for keyword \"" + k + "\"" + "-----");
		
		for(int i = 0; i < k.length(); i++) {
			
			for(int j = 0; j < SIZE; j++) {
				
				System.out.print(cipher[i][j] + " ");
				
			}
			
			System.out.println();
			
		}
		
	}
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded,row the row number of the 2D array
	 * @return the encoded character
	 */	
	public char encode(char ch, int row) {
		
		for(int i = 0; i < SIZE; i++) {
			
			if(ch == alphabet[i]) {
				
				return cipher[row][i];
				
			}
			
		}
		
		return ' ' ;  
		
	}
	
	/**
	 * Decode a character
	 * @param ch the character to be decoded,row the row number of the 2D array
	 * @return the decoded character
	 */  
	public char decode(char ch,int row) {
		
		for(int i = 0; i < SIZE; i++) {
			
			if(ch == cipher[row][i]) {
				
				return alphabet[i];
				
			}
			
		}
		
		return ' ' ; 
		
	}
	
}
