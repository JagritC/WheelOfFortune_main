import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Jimit Soni
 * Date: January, 2018
 * Description: Welcome menu for the game GUI (start with this class first)
 * 
 * Method List: public Welcome (), public void actionPerformed (ActionEvent e), public static void main (String[] args)
 */

public class Welcome extends JFrame implements ActionListener
{
	//declaring variables  
	JButton btnContinue;
	JLabel lblGif;

	public Welcome ()
	{
		super ("Welcome");// Set the frame's name
		setSize (720, 570);// Set the frame's size
		setLayout (null);

		//innitializing the variables
		btnContinue = new JButton ("Continue"); 
		btnContinue.setBackground(new Color(73, 226, 109));
		try {
			lblGif = new JLabel(new ImageIcon(ImageIO.read(new FileInputStream("res/welcome.gif"))));
		} catch (IOException e) {
			System.out.println("lblGif ImageIcon Error");
		}


		//setting bounds
		btnContinue.setBounds (600, 500, 100, 20);
		lblGif.setBounds(0,0, 720, 540);

		//adding items to the JFrame
		add (btnContinue);
		add(lblGif);

		//makes the continue buttons as a action listener
		btnContinue.addActionListener (this);

		setLocation(200, 0);

		setVisible (true); // Show the frame
	} // ends Constructor

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource()==btnContinue) //if the continue button is pressed
		{   
			WheelOfFortuneMainMenu win1 = new WheelOfFortuneMainMenu();  // create a new MainWindow
			super.dispose();  // kill window 2      
		}

	}

	public static void main (String[] args)
	{
		new Welcome (); //Run the Welcome class
	} // main method

} //Ends Welcome class
