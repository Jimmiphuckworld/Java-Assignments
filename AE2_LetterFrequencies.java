/** 
 * Student Name: Gemeng Yang
 * Student ID:   2204409
 */

/**
 * Programming AE2
 * Processes report on letter frequencies
 */

public class LetterFrequencies {
	
	/** Size of the alphabet */
	private final int SIZE = 26;
	
	/** Count for each letter */
	private int [] alphaCounts;
	
	/** The alphabet */
	private char [] alphabet; 
												 	
	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
							       0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
								   6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;

	/** Total number of characters encrypted/decrypted */
	private int totChars;
	
	/**
	 * Instantiates a new letterFrequencies object.
	 */
	
	public LetterFrequencies() {
		
		//create alphabet & alphabetCount
		alphabet = new char [SIZE];
		alphaCounts = new int[SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			
			alphabet[i] = (char)('A' + i);
			alphaCounts[i] = 0;
			
		}
		
		totChars = 0;
		
	}
		
	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch) {			
		
		int index = ch - 65;				//the index of alphabet in array
		int count = alphaCounts[index];		//alphabet number								
		alphaCounts[index] = count + 1;  					
		totChars ++;

	}
	
	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC() {
		
		int max = alphaCounts[0];
		
		for(int i = 0; i < SIZE; i++) {
			
			if(max <= alphaCounts[i]) {
				
				max = alphaCounts[i];
				maxCh = alphabet[i];
				
			}
			
		}
		
		double maxpc = (double)max / (double)totChars;
		maxpc = Math.round( (maxpc * 100) * 10 ) / 10.0;
	    return maxpc;  

	}
	
	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport() {
		
		String report = "LETTER ANALYSIS" + " \r\n " + " \r\n" + "Letter   Freq   Freq%   AvgFreq%   Diff" + "\r\n";
		
		for(int i = 0; i < SIZE; i++) {
			
			report = report + "   " + alphabet[i];   						//add the first row
		
			report = report + getSpace(alphaCounts[i]) + alphaCounts[i];	//add the second row
			
			double freq = alphaCounts[i] / (double)totChars;			    //compute the frequencies of each letter
			freq = Math.round( (freq * 100) * 10 ) / 10.0;
			report = report + getSpace(freq) + freq;						//add the third row
			
			report = report + getSpace(avgCounts[i]) + avgCounts[i]; 		//add the fourth row
			
			double diff = freq - avgCounts[i];	
			diff = Math.round( diff * 10 ) / 10.0;
			report = report + getSpace(diff) + diff;						//add fifth row 

			report = report + "\r\n";
			
		}
		
		getMaxPC();
		report = report + "The most frequent letter is " + maxCh + " at " + getMaxPC() + " %"; 

	    return report; 
	    
	}
	
	/**
	 * Returns a space for frequency report
	 * @param int number
	 * @return the String of the space
	 */
	public String getSpace(int num) {
		
		int length = 7 - String.valueOf(num).length();    //length of the number
		String space = "";	
		
		for(int j = 0; j < length; j++) {
			
			space = space + " ";						  //length of space
			
		}
		
		return space;
		
	}
	
	public String getSpace(double num) {
		
		int length = 9 - String.valueOf(num).length();    //length of the number
		String space = "";	
		
		for(int j = 0; j < length; j++) {
			
			space = space + " ";						  //length of space
			
		}
		
		return space;
		
	}
	
}
