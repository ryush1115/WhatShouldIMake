import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class OpenWebPage {

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

}
