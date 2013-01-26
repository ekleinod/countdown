import javax.swing.UIManager;
import java.awt.*;

/**
 * This is the Countdown Timer Version&nbsp;0.2: main class.
 * <p>
 * The Countdown Timer counts from a given time backwards to zero.
 * Reaching zero it can play a sound, count upwards or end itself.
 * </p>
 * <p>
 * This class initializes the frame (ConfigureTimer) and displays it.
 * </p>
 * <p>
 * Call:<br />
 * <code>java -jar CountdownTimer.jar</code><br />
 * <code>java -jar CountdownTimer.jar config.ct</code>
 * </p>
 * 
 * @author Mustafa Hamdani (V 0.1), Ekkart Kleinod (V 0.2-0.3)
 * @version 0.2
 * @since 0.1
 */
public class CountdownTimer {
	
	/** Flag, if the Frame should be visually packed. */ 
	private boolean packFrame = false;
	
	/** Application name. */
	public final static String APP_NAME = "Countdown Timer";

	/** Version. */
	public final static String APP_VERSION = "0.3";

	/**
	 * Constructs the application.
	 * <br />
	 * Therefore create a ConfigureTimer and display it.
	 * 
	 * @param strConfigFile configuration file; null if there is none
	 */ 
	public CountdownTimer(String strConfigFile) {
		
		ConfigureTimer frame = new ConfigureTimer(strConfigFile);
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();

		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	/**
	 * Calls the Constructor.
	 * <br />
	 * If a file was given as second argument, open it as settings file.  
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// two arguments: open file; one argument: normal start
		if (args.length > 0) {
			new CountdownTimer(args[0]);
		} else {
			new CountdownTimer(null);
		}
	}
	
}
