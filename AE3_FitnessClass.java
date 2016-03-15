/**
 * Name: Gemeng Yang
 * ID:   2204409
 */

/** 
 * Defines an object representing a single fitness class
 */

public class FitnessClass implements Comparable<FitnessClass> {
	
    /* data filed */
	private String id;
	private String name;
	private String tname;
	private int time;
	private int[] attendance = {0, 0, 0, 0, 0};    //set default attendance array to five 0
	
	/**
	 * constructor 
	 */
	public FitnessClass(String id, String name, String tname, int time, int[] attendance ) {
		
		setId(id);
		setName(name);
		setTName(tname);
		setTime(time);
		setAttendance(attendance);
		
	}
	
	/**
	 * overload constructor with four arguments
	 */
	public FitnessClass(String id, String name, String tname, int time) {
		
		setId(id);
		setName(name);
		setTName(tname);
		setTime(time);	
		
	}
	
	/**
	 * mutator
	 */
	public void setId(String id) {
		
		this.id = id;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setTName(String tname) {
		
		this.tname = tname;
		
	}
	
	public void setTime(int time) {
		
		this.time = time;
		
	}
	
	public void setAttendance(int[] a) {
		
		for(int i  =0; i < 5; i++) {
			
			this.attendance[i] = a[i];
			
		}
		
	}
	
	/**
	 * accessor
	 */
	public String getId() {
		
		return id;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public String getTName() {
		
		return tname;
		
	}
	
	public int getTime() {
		
		return time;
		
	}
	
	public int[] getAttendance() {
		
		return attendance;
		
	}
	
	/**
	 * compareTo method: compare an FitnessClass with itself
	 * @param other
	 * @return integer
	 * if the average attendance greater than itself return 1
	 * if the average attendance less than itself return -1
	 * if the average attendance equals to itself return 0  
	 */
    public int compareTo(FitnessClass other) {
    	
    	double avg = getAvg();
    	
    	if(avg < other.getAvg()) {
    		
    		return 1;
    		
    	}
    	
    	if(avg == other.getAvg()) {
    		
    		return 0;
    		
    	}
    	
    	return -1;
    	
    }
    
	/**
	 * compute the avg of attendance of every five weeks
	 * @return avg
	 */
	public double getAvg() {
		
		int sum = 0;
		for(int i = 0; i < 5; i++) {
			
			sum = sum + attendance[i];
			
		}
		
		double avg = (double)sum / (double)5;
		return avg;
		
	}
	
	/**
	 * make a String formatted appropriately for the attendance report
	 * @return string
	 */
	public String ReportAttendance() {
		
		String msg = "";										 //set one space blank
		for(int i = 0; i < 5; i++) {							 //loop five attendance
			
			if((String.valueOf(attendance[i]).length()) == 2) {  //check the number is one decimal or two
				
				msg = msg + String.valueOf(attendance[i]);		 //the number of two decimal without space 
				
			}
			
			else {
				
				msg = msg + " " +String.valueOf(attendance[i]);  //the number of one decimal with an space 
				
			}
			
			msg = msg + "  ";
			
		}
		
		return msg;
	}
	
	/**
	 * count the number of the space before each word
	 */
	public String countSpaces(String str,int x) {
		
		String msg = "";
		int count = 0;
		
		if(x == 1) {
			
			count = 24 - 6 - str.length();
			
		}
		
		else if(x == 2) {
			
			count = 40 - 24 - str.length();
			
		}
		
		else {
			
			count = str.length() == 4 ? 10 : 11;
			
		}
		
		for(int i = 0; i < count; i++) {
			
			msg = msg + " ";
			
		}
		
		return msg;
		
	}
	
	
	/**
	 * toString method
	 */
	public String toString() {
		
		String str = " " + getId() + "  " + getName();
		str = str + countSpaces(getName(),1) + getTName();
		str = str + countSpaces(getTName(),2) + ReportAttendance();
		str = str + countSpaces(String.valueOf(getAvg()), 3) + String .format("%.2f", getAvg());    //set the avg's format to two decimal places
		return str;
		
	}
	
}