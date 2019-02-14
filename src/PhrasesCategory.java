import java.io.IOException;

import javax.swing.JOptionPane;

// Author: Sahib Lubhana
// Date: January 2018
// Description: Category of phrases that inherits from category in order to make a category of questions that are phrases

// Method List:
// public PhrasesCategory(String fileName) throws IOException - Constructor method
// public static void main (String[] args)

public class PhrasesCategory extends Category {

	private String phrase; // Private data

	public PhrasesCategory() {

		this.phrase = "";

	}

	public PhrasesCategory (String fileName) throws IOException { // Overriding constructor method

		super(fileName);

	}

	public static void main(String[] args) { // Self-testing main method

		try {
			
			PhrasesCategory question = new PhrasesCategory ("phrasesForWOF.txt");
			question.loadFile();
			question.randomElement();
			question.setElement();
			
			String holder = question.getSentence();
			
			System.out.println(holder);
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error, check is file name is correct!");
			
		}

	}

}