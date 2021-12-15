import java.awt.BorderLayout;

import java.awt.Image;
import java.awt.Insets;
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

	private JLabel label1;
	private JLabel label2;
	private JFrame frame;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JButton button;

	public GUI(String recipeName, String[] recipeIngredients, String recipeSourceUrl, String recipeImageUrl) {
		// Create frame
		frame = new JFrame();
		
		// Create left panel
		panelLeft = new JPanel(new GridBagLayout());
		frame.add(panelLeft, BorderLayout.WEST);
        panelLeft.setBorder(BorderFactory.createTitledBorder("Your Recipe"));
        
        // Create button to open web site with recipe
        button = new JButton(recipeName + " - Click to open");
		button.addActionListener(this);
        
		// Adjust format of button
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);
        buttonConstraints.weighty = 5;
        buttonConstraints.gridy = 0;
		
        // Add button to panel
		panelLeft.add(button, buttonConstraints);
		
		// Create label for recipe ingredients
		String ingredientsString = "<br>";
		for (int i = 0; i < recipeIngredients.length; i++) {
			ingredientsString = ingredientsString + "<br>" + "- " + recipeIngredients[i];
		}
		
		label1 = new JLabel("<html>" + "Recipe Ingredients" + ingredientsString + "</html>");
		
		// Adjust format of ingredient list
        GridBagConstraints label1Constraints = new GridBagConstraints();
        label1Constraints.anchor = GridBagConstraints.WEST;
        label1Constraints.gridy = 1;
        label1Constraints.weightx = 1;
        label1Constraints.weighty = 1;
        label1Constraints.insets = new Insets(10, 10, 10, 10);
		
		// Add label to panel
		panelLeft.add(label1, label1Constraints);	

		// Create right panel
		panelRight = new JPanel(new GridBagLayout());
		frame.add(panelRight, BorderLayout.EAST);
		panelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Create label for picture
        label2 = new JLabel(DisplayImage(recipeImageUrl));
        
        // Adjust format of picture label
        GridBagConstraints label2Constraints = new GridBagConstraints();
        label2Constraints.anchor = GridBagConstraints.WEST;
        label2Constraints.gridx = 0;
        label2Constraints.gridy = 0;
        label2Constraints.weightx = 0.5;
        label2Constraints.weighty = 1;
        label2Constraints.insets = new Insets(5, 10, 5, 10);
        
        // /Add label to panel
		panelRight.add(label2, label2Constraints);
		
		// Pack and close frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("What should I make");
		frame.pack();
		frame.setVisible(true);
	}
	
	// to be called to run the GUI
	public static void runGUI(String recipeName, String[] recipeIngredients, String recipeSourceUrl, String recipeImageUrl) {
		new GUI(recipeName, recipeIngredients, recipeSourceUrl, recipeImageUrl);
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
		int newHeight = 400; // depends on final format

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
		String urlString;
		openWebPage(urlString);
	}

}