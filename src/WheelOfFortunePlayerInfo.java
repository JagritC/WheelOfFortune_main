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
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author Jimit Soni
 * Date: January, 2018
 * Description: GUI for player to put in information about themselves including info on name, age and gender which are used for the leaderbaord
 * and player identification purposes when playing the game
 * 
 * Method List: public WheelOfFortunePlayerInfo(), public void actionPerformed(ActionEvent e), public static void main(String[] args)
 */

public class WheelOfFortunePlayerInfo extends JFrame implements ActionListener{

	//private data
	private ImagePicture myTitle; 
	private JLabel backGround, player1, player2;
	private JButton continuebtn, exitbtn, backbtn, setName1btn, setName2btn, setAge1btn, setAge2btn, setGender1btn, setGender2btn;
	private JTextField name1Input, name2Input, age1Input, age2Input, gender1Input, gender2Input;
	private PlayerRecord playerRecord1, playerRecord2;
	private JTextArea txtOutput;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//main constructor to set up window
	public WheelOfFortunePlayerInfo() {
		
		super("Galaxy Game Main Menu"); //sets up the window name
		setLayout(null); //sets the layout for the window to null

		try {
			myTitle = new ImagePicture(new ImageIcon(ImageIO.read(new FileInputStream("res/title3.png"))));
		} catch (IOException e1) {
			System.out.println("myTitle ImageIcon Error");
		} //declares and initialized the title
		
		//initializes the jLabels for the pictures
		try {
			backGround = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/background.jpg"))));
		} catch (IOException e) {
			System.out.println("backGround ImageIcon Error!");
		}
		try {
			player1 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/player1.png"))));
		} catch (IOException e) {
			System.out.println("player1 ImageIcon Error!");
		}
		try {
			player2 = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/player2.png"))));
		} catch (IOException e) {
			System.out.println("player2 ImageIcon Error!");
		}
		
		//initializes two player records
		playerRecord1 = new PlayerRecord();
		playerRecord2 = new PlayerRecord();
		
		//initializes the jButtons
		continuebtn = new JButton("Continue"); 
		exitbtn = new JButton("Exit");
		backbtn = new JButton("Back to Menu");
		setName1btn = new JButton("Set Player #1 Name");
		setName2btn = new JButton("Set Player #2 Name");
		setAge1btn = new JButton("Set Player #1 Age");
		setAge2btn = new JButton("Set Player #2 Age");
		setGender1btn = new JButton("Set Player #1 Gender");
		setGender2btn = new JButton("Set Player #2 Gender");
		
		//initializes the jTextfields for the player names
		name1Input = new JTextField("Player 1"); 
		name2Input = new JTextField("Player 2");
		
		//initializes the jTextfields for the player age
		age1Input = new JTextField("18");
		age2Input = new JTextField("18");
		
		//initializes the jTextfields the player genders
		gender1Input = new JTextField("Male");
		gender2Input = new JTextField("Female");
		
		//initializes the jTextArea for the outputs confirming the actions
		txtOutput = new JTextArea();
		
		//declares and initializes the border 
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		//initializing and declaring the RGB colours
		Color userCol = new Color(247, 227, 79); 
		Color contCol = new Color(79, 247, 101);
		Color exitCol = new Color(255, 68, 68);
		Color backCol = new Color(130, 168, 229);
		
		//============================================================================================================================================================
		
		//setting the bounds for the title
		myTitle.setBounds(110, -60, 1000, 228); 
		
		//setting the bounds for the jLabels/ pictures
		backGround.setBounds(0, 0, 1000, 700); 
		player1.setBounds(95, 200, 128, 128);
		player2.setBounds(775, 200, 128, 128);

		//setting the bounds and setting the colours for the buttons
		setName1btn.setBounds(85, 395, 150, 25);
		setName1btn.setBackground(userCol);
		setName2btn.setBounds(750, 395, 150, 25 );
		setName2btn.setBackground(userCol);
		setAge1btn.setBounds(85, 475, 150, 25);
		setAge1btn.setBackground(userCol);
		setAge2btn.setBounds(750, 475, 150, 25);
		setAge2btn.setBackground(userCol);
		setGender1btn.setBounds(85, 550, 155, 25);
		setGender1btn.setBackground(userCol);
		setGender2btn.setBounds(750, 550, 155, 25);
		setGender2btn.setBackground(userCol);
		continuebtn.setBounds(300, 570, 100, 25);
		continuebtn.setBackground(contCol);
		exitbtn.setBounds(575, 570, 100, 25);
		exitbtn.setBackground(exitCol);
		backbtn.setBounds(435, 570, 100, 25);
		backbtn.setBackground(backCol);
		
		//setting the bounds for the JTextFields
		name1Input.setBounds(60, 350, 200, 25); 
		name2Input.setBounds(725, 350, 200, 25);		
		age1Input.setBounds(60, 435, 200, 25);
		age2Input.setBounds(725, 435, 200, 25);
		gender1Input.setBounds(60, 510, 200, 25);
		gender2Input.setBounds(725, 510, 200, 25);
	
		//setting the border for the buttons
		setName1btn.setBorder(border); 
		setName2btn.setBorder(border);
		setAge1btn.setBorder(border);
		setAge2btn.setBorder(border);
		continuebtn.setBorder(border);
		exitbtn.setBorder(border);
		backbtn.setBorder(border);

		//changing the borders values
		border = BorderFactory.createLineBorder(Color.BLACK, 2); 
		
		//setting up the border for the JTextFields
		name1Input.setBorder(border);
		name2Input.setBorder(border);
		age1Input.setBorder(border);
		age2Input.setBorder(border);
		gender1Input.setBorder(border);
		gender2Input.setBorder(border);
		
		//============================================================================================================================================================
		
		//changing the JTextArea into a scrollable interface
		
		border = BorderFactory.createLineBorder(Color.BLACK, 5); //changing the border values
		
		Color outputCol = new Color(171, 135, 255); //Initializing and declaring the RGB color

		JScrollPane scroll = new JScrollPane(txtOutput); //creating a new JScrollPane to make the txtOutput scrollable
		
		scroll.setBounds(365, 200, 250, 300); //setting the bounds for the JTextArea
		
		scroll.setBorder(border); //setting the border on the scroll
		
		txtOutput.setBackground(outputCol); //setting the background colour for the JTextArea
		
		txtOutput.setEditable(false); //setting the JTextArea to not be editable
		
		
		//============================================================================================================================================================
		
		//adding the elements to the JFrame/ window
		add(myTitle); 
		add(player1);
		add(player2);
		add(name1Input);
		add(name2Input);
		add(age1Input);
		add(age2Input);
		add(gender1Input);
		add(gender2Input);
		add(continuebtn);
		add(exitbtn);
		add(backbtn);
		add(setName1btn);
		add(setName2btn);
		add(setAge1btn);
		add(setAge2btn);
		add(setGender1btn);
		add(setGender2btn);
		add(scroll);
		add(myTitle);
		add(backGround);

		
		//============================================================================================================================================================
		
		//making the buttons into action listeners
		continuebtn.addActionListener(this);
		exitbtn.addActionListener(this); 
		backbtn.addActionListener(this);
		setName1btn.addActionListener(this); 
		setName2btn.addActionListener(this); 
		setAge1btn.addActionListener(this);
		setAge2btn.addActionListener(this); 
		setGender1btn.addActionListener(this);
		setGender2btn.addActionListener(this);
		

		//============================================================================================================================================================
		
		//setting up window attributes
		setSize(1000, 650); //setting the size of the JFrame (window)
		setVisible(true); //setting the JFrames visibility to true
		setLocation(200, 0); //setting the location of the JFrame on the users desktop
		setResizable(false); //setting the resizability of JFrame to false
		setDefaultCloseOperation(EXIT_ON_CLOSE); //makes it so that when the "x" is pressed on the window, the program ends
	} //ends constructor
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void actionPerformed(ActionEvent e) {

			if (e.getSource() == continuebtn) {  //if the enter button is pressed open the next Window (GalaxyGame) 
				
				wheelOfFortuneGUI game = new wheelOfFortuneGUI();
				game.setVisible(true);
				
				game.setPlayer1(playerRecord1);
				
				game.setPlayer2(playerRecord2);
				
				super.dispose();
				
			} //ends continuebtn if

			//============================================================================================================================================================
			
			if(e.getSource() == exitbtn) {  //if the exitbtn button is pressed than close the program 
				System.exit(0); //exits system
			} //ends exitbtn if 
		
			//============================================================================================================================================================
			
			if(e.getSource() == backbtn) {  //if the back button is pressed than go back to the menu
				  WheelOfFortuneMainMenu win6 = new WheelOfFortuneMainMenu();  //create a new MainWindow
				  super.dispose(); //kill window   
			} //ends backbtn if 
		
			//============================================================================================================================================================
			
			if (e.getSource() == setName1btn) { //if the setName1btn is pressed then set the name of the player1 to the name the user inputed
				
				String name1 = "";	//declares and initialized string name1
				
				name1 = name1Input.getText(); //sets name1 to the text in the JTextfield
				
				txtOutput.append("Player 1 Name = " + name1 + "\n"); //appends a corresponding message in the JTextArea
				
				//System.out.println(name1); //used for debugging proccesses 
				
				playerRecord1.setName(name1); //sets the name in the playerRecord for player 1 to the name assignmed the user has inputed	
			}//ends setName1btn if
			
			//============================================================================================================================================================
			
			if (e.getSource() == setName2btn ) {  //if the setName2btn is pressed then set the name of the player2 to the name the user inputed
				
				String name2 = "";	//declares and initialized string name2
				
				name2 = name2Input.getText(); //sets name2 to the text in the JTextfield
				
				txtOutput.append("Player 2 Name = " + name2 + "\n"); //appends a corresponding message in the JTextArea
				
				//System.out.println(name2); //used for debugging proccesses 
				
				playerRecord2.setName(name2); //sets the name in the playerRecord for player 2 to the name assignmed the user has inputed	

			}//ends setName2btn if
			
			//============================================================================================================================================================
			
			if (e.getSource() == setAge1btn) { //if setAge1btn is pressed then set the age of player 1 to the age the user has given (also assign age to the playerRecord)				
				String agePlayer1 = ""; //declares and initialized string agePlayer1
				
				agePlayer1 = age1Input.getText(); //sets agePlayer1 to the text in the JTextfield
			
				txtOutput.append(name1Input.getText() + "'s age is set too: " + agePlayer1 + "\n"); //appends a corresponding message in the JTextArea
				
				//System.out.println(agePlayer1); //used for debugging proccesses 
				
				playerRecord1.setAge(agePlayer1); //sets the age of player 1 in the playerRecord to the age the user has entered
			}//ends setAge1btn if
		
			//============================================================================================================================================================
			
			if (e.getSource() == setAge2btn) { //if setAge2btn is pressed then set the age of player 2 to the age the user has given (also assign age to the playerRecord)				
				
				String agePlayer2 = ""; //declares and initialized string agePlayer1
				
				agePlayer2 =  age2Input.getText(); //sets agePlayer1 to the text in the JTextfield
				
				txtOutput.append(name2Input.getText() + "'s age is set too: " + agePlayer2 + "\n"); //appends a corresponding message in the JTextArea
						
				//System.out.println(agePlayer2); //used for debugging proccesses 
				
				playerRecord2.setAge(agePlayer2); //sets the age of player 2 in the playerRecord to the age the user has entered
				
			}//ends setAge2btn if
			
			//============================================================================================================================================================
			
			if (e.getSource() == setGender1btn) { //if setGender1btn is pressed then set the gender of player 1 to the gender the user has given (also assign gender to the playerRecord)				
   
				
				String genderPlayer1 = ""; //declares and initialized string genderPlayer1
				
				genderPlayer1 =  gender1Input.getText(); //sets genderPlayer1 to the text in the JTextfield
				
				txtOutput.append(name1Input.getText() + "'s gender is set too: " + genderPlayer1 + "\n"); //appends a corresponding message in the JTextArea
						
				//System.out.println(genderPlayer1); //used for debugging proccesses 
				
				playerRecord1.setGender(genderPlayer1); //sets the gender of player 1 in the playerRecord to the gender the user has entered

			}//ends setGender1btn if
			
			//============================================================================================================================================================
			
			if (e.getSource() == setGender2btn) {   //if setGender2btn is pressed then set the gender of player 2 to the gender the user has given (also assign gender to the playerRecord)				
				
				String genderPlayer2 = ""; //declares and initialized string genderPlayer2
				
				genderPlayer2 =  gender2Input.getText(); //sets genderPlayer2 to the text in the JTextfield
				
				txtOutput.append(name2Input.getText() + "'s gender is set too: " + genderPlayer2 + "\n"); //appends a corresponding message in the JTextArea
						
				//System.out.println(genderPlayer2); //used for debugging proccesses 
				
				playerRecord2.setGender(genderPlayer2); //sets the gender of player 2 in the playerRecord to the gender the user has entered

			}//ends setGender2btn= if
			
			//===========================================================================================================================================================
		
			//============================================================================================================================================================
		
	}//ends action performed method
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//main method
	public static void main(String[] args) {
		WheelOfFortunePlayerInfo win6 = new WheelOfFortunePlayerInfo (); //Assigns the entire constructor to the main method, so the program can be displayed

	}//ends main
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------

}//ends WheelOfFortunePlayerInfo class
