/**
 * Student Name: Gemeng Yang
 * Student ID: 2204409
 */

import java.text.*;

import javax.swing.*;


//Testing for Wine store
public class AssEx1 {
	
	public static void main(String args[]) {
		
		String name;
		String balance;
		double dbalance = 0;
		int ibalance = 0;
		
		//first dialog box for enter the customer's name
		name = JOptionPane.showInputDialog(null, 
											"Enter the name:", 
												"Input Dialog box", 
													JOptionPane.QUESTION_MESSAGE);
		
		while(name.length() > 30) {
			
			name = JOptionPane.showInputDialog(null, 
												"Length invalidate ! Enter the name:", 
													"Input Dialog box", 
														JOptionPane.QUESTION_MESSAGE);
			
		}	
		
		if(name.isEmpty() || (name.replaceAll(" ","")).isEmpty()) {
			
			System.exit(0);
			
		}
		
		else {
			//second dialog box for enter the customer balance
			//enter the balance should be correct to two decimal places
			balance = JOptionPane.showInputDialog(null, 
													"Enter the balance:", 
														"Input Dialog box", 
															JOptionPane.QUESTION_MESSAGE);
			
			while(!isNumberic(balance) ) {
				 
				balance = JOptionPane.showInputDialog(null, 
														"Wrong format!Enter the balance again:", 
															"Input Dialog box", 
																JOptionPane.CANCEL_OPTION);
				
				if(balance == null) {
					
					System.exit(0);
					
				}
			}		
			
			dbalance = Double.parseDouble(balance);
			ibalance = (int)(dbalance * 100);
			
		}

		CustomerAccount cus = new CustomerAccount(ibalance,name);
		LWMGUI L = new LWMGUI(cus);
		
	}
	
	
	//check the string is a number or not
	//uses ASCII to checks the character is number or not
	public static boolean isNumberic(String s) {
		
		boolean N  = true;
		
		try {
			
			Double.parseDouble(s);
			
		} 
		
		catch(Exception e) {
			
			return false;
			
		}
		
		if((s.length()-s.indexOf('.') > 3) && s.indexOf('.') != -1) {
			
			return false;
			
		}
		
		return true;
		
	}
}

