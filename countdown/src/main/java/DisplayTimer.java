import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * This is the Countdown Timer Version&nbsp;0.2: time display.
 * <p>
 * The Countdown Timer counts from a given time backwards to zero. Reaching zero
 * it can play a sound, count upwards or end itself.
 * </p>
 * <p>
 * This class is the actual display for the countdown.
 * </p>
 *
 * @author Mustafa Hamdani (V 0.1), Ekkart Kleinod (V 0.2)
 * @version 0.2
 * @since 0.1
 */
public class DisplayTimer extends JDialog {
	
	/** serial ID of java 1.5. */
	private static final long serialVersionUID = -53321692610649523L;
	
	/** Current minute. */
	private int iMinute = 0;
	
	/** Current second. */
	private int iSecond = 0;
	
	/** Size-divisor of caption (height/size). */
	private int iSizeCaption = 13;
	
	/** Size-divisor of counter (height/size). */
	private int iSizeCounter = 3;
	
	/** Size-divisor of description (height/size). */
	private int iSizeDescription = 30;
	
	/** Size-divisor of current time (height/size). */
	private int iSizeCurrentTime = 30;
	
	/** Distance between numbers and colon. */
	private int iColonDistance = 50;
	
	/** Configuration of the timer. */
	private Properties prpConfiguration = null;
	
	/** Text above counter. */
	private JLabel lblCaption = new JLabel();
	
	/** Counter: minutes. */
	private JLabel lblCounterMinutes = new JLabel();
	
	/** Counter: colon. */
	private JLabel lblCounterColon = new JLabel();
	
	/** Counter: seconds. */
	private JLabel lblCounterSeconds = new JLabel();
	
	/** Counter-description: minutes. */
	private JLabel lblDescriptionMinutes = new JLabel();
	
	/** Counter-description: colon. */
	private JLabel lblDescriptionColon = new JLabel();
	
	/** Counter-description: seconds. */
	private JLabel lblDescriptionSeconds = new JLabel();
	
	/** Current time. */
	private JLabel lblCurrentTime = new JLabel();
	
	/** Flag, if overtime should be shown. */
	private boolean bOvertime = false;
	
	/** Flag, if timer decreases at the moment. */
	private boolean bDecreasing = true;
	
	/** Flag, if timer pauses. */
	private boolean bPaused = false;
	
	/** Flag, if timer is currently showing Deciseconds. */
	private boolean bIsShowingDecis = false;
	
	/** Flag, if timer should show Deciseconds. */
	private boolean bShowDeciSeconds = false;
	
	/** Flag, if timer should close when reaching zero. */
	private boolean bDisposeOnZero = false;
	
	/** Thread for playing wavs. */
	private WAVThread thrWavPlay = new WAVThread();
	
	/** Clip for WAV-Playing. */
	public static Clip newClip = null;
	
	/** Central timer. */
	private Timer theTimer = null;
	
	/** Flag, if color should be changed randomly. */
	private boolean bRandomColor = false;
	
	/** Change interval for color. */
	private int iColorChangeInterval = 0;
	
	/** Counter for random color change. */
	private int iRandomColorCounter = 0;
	
	/** Random number generator. */
	private Random theRandom = new Random();
	
	/** Time of last update-call. */
	private int lastTime = 0;
	
	/** Flag, if zero has been reached. */
	private boolean bZeroReached = false;
	
	/** Flag, if wav should be played. */
	private boolean bPlayWav = true;
	
	/** Count time. */
	private int count = 0;
	
	/** Count seconds or deciseconds. */
	private int iCountLimit = 1000;
	
	/**
	 * Constructor, initializes display via initDisplay-method.
	 *
	 * @param prpInit
	 *            configuration of the timer
	 */
	public DisplayTimer(Properties prpInit) {
		try {
			prpConfiguration = prpInit;
			initTimer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes timer incl. configuration.
	 */
	private void initTimer() {
		
		Font fntTemp = null;
		Color clrTemp = null;
		int iYPos = 0;
		
		// size, location, layout, color, other things
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrBack"))));
		setBackground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrBack"))));
		
		// Listeners
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				closeDialog();
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				closeDialog();
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkKey(e);
			}
		});
		
		// set flags and values
		bShowDeciSeconds = Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDeci"));
		bOvertime = Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayOvertime"));
		bRandomColor = Boolean.parseBoolean(prpConfiguration.getProperty("chkRandom"));
		iColorChangeInterval = Integer.parseInt(prpConfiguration.getProperty("intInterval"));
		bDisposeOnZero = Boolean.parseBoolean(prpConfiguration.getProperty("chkDispose"));
		
		// text above counter
		fntTemp = new Font(prpConfiguration.getProperty("cboTextFont"),
				Integer.parseInt(prpConfiguration.getProperty("cboTextFontStyle")), 
				getHeight() / iSizeCaption);
		clrTemp = new Color(Integer.parseInt(prpConfiguration.getProperty("clrText")));
		
		lblCaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaption.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCaption.setSize(getWidth(), fntTemp.getSize());
		lblCaption.setLocation(0, (getHeight() / 5) - (lblCaption.getHeight() / 2));
		lblCaption.setFont(fntTemp);
		lblCaption.setForeground(clrTemp);
		
		// counter
		fntTemp = new Font(prpConfiguration.getProperty("cboNumberFont"),
				Integer.parseInt(prpConfiguration.getProperty("cboNumberFontStyle")),
				getHeight() / iSizeCounter);
		clrTemp = new Color(Integer.parseInt(prpConfiguration.getProperty("clrNumber")));
		iYPos = (getHeight() - fntTemp.getSize()) / 2;
		
		lblCounterMinutes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCounterMinutes.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCounterMinutes.setSize(getWidth() / 2 - iColonDistance, fntTemp.getSize());
		lblCounterMinutes.setLocation(0, iYPos);
		lblCounterMinutes.setFont(fntTemp);
		lblCounterMinutes.setForeground(clrTemp);
		
		lblCounterColon.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterColon.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCounterColon.setSize(this.getWidth(), fntTemp.getSize());
		lblCounterColon.setLocation(0, iYPos);
		lblCounterColon.setFont(fntTemp);
		lblCounterColon.setForeground(clrTemp);
		lblCounterColon.setText(":");
		
		lblCounterSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		lblCounterSeconds.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCounterSeconds.setSize(this.getWidth() / 2, fntTemp.getSize());
		lblCounterSeconds.setLocation(this.getWidth() / 2 + iColonDistance, iYPos);
		lblCounterSeconds.setFont(fntTemp);
		lblCounterSeconds.setForeground(clrTemp);
		
		// description
		fntTemp = new Font(prpConfiguration.getProperty("cboTextFont"), Font.PLAIN, getHeight() / iSizeDescription);
		clrTemp = new Color(Integer.parseInt(prpConfiguration.getProperty("clrNumber")));
		iYPos += lblCounterColon.getHeight();
		
		lblDescriptionMinutes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescriptionMinutes.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblDescriptionMinutes.setSize(getWidth() / 2 - iColonDistance, fntTemp.getSize());
		lblDescriptionMinutes.setLocation(0, iYPos);
		lblDescriptionMinutes.setFont(fntTemp);
		lblDescriptionMinutes.setForeground(clrTemp);
		lblDescriptionMinutes.setVisible(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDescription")));
		
		lblDescriptionColon.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescriptionColon.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescriptionColon.setSize(getWidth(), fntTemp.getSize());
		lblDescriptionColon.setLocation(0, iYPos);
		lblDescriptionColon.setFont(fntTemp);
		lblDescriptionColon.setForeground(clrTemp);
		lblDescriptionColon.setVisible(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDescription")));
		lblDescriptionColon.setText(":");
		
		lblDescriptionSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescriptionSeconds.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDescriptionSeconds.setSize(getWidth() / 2, fntTemp.getSize());
		lblDescriptionSeconds.setLocation(getWidth() / 2 + iColonDistance, iYPos);
		lblDescriptionSeconds.setFont(fntTemp);
		lblDescriptionSeconds.setForeground(clrTemp);
		lblDescriptionSeconds.setVisible(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDescription")));

		// current time
		Date theDate = new Date();
		lastTime = (int) theDate.getTime();
		lblCurrentTime.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).format(theDate));
		lblCurrentTime.setSize(getWidth(), getHeight() / iSizeCurrentTime);
		lblCurrentTime.setLocation(0, getHeight() - lblCurrentTime.getHeight());
		lblCurrentTime.setVisible(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayCurrent")));
		lblCurrentTime.setFont(new Font(prpConfiguration.getProperty("cboTextFont"), Font.PLAIN, getHeight() / iSizeCurrentTime));
		lblCurrentTime.setForeground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrText"))));
		
		// set values
		try {
			iMinute = Integer.parseInt(prpConfiguration.getProperty("txtMinutes"));
		} catch (NumberFormatException e) {
			iMinute = 0;
		}
		try {
			iSecond = Integer.parseInt(prpConfiguration.getProperty("txtSeconds"));
		} catch (NumberFormatException e) {
			iSecond = 0;
		}
		setTime(true);
		
		// layout of components
		getContentPane().add(lblCaption, null);
		getContentPane().add(lblCounterMinutes, null);
		getContentPane().add(lblCounterSeconds, null);
		getContentPane().add(lblCounterColon, null);
		getContentPane().add(lblDescriptionMinutes, null);
		getContentPane().add(lblDescriptionColon, null);
		getContentPane().add(lblDescriptionSeconds, null);
		getContentPane().add(lblCurrentTime, null);
		
		// background image
		if (!prpConfiguration.getProperty("txtBackgroundImage").equalsIgnoreCase("")) {
			ImageIcon imgBack = null;
			if (Boolean.parseBoolean(prpConfiguration.getProperty("chkMaximizeBackground"))) {
				imgBack = ImageHandler.getImageIcon(this, 
						prpConfiguration.getProperty("txtBackgroundImage"), getWidth(), getHeight());
			} else {
				imgBack = ImageHandler.getImageIcon(this, prpConfiguration.getProperty("txtBackgroundImage"));
			}
			JLabel lblBack = new JLabel(imgBack);
			lblBack.setSize(imgBack.getIconWidth(), imgBack.getIconHeight());
			// center image
			lblBack.setLocation((getWidth() - imgBack.getIconWidth()) / 2, (getHeight() - imgBack.getIconHeight()) / 2);
			getContentPane().add(lblBack, null);
		}
		
		// timer (ticks every 50 milliseconds)
		theTimer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTimer();
			}
		});
		theTimer.setRepeats(true);
		theTimer.start();
		
	}
	
	/**
	 * Sets the time on the display, given by the classes variables.
	 *
	 *  @param bUpdateTexts true == update all texts; false == update only counter
	 */
	private void setTime(boolean bUpdateTexts) {
		if (bUpdateTexts) {
			// if Deciseconds should be shown and the time is less than 60 seconds, show them
			if (bShowDeciSeconds && !bIsShowingDecis) {
				if (iMinute == 0) {
					iMinute = iSecond;
					iSecond = 0;
					bIsShowingDecis = true;
				} else if ((iMinute == 1) && (iSecond == 0)) {
					iMinute = 60;
					bIsShowingDecis = true;
				}
			}
			if (bIsShowingDecis) {
				lblDescriptionMinutes.setText("Seconds");
				lblDescriptionSeconds.setText("Tenths");
			} else {
				lblDescriptionMinutes.setText("Minutes");
				lblDescriptionSeconds.setText("Seconds");
			}
			lblCaption.setText((bZeroReached) ? 
					prpConfiguration.getProperty("txtStop") : prpConfiguration.getProperty("txtRunning"));
			
		}
		lblCounterMinutes.setText(Integer.toString(iMinute));
		lblCounterSeconds.setText(((iSecond < 10) && !bIsShowingDecis) ? "0" + Integer.toString(iSecond) : Integer.toString(iSecond));
	}
	
	/**
	 * Closes the dialog.
	 */
	private void closeDialog() {
		theTimer.stop();
		if (newClip != null) {
			newClip.stop();
		}
		setVisible(false);
	}
	
	/**
	 * Increments counter by one second.
	 */
	private void incrementBySecond() {
		
		iSecond++;
		if (iSecond == 60) {
			iSecond = 0;
			iMinute++;
		}
		
	}
	
	/**
	 * Decrements counter by one second.
	 */
	private void decrementBySecond() {
		
		iSecond--;
		if (iSecond == -1) {
			iSecond = (bIsShowingDecis) ? 9 : 59;
			iMinute--;
		}
		
	}
	
	/**
	 * Updates display.
	 */
	private void updateTimer() {
		
		// do nothing if timer is paused
		if (bPaused) {
			return;
		}
		
		// update time
		Date theDate = new Date();
		long lTime = theDate.getTime();
		lblCurrentTime.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).format(theDate));
		
		// change color, if random colors are selected
		if (bRandomColor) {
			iRandomColorCounter += lTime - lastTime;
			if (iRandomColorCounter >= iColorChangeInterval * 1000) {
				iRandomColorCounter -= iColorChangeInterval * 1000;
				
				getContentPane().setBackground(new Color(theRandom.nextInt()));
				lblCaption.setForeground(new Color(theRandom.nextInt()));
				
				Color clrTemp = new Color(theRandom.nextInt());
				lblCounterMinutes.setForeground(clrTemp);
				lblCounterColon.setForeground(clrTemp);
				lblCounterSeconds.setForeground(clrTemp);
				lblDescriptionMinutes.setForeground(clrTemp);
				lblDescriptionColon.setForeground(clrTemp);
				lblDescriptionSeconds.setForeground(clrTemp);
			}
		}
		
		// remember times
		count += lTime - lastTime;
		lastTime = (int) lTime;
		
		// decrease timer if necessary
		iCountLimit = (bIsShowingDecis) ? 100 : 1000;
		if (bDecreasing) {
			
			if (count >= iCountLimit) {
				count -= iCountLimit;
				decrementBySecond();
				setTime(((iMinute == 1) && (iSecond == 0)));
			}
				
		} else if (bOvertime && (count >= iCountLimit)) {
			count -= iCountLimit;
			incrementBySecond();
			setTime(false);
		}
		
		// time's up?
		if (iMinute == 0 && iSecond == 0) {
			
			bDecreasing = false;
			bIsShowingDecis = false;
			bShowDeciSeconds = false;
			bZeroReached = true;
			
			setTime(true);
			
			if (bPlayWav) {
				bPlayWav = false;
				thrWavPlay.loop = Boolean.parseBoolean(prpConfiguration.getProperty("chkLoop"));
				thrWavPlay.wav = prpConfiguration.getProperty("txtWAV");
				thrWavPlay.start();
			}
			
			if (bDisposeOnZero) {
				closeDialog();
			}
			
		}
		
	}
	
	/**
	 * Checks typed key.
	 * <br />
	 * 'p' or 'P' od SPACE pauses the timer, 'q' stops it
	 *
	 * @param e key event
	 */
	private void checkKey(KeyEvent e) {
		if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P' || e.getKeyChar() == ' ') {
			bPaused = !bPaused;
			if (bPaused) {
				lblCurrentTime.setText("Paused");
				lblCurrentTime.setVisible(true);
			} else {
				lblCurrentTime.setVisible(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayCurrent")));
			}
		}
		if (e.getKeyChar() == 'q') {
			closeDialog();
		}
	}
	
}

class WAVThread extends Thread {
	String wav = new String();
	
	boolean loop = false;
	
	public void run() {
		if (wav.length() > 0) {
			File soundFile = new File(wav); // File object for filename
			
			try {
				AudioInputStream source = AudioSystem
				.getAudioInputStream(soundFile);
				DataLine.Info clipInfo = new DataLine.Info(Clip.class, source
						.getFormat());
				if (AudioSystem.isLineSupported(clipInfo)) {
					DisplayTimer.newClip = (Clip) AudioSystem.getLine(clipInfo); // Create
					// the
					// clip
					DisplayTimer.newClip.open(source);
					if (loop)
						DisplayTimer.newClip.loop(Clip.LOOP_CONTINUOUSLY);
					else
						DisplayTimer.newClip.start();
				} else {
					JOptionPane.showMessageDialog(null, "Clip Not Supported",
							"Clip Not Supported", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error Playing Clip",
						"Clip Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
