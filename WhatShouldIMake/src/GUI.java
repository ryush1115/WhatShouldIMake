import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Desktop;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Creates User Interface
 * @author lisaf
 *
 */
public class GUI implements ActionListener {

	private String urlString;
	private int count = 0;
	private JLabel label1;
	private JLabel label2;
	private JFrame frame;
	private JPanel panel;
	private JButton button;

	public GUI() {
		frame = new JFrame();
		panel = new JPanel();

		button = new JButton("Open recipe");
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(40, 40));

		label1 = new JLabel("Recipe name"); // Recipe.getName() - needs to be static
		label2 = new JLabel(DisplayImage("https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg")); // Recipe.getIamgeUrl() - needs to be static

		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridLayout(0, 1)); // figure out what best format is
		panel.add(label1);
		panel.add(button);
		panel.add(label2);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("What should I make");
		frame.pack();
		frame.setVisible(true);
	}
	
	// delete and use in main class
	public static void main(String[] args) {
		new GUI();

	}

	/**
	 * Create ImageIcon from image URL given as string and resizes
	 * @param imageURL
	 * @return image as icon
	 */
	public static ImageIcon DisplayImage(String imageURL) {

		// Import image from URL
		Image image = null;
		try {
			URL url = new URL(imageURL);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Adjust size of image
		ImageIcon icon1 = new ImageIcon(image);
		int width = icon1.getIconWidth();

		int height = icon1.getIconHeight();

		Double ratio = ((double) width) / ((double) height);
		int newHeight = 600; // depends on final format

		Double newWidth1 = (newHeight * ratio);
		int newWidth = newWidth1.intValue();
		ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));

		return icon;
	}


	// methods to open URL
	/**
	 * checks if desktop supported
	 * @param uri
	 * @return true if desktop is supported
	 */
	public static boolean openWebPage(URI uri) {

		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * open a web site from URL given in String-format
	 * @param urlString
	 * @return opens the WebPage, else returns false
	 */
	public static boolean openWebPage(String urlString) {
		try {
			URL url;
			try {
				url = new URL(urlString);
				return openWebPage(url.toURI());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return false;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * assigns action to be performed by button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		openWebPage("https://www.google.com"); // Recipe.getSourceUrl() - sourceURL needs to be static
	}

}
