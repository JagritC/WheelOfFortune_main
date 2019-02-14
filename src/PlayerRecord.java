import javax.swing.JOptionPane;

/** 
 * @author Jimit Soni
 * Date: January, 2018
 * Description: Class that keeps a record of the player information about the user
 * 
 * Method List: public PlayerRecord(), public String getName(), public void setName(String name), public String getAge(),
 * public void setAge(String age), public String getGender(), public void setGender(String gender), public double getBal(), 
 * public void setBal(double bal), public void processRecord(String record), public String toString(), public static void main(String[] args)
 */ 

public class PlayerRecord {

	//privaate Data
	private String name; //user/player name
	private String age; //user/ player age
	private String gender; //user/player gender
	private double bal; //player balance

//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	public PlayerRecord() { //main constructor
		//initializing variables
		this.name = ""; 
		this.age = "";
		this.gender = "";
		this.bal = 0;
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------
	//getters and setters	
	public String getName() { //get Name
		return name;
	}//end getName
	
	//====================================================================================================================================================

	public void setName(String name) { //set Name
		this.name = name;
	}//end setName

	//====================================================================================================================================================

	
	public String getAge() { //get Age
		return age;
	}//end getAge
	
	//====================================================================================================================================================


	public void setAge(String age) {//set Age
		this.age = age;
	}//end setAge
	
	//====================================================================================================================================================


	public String getGender() {//get Gender
		return gender;
	}//end getGender
	
	//====================================================================================================================================================


	public void setGender(String gender) {//set Gender
		this.gender = gender;
	}//end setGender
	
	//====================================================================================================================================================


	public double getBal() {//get Balance
		return bal;
	}//end getBal
	
	//====================================================================================================================================================


	public void setBal(double bal) {//set Balance
		this.bal = bal;
	}//end setBal
	
//---------------------------------------------------------------------------------------------------------------------------------------------------

	public void processRecord(String record) { //proccess Record (splitting up the info with commas)
		String word []; //decaring string array variable
		word = record.split(","); //splitting record using commas
		this.name = word[0]; //setting up variables depending on position in string
		this.age = word[1];
		this.gender = word[2];
		this.bal = Double.parseDouble(word[3]);
	}//end processRecord
	
//---------------------------------------------------------------------------------------------------------------------------------------------------

	public String toString() { //to string method (converts all inputs into a string)

		return "Player Name: " + name  
				+ "\nPlayer Age: " + age
				+"\nPlayer Gender: " + gender
				+"\nPlayer Balance: " + bal; //returns a string with all the player information combined as a string
	}//end toString
	
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) { //self testing main method
		
		String pName = "Jimit"; //initializing and declaring variable for player name
		String pAge = "12"; //initializing and declaring variable for player age
		String pGender = "Male"; //initializing and declaring variable for player gender
		double pBal = 1200; //initializing and declaring variable for player balance
		
		
		String info = pName+"," + pAge+","+ pGender + "," + pBal; //putting info into one string
		
		PlayerRecord pRecord = new PlayerRecord(); //initializing and declaring a playerRecord
		pRecord.processRecord(info); //proccessing the playerRecord with the info
    
		System.out.println(pRecord.toString()); //outputting the playerreord info to the console using a toString
	}//end main Method
	
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
}//end PlayerRecord class
