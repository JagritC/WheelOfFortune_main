import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Author: Sahib Lubhana and Jagrit Chaitanya
// Date: January 2018
// Description: This is the spinner for the Wheel of Fortune game where all wedges have a value related to the user's balance/turn

// Method List:
// public int getAmount() - This gets the amount the user landed on
// public void setAmount(int amount) - This sets the amount
// public Spinner() - Constructor method
// public static void main(String[] args) - Main method with JFrame
// public void paintComponent (Graphics g) - Paint method that makes the spinner work
// public void setIntI(int i) - Setting the angle value
// public void getIntI - Getting the angle value
// public BufferedImage LoadImage (String fileName) - Method to load images
// public int monetaryAssigner - Method to assign a value to the wedges that are landed on

public class Spinner extends JPanel { 

	private int i, j;
	private int amount; // Private data

	public int getAmount() { // Method to get the amount landed on

		return amount;

	}

	public void setAmount(int amount) { // Method to set the amount

		this.amount = amount;

	}

	private int random;
	private int [] values = {396, 432, 468, 504, 540, 576, 612, 648, 684, 720}; // These are the angles that the circle will spin towards in order to randomly get a value

	public Spinner () {
		random = (int) (Math.random()*values.length);
		i = values [random];
		j = 0; // These are the variables that will be used to help determine where the wheel lands (further explained in video)
	}

	public static void main(String[] args) { // Main method with JFrame

		Spinner test = new Spinner();

		JFrame F = new JFrame();
		F.add(test);
		F.setSize(511, 530);
		F.setVisible(true);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(test.monetaryAssigner());

	}

	public void paintComponent (Graphics g) {

		// We did not learn about Graphics2D in class, and me and Jagrit learned about this information from:
		// https://www.youtube.com/watch?v=vHfGiTFWoc4
		
		if (Math.toRadians(j) <= Math.toRadians(this.i)) { // This statement has a value that the spinner will spin to, as well as a limit to where it can spin (as long as the angle is less than that of i, which is the limit of the wheels spin, then the wheel will spin)

			BufferedImage spinner = LoadImage("Spinner.png");
			BufferedImage pointer = LoadImage("Pointer.png"); // Loading images to be used in the spinner
			AffineTransform at = AffineTransform.getTranslateInstance(-10, 0);
			AffineTransform at2 = AffineTransform.getTranslateInstance(215, -15); // Setting the locations of the two pictures, where at is the Spinner.png, and at2 is the Pointer.png

			at.rotate(Math.toRadians(j+=5), spinner.getWidth()/2, spinner.getHeight()/2); // Rotating the wheel, increasing by 3 radians each time, and halving both the width and height to make it rotate on it's center

			Graphics2D g2d = (Graphics2D) g; 
			Graphics2D g2d2 = (Graphics2D) g; // Making two variables of Graphics 2D to use for the spinner and wheel

			g2d.drawImage(spinner, at, null); // Assigning the spinner to at and drawing it
			g2d2.drawImage(pointer, at2, null); // Assigning the pointer to at2 and drawing it

			repaint(); // As long as the if-statement is met, it will keep repainting
		}

	}

	public void setintI (int i) { // Method to set the limit of the wheel to spin to

		this.i = i;

	}

	public int getintI() { // Getting the value of the angle that the spinner can rotate to

		return this.i;

	}

	public BufferedImage LoadImage (String fileName) { // Method to load image
		// This was learned at: https://www.youtube.com/watch?v=vHfGiTFWoc4

		BufferedImage img = null;

		try 

		{

			img = ImageIO.read(new FileInputStream("res/" + fileName));

		} 

		catch (IOException e) {

		}

		return img;

	}

	public int monetaryAssigner () { // Method to assign a value to each angle that i can be. Each angle/value of i is a wedge on the wheel, so for example, if it lands on 468 degrees,
		// then that would be the 2400 dollar wedge, so the amount returned to the user will be 2400

		if (this.i == 396) {

			this.amount = 500;

		}

		else if (this.i == 432) {

			this.amount = -5;

		}

		else if (this.i == 468) {

			this.amount = 2400;

		}

		else if (this.i == 504) {

			this.amount = 100;

		}

		else if (this.i == 540) {

			this.amount = 1200;

		}

		else if (this.i == 576) {

			this.amount = 400;

		}

		else if (this.i == 612) {

			amount = 10000;

		}

		else if (this.i == 648) {

			this.amount = -1;

		}

		else if (this.i == 684) {

			this.amount = 800;

		}

		else if (this.i == 720) {

			this.amount = 200;

		}

		return this.amount;

	}

}