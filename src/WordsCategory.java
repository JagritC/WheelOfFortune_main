import java.io.IOException;

import javax.swing.JOptionPane;

// Author: Sahib Lubhana
// Date: January 2018
// Description: Category of phrases that inherits from category in order to make a category of questions that are words

// Method List:
// public WordsCategory(String fileName) throws IOException - Constructor method
// public String getWord() - Method to get the word
// public void setWord (String word) - Method to set the word
// public String getHint() - Method to get the hint that will be displayed
// public static void main(String[] args) - Self-testing main method

public class WordsCategory extends Category {

	private String word;
	private String hint; // Private data

	public WordsCategory() {

		this.word = "";
		this.hint = "";

	}

	public WordsCategory (String fileName) throws IOException { // Constructor

		super(fileName);

	}

	public String getWord() { // Method to get word

		return this.word;

	}

	public void setWord (String word) { // Method to set word

		this.word = word;

	}
	
	public String getHint() { // Method to get the hint
		
		if (getRandom() < 10 && getRandom() >= 0) {
			
			return this.hint = "Found in house";
			
		}
		
		else if (getRandom() < 20 && getRandom() >= 10) {
			
			return this.hint = "School subject";
			
		}
		
		else if (getRandom() < 30 && getRandom() >= 20) {
			
			return this.hint = "ICS4U0";
			
		}
		
		else
			return this.hint = ""; // These checks make it so that from the hints file, where the hints are found, if the random value is within the range of certain elements, they are a specific
		// type of word question. For example, if the random number is 22, the elements from 20-30 are all related to course content, so the hint would be "ICS4U0" as the word is related to course
		
		
	}

	public static void main(String[] args) { // Self-testing main method

		try {
			
			WordsCategory question = new WordsCategory ("WordsFile.txt");
			question.loadFile();
			question.randomElement();
			question.setElement();
			System.out.println(question.getSentence());
			
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Error, check is file name is correct!");
			
		}

	}

}