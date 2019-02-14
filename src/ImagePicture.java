import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author p0067706
 *
 */
public class ImagePicture extends Picture{

	private ImageIcon img;
	/**
	 * 
	 */
	public ImagePicture(ImageIcon img) {
		super();
		setxPos(0);
		setyPos(0);
		this.img = img;
		setWidth (this.img.getIconWidth());
		setHeight(this.img.getIconHeight());
		repaint();
	}
	
	 /**
	 * @param img
	 * @param x
	 * @param y
	 * second constructor - overloads the previous 
	 */
	public ImagePicture (ImageIcon img, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.img = img;
		setWidth (this.img.getIconWidth());
		setHeight(this.img.getIconHeight());
		repaint();
	}

	public void paint (Graphics g) {
		this.img.paintIcon(this, g, getxPos(), getyPos());
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		ImagePicture p = new ImagePicture(new ImageIcon("background2.jpg"));
		
		f.setSize(400,  350);
		
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "Wait");
		
		ImagePicture p1 = new ImagePicture(new ImageIcon("background2.jpg"), 100, 100);
		f.add(p1);
		
		f.setVisible(true);
		


	}

}
