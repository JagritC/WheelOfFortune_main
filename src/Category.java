import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

// Author: Sahib Lubhana
// Date: January 2018
// Description: Category class that will be used by the Wheel of Fortune game to load either words or pharses

// Method List:
// public Category () - Constructor
// public Category (String fileName) - Overriding constructor to initialize private data
// public String [] loadFile() throws IOException - Method to load file
// public void setPhrases (String [] newArray) - Method to set phrases
// public void randomElement - Method to get random element
// public void setElement - method to set element
// public int getRandom - method to get the random value
// public String getElement - method to get the question
// public static void main(String[] args) - self-testing main method

public class Category {

	private String [] phraseArray;
	private String fileName;
	private int random;
	private String phrase; // Private data

	public Category() {

		this.phraseArray = new String[0];
		this.fileName = "";

	}

	public Category (String fileName) { // Overriding Constructor to initialize private data 

		this.phraseArray = new String[0];
		this.fileName = fileName;

	}

	public String [] loadFile() throws IOException { // Method to load file

		int size = 0;

		BufferedReader input = new BufferedReader (new FileReader (this.fileName));

		while (!input.readLine().equalsIgnoreCase("EOF")) {

			size++;

		}

		input.close();

		input = new BufferedReader (new FileReader (this.fileName));

		this.phraseArray = new String [size];

		for (int i = 0; i < size; i++) {

			phraseArray[i] = input.readLine(); // Once file is loaded, make the array of questions to the lines in the file

		}

		input.close();

		return phraseArray;

	}

	public void setPhrases (String [] newArray) {

		this.phraseArray = newArray; // Method to set the array of questions

	}

	public void randomElement () {

		this.random = (int)(Math.random()*this.phraseArray.length); // Gets a random element from the array

	}

	public void setElement() {

		this.phrase = phraseArray[this.random]; // The question that will be used is taken at random

	}

	public int getRandom() {

		return this.random; // Getting the random value

	}

	public String getSentence() {

		return this.phrase; // Getting the phrase

	}

	public static void main(String[] args) { // Self-testing main method

		Category test = new Category("phrasesForWOF.txt");
		String [] tes = new String[0];
		
		try {

			tes = test.loadFile();

		} 

		catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Error, check is file name is correct!");

		}
		
		for (int i = 0; i < tes.length; i++) {
			
			System.out.println(tes[i]);
			
		}
		
		String [] tes2 = {"Hello", "Hi", "Yo", "Man"};
		
		test.setPhrases(tes2);
		
		for (int j = 0; j < tes2.length; j++) {
			
			System.out.println(tes2[j]);
			
		}
		
		test.randomElement();
		
		System.out.println(test.getRandom());
		
		test.setElement();
		
		System.out.println(test.getSentence());



	}

}
