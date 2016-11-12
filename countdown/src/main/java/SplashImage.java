import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * This is the Countdown Timer Version&nbsp;0.2: splash image.
 * <p>
 * The Countdown Timer counts from a given time backwards to zero. Reaching zero
 * it can play a sound, count upwards or end itself.
 * </p>
 * <p>
 * This class is the display for the splash image.
 * </p>
 * 
 * @author Mustafa Hamdani (V 0.1), Ekkart Kleinod (V 0.2)
 * @version 0.2
 * @since 0.1
 */
public class SplashImage extends JDialog {
	
	/** serial ID of java 1.5. */
	private static final long serialVersionUID = 5899552642510684736L;

	/**
	 * Constructor, initializes dialog.
	 * 
	 * @param strImage image to be shown
	 * @param bMaximize true == maximize splash; false == original size
	 */ 
	public SplashImage(String strImage, boolean bMaximize) {
		super();
		Dimension dimSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		try {
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			ImageIcon imgSplash = null;
			if (bMaximize) {
				imgSplash = ImageHandler.getImageIcon(this, 
						strImage, (int) dimSize.getWidth(), (int) dimSize.getHeight());
			} else {
				imgSplash = ImageHandler.getImageIcon(this, strImage);
			}
	        getContentPane().add(new JLabel(imgSplash));
	        
	        setUndecorated(true);
	        setAlwaysOnTop(true);
	        setModal(true);
			pack();
			setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
