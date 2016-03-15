/**
 * Name: Gemeng Yang
 * ID:   2204409
 */

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.*;

import java.io.*;

/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */
public class SportsCentreGUI extends JFrame implements ActionListener {
	
	/* GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/* GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/* Display of class timetable */
	private JTextArea display;

	/* Display of attendance information */
	private ReportFrame report;

	/* Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";
	
	/* FiynessProgram */
	FitnessProgram program;
	
	/**
	 * Constructor for AssEx3GUI class
	 * @throws FileNotFoundException 
	 */
	public SportsCentreGUI() throws FileNotFoundException {	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(700, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier New", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();
		
		// more code needed here
		program = new FitnessProgram();
		initLadiesDay();
		updateDisplay();
		
	}

	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 * @throws FileNotFoundException 
	 */
	public void initLadiesDay() throws FileNotFoundException {
		
		FitnessClass fc;												//instance of FitnessClass	
	    int count = 0;													//pointer for reading file content
	    String[] contents = (fileInput(classesInFile)).split(" ");		//new String array
	    
	    for(int i = 0; i < contents.length / 4; i++) {
	    	
	   		fc = new FitnessClass(contents[count], contents[count+1], contents[count+2], Integer.parseInt(contents[count+3]));
	   		program.addClass(fc, fc.getTime() - 9);			  			//add FitnessClass into program
	    	count = count + 4;
	    	
	    }
	    
	    initAttendances();
	    
	}

	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 * @throws FileNotFoundException 
	 */
	public void initAttendances() throws FileNotFoundException {
		
		String[] contents = (fileInput(attendancesFile)).split(" ");  //new String array
		int a, b, c, d, e;											  //set five int for five times attendance
		 
		for(int i = 0; i < (contents.length) / 6; i++) {			  //loop all contents array
			
			a = Integer.parseInt(contents[i * 6 + 1]);				  //No.1 attendance
			b = Integer.parseInt(contents[i * 6 + 2]);				  //No.2 attendance
			c = Integer.parseInt(contents[i * 6 + 3]);				  //No.3 attendance
			d = Integer.parseInt(contents[i * 6 + 4]);				  //No.4 attendance
			e = Integer.parseInt(contents[i * 6 + 5]);				  //No.5 attendance
			int[] attend = {a, b, c, d, e};							  //set an new array of attendance
			program.findClass(contents[i * 6]).setAttendance(attend); //set the attendance of each FitnessClass	
			
		}	
		
	}

	/**
	 * Instantiates timetable display and adds it to GUI
	 */
	public void updateDisplay() {
		
		String line1, line2 = "", line3 = "";
		FitnessClass[] list = program.getList();											//get FitessClassList from FitnessProgram 
		line1 = "  " + "9-10"  + "       " 
					 + "10-11" + "       " 
					 + "11-12" + "       " 
					 + "12-13" + "       " 
					 + "13-14" + "       " 
					 + "14-15" + "       " 
					 + "15-16" + "\n";
	    display.setText(line1);																//display the first line
	    
	    line2 = list[0] == null ? (line2 + " Available " ): (line2 + " " + list[0].getName());
	    line3 = list[0] == null ? (line3 + "           " ): (line3 + " " + list[0].getTName() +  "      "); 
	    
	    for(int i = 1; i < 7; i++) {														//add the second line by each FitnessClass
	    	
	    	if(list[i] == null) {															//check the FitnessClass is null or not
	    		
	    		line2 = line2 + countSpace(line2,i) + " Available ";						//line2
	    		line3 = line3 + countSpace(line3,i) + "           "; 						//line3
	    		
	    	}
	    	
	    	else {																			//Fitness is exist
	    		
	    		line2 = line2 + countSpace(line2,i) + list[i].getName();					//line2
	    		line3 = line3 + countSpace(line3,i) + list[i].getTName();					//line3
	    		
	    	}
	    	
	    }
	    
	    line2 = line2 + "\n";																//new line
	    display.append(line2);																//append lin2
	    display.append(line3);																//append line3
	    
	}

	/**
	 * adds buttons to top of GUI
	 */
	public void layoutTop() {
		
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
		
	}

	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	public void layoutBottom() {
		
		/* instantiate panel for bottom of display */
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		/* add upper label, text field and button */
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		/* add middle label, text field and button */
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		/* add lower label text field and button */
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
		
	}

	/**
	 * Processes adding a class
	 */
	public void processAdding() {
		
		/* get id,class name and tutor name */
	    String id = idIn.getText();
	    String name = classIn.getText();
	    String tutor = tutorIn.getText();
	    int time = program.getSlot();
	    
	    if(time == -1 ) {    //check the time available or not
	    	
	    	showError(" No available time. ");
	    	
	    }
	    
	    else if(id.length() > 3) {    //check id length
	    	
	    	showError(" The ID out of the length(3 characters). ");		
	    	
	    }
	    
	    else if(isLetter(name)) {
	    	
	    	showError(" The class name is wrong format. ");
	    	
	    }
	    
	    else if(isLetter(tutor)) {
	    	
	    	showError(" The tutor name is wrong format. ");
	    	
	    }
	    
	    else if(name.length() > 11 || tutor.length() > 11) {    //check tutor name length
	    	
	    	showError(" The class name, tutor name out of the length(11 characters). ");
	    	
	    }
	    
	    else if (program.checkClass(id)) {    //check id
	    	
	    	showError(" The class ID has repeated. ");
	    	
	    }
	    
	    else if(id.isEmpty() || name.isEmpty() || tutor.isEmpty()) {    //check the text filed is empty or not
	    	
	    	showError(" ID, class name and tutor name should not be empty. ");
	    	
	    }
	    
	    else {
	    	
	    	FitnessClass fClass = new FitnessClass(id, name, tutor, time);	//new class
	    	program.addClass(fClass, time-9);								//add class	
	    	updateDisplay();
	    	
	    }
	    
	}

	/**
	 * Processes deleting a class
	 */
	public void processDeletion() {
		
		/* get id,class name and tutor name */
	    String id = idIn.getText();
	    
	    if(id.isEmpty()) {    //check the textFelid is empty or not
	    	
	    	showError(" ID is null! " );
	    	
	    }
	    
	    else if(! program.checkClass(id)) {    //check the class info 
	    	
	    	showError(" No class of " + id + " in list. " );    //output error message
	    	
	    }
	    
	    else {
	    	
	    	program.deleteClass(id);    //delete the class
	    	updateDisplay();			//update the text Area
	    	
	    }
		
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	public void displayReport() {
		
		report = new ReportFrame(program);
		report.setVisible(true);
		
	}

	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 * @throws FileNotFoundException 
	 */
	public void processSaveAndClose() throws FileNotFoundException {
		
		String line = "";						//Initials the string for writing into out file					
		FitnessClass fc;						//temp of FitnessClass
		File out = new File(classesOutFile);	//out file
		PrintWriter pw = new PrintWriter(out);	//set the printWriter
		
		for(int i = 0; i < 7; i++) {			//loop for write  infos into file
			
			fc = program.findClass(i);			//use temp point to store the FitnessClass
			if(fc != null) {					//check the class is null or not
				
				line = line + fc.getId() + " " 	
							+ fc.getName() + " " 
							+ fc.getTName() + " " 
							+ fc.getTime() + "\r\n";
				
			}
			
		}
		
		pw.write(line);							//write into out file
		pw.close();								//close the printer writer
		System.exit(0);							//exit
		
	}

	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource().equals(attendanceButton)) {
			
			displayReport();	
			
		}
		
		else if(ae.getSource().equals(closeButton)) {
			
			try {
				
				processSaveAndClose();
				
			} 
			
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		else if(ae.getSource().equals(deleteButton)) {
			
			processDeletion();
			
		}
		
		else if(ae.getSource().equals(addButton)) {
			
			processAdding();
			
		}
		
	}
	
	/**
	 * fileInput reads the file contents and add into an array
	 * @param filename
	 * @throws FileNotFoundException 
	 * @return str_array
	 */
	public String fileInput(String filename) throws FileNotFoundException {
		
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		String str = "";
		
		while(scan.hasNext()) {
			
			str = str + scan.next() + " ";
			
		}
		
		scan.close();
		return str;
		
	}
	
	/* count the space of in front of the word */
	public String countSpace(String str,int i) {
		
		int count = i * 12 - str.length();					
		String space = "";
		
		for(int j = 0; j < count; j++) {
			
			space = space + " ";
			
		}
		
		return space;
		
	}
	
	/* display the error */
	public void showError(String er) {
		
		JOptionPane.showMessageDialog(null, er);
		idIn.setText(null);
		classIn.setText(null);
		tutorIn.setText(null);
		
	}
	
	/* check the class name and tutor name is all letter or not */
	public boolean isLetter(String str) {
		
		for(int i = 0; i < str.length(); i++) {
			
			if(str.charAt(i) > 122 || str.charAt(i) < 97 ) {	//ascii check the str
				
				return true;	//is not all letter	
				
			}
			
		}
		
		return false;			//all letters
		
	}
	                       
}
