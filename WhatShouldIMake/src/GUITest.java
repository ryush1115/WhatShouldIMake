import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GUITest {	

	@Test
	public void displayImageTest() {

		// test original size
		Image image = null;
		try {
			URL url = new URL("https://www.edamam.com/web-img/6bd/6bde8749b7c9c88c1049696324502701.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(image);

		assertEquals(300, icon.getIconHeight());

		// test new size
		assertEquals(400, GUI.displayImage("https://www.edamam.com/web-img/6bd/6bde8749b7c9c88c1049696324502701.jpg").getIconHeight());
	}

}
