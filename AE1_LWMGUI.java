/**
 * Student Name: Gemeng Yang
 * Student ID: 2204409
 */

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;


//GUI class 
public class LWMGUI extends JFrame {
	
	//all attributes
	private JTextField name, quantity, price;
	private JButton process_sale,process_return;
	private JPanel p1, p2, p3, p4;
	private JLabel wine_name, amount, balance;
	private CustomerAccount cus;
	private String text_amount = "                    ";
	private String text_balance = "                    ";
	
	//constructor method
	public LWMGUI(CustomerAccount cus) {
		
		super("Lilybank Wine Merchants: " + cus.getName());				//set title of the GUI
		this.cus = cus;
		double temp =(double)cus.getAccount() / 100.00;					// change the data type;
		text_balance = " £ " + String.valueOf(isCredit(temp)) + "              ";	//change double to String 
		
		//set all default elements on GUI
		name = new JTextField(15);
		quantity = new JTextField(8);
		price = new JTextField(8);
		wine_name = new JLabel("");
		amount = new JLabel(text_amount);
		balance = new JLabel(text_balance);
		
		//add a border outside the JLabel
		amount.setBorder(BorderFactory.createLineBorder(Color.blue));
		balance.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		process_sale = new JButton("Process Sale");
		process_return = new JButton("Process Return");
		
		//add an event click handler
		process_sale.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("the button of Process Sale has been pressed");
				saleAct();		//call the method for calculate of the transaction 
				
			}
			
		});
		
		//add an event click handler
		process_return.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("the button of Process Return has been pressed");
				returnAct();	//call the method for calculate of the transaction
				
			}
			
		});
		
		
		// set layout
		p1 = new JPanel();
		p1.add(new JLabel("Name: ", JLabel.RIGHT));
		p1.add(name);
		p1.add(new JLabel("Quantity: ", JLabel.CENTER));
		p1.add(quantity);
		p1.add(new JLabel("Price£: ", JLabel.LEFT));
		p1.add(price);
		
		p2 = new JPanel();
		p2.add(process_sale);
		p2.add(process_return);
		
		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(new JLabel("Wine purchased: "), BorderLayout.WEST);
		p3.add(wine_name);
		
		p4 = new JPanel();
		p4.add(new JLabel("Amount of Transaction: "));
		p4.add(amount);
		p4.add(new JLabel("Current balance: "));
		p4.add(balance);
		
		//add all panels on the frame
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.setSize(550,200);
		this.setLayout((new GridLayout(4,3)));
	    this.setVisible(true);
	    this.setLocation(150, 150);
	    
	}
	
	
	//calculate all data when button of "process sale"
	public void saleAct() {	
		
		if(createWine() != null) {
			
			text_amount =String.valueOf( cus.saleTransaction(createWine()));
			wine_name.setText(name.getText());
			amount.setText(" £ " + text_amount + "        " );
			balance.setText(" £ " + isCredit((double)cus.getAccount() / 100.00) + "        ");
			
			//reset all textfield
			name.setText(null);
			quantity.setText(null);
			price.setText(null);
			
		}
		
		else {
			
			name.setText(null);
			quantity.setText(null);
			price.setText(null);
			
		}
	}
	
	
	//calculate all data when button of "process return"
	public void returnAct() {
		
		if(createWine() != null) {
			
			text_amount =String.valueOf( cus.returnTransaction(createWine()));
			wine_name.setText(name.getText());
			amount.setText(" £ " + text_amount + "        " );
			balance.setText(" £ " + isCredit((double)cus.getAccount() / 100.00) + "        ");
			
			//reset all textfield
			name.setText(null);
			quantity.setText(null);
			price.setText(null);
			
		}
		
		else {
			
			name.setText(null);
			quantity.setText(null);
			price.setText(null);
			
		}
	}			
	
	
	//instantiate Wine
	public Wine createWine() {
		
		if(checkFormat() == null) {
			
		Wine w = new Wine(name.getText(), 
							Double.parseDouble(price.getText()), 
								Integer.parseInt(quantity.getText()));
		return w;
		
		}
		
		else { 
			
			JOptionPane.showMessageDialog(null, checkFormat());
			return null;
			
		}
	}
	
	
	//determine balance whether positive or negative
	public String isCredit(double balance) {
		
		if(balance < 0) {
			
			return String.valueOf(Math.abs(balance)) + "CR";    //CR value
			
		}
		
		else
			return String.valueOf(balance);
		
	}
	
	//check the input data is validate or not
	public String checkFormat() {
		
		String msg = null;
		
		try {											//input is not a number
			
			Double.parseDouble(price.getText());
			Integer.parseInt(quantity.getText());
			 
		}
		
		catch(Exception e) {
			
			return msg = "Data format is invalid";
			
		}
		
		if(name.getText().isEmpty() 					//wine name is empty
				|| name.getText().length() > 30			//wine name is more than 30 letters
					|| quantity.getText().isEmpty()		//quantity is empty
						||price.getText().isEmpty()		//price is empty
							||((!(price.getText().length() - price.getText().indexOf('.') == 3))	//price is not two decimal spaces 
									&& price.getText().indexOf('.')  != -1))
		{
			
			msg = "Data format is invalid";	
			
		}
		
		else {
			
			msg = null;
			
		}
		
		return msg;
		
	}
}
