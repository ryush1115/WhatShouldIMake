import javax.swing.JFrame;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Displays an image
 * @author lisaf
 *
 */
public class DisplayImage {
	
	public static void DisplayImage(String imageURL) {
		
		// Import image from URL
		Image image = null;
		try {
			URL url = new URL(imageURL);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create frame
		JFrame frame = new JFrame();
		// frame.setSize(600, 600);
		
		// Adjust size of image
		ImageIcon icon1 = new ImageIcon(image);
		int width = icon1.getIconWidth();
		
		int height = icon1.getIconHeight();
		
		Double ratio = ((double) width) / ((double) height);
		int newHeight = 600;
		
		Double newWidth1 = (newHeight * ratio);
		int newWidth = newWidth1.intValue();
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
		
		// Add image icon to frame and display
		JLabel label = new JLabel(icon);
		
		frame.add(label);
		frame.setDefaultCloseOperation
		(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}