import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Die Klasse kümmert sich um Belange des Bilderholens.
 * <br />
 * Geht mit normalen Bildern und SVG-Bildern.
 * 
 * @author ekleinod
 * @version 0.2
 * @since 0.1
 */
public class ImageHandler {
	
	/** Wert für Originalmaße. */
	private final static int ORIGINAL = -1;

	/**
	 * Die Methode gibt ein ImageIcon zurück, dass die angegebene Datei als Bild enthält.
	 * 
	 * @param objCalling aufrufendes Objekt, für den <code>getClass</code>-Aufruf nötig, sonst kann das wegfallen 
	 * @param strFilename Dateiname des Bilds
	 * @return Bild-Objekt oder leeres Objekt, falls ein Fehler auftrat
	 */
	public static ImageIcon getImageIcon(Object objCalling, String strFilename) {
		
		return getImageIcon(objCalling, strFilename, ORIGINAL, ORIGINAL);
		
	}
	
	/**
	 * Die Methode gibt ein ImageIcon zurück, dass die angegebene Datei als Bild enthält.
	 * 
	 * @param objCalling aufrufendes Objekt, für den <code>getClass</code>-Aufruf nötig, sonst kann das wegfallen 
	 * @param strFilename Dateiname des Bilds
	 * @return Bild-Objekt oder leeres Objekt, falls ein Fehler auftrat
	 */
	public static ImageIcon getImageIcon(Object objCalling, String strFilename, int iWidth, int iHeight) {
		
		ImageIcon imgReturn = null;

		// einfache Lösung, die in jars versagt: imgReturn = new ImageIcon(strFilename);
		// daher komplizierte Lösung über URLs
		try {

			URL urlFile = null;
			
			// normale Datei und Ressource versuchen
			File fleTemp = new File(strFilename);
			if (fleTemp.exists()) {
				urlFile = fleTemp.toURL();
			} else {
				strFilename = "/" + strFilename;
				urlFile = objCalling.getClass().getResource(strFilename);
			}
			
			// wieder nix: Fehler
			if (urlFile == null) {
				throw new IOException("Datei konnte nicht gefunden werden.");
			}
			
			imgReturn = new ImageIcon(urlFile);
			if (iWidth != ORIGINAL) {
				imgReturn = new ImageIcon(imgReturn.getImage().getScaledInstance(
					iWidth, iHeight, Image.SCALE_DEFAULT));
			}
			
		} catch (IOException e) {
			imgReturn = new ImageIcon();
			System.err.println("Bild '" + strFilename + "' konnte nicht geladen werden: " + e.getMessage());
		}
		
		return imgReturn;
		
	}
	
}
