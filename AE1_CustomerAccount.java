/**
 * Student Name: Gemeng Yang
 * Student ID: 2204409
 */

import java.text.*;


//customerAccount class includes all info of customer
public class CustomerAccount {
	
	private int account;	//represents the current balance
	private String name;	//represents the name of the account holder
	
	//constructor
	public CustomerAccount(int account, String name) {
		
		setAccount(account);
		setName(name);
		
	}
	
	
	//mutator method
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setAccount(int account) {
		
		this.account = account;
		
	}
	
	
	//accessor method
	public int getAccount() {
		
		return account;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	
	//The method to process the sale transaction
	public double saleTransaction(Wine w) { 
		
		double amount = w.getQuantity() * w.getPrice();
		amount = Math.round(amount * 100) / 100.0;
		this.account = this.account + (int)(amount * 100);
		return amount;
		
	}
	
	
	//The method to process the return transaction
	public  double returnTransaction(Wine w) { 
		
		double amount = w.getQuantity() * w.getPrice() * (1 - 0.2);  //service charge <- 20%
		amount = Math.round(amount * 100) / 100.0;
		this.account = this.account - (int)(amount * 100);
		return amount;
		
	}
	        
	
	//toString method
	public String toString() {
		
		return "the user name: " + name + ", account: " + account + " ";
		
	}
}
