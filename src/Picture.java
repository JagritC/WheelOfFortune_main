import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Campos
 * @version 1.00 2017/10/12
 * Description: Draws a pictures on JFrame
 */
public class Picture extends JComponent{

	// private data
	private Color c;
	public Color getColor() {
		return c;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private int xPos, yPos, width, height;
	
	/**
	 * default constructor
	 */
	public Picture() {
		this.c = Color.RED;
		this.xPos = 0;
		this.yPos = 0;
		this.width = 50;
		this.height = 25;
		repaint();
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
		repaint();
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
		repaint();
	}

	public int getMyWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
		repaint();
	}

	public int getMyHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
		repaint();
	}

	/*
	 * New constructor with location and color
	 * 
	 */
	public Picture (int xPos, int yPos, int width, int height) {
		this.c = Color.RED;
		setxPos(xPos);
		setyPos(yPos);
		setWidth(width);
		setHeight(height);
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(this.c);
		g.drawRect(getxPos(), getyPos(), getMyWidth(), getMyHeight());
	}
	
	public void setColor(Color c) {
		this.c = c;
		repaint();
	}
	
	public void setColor(int r, int g, int b) {
		this.c = new Color(r,g,b);
		repaint();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame f = new JFrame();
		Picture p = new Picture();
		
		f.setSize(400, 350);
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "wait");
		p.setColor(Color.BLUE);
		
		JOptionPane.showMessageDialog(null, "wait");
		p.setColor(50, 50, 120);
		
		JOptionPane.showMessageDialog(null, "wait");
		Picture p1 = new Picture (200, 150, 100, 50);
		
		f.add(p1);
		f.setVisible(true);
		
	}

}
