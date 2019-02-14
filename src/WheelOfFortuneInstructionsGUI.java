import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * @author Jimit Soni
 * Date: January, 2018
 * Description: GUI that allows users to view the instructions on how to play the game, users can then go back to the main menu or 
 * exit the program
 * 
 * Method List: plublic WheelOfFortuneInstructionsGUI(), public void actionPerformed(ActionEvent e), public static void main(String[] args)
 */

public class WheelOfFortuneInstructionsGUI extends JFrame implements ActionListener {

	//private data
	private ImagePicture myTitle; 
	private JLabel backGround, wheel1, wheel2, spotlight1, spotlight2, box;
	private JButton backBtn, exitBtn;
	private JTextArea txtOutput;

//------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//main constructor to set up window
	public WheelOfFortuneInstructionsGUI() {
		
		super("Wheel Of Fortune Instructions"); //sets up the window name
		setLayout(null); //sets the layout for the window to null
		
		//initializes the title
		try {
			myTitle = new ImagePicture(new ImageIcon(ImageIO.read(new FileInputStream("res/title2.png"))));
		} catch (IOException e) {
			System.out.println("myTitle ImageIcon Error");
		}
		
		//initializes the JLabels/ pictures
		try {
			backGround = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/background.jpg"))));
		} catch (IOException e) {
			System.out.println("backGround ImageIcon Error");
		}
		try {
			wheel1 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/spinner1.png"))));
		} catch (IOException e) {
			System.out.println("wheel1 ImageIcon Error");
		}
		try {
			wheel2 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/spinner2.png"))));
		} catch (IOException e) {
			System.out.println("wheel2 ImageIcon Error");
		}
		try {
			spotlight1 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/spotlight.png"))));
		} catch (IOException e) {
			System.out.println("spotlight1 ImageIcon Error");
		}
		try {
			spotlight2 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/spotlight2.png"))));
		} catch (IOException e) {
			System.out.println("spotlight2 ImageIcon Error");
		}
		try {
			box = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/border.png"))));
		} catch (IOException e) {
			System.out.println("box ImageIcon Error");
		}
		
		//initializes the jButtons
		backBtn = new JButton("Return to Main Menu"); 
		exitBtn = new JButton("Exit");

		//initializes the jTextArea
		txtOutput = new JTextArea(); 
		
		//initializes the border
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
		
		//Initializing and declaring the RGB colors
		Color outputCol = new Color(171, 135, 255); 
		Color contCol = new Color(79, 247, 101);
		Color exitCol = new Color(255, 68, 68);
		
		
		//===========================================================================================================================================================		
		
		//setting bounds for the title
		myTitle.setBounds(55, -40 ,880, 228); 
		
		//setting the bounds for the buttons
		backBtn.setBounds(750, 600, 200, 50);
		backBtn.setBackground(contCol);
		exitBtn.setBounds(50, 600, 200, 50);
		exitBtn.setBackground(exitCol);
		
		//setting bounds for the jLables
		backGround.setBounds(0, 0, 1000, 700); 
		wheel1.setBounds(-10,400, 520, 700); 
		wheel2.setBounds(490,400, 520, 700); 
		spotlight1.setBounds(-60, -130, 450, 450); 
		spotlight2.setBounds(610, -130, 450, 450); 
		
		//setting bounds for the JTextArea
		txtOutput.setBounds(240, 203,522, 360);
		
		//setting up borders
		exitBtn.setBorder(border); 
		backBtn.setBorder(border);
		
		//============================================================================================================================================================
		
		//changing the JTextArea into a scrollable interface
		
		border = BorderFactory.createLineBorder(Color.BLACK, 5); //changing the border values
				
		JScrollPane scroll = new JScrollPane(txtOutput); //creating a new JScrollPane to make the txtOutput scrollable
		
		scroll.setBounds(250, 200, 500, 350); //setting the bounds for the JTextArea
		
		txtOutput.setEditable(false); //setting the JTextArea to not be editable
		
		scroll.setBorder(border); //setting the border on the scroll
		
		txtOutput.setBackground(outputCol); //setting the background colour for the JTextArea

		//============================================================================================================================================================
		
		//setting up the text that is being displayed in the JTextArea
		txtOutput.setText("- Click on Play to start game\r\n" + 
				"\r\n" + 
				"- Enter name, and age of player 1 followed by player 2.\r\n" + 
				" \r\n" + 
				"- Either player 1 or player 2 will be given the choice \r\n" + 
				"  to spin the wheel\r\n" + 
				"\r\n" + 
				"- On the wheel there will be different amounts of moneys\r\n" + 
				"  Everyone starts with $0\r\n" + 
				"\r\n" + 
				"- If you land on a money piece then you will be given a \r\n" + 
				"  choice of guessing a consonant or buying a vowel.\n" + 
				"        - However to a guess vowel you must pay $500 so \r\n" + 
				"          you can't guess a vowel on the first spin hence \r\n" + 
				"          you must guess a consonant\r\n" + 
				"\r\n" + 
				"- Depending on how many of the consonant that you picked \r\n" + 
				"  are on board you will get money for it.\r\n" + 
				"\r\n" + 
				"- The money you get will be equal to amount you land times \r\n" + 
				"  how many same consonant are there of the one you picked\n" + 
				"        - For example. you landed on $400 and you \r\n" + 
				"          guessed s, if there are 3 S's then you \r\n" + 
				"          will get 3 x 400 = $1200\r\n" + 
				"\r\n" + 
				"- If you land on lose a turn then you lose a turn\r\n" + 
				"\r\n" + 
				"- If you land on bankrupt you lose a turn AND you get \r\n" + 
				"  bankrupted meaning your balance for that round becomes 0\r\n" + 
				"\r\n" + 
				"- If you choose a letter that can't be found in the given \r\n" + 
				"  phrase or word then it is the next person's turn.\r\n" + 
				"\r\n" + 
				"- You also have the choice to guess the word if you have a \r\n" + 
				"  guess on what the word is. \r\n" + 
				"\r\n" + 
				"- After someone guess's the word correctly they keep the money\r\n" + 
				"  they earned that round however everyone else's balance becomes 0\r\n" + 
				"\r\n" + 
				"- After all 3 rounds whoever was the winner of each round gets \r\n" + 
				"  to keep their total money. \r\n" + 
				"\r\n" + 
				"- You can open leaderboard in the future to check how much money \r\n" + 
				"  you made last time");
		
		//============================================================================================================================================================
		
		//adding the elements to the JFrame/ window
		add(myTitle);
		add(exitBtn);
		add(backBtn);
		add(spotlight1);
		add(spotlight2);
		add(scroll);
		add(backGround);

		//============================================================================================================================================================
		
		//making the buttons into action listeners
		backBtn.addActionListener(this);
		exitBtn.addActionListener(this); 
		
		//============================================================================================================================================================
		
		//setting up window attributes
		setSize(1000, 700); //setting the size of the JFrame (window)
		setVisible(true); //setting the JFrames visibility to true
		setLocation(200, 0); //setting the location of the JFrame on the users desktop
		setResizable(false); //setting the resizability of JFrame to false
		setDefaultCloseOperation(EXIT_ON_CLOSE); //makes it so that when the "x" is pressed on the window, the program ends 
	}//ends main contructor
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == exitBtn) {  //if the exitbtn button is pressed than close the program 
			System.exit(0); //exits system
		}//ends exitbtn if 
		
		if(e.getSource() == backBtn) {  //if the back btn is pressed then go back to the main menu
			WheelOfFortuneMainMenu win5 = new WheelOfFortuneMainMenu(); //opens the main menu window
			super.dispose();//closes/exits the current window open (WheelOfFortuneInstructionsGUI)	
		}//ends backbtn if
		
	}//ends action performed class
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//main method
	public static void main(String[] args) {
		WheelOfFortuneInstructionsGUI menu2= new WheelOfFortuneInstructionsGUI();  //Assigns the entire constructor to the main method, so the program can be displayed
	}//ends main method
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}//ends WheelOFFortuneInstructions GUI class
