/**
 * Student Name: Gemeng Yang
 * Student ID: 2204409
 *
 * The wine class that include all wine info.
 */

public class Wine {
	
	String name;	//represents the name of the wine
	double price;	//represents the price
	int quantity;	//represents the quantity
	
	
	//constructor
	public Wine(String name,double price,int quantity) {
		
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		
	}
	
	
	//mutator methods
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setPrice(double price) {
		
		this.price = price;
		
	}
	
	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
		
	}
	
	
	//accessor methods
	public String getName() {
		
		return name;
		
	}
	
	public double getPrice() {
		
		return price;
		
	}
	
	public int getQuantity() {
		
		return quantity;
		
	}
	
	
	//toString method
	public String toString() {
		
		return "wine name: " + name + ", price: " + price + ", quantity: " + quantity + " ";
		
	}
}
