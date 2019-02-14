import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

// Author: Sahib Lubhana
// Date: January 2018
// Description: Leaderboard that will display the records of all users who have played the game

// Method list:
// public class Leaderboard extends JFrame implements ActionListener - class and it's private data
// public Leaderboard() throws IOException - constructor with all the GUI features
// public static void main(String[] args) throws IOException - main method
// public void actionPerformed(ActionEvent evt) - Method to check all of the buttons that are clicked

public class Leaderboard extends JFrame implements ActionListener {

	private JButton sortByName, sortByBal, backbtn, searchBtn;
	private JTextField searchByName;
	private JTextArea leaderboard;
	private JLabel background, title;
	private Container frame;
	private int size = 1;
	private String [] recordHolder;
	private PlayerList list;
	private String show;
	private JScrollPane scroll; // Declaring private data

	NumberFormat m = NumberFormat.getCurrencyInstance(); // Currency format

	public Leaderboard(){

		super("Wheel of Fortune Leaderboard"); // Setting title

		frame = getContentPane();
		frame.setLayout(null);
		setSize (1000, 700); // Setting frame data

		try {
			background = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/background.jpg"))));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			title = new JLabel (new ImageIcon(ImageIO.read(new FileInputStream("res/leaderboard.png"))));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		sortByName = new JButton("Sort by name");
		sortByBal = new JButton("Sort by balance");
		backbtn = new JButton("Back to Menu");
		searchBtn = new JButton("Search");

		searchByName = new JTextField("Enter player, hit search");

		leaderboard = new JTextArea();
		scroll = new JScrollPane(leaderboard); // Initializing labels, buttons, and text field/area

		background.setBounds(0, 0, 1000, 700);
		title.setBounds(0, -25, 639, 192);
		sortByName.setBounds(775, 250, 150, 50);
		searchByName.setBounds(25, 600, 500, 50);
		searchBtn.setBounds(575, 600, 150, 50);
		sortByBal.setBounds(775, 340, 150, 50);
		backbtn.setBounds(775, 430, 150, 50);
		scroll.setBounds(25, 125, 700, 450); // Setting bounds

		Color purple = new Color(204, 153, 255);
		sortByName.setBackground(purple);
		sortByBal.setBackground(purple);
		searchByName.setBackground(purple);
		leaderboard.setBackground(purple);
		backbtn.setBackground(purple);
		searchBtn.setBackground(purple); // Setting color

		Font font = new Font("Tahoma", Font.PLAIN, 18);
		leaderboard.setFont(font);
		leaderboard.setColumns(5);
		leaderboard.setTabSize(9); // Setting table up for the JTextArea

		searchByName.setBorder(BorderFactory.createLineBorder(Color.black));
		sortByName.setBorder(BorderFactory.createLineBorder(Color.black));
		sortByBal.setBorder(BorderFactory.createLineBorder(Color.black));
		leaderboard.setBorder(BorderFactory.createLineBorder(Color.black));
		backbtn.setBorder(BorderFactory.createLineBorder(Color.black));
		searchBtn.setBorder(BorderFactory.createLineBorder(Color.black)); // Setting border


		frame.add(sortByName);
		frame.add(sortByBal);
		frame.add(searchByName);
		frame.add(searchBtn);
		frame.add(backbtn);
		frame.add(scroll);
		frame.add(title);
		frame.add(background); // Adding onto frame

		leaderboard.setEditable(false);

		sortByName.addActionListener(this);
		sortByBal.addActionListener(this);
		backbtn.addActionListener(this);
		searchBtn.addActionListener(this); // Adding listeners to the buttons

		setResizable(false);
		setVisible(true); // Making visible and making it so it can't be resized

		list = new PlayerList();

		recordHolder = new String[size];

		try 
		{
			recordHolder = list.loadFile("leaderboardRecordHolder.txt"); // Loading the file to be read
		} 

		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Error while loading the file containing the leaderboard!");
		}

		list.quickSort(0, list.getSize()-1); // Sorting the file so that it can be displayed by balance

		display(); // This is the initial display for the leaderboard

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) throws IOException {

		new Leaderboard();

	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == sortByBal) {

			list.quickSort(0, list.getSize()-1);

			display(); // If the user clicks on sortByBal button, it will sort the records from highest balance to lowest

		}

		if (evt.getSource() == sortByName) {

			list.insertSort(); // If the user clicks on sortByName, it will sort the records alphabetically 

			display();

		}

		if (evt.getSource() == backbtn) { 

			WheelOfFortuneMainMenu win6 = new WheelOfFortuneMainMenu();  // Back to main menu button

			super.dispose();

		}

		if (evt.getSource() == searchBtn) {

			int location;

			list.insertSort();

			location = list.binarySearch(searchByName.getText()); // If the user chooses to search for a player, they enter name into text field and then click the search button, then record is displayed

			if (location<0) {


				JOptionPane.showMessageDialog(null, "Please enter full name."); //error msg incase the make is not found

			}

			else {

				this.show = "Position \t Name \t Age \t Gender \t Balance\n";
				this.show += "n/a " + "\t" + list.getList()[location].getName() + "\t" + list.getList()[location].getAge() + "\t" + list.getList()[location].getGender() + "\t" + m.format(list.getList()[location].getBal()) + "\n";
				leaderboard.setText("");
				leaderboard.setText(this.show); // Displaying the record

			}
		}

	}

	public void display() { // Method that when called will display all the records based on sorting

		this.show = "Position \t Name \t Age \t Gender \t Balance\n";

		this.size = 1;

		for (int i = 0; i < list.getSize(); i++) {

			this.show += size++ + ". " + "\t" + list.getList()[i].getName() + "\t" + list.getList()[i].getAge() + "\t" + list.getList()[i].getGender() + "\t" + m.format(list.getList()[i].getBal()) + "\n";

		}

		leaderboard.setText(this.show);

	}

}