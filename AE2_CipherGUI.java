/** 
 * Student Name: Gemeng Yang
 * Student ID:   2204409
 */

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.io.*;

import java.util.*;

public class CipherGUI extends JFrame implements ActionListener {
	
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;
	
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI() {
		
		this.setSize(400, 150);
		this.setLocation(100, 100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
		
	}
	
	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents() {
		
		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.gray);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);
		
		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.gray);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);
		
		//bottom panel is green and contains 2 buttons
		bottom = new JPanel();
		bottom.setBackground(Color.gray);
		
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
		
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
		
		//add the top panel
		this.add(bottom, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e) {

		//check keyword and filename 
		if(getKeyword() && processFileName()) {
			
			//check which button has been pressed
			boolean vigenere = true;    //press vigenereButton
			
			if (e.getSource().equals(monoButton)) {    //press monoButton 
				
				vigenere = false;
				
			}
			
			try {
				
				processFile(vigenere);
				
			} 
			
			catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
				
			}  
			
		}
		
	}
	
	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword() {
		
		boolean result = false;
		
		if(keyField.getText().isEmpty()) {											  //check content
			
	    	error("The Keyword or file name is empty!", keyField);	
	    	
		}
		
		else if (keyField.getText().length() > 26) {								  //check length
			
			error("The keyword should be less than 26 letters!", keyField);
			
		}
		
		else if(!isUpperCase()) {
			
	    	error("The Keyword should be upper case letter of alphabet!", keyField);  //check upper case
	    	
		}
		
		else if(!isrepeat()) {
			
	    	error("The Keyword should be no repeat letter!", keyField);			      //check repeat
	    	
		}
		
		else{result = true;}
		
		return result;
		
	}
	
	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName() {
		
		String fileName = messageField.getText();
		
		if(fileName.isEmpty()) {    //check content
			
			error("The file name is empty!", messageField);
			return false;
			
		}
		
		String fileName_suffix = fileName.substring((fileName.length()-1), (fileName.length()));
		String suffix1 = "C";
		String suffix2 = "P";
		
		if(fileName_suffix.equals(suffix1)) {    //check suffix is C
			
			return true;
			
		}
		
		else if(fileName_suffix.equals(suffix2)) {    //check suffix is P
			
			return true;
			
		}
		
		else{error("The file name is invaild format!", messageField); return false;}
		 
	}
	
	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful
	 * @throws FileNotFoundException 
	 */
	private boolean processFile(boolean vigenere) throws FileNotFoundException {	
		
		LetterFrequencies l = new LetterFrequencies();										//new LetterFrequencies instance
		File f = new File(messageField.getText() + ".txt");									//original file 
		Scanner scan = null;																//read file
		File nf1;																			//new file for encode or decode
		File nf2;																			//new file for letter analysis
			
		String fileName = messageField.getText();											//get file name
		String indicator = fileName.substring((fileName.length()-1), (fileName.length()));	//get indicator letter:C for decode and P for encode
		String name =fileName.substring(0, (messageField.getText().length()-1));		 	//get the file name
		
		nf2 =new File (name + "F.txt");														//set new file's name for letter analysis
		
		if(indicator.equals("P")) {
			
			nf1 = new File(name + "C.txt");													//set new file's name for encode	
			
		}
		
		else nf1 = new File( name + "D.txt");												//set new file's name for decode
		
		PrintWriter fw = new PrintWriter(nf1);												//write into file
		
		if(vigenere) {	
			
			operationVc(scan, f, nf1, nf2, l, fw, indicator);
			
		}
		
		else {
			
			operationMo(scan, f,nf1, nf2, l, fw, indicator);
			
		}
		
	    return true; 
	    
	}
	
	/** 
	 * operation of MonoCipher
	 * read each line from the original file and then read each char from the line
	 * then encrypt and written to the output text file
	 * @param  ,sanner scan for reading from the file ;
	 * 			File f for original file;
	 * 			File nf for  the new file
	 * 			File ff for the new file of letter analysis
	 * 			LetterFrequencies l for the LetterFrequencies instance
	 * 			printWriter pw 
	 * 			String indicator for whether encode or decode
	 * @return whether the I/O operations were successful
	 * @throws FileNotFoundException 
	 */
	private void operationMo(Scanner scan, File f, File nf, File ff, LetterFrequencies l, PrintWriter fw,String indicator) throws FileNotFoundException {
		
		MonoCipher mo = new MonoCipher(keyField.getText());			//new MonoCipher() 
		String line = null;											//read the each line from the file
		char ch;													//store the char after encode or decode
		
		try {
			
			scan = new Scanner(f);	
			
			while(scan.hasNextLine()) {
				
				line = scan.nextLine();
				
				for(int i = 0; i < line.length(); i++) {			//check the char is letter or not
					
					if(line.charAt(i) < 65 || line.charAt(i) > 90) {
						
						fw.append(line.charAt(i));
						fw.flush();
						
					}
					
					else {
						
						if(indicator.equals("P")) {					//encode 
							
							ch = mo.encode(line.charAt(i));			//encode the char
							l.addChar(ch);							//add the letter to the letterFrequencies class,processing for write in to the file
							fw.append(ch);							//write into file
							
						}
						
						else {
							
							ch = mo.decode(line.charAt(i));
							l.addChar(ch);							//add the letter to the letterFrequencies class,processing for write in to the file
							fw.append(ch);							//write into file
							
						} fw.flush();								//flush
						
					}
					
				}
				
			fw.write("\r\n");	
			
			}
			
			//write into file of letter analysis		
			fw = new PrintWriter(ff);
			fw.write(l.getReport());
			fw.close();
			System.exit(0);
			
		}
		
		catch(Exception e) {
			
			error("Not find file!", messageField);					//display the error of not file
			
		}
		
	}
	
	/** 
	 * operation of VCipher
	 * read each word from the original file and then read each char from the word
	 * then encrypt it and written to the output text file
	 * @param  ,sanner for reading from the file ;
	 * 			File for original file;
	 * 			File nf for  the new file
	 * 			File ff for the new file of letter analysis
	 * 			LetterFrequencies l for the LetterFrequencies instance
	 * 			PrintWriter pw for write into the file
	 * 			String indicator for whether encode or decode
	 * @return whether the I/O operations were successful
	 * @throws FileNotFoundException 
	 */
	private void operationVc(Scanner scan, File f, File nf, File ff, LetterFrequencies l, PrintWriter fw, String indicator) throws FileNotFoundException {
		
		String key = keyField.getText();
		VCipher vc = new VCipher(key);								//new MonoCipher()
		String line = null;											//read the each line from the file
		char ch;													//store the char after encode or decode
		
		try {
			
			int count = -1; //count letter except space
			scan = new Scanner(f);	
			
			while(scan.hasNextLine()) {	
				
				line = scan.nextLine();
				
				for(int i = 0; i < line.length(); i++) {
					
					count++;
					
					if(line.charAt(i) < 65 || line.charAt(i) > 90) {		//check the char is letter or others
						
						count--;
						fw.append(line.charAt(i));
						fw.flush();
						
					}
					
					else {
						
						if(indicator.equals("P")) {
							
							ch = vc.encode(line.charAt(i), count%key.length()); //encode the char
							l.addChar(ch);										//add the letter to the letterFrequencies class,processing for write in to the file
							fw.append(ch);										//write into file
							
						}
						
						else {
							
							ch = vc.decode(line.charAt(i), count%key.length()); //encode the char
							l.addChar(ch);										//add the letter to the letterFrequencies class,processing for write in to the file
							fw.append(ch);										//decode the char
							
						} fw.flush();
						
					}
					
				}
				
			fw.write("\r\n");
			
			}
			
			//write into file of letter analysis		
			fw = new PrintWriter(ff);
			fw.write(l.getReport());
			fw.close();
			System.exit(0);
			
		}
		
		catch(Exception e) {
			
			error("Not find file!", messageField);					//display the error of not file
			
		}
		
	}
	
	//check the input keyword is upper letter or not 
	private boolean isUpperCase() {	
		
		String keyword = keyField.getText();
		
		for(int i = 0; i < keyword.length(); i++) {
			
			if(keyword.charAt(i) < 65 || keyword.charAt(i) > 90 ) {
				
				return  false;
				
			}
			
		}
		
		return true;
		
	}
	
	//check the boolean input keyword has repeat letter or not 
	private boolean isrepeat() {
		
		String keyword = keyField.getText();
		
			for(int i = 0; i<keyword.length(); i++) {
				
				for(int j = i + 1; j<keyword.length(); j++) {
					
					if(keyword.charAt(i) == keyword.charAt(j)) {
						
						return false;
						
					}
					
				}
				
			}
		
		return true;
		
	}
	
	/**
	 * display error
	 * @param String:error message
	 * @param JTextField: the JTextField that need to set null 
	 * @return void
	 */
	private void error(String msg, JTextField jtext) {
		
		JOptionPane.showMessageDialog(null, msg);
    	jtext.setText(null);
    	
	}
	
}
