import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextPicture extends Picture {

	private String text;    // Data for Text and Font
	private Font myFont;
	
	//default Constructor
	public TextPicture() {
		super();
		this.myFont = new Font("Arial", Font.PLAIN, 12);
		this.text = "";
	}
	
	// Constructor to for specific location and title
	public TextPicture(String title, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.myFont = new Font("Arial", Font.PLAIN, 12);
		this.text = title;
		repaint();
	}
	
	// setter for font
	public void setFont(Font f) {
		this.myFont = f;
	}

	// paint method
	public void paint (Graphics g) {
		g.setColor (this.getColor());
		g.setFont(this.myFont);
		g.drawString(this.text, getxPos(), getyPos());
	}
	
	public static void main(String[] args) {
		// self testing main method;            
		JFrame f = new JFrame("Testing");
		
		String title = "My title";
		
		TextPicture p = new TextPicture(title, 10, 20);
		
		p.setFont(new Font("Arial", Font.PLAIN, 24));
		
		f.setSize(400,350);  // size for graphics
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(Color.BLUE);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(50,50,120);  
		
		JOptionPane.showMessageDialog(null,"Wait");

		p.setxPos(100);
		p.setyPos(100);
		
		p.repaint();  // repaint picture
	}
}
