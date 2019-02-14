	import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * @author Jimit Soni
 * Date: January, 2018
 * Description: lets the user input the data in a PlayerRecord array
 * and output stored data data in record list allowing for user communication
 * with inputting, deleting, printing and changing, sorting and searching for elements in the arrays
 * 
 * Method List: public PlayerList(), public int getSize(), public void setSize(int size), public PlayerRecord[] getList(), 
 * public boolean insert (PlayerRecord record), public boolean delete (PlayerRecord record), public int binarySearch (String search),
 * public void quickSort(int aMin, int aMax), public void insertSort(), public String [] loadFile(String fileName), public void saveToFile(),
 * public static void main(String[] args)
 */

public class PlayerList {

	//Private Data
	private PlayerRecord list[]; //delcaring playerRecord list
	private int maxSize; //declaring int maxSize
	private int size; //declating int size

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public PlayerList() { //PlayerList main constructor
		//initializing variables
		this.maxSize = 30;
		this.list = new PlayerRecord[maxSize];
		this.size = 0;
	}//ends PlayerList constructor

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public int getSize(){
		return this.size;
	}//ends getSize

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void setSize(int size) {
		this.size = size;
	}//ends setSize

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public PlayerRecord[] getList(){
		return this.list;
	}//ends getList

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean insert (PlayerRecord record) { //insert method (user can insert a new playerRecord into the list)
		// if size is below maxSize
		if (size < maxSize) { 
			size++;
			list [size-1] = record; // inserts record to the end of the list 
			return true;// returns true if it was successful
		}
		return false; //otherwise returns false
		
	}//ends insert method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------       

	public boolean delete (PlayerRecord record) { //delete method (allows user to be able to delete a playerRecord from the list of arrays)

		int a = binarySearch(record.getName()); // call binarySearch method to get location of the record

		if (a >= 0) { // if record is found 

			list[a] = list[size-1]; // place at end of the list
			size--; // decrease size to delete 
			quickSort(0, size-1); // sort by transaction amount
			return true; //return true
		}
		return false; //otherwise return false
	}


//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public int binarySearch (String search) //binary search method to search for elements using the binary search way of sorting
	{
		int bottom = 0; // bottom of the list
		int top = this.size-1; // top of the list
		int middle;  // middle of the list
		int compare;

		while (bottom<=top)
		{
			middle = (bottom + top)/2; // find the middle 
			// if it finds the record in the middle
			if (search.compareToIgnoreCase(list[middle].getName()) == 0)
			{
				return middle;
			}
			// if the record is below the middle 
			else if (search.compareToIgnoreCase(list[middle].getName())<0)//A
			{
				System.out.println("A");
				top = middle -1;
			}
			// if the record is above the middle
			else
			{
				System.out.println("B");
				bottom = middle+1;
			}

		}
		return -1; //otherwise return -1
	}//ends binarySearch method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void quickSort(int aMin, int aMax) { //quick sort method sorts the list by transaction amount
		int rightPos, leftPos;
		double pivot;

		if(aMin > aMax) { //if there's nothing more to sort
			return;
		}
		pivot = list[(aMin+aMax)/2].getBal(); //choose pivot point 
		leftPos = aMin; //set starting positions 
		rightPos = aMax;

		do {
			while(pivot < list[leftPos].getBal()) {
				leftPos++;
			}
			
			while(pivot >  list[rightPos].getBal()) {
				rightPos--;
			}
			
			if (leftPos <= rightPos) {
				if(list[leftPos].getBal() != list[rightPos].getBal()) {
					// swap elements
					PlayerRecord temp;
					temp = list[leftPos];
					list[leftPos] = list [rightPos];
					list[rightPos] = temp;
				}
				leftPos++;
				rightPos--;
			}
		} while (leftPos < rightPos);

		quickSort(aMin, rightPos); //call method with new parameters
		quickSort(leftPos, aMax);

	}//ends quickSort method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void insertSort() // insertion sort to sort records alphabetically 
	{
		PlayerRecord holder = new PlayerRecord (); //create new player record
		int i = 0; 
		for (int j = 1; j < size; j++)
		{
			holder = list[j]; //set the holder to hold a the current record
			i = j - 1;
			while (i >= 0) //while i is greater than 0
			{
				if (holder.getName().compareToIgnoreCase(list[i].getName())>0) { //if the holder gets the name and finds a mathc then brek out of the loop
					break;
				}
				list[i+1] = list[i]; //list = the i value
				i--;
			}
			list[i+1] = holder; //the position on the list is then equal to holder 
		}
	}//ends insertSort method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public String [] loadFile(String fileName) throws IOException { //loadfile method loads a file entered by the user

		int a = 0;
		FileReader fr = new FileReader(fileName); //initializing and declaring file readers and buffered file readers
		BufferedReader input = new BufferedReader (fr);
		while(input.readLine() != null) { //while the line in the files is not equal to nothing the variable a keeps getting added on too by 1
			a++;
		}

		input.close(); //closes the input
		
		String [] fileHolder = new String[a]; //the fileHolder array is initialized by the amount of lines in the file


		FileReader fr2 = new FileReader(fileName); //file reader variable is initialized and declared
		BufferedReader input2 = new BufferedReader (fr2);

		for (int i = 0; i<a; i++) //for each line of the of the file it reads the report and proccess the string
		{
			fileHolder[i] = input2.readLine();
			PlayerRecord r = new PlayerRecord ();
			r.processRecord(fileHolder[i]);
			insert(r);
		}

		return fileHolder; //return fileHolder

	}//ends LoadFile Method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void saveToFile(){ //saveToFile Method to save records to a file names "SavedPlayerRecords"

		FileWriter outputFile;
		
		try {
			outputFile = new FileWriter("leaderboardRecordHolder.txt", true);//declaring file reader on the file that is given by the user
			PrintWriter outputPrint = new PrintWriter(outputFile); 

			for (int i = 0; i < getSize(); i++) { //for each record

				outputPrint.println(list[i].getName() + "," + list[i].getAge() + "," //Prints the records into the file given by the user
						+ list[i].getGender() + ","+ list[i].getBal());
			}
			outputPrint.close();// close file
		
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "This is in PlayerList saveToFile class");
		} 
		
		
	}//ends saveToFile Method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HeadlessException 
	 */
	
	//self testing main method
	public static void main(String[] args) throws HeadlessException, IOException {
		PlayerList sRecList = new PlayerList();

		while (true)
		{

			char command = JOptionPane.showInputDialog("Please enter a letter coresponding to the command\n" //outputs the options to the user
					+ "i - Insert \n" 
					+ "p - Print\n"
					+ "d - Delete \n"
					+ "q - quicksort by balance\n"
					+ "r - Sort by names\n"
					+ "b - Binary Search for record\n"
					+ "e - Exit").charAt(0);

			//=========================================================================================================================================

			if (command == 'i' || command == 'I') //do this if the user picks i (insert)
			{ 
				String oldRec;

				//creating a StudentRecord object
				PlayerRecord sRec = new PlayerRecord();

				oldRec = JOptionPane.showInputDialog("Enter record", "jimit,17,male,200");
				sRec.processRecord(oldRec);

				if (!sRecList.insert(sRec)) {
					JOptionPane.showMessageDialog(null, "Error! Please enter a valid format \n"
							+ "(Name,Age,Gender,Word/letterGuess");
				}
			}//ends if

			//=========================================================================================================================================

			else if (command == 'p' || command == 'P') //do this if the user picks p (print)
			{
				PlayerRecord [] myList = sRecList.getList();
				for(int i = 0; i<sRecList.getSize(); i++) {
					System.out.println(myList[i] + "\n-----------------------------------------------------------"
							+ "---------------------------------------------------------"); //print the array to the user in the consle
				}
			}//ends if

			//=========================================================================================================================================

			else if (command =='d' || command == 'D') //do this if the user picks d (delete)
			{
				String oldRec;


				PlayerRecord sRec = new PlayerRecord();

				oldRec = JOptionPane.showInputDialog("Enter record to delete", "jimit,17,male,200"); //user enters the record they would like to delete
				sRec.processRecord(oldRec);

				if (!sRecList.delete(sRec))
				{
					JOptionPane.showMessageDialog(null, "Please enter an existing record");
				}
			} //ends if

			//=========================================================================================================================================

			else if (command == 'q' || command == 'Q' )// quick sort 
			{
				sRecList.quickSort(0, sRecList.getSize()-1); // call sort method 
				PlayerRecord [] myList = sRecList.getList();

				// print sorted list 
				for (int i = 0; i < sRecList.getSize(); i++) {
					System.out.println(myList[i] + "\n-----------------------------------------------------------"
							+ "---------------------------------------------------------");
				}
			}

			//=========================================================================================================================================

			else if (command == 'r' || command == 'R') //do this if the user picks r (sort)
			{
				sRecList.insertSort(); //pass list through the insertSort method
			}

			//=========================================================================================================================================

			else if (command == 'b' || command == 'B') {  //do this if the user picks b (binary search)
				String name;
				name = JOptionPane.showInputDialog("Please enter the name of the person you would like to search."); //user enters the name of the player
				sRecList.insertSort();
				int location = sRecList.binarySearch(name);

				if (location<0) {
					JOptionPane.showMessageDialog(null, name + "Please enter a correct name/ name not found."); //error msg incase the make is not found
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "The informaion in the record going by the name: " + name +" is \n" + sRecList.getList()[location]); //outputs the info of that player to user
			}//ends else
			}

			//=========================================================================================================================================

			else if (command == 'e' || command == 'E') //do this if the user picks e (exit)
			{
				System.exit(0); //exits the application and terminates the program
			}

			else
			{
				JOptionPane.showMessageDialog(null, "Error! Please enter a valid letter"); //if none of the commands are entered than output an error msgs telling the user to enter a valid input
			}//ends if

			//=========================================================================================================================================

		}//ends while loop 

	}//ends self-testing main method

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------

}//ends PlayerList Class
