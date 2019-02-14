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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author Jimit Soni
 * Date: January, 2018 
 * Description: Main menu GUI for the user to interact with and either start the game, open the instructions, open the leaderboards or exit
 * 
 * Method list: public WheelOfFortuneMainMenu(), public void actionPerformed(ActionEvent e), public static void main(String[] args)
 */

public class WheelOfFortuneMainMenu extends JFrame implements ActionListener {

	//delcaring private variables
	private ImagePicture myTitle; 
	private JLabel backGround, wheel1, wheel2, spotlight1, spotlight2, box;
	private JButton playBtn, exitBtn, instructionBtn, leaderboardBtn;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public WheelOfFortuneMainMenu() { //main constructor
		super("Wheel Of Fortune Main Menu"); //sets up the window name
		 setLayout(null); //sets the layout for the window to null
		 
		 	//innitializing variables
			try {
				myTitle = new ImagePicture(new ImageIcon(ImageIO.read(new FileInputStream("res/title.png"))));
			} catch (IOException e) {
				System.out.println("myTitle ImageIcon Error");
			}
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
				System.out.println("jLabel ImageIcon Error");
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
			
			
			
		 	playBtn = new JButton("Start Game"); 
			exitBtn = new JButton("Exit");
			instructionBtn = new JButton("Game Instructions");
			leaderboardBtn = new JButton("Leaderboard");

			//declaring and initializing border
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1); //declares and initialized the border
			
			//setting up button colours
			Color userCol = new Color(247, 227, 79); //Initializing and declaring the RGB colors
			Color contCol = new Color(79, 247, 101);
			Color exitCol = new Color(255, 68, 68);
			
			//setting the bounds and color for the buttons		
			leaderboardBtn.setBounds(400, 325, 200, 50);
			leaderboardBtn.setBackground(userCol);
			instructionBtn.setBounds(400, 400 , 200, 50);
			instructionBtn.setBackground(userCol);
			playBtn.setBounds(400, 250, 200, 50);
			playBtn.setBackground(contCol);
			exitBtn.setBounds(400, 475, 200, 50);
			exitBtn.setBackground(exitCol);
			
			//setting up bounds for the jlabels
			backGround.setBounds(0, 0, 1000, 700); 
			wheel1.setBounds(-10,400, 520, 700); 
			wheel2.setBounds(490,400, 520, 700); 
			spotlight1.setBounds(-60, -130, 450, 450); 
			spotlight2.setBounds(610, -130, 450, 450); 
			box.setBounds(240,203,522,360);
			
			//setting up bounds for the title
			myTitle.setBounds(170, -40 ,652, 309); 
			
			//setting up borders for variables
			exitBtn.setBorder(border); 
			playBtn.setBorder(border);
			instructionBtn.setBorder(border);
			leaderboardBtn.setBorder(border);
			
			//adding variables to the GUI/ window
			add(myTitle);
			add(exitBtn);
			add(playBtn);
			add(instructionBtn);
			add(leaderboardBtn);
			add(wheel1);
			add(wheel2);
			add(spotlight1);
			add(spotlight2);
			add(box);
			add(backGround);
			
			//making the buttons into action listeners
			playBtn.addActionListener(this);
			exitBtn.addActionListener(this); 
			instructionBtn.addActionListener(this); 
			leaderboardBtn.addActionListener(this); 
			
			//setting up window attributes
			setSize(1000, 700); //setting the size of the JFrame (window)
			setVisible(true); //setting the JFrames visibility to true
			setLocation(200, 0); //setting the location of the JFrame on the users desktop
			setResizable(false); //setting the resizability of JFrame to false
			setDefaultCloseOperation(EXIT_ON_CLOSE); //makes it so that when the "x" is pressed on the window, the program ends
			
	}//ending constructor
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == exitBtn) {  //if the exitbtn button is pressed than close the program 
			System.exit(0); //exits system
		} //ends exitButton if
		
		
		if(e.getSource() == playBtn) {  //if the exitbtn button is pressed than close the program 
			WheelOfFortunePlayerInfo win2 = new WheelOfFortunePlayerInfo();
			super.dispose();

		}//ends playBtn if
		
		if(e.getSource() == leaderboardBtn) {  //if the exitbtn button is pressed than close the program 
			Leaderboard ldrboard = new Leaderboard();
			super.dispose();
		}//ends leaderBoardbtn if
		
		if(e.getSource() == instructionBtn) {  //if the exitbtn button is pressed than close the program 
			WheelOfFortuneInstructionsGUI win2 = new WheelOfFortuneInstructionsGUI();  // create a new MainWindow
	         super.dispose();  // kill window 2 
		}//ends instructionBtn if
		
	} //ends actionPerformed method

//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * @param args
	 */
	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WheelOfFortuneMainMenu myProgramMenu = new WheelOfFortuneMainMenu();  //Assigns the entire constructor to the main method, so the program can be displayed
		
	}//ends main method
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}//ends WheelOfFortuneMainMenu class
