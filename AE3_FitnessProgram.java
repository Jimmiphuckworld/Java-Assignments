/**
 * Name: Gemeng Yang
 * ID:   2204409
 */

import java.io.*;

import java.util.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */

public class FitnessProgram {
	
   private final int maxVolume = 7;
   private int Volume;
   private FitnessClass[] listOfFitness;
	
   /**
    * constructor
    */
   public FitnessProgram() {
	   
	   listOfFitness = new FitnessClass[maxVolume];
	   Volume = 0;
	   
   }
   
   /**
    * accessor
    */
   public FitnessClass[] getList() {
	   
	   return listOfFitness;
	   
   }
   
   public int getVolume() {
	   
	   return Volume;
	   
   }

   /**
    * addClass that add an class into the listOfFitness
    * @param fClass
    */
   public void addClass(FitnessClass fClass, int index) {	
	   
		   listOfFitness[index] = fClass;
		   Volume++;
		   
   }
   
   public void deleteClass(String id) {
	   
	   for(int i = 0; i < 7; i++) {													//loop all list
		   
		   if(listOfFitness[i] != null && listOfFitness[i].getId().equals(id)) {    //check the FitnessClass is null and equals to id o not
			   
		   		listOfFitness[i] = null;										    //return the class if it is suitable,otherwise return false
		   		Volume--;
		   		
	   		}
		   
	   }

   }
   
   /**
    * findClass:enter index and find the fitnessClass in listOfFitness
    * @param x the index
    * @return fitness or null
    */
   public FitnessClass findClass(int x) {
	   
	   if(listOfFitness[x] != null) {
		   
		   return listOfFitness[x]; 
		   
	   }
	   
	   return null;
	   
   }
   
   /**
    * findClass:overload the findClass,
    * enter ID and find the fitnessClass in listOfFitness
    * @param x the index
    * @return fitness or null
    */
   public FitnessClass findClass(String id) {
	   
	   for(int i = 0; i < 7; i++) {													    //loop all list
		   
		   if(listOfFitness[i] != null && listOfFitness[i].getId().equals(id)) {    //check the FitnessClass is null and equals to id o not
			   
		   		return listOfFitness[i];										    //return the class if it is suitable,otherwise return false 
		   		
	   		}
		   
	   }
	   
	   return null;
	   
   }
	
   /**find the FitnessClass at a particular time
    * @param time
	* @return null if no such class exist.
	*/
	public FitnessClass findClassByTime(int t) {
		
		if(findClass(t - 9) != null) {
			
			return findClass(t - 9);
			
		}
		
		return null;
		
	}
	
	/** find the first start time that is available for a class
	 *@return -1 is no time is available. 
	 */
	public int getSlot() {
		
		for(int i = 0; i < maxVolume; i++) {
			
			if(findClass(i) == null) {
				
				return i + 9;				//return the first time is available
				
			}
			
		}
		
		return -1;						    //no time for class    
		
	}
   
   /** 
    * sort the listOfFitness
    */
   public FitnessClass[] sortList() {
	   
	   FitnessClass[] list = new FitnessClass[Volume];			//make new list for storing fitnessClass
	   int index = 0;											//index of list 
	   
	   for(int i = 0 ; i < maxVolume; i++) {					//store the fitnessClass by using for loop
		   
		   if(listOfFitness[i] != null) {						//check the FitnessClass is null or not
			   
			   list[index] = listOfFitness[i];
			   index++;
			   
		   }
		   
	   }
	   
	   Arrays.sort(list); 										//sort the list
	   return list;
		
   }

   /**
    * computer the average of all FitenessClass
    */
   public String getOverallAvg() {
	   
	   double sum = 0;										 //set initial sum =0
	   
	   for(int i = 0; i < listOfFitness.length; i++) {		 //loop the list 
		   
		   if(listOfFitness[i] != null) {					 //check the Fitness is null or not
			   
			   sum = sum + listOfFitness[i].getAvg();		 //add up the sum
			   
			}
		   
	   }
	   
	   double avg = Math.round((sum / Volume) * 100) / 100.0;//computer the over all sum
	   return String.format("%.2f", avg);					 //return the avg	
	   
   }

  /**
   * checkClass that check the FitnessClass is valid or not
   * @param fClass
   * @return a number :positive number means there is error with adding process
   * 				   negative number means no error with adding process
   */
   public boolean checkClass(String id) {
	   
	   boolean flag = true;				//the id already in list
	   
	   for(int i = 0; i < 7; i++) {
		   
		   if(findClass(id) == null) {	
			   
			   flag = false;			//no id in list
			   
		   }
		   
	   }
	   
	   return flag;
	   
   }

}
