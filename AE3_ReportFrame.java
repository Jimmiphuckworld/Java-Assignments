/**
 * Name: Gemeng Yang
 * ID:   2204409
 */

import java.awt.*;

import java.util.*;

import javax.swing.*;

/**
 * Class to define window in which attendance report is displayed.
 */
public class ReportFrame extends JFrame {
	
	/* Display of class timetable */
	private JTextArea display;
	
	/* constructor */
	public ReportFrame(FitnessProgram program) {
		
		setTitle("Attendance Report");
		setSize(700, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier New", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		this.setLocation(300, 100);
		setReport(program);
		
	}
	
	/**
	 * setReport() writes all contents into the textArea
	 * @param program
	 */
	public void setReport(FitnessProgram program) {
		
		FitnessClass[] list = program.sortList();			//sort the list
		String line1, line2;								//line1, line2
		
		line1 = " ID   Class            Tutor                Atttendance        Average Attendance " + "\n";
		line2 = " =================================================================================" + "\n";
		display.setText(line1);								//writes into TextArea
		display.append(line2);								//writes into TextArea
		
		for(int i = 0; i < list.length; i++) {				//writes info of each Fitness class
			
			if(list[i] != null) {
				
				display.append(list[i].toString() + "\n");
				
			}
			
		}
		
		display.append("\n");							 	//blank line
		display.append("                                              overall average:    " + program.getOverallAvg());
		
	}
	
}
