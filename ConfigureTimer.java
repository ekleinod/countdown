import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Date;

import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.filechooser.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Random;
import java.util.Date;

public class ConfigureTimer extends JFrame
{
	JPanel contentPane;
	JMenuBar jMenuBar1 = new JMenuBar ();
	JMenu jMenuFile = new JMenu ();
	JMenuItem jMenuFileExit = new JMenuItem ();
	JMenu jMenuHelp = new JMenu ();
	JMenuItem jMenuHelpShortcut = new JMenuItem ();
	JMenuItem jMenuHelpAbout = new JMenuItem ();
	JLabel jLabel1 = new JLabel ();
	JPanel jPanel1 = new JPanel ();
	Border border1;
	JTextField caption = new JTextField ();
	JLabel currTime = new JLabel ();
	Timer timer =
		new Timer (1000,
			   new ConfigureTimer_setTime_ActionAdapter (this));
	JLabel jLabel3 = new JLabel ();
	JTextField endCaption = new JTextField ();
	JPanel jPanel2 = new JPanel ();
	JLabel jLabel4 = new JLabel ();
	JLabel jLabel5 = new JLabel ();
	JPanel jPanel3 = new JPanel ();
	JCheckBox displayCurrTime = new JCheckBox ();
	JCheckBox overtime = new JCheckBox ();
	JLabel jLabel6 = new JLabel ();
	JPanel jPanel4 = new JPanel ();
	JLabel jLabel7 = new JLabel ();
	JLabel jLabel8 = new JLabel ();
	JLabel number = new JLabel ();
	JLabel text = new JLabel ();
	JButton changeNumberColor = new JButton ();
	JButton changeTextColor = new JButton ();
	JButton chnageBackgroudColor = new JButton ();
	JLabel jLabel9 = new JLabel ();
	JPanel jPanel5 = new JPanel ();
	JTextField backgroundColor = new JTextField ();
	JPanel jPanel6 = new JPanel ();
	JLabel jLabel10 = new JLabel ();
	JTextField imagePath = new JTextField ();
	JButton browseImage = new JButton ();
	JButton browseWAV = new JButton ();
	JTextField wavPath = new JTextField ();
	JPanel jPanel7 = new JPanel ();
	JLabel jLabel11 = new JLabel ();
	JButton run = new JButton ();
	JButton exit = new JButton ();
	JMenu jMenuOptions = new JMenu ();
	JMenuItem jMenuOptSave = new JMenuItem ();
	JMenuItem jMenuOptLoad = new JMenuItem ();

	ComboBoxModel phyFonts1 =
		new DefaultComboBoxModel (GraphicsEnvironment.
					  getLocalGraphicsEnvironment ().
					  getAvailableFontFamilyNames ());

	ComboBoxModel phyFonts2 =
		new DefaultComboBoxModel (GraphicsEnvironment.
					  getLocalGraphicsEnvironment ().
					  getAvailableFontFamilyNames ());

	JComboBox numberFonts = new JComboBox (phyFonts1);
	JComboBox textFonts = new JComboBox (phyFonts2);
	JComboBox numberFontStyles = new JComboBox ();
	JComboBox textFontStyles = new JComboBox ();
	JTextField minutes = new JTextField ();
	JTextField seconds = new JTextField ();
	JCheckBox displayMS = new JCheckBox ();
	ButtonGroup precision = new ButtonGroup ();
	JCheckBox loop = new JCheckBox ();
	JCheckBox random = new JCheckBox ();

	Random r = new Random (20000);
	JSpinner interval = new JSpinner ();
	JLabel jLabel2 = new JLabel ();

	//Construct the frame
	public ConfigureTimer ()
	{
		enableEvents (AWTEvent.WINDOW_EVENT_MASK);
		try
		{
			jbInit ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
	//Component initialization
	private void jbInit () throws Exception
	{
		contentPane = (JPanel) this.getContentPane ();
		border1 =
			BorderFactory.createLineBorder (SystemColor.
							controlText, 1);
		contentPane.setLayout (null);
		this.setResizable (false);
		this.setSize (new Dimension (498, 630));
		this.setTitle ("Countdown Timer");
		jMenuFile.setText ("File");
		jMenuFileExit.setText ("Exit");
		jMenuFileExit.
			addActionListener (new
					   ConfigureTimer_jMenuFileExit_ActionAdapter
					   (this));
		jMenuOptSave.
			addActionListener (new
					   ConfigureTimer_jMenuOptSave_ActionAdapter
					   (this));
		jMenuOptLoad.
			addActionListener (new
					   ConfigureTimer_jMenuOptLoad_ActionAdapter
					   (this));
		jMenuHelp.setText ("Help");
		jMenuHelpShortcut.setText ("Shortcut Keys");
		jMenuHelpShortcut.
			addActionListener (new
					   ConfigureTimer_jMenuHelpShortcut_ActionAdapter
					   (this));
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout.
			addActionListener (new
					ConfigureTimer_jMenuHelpAbout_ActionAdapter
					(this));
		jLabel1.setText
			("Caption during Countdown (30 characters max.)");
		jLabel1.setBounds (new Rectangle (8, 7, 429, 15));
		jPanel1.setBorder (BorderFactory.createEtchedBorder ());
		jPanel1.setBounds (new Rectangle (19, 28, 457, 113));
		jPanel1.setLayout (null);
		caption.setInputVerifier (null);
		caption.setText ("");
		caption.setBounds (new Rectangle (8, 28, 437, 21));
		caption.addKeyListener (new
					ConfigureTimer_caption_keyAdapter
					(this));
		Date date = new Date ();
		 currTime.setHorizontalAlignment (SwingConstants.RIGHT);
		 currTime.setHorizontalTextPosition (SwingConstants.RIGHT);
		 currTime.setText (date.toString ());
		 currTime.setBounds (new Rectangle (29, 7, 447, 15));
		 jLabel3.
			setText
			("Caption to Display at End of Countdown (30 characters max.)");
		 jLabel3.setBounds (new Rectangle (8, 58, 433, 15));
		 endCaption.setText ("");
		 endCaption.setBounds (new Rectangle (8, 79, 437, 21));
		 endCaption.
			addKeyListener (new
					ConfigureTimer_endCaption_keyAdapter
					(this));
		 jPanel2.setBorder (BorderFactory.createEtchedBorder ());
		 jPanel2.setBounds (new Rectangle (19, 145, 260, 78));
		 jPanel2.setLayout (null);
		 jLabel4.setText ("Minutes");
		 jLabel4.setBounds (new Rectangle (15, 43, 38, 15));
		 jLabel5.setBounds (new Rectangle (130, 43, 43, 15));
		 jLabel5.setText ("Seconds");
		 contentPane.
			setToolTipText ("Enter the minutes to countdown");
		 jPanel3.setBorder (BorderFactory.createEtchedBorder ());
		 jPanel3.setBounds (new Rectangle (284, 145, 192, 78));
		 jPanel3.setLayout (null);
		 displayCurrTime.setText ("Display current time");
		 displayCurrTime.setBounds (new Rectangle (5, 6, 159, 23));
		 overtime.setText ("Count up for overtime");
		 overtime.setBounds (new Rectangle (5, 28, 159, 23));
		 jLabel6.setText ("Time for Countdown");
		 jLabel6.setBounds (new Rectangle (13, 10, 219, 15));
		 jPanel4.setBorder (BorderFactory.createEtchedBorder ());
		 jPanel4.setBounds (new Rectangle (19, 228, 457, 71));
		 jPanel4.setLayout (null);
		 jLabel7.setText ("Number Font");
		 jLabel7.setBounds (new Rectangle (13, 15, 69, 15));
		 jLabel8.setBounds (new Rectangle (13, 43, 57, 15));
		 jLabel8.setText ("Text Font");
		 number.setBounds (new Rectangle (81, 15, 51, 15));
		 number.setFont (new java.awt.Font ("Dialog", 0, 15));
		 number.setText ("12:34");
		 text.setBounds (new Rectangle (81, 43, 56, 15));
		 text.setFont (new java.awt.Font ("Dialog", 0, 15));
		 text.setText ("ABCD");
		 changeNumberColor.setText ("Change Color");
		 changeNumberColor.
			addActionListener (new
					   ConfigureTimer_changeNumberColor_actionAdapter
					   (this));
		 changeNumberColor.
			setBounds (new Rectangle (335, 10, 100, 25));
		 changeTextColor.setText ("Change Color");
		 changeTextColor.
			addActionListener (new
					   ConfigureTimer_changeTextColor_actionAdapter
					   (this));
		 changeTextColor.setBounds (new Rectangle (335, 38, 100, 25));
		 changeTextColor.setVerifyInputWhenFocusTarget (true);
		 chnageBackgroudColor.setText ("Change Background Color");
		 chnageBackgroudColor.
			addActionListener (new
					   ConfigureTimer_chnageBackgroudColor_actionAdapter
					   (this));
		 chnageBackgroudColor.
			setBounds (new Rectangle (270, 11, 167, 25));
		 jLabel9.setText ("Background Color");
		 jLabel9.setBounds (new Rectangle (14, 16, 96, 15));
		 jPanel5.setBorder (BorderFactory.createEtchedBorder ());
		 jPanel5.setBounds (new Rectangle (19, 303, 457, 70));
		 jPanel5.setLayout (null);
		 backgroundColor.setEnabled (false);
		 backgroundColor.setBorder (null);
		 backgroundColor.setEditable (true);
		 backgroundColor.setText ("");
		 backgroundColor.setBounds (new Rectangle (117, 13, 128, 21));
		 jPanel6.setBorder (BorderFactory.createEtchedBorder ());
		 jPanel6.setBounds (new Rectangle (18, 377, 458, 59));
		 jPanel6.setLayout (null);
		 jLabel10.setText ("Splash Screen Graphic");
		 jLabel10.setBounds (new Rectangle (13, 5, 306, 15));
		 imagePath.setText ("");
		 imagePath.setBounds (new Rectangle (12, 25, 321, 21));
		 browseImage.setBounds (new Rectangle (349, 23, 87, 25));
		 browseImage.setText ("Browse");
		 browseImage.
			addActionListener (new
					   ConfigureTimer_browseImage_actionAdapter
					   (this));
		 browseWAV.
			addActionListener (new
					   ConfigureTimer_browseWAV_actionAdapter
					   (this));
		 browseWAV.setText ("Browse");
		 browseWAV.setBounds (new Rectangle (349, 23, 87, 25));
		 wavPath.setBounds (new Rectangle (12, 25, 321, 21));
		 wavPath.setText ("");
		 jPanel7.setLayout (null);
		 jPanel7.setBounds (new Rectangle (18, 440, 458, 80));
		 jPanel7.setBorder (BorderFactory.createEtchedBorder ());
		 jLabel11.setText ("WAV to Play");
		 jLabel11.setBounds (new Rectangle (13, 5, 233, 15));
		 run.setBounds (new Rectangle (136, 538, 96, 25));
		 run.setText ("Run");
		 run.addActionListener (new
					ConfigureTimer_run_actionAdapter
					(this));
		 exit.setBounds (new Rectangle (259, 537, 96, 25));
		 exit.setText ("Exit");
		 exit.addActionListener (new
					 ConfigureTimer_exit_actionAdapter
					 (this));
		 jMenuOptions.setText ("Options");
		 jMenuOptSave.setText ("Save Configuration");
		 jMenuOptLoad.setText ("Load Configuration");
		 numberFonts.setBounds (new Rectangle (145, 11, 105, 22));
		 numberFonts.
			addActionListener (new
					   ConfigureTimer_numberFonts_actionAdapter
					   (this));
		 textFonts.setBounds (new Rectangle (145, 39, 105, 22));
		 textFonts.
			addActionListener (new
					   ConfigureTimer_textFonts_actionAdapter
					   (this));
		 numberFontStyles.setBounds (new Rectangle (257, 11, 66, 22));
		 numberFontStyles.
			addActionListener (new
					   ConfigureTimer_numberFontStyles_actionAdapter
					   (this));
		 textFontStyles.setBounds (new Rectangle (257, 39, 66, 22));
		 textFontStyles.
			addActionListener (new
					   ConfigureTimer_textFontStyles_actionAdapter
					   (this));
		 minutes.setText ("");
		 minutes.setBounds (new Rectangle (66, 40, 57, 21));
		 minutes.
			addKeyListener (new
					ConfigureTimer_minutes_keyAdapter
					(this));
		 seconds.setText ("");
		 seconds.setBounds (new Rectangle (181, 40, 57, 21));
		 seconds.
			addKeyListener (new
					ConfigureTimer_seconds_keyAdapter
					(this));
		 displayMS.setText ("Display tenths of second");
		 displayMS.setVerticalAlignment (SwingConstants.CENTER);
		 displayMS.setBounds (new Rectangle (5, 50, 163, 23));
		 displayMS.
			addActionListener (new
					   ConfigureTimer_displayMS_actionAdapter
					   (this));
		 loop.setText ("Play in Loop");
		 loop.setBounds (new Rectangle (12, 53, 180, 23));
		 random.setText ("Random Color");
		 random.setBounds (new Rectangle (10, 42, 194, 23));
		 random.addActionListener (new
					   ConfigureTimer_random_actionAdapter
					   (this));
		 interval.setBounds (new Rectangle (381, 41, 56, 24));
		 jLabel2.setHorizontalAlignment (SwingConstants.RIGHT);
		 jLabel2.setText ("Interval (in seconds)");
		 jLabel2.setBounds (new Rectangle (256, 46, 116, 15));
		 jPanel2.add (minutes, null);
		 jPanel2.add (seconds, null);
		 jPanel2.add (jLabel5, null);
		 jPanel2.add (jLabel4, null);
		 jPanel2.add (jLabel6, null);
		 jPanel7.add (jLabel11, null);
		 jPanel7.add (wavPath, null);
		 jPanel7.add (browseWAV, null);
		 jPanel7.add (loop, null);
		 contentPane.add (run, null);
		 contentPane.add (exit, null);
		 contentPane.add (jPanel6, null);
		 contentPane.add (jPanel4, null);
		 jPanel6.add (jLabel10, null);
		 jPanel6.add (imagePath, null);
		 jPanel6.add (browseImage, null);
		 contentPane.add (jPanel7, null);
		 jPanel5.add (jLabel9, null);
		 jPanel5.add (chnageBackgroudColor, null);
		 jPanel5.add (backgroundColor, null);
		 jPanel5.add (random, null);
		 jPanel5.add (interval, null);
		 jPanel4.add (jLabel7, null);
		 jPanel4.add (jLabel8, null);
		 jPanel4.add (changeNumberColor, null);
		 jPanel4.add (changeTextColor, null);
		 jPanel4.add (textFonts, null);
		 jPanel4.add (number, null);
		 jPanel4.add (text, null);
		 jPanel4.add (numberFonts, null);
		 jPanel4.add (numberFontStyles, null);
		 jPanel4.add (textFontStyles, null);
		 contentPane.add (jPanel5, null);
		 contentPane.add (jPanel3, null);
		 jPanel3.add (displayCurrTime, null);
		 jPanel3.add (overtime, null);
		 jPanel3.add (displayMS, null);
		 jMenuFile.add (jMenuFileExit);
		 jMenuHelp.add (jMenuHelpShortcut);
		 jMenuHelp.add (jMenuHelpAbout);
		 jMenuBar1.add (jMenuFile);
		 jMenuBar1.add (jMenuOptions);
		 jMenuBar1.add (jMenuHelp);
		 jPanel1.add (jLabel1, null);
		 jPanel1.add (caption, null);
		 jPanel1.add (jLabel3, null);
		 jPanel1.add (endCaption, null);
		 contentPane.add (currTime, null);
		 contentPane.add (jPanel1, null);
		 contentPane.add (jPanel2, null);
		 jMenuOptions.add (jMenuOptSave);
		 jMenuOptions.add (jMenuOptLoad);
		 jPanel5.add (jLabel2, null);
		 this.setJMenuBar (jMenuBar1);
		 timer.setRepeats (true);
		 timer.start ();
		 numberFontStyles.addItem ("Regular");
		 numberFontStyles.addItem ("Bold");
		 numberFontStyles.addItem ("Italics");
		 textFontStyles.addItem ("Regular");
		 textFontStyles.addItem ("Bold");
		 textFontStyles.addItem ("Italics");
	}

	//Updating the current displayed time.
	public void setTime_actionPerformed (ActionEvent e)
	{
		Date date = new Date ();

		currTime.setText (date.toString ());
	}

	//File | Exit action performed
	public void jMenuFileExit_actionPerformed (ActionEvent e)
	{
		System.exit (0);
	}
	//Help | About action performed
	public void jMenuHelpShortcut_actionPerformed (ActionEvent e)
	{
		JOptionPane.showMessageDialog (this,
					       "Pause / Resume         P\nClose Window            Alt+F4\n",
					       "Shortcut Keys",
					       JOptionPane.
					       INFORMATION_MESSAGE);
	}
	//Help | About action performed
	public void jMenuHelpAbout_actionPerformed (ActionEvent e)
	{
		JOptionPane.showMessageDialog (this,
					       "Countdown Timer\nVersion 0.1\n\nWeb site: http://www.sourceforge.net/projects/countdown/",
					       "About the program",
					       JOptionPane.
					       INFORMATION_MESSAGE);
	}
	//Overridden so we can exit when window is closed
	protected void processWindowEvent (WindowEvent e)
	{
		super.processWindowEvent (e);
		if (e.getID () == WindowEvent.WINDOW_CLOSING)
		{
			jMenuFileExit_actionPerformed (null);
		}
	}

	public void jMenuOptSave_actionPerformed (ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser ();
		MyFileFilter filter = new MyFileFilter ();

		filter.addExtension ("ct");
		fileChooser.setFileFilter (filter);
		if (fileChooser.showSaveDialog (this) !=
		    JFileChooser.APPROVE_OPTION)
			return;

		try
		{
			BufferedWriter out =
				new BufferedWriter (new
						    FileWriter (fileChooser.
								getSelectedFile
								().
								toString () +
								".ct"));
			out.write (caption.getText ());
			out.newLine ();
			out.write (endCaption.getText ());
			out.newLine ();
			out.write (minutes.getText ());
			out.newLine ();
			out.write (seconds.getText ());
			out.newLine ();
			out.write (displayCurrTime.isSelected ()? "1" : "0");
			out.newLine ();
			out.write (overtime.isSelected ()? "1" : "0");
			out.newLine ();
			out.write (displayMS.isSelected ()? "1" : "0");
			out.newLine ();
			Integer i =
				new Integer (numberFonts.getSelectedIndex ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (numberFontStyles.
					 getSelectedIndex ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (number.getForeground ().getRGB ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (textFonts.getSelectedIndex ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (textFontStyles.getSelectedIndex ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (text.getForeground ().getRGB ());
			out.write (i.toString ());
			out.newLine ();
			i = new Integer (backgroundColor.getBackground ().
					 getRGB ());
			out.write (i.toString ());
			out.newLine ();
			out.write (imagePath.getText ());
			out.newLine ();
			out.write (wavPath.getText ());
			out.newLine ();
			out.write (random.isSelected ()? "1" : "0");
			out.newLine ();
			out.write (interval.getValue ().toString ());
			out.newLine ();
			out.write (loop.isSelected ()? "1" : "0");
			out.newLine ();
			out.close ();
		}
		catch (IOException exc)
		{
			JOptionPane.showMessageDialog (this,
						       "File Could Not be Saved",
						       "File Save Error",
						       JOptionPane.
						       WARNING_MESSAGE);
		}
	}

	public void jMenuOptLoad_actionPerformed (ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser ();
		MyFileFilter filter = new MyFileFilter ();

		filter.addExtension ("ct");
		fileChooser.setFileFilter (filter);
		if (fileChooser.showOpenDialog (this) !=
		    JFileChooser.APPROVE_OPTION)
			return;

		try
		{
			BufferedReader in =
				new BufferedReader (new
						    FileReader (fileChooser.
								getSelectedFile
								().
								toString ()));
			caption.setText (in.readLine ());
			endCaption.setText (in.readLine ());
			minutes.setText (in.readLine ());
			seconds.setText (in.readLine ());
			displayCurrTime.setSelected (in.readLine ().
						     charAt (0) == '1');
			overtime.setSelected (in.readLine ().charAt (0) ==
					      '1');
			displayMS.setSelected (in.readLine ().charAt (0) ==
					       '1');
			Integer i = new Integer (in.readLine ());

			numberFonts.setSelectedIndex (i.intValue ());
			i = new Integer (in.readLine ());
			numberFontStyles.setSelectedIndex (i.intValue ());
			i = new Integer (in.readLine ());
			number.setForeground (new Color (i.intValue ()));
			i = new Integer (in.readLine ());
			textFonts.setSelectedIndex (i.intValue ());
			i = new Integer (in.readLine ());
			textFontStyles.setSelectedIndex (i.intValue ());
			i = new Integer (in.readLine ());
			text.setForeground (new Color (i.intValue ()));
			i = new Integer (in.readLine ());
			backgroundColor.
				setBackground (new Color (i.intValue ()));
			imagePath.setText (in.readLine ());
			wavPath.setText (in.readLine ());
			random.setSelected (in.readLine ().charAt (0) == '1');
			i = new Integer (in.readLine ());
			interval.setValue (i);
			loop.setSelected (in.readLine ().charAt (0) == '1');
			in.close ();
		}
		catch (IOException exc)
		{
			JOptionPane.showMessageDialog (this,
						       "File Could Not be Loaded",
						       "File Load Error",
						       JOptionPane.
						       WARNING_MESSAGE);
		}
	}

	void chnageBackgroudColor_actionPerformed (ActionEvent e)
	{
		JColorChooser colorChooser = new JColorChooser ();

		backgroundColor.setBackground (colorChooser.
					       showDialog (null,
							   "Choose the Background Color",
							   backgroundColor.
							   getBackground ()));
	}

	void changeNumberColor_actionPerformed (ActionEvent e)
	{
		JColorChooser colorChooser = new JColorChooser ();

		number.setForeground (colorChooser.
				      showDialog (null,
						  "Choose the Background Color",
						  number.getForeground ()));
	}

	void changeTextColor_actionPerformed (ActionEvent e)
	{
		JColorChooser colorChooser = new JColorChooser ();

		text.setForeground (colorChooser.
				    showDialog (null,
						"Choose the Background Color",
						text.getForeground ()));
	}

	void browseImage_actionPerformed (ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser ();
		MyFileFilter filter = new MyFileFilter ();

		filter.addExtension ("jpg");
		filter.addExtension ("gif");
		filter.setDescription ("Images");
		fileChooser.setFileFilter (filter);
		if (fileChooser.showOpenDialog (this) ==
		    JFileChooser.APPROVE_OPTION)
			imagePath.setText (fileChooser.getSelectedFile ().
					   toString ());
	}

	void browseWAV_actionPerformed (ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser ();
		MyFileFilter filter = new MyFileFilter ();

		filter.addExtension ("wav");
		filter.setDescription ("WAV File");
		fileChooser.setFileFilter (filter);
		if (fileChooser.showOpenDialog (this) ==
		    JFileChooser.APPROVE_OPTION)
			wavPath.setText (fileChooser.getSelectedFile ().
					 toString ());
	}

	void exit_actionPerformed (ActionEvent e)
	{
		System.exit (0);
	}

	void numberFonts_actionPerformed (ActionEvent e)
	{
		number.setFont (new
				Font (numberFonts.getSelectedItem ().
				      toString (),
				      numberFontStyles.getSelectedIndex (),
				      15));
	}

	void textFonts_actionPerformed (ActionEvent e)
	{
		text.setFont (new
			      Font (textFonts.getSelectedItem ().toString (),
				    textFontStyles.getSelectedIndex (), 15));
	}

	void numberFontStyles_actionPerformed (ActionEvent e)
	{
		number.setFont (new
				Font (numberFonts.getSelectedItem ().
				      toString (),
				      numberFontStyles.getSelectedIndex (),
				      15));
	}

	void textFontStyles_actionPerformed (ActionEvent e)
	{
		text.setFont (new
			      Font (textFonts.getSelectedItem ().toString (),
				    textFontStyles.getSelectedIndex (), 15));
	}

	void caption_keyTyped (KeyEvent e)
	{
		if (caption.getText ().length () == 30
		    && (int) e.getKeyChar () != 8)
			e.consume ();
	}

	void endCaption_keyTyped (KeyEvent e)
	{
		if (endCaption.getText ().length () == 30
		    && (int) e.getKeyChar () != 8)
			e.consume ();
	}

	void minutes_keyTyped (KeyEvent e)
	{
		if ((int) e.getKeyChar () == 8)
			return;
		if (minutes.getText ().length () == 3)
			e.consume ();
		else if (e.getKeyChar () < '0' || e.getKeyChar () > '9')
			e.consume ();
	}

	void seconds_keyTyped (KeyEvent e)
	{
		if ((int) e.getKeyChar () == 8)
			return;
		if (seconds.getText ().length () == 2)
			e.consume ();
		else if (e.getKeyChar () < '0' || e.getKeyChar () > '9')
			e.consume ();
		else if (seconds.getText ().length () == 1
			 && seconds.getText ().charAt (0) >= '6')
			e.consume ();
	}

	void run_actionPerformed (ActionEvent e)
	{
		// displaying the splash image.
		if (imagePath.getText ().length () > 0)
		{
			SplashImage splash = new SplashImage ();

			splash.imagePath = imagePath.getText ();
			splash.show ();
		}

		// displaying the timer.
		DisplayTimer t = new DisplayTimer ();

		if (minutes.getText ().length () == 0)
			minutes.setText ("0");
		if (seconds.getText ().length () == 0)
			seconds.setText ("0");
		if (seconds.getText ().length () == 1)
			seconds.setText ("0" + seconds.getText ());

		t.getContentPane ().setBackground (backgroundColor.
						   getBackground ());

		t.minsLabel.setForeground (number.getForeground ());
		t.secsLabel.setForeground (number.getForeground ());
		t.colonLabel.setForeground (number.getForeground ());

		t.colon.setFont (new
				 Font (number.getFont ().getName (),
				       number.getFont ().getStyle (), 120));
		t.colon.setForeground (number.getForeground ());
		t.colon.setFont (new
				 Font (t.colon.getFont ().getName (),
				       t.colon.getFont ().getStyle (),
				       t.getHeight () / 3));
		t.colon.setSize (t.getWidth (), t.getHeight () / 3);
		Dimension frameSize = t.getSize ();

		t.colon.setLocation (0,
				     (t.getHeight () - t.colon.getHeight () -
				      100) / 2);

		t.seconds.
			setFont (new
				 Font (number.getFont ().getName (),
				       number.getFont ().getStyle (), 120));
		t.seconds.setForeground (number.getForeground ());
		t.seconds.
			setFont (new
				 Font (t.seconds.getFont ().getName (),
				       t.seconds.getFont ().getStyle (),
				       t.getHeight () / 3));
		t.seconds.setSize (t.getWidth () / 2, t.getHeight () / 3);
		t.seconds.setLocation (t.getWidth () / 2 + 50,
				       (t.getHeight () -
					t.colon.getHeight () - 100) / 2);
		t.seconds.setText (seconds.getText ());

		t.minutes.
			setFont (new
				 Font (number.getFont ().getName (),
				       number.getFont ().getStyle (), 120));
		t.minutes.setForeground (number.getForeground ());
		t.minutes.
			setFont (new
				 Font (t.minutes.getFont ().getName (),
				       t.minutes.getFont ().getStyle (),
				       t.getHeight () / 3));
		t.minutes.setSize (t.getWidth () / 2 - 50,
				   t.getHeight () / 3);
		t.minutes.setLocation (0,
				       (t.getHeight () -
					t.colon.getHeight () - 100) / 2);
		t.minutes.setText (minutes.getText ());

		t.currTime.setVisible (displayCurrTime.isSelected ());
		t.currTime.setForeground (text.getForeground ());
		t.overtime = overtime.isSelected ();
		t.showMS = displayMS.isSelected ();

		t.endCaption = endCaption.getText ();

		t.caption.setText (caption.getText ());
		t.caption.
			setFont (new
				 Font (text.getFont ().getName (),
				       text.getFont ().getStyle (), 60));
		t.caption.setForeground (text.getForeground ());
		t.caption.setSize (t.getWidth (), 80);
		t.caption.setLocation (0,
				       (t.getHeight () / 3 -
					t.caption.getHeight ()) / 2);
		t.caption.setText (caption.getText ());

		if (displayMS.isSelected ())
		{
			Integer min = new Integer (minutes.getText ());
			Integer sec = new Integer (seconds.getText ());
			int temp1 = min.intValue ();
			int temp2 = sec.intValue ();

			if (temp1 == 0)
			{
				t.minutes.setText (seconds.getText ());
				t.seconds.setText ("0");
				t.displayingTenths = true;
				t.minsLabel.setText ("Seconds");
				t.secsLabel.setText ("Tenths");
			}
			else if (temp1 == 1 && temp2 == 0)
			{
				t.minutes.setText ("60");
				t.seconds.setText ("0");
				t.displayingTenths = true;
				t.minsLabel.setText ("Seconds");
				t.secsLabel.setText ("Tenths");
			}
		}

		t.wav = wavPath.getText ();
		t.loop = loop.isSelected ();
		t.randomColor = random.isSelected ();

		if (t.randomColor)
		{
			t.getContentPane ().
				setBackground (new Color (r.nextInt ()));
			t.caption.setForeground (new Color (r.nextInt ()));
			int temp = r.nextInt ();

			t.colon.setForeground (new Color (temp));
			t.colonLabel.setForeground (new Color (temp));
			t.minutes.setForeground (new Color (temp));
			t.seconds.setForeground (new Color (temp));
			t.minsLabel.setForeground (new Color (temp));
			t.secsLabel.setForeground (new Color (temp));
			Integer i =
				new Integer (interval.getValue ().
					     toString ());
			t.colorChangeInterval = i.intValue ();
		}
		t.show ();
	}

	void displayMS_actionPerformed (ActionEvent e)
	{
	}

	void jMenuOptSave_mouseClicked (MouseEvent e)
	{
		System.out.println ("Clicked");
	}

	void random_actionPerformed (ActionEvent e)
	{
	}

}

class ConfigureTimer_jMenuFileExit_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_jMenuFileExit_ActionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.jMenuFileExit_actionPerformed (e);
	}
}

class ConfigureTimer_jMenuHelpShortcut_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_jMenuHelpShortcut_ActionAdapter (ConfigureTimer
							 adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.jMenuHelpShortcut_actionPerformed (e);
	}
}

class ConfigureTimer_jMenuHelpAbout_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_jMenuHelpAbout_ActionAdapter (ConfigureTimer
							 adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.jMenuHelpAbout_actionPerformed (e);
	}
}

class ConfigureTimer_setTime_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_setTime_ActionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.setTime_actionPerformed (e);
	}
}

class ConfigureTimer_chnageBackgroudColor_actionAdapter implements java.awt.
	event.ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_chnageBackgroudColor_actionAdapter (ConfigureTimer
							    adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.chnageBackgroudColor_actionPerformed (e);
	}
}

class ConfigureTimer_changeNumberColor_actionAdapter implements java.awt.
	event.ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_changeNumberColor_actionAdapter (ConfigureTimer
							 adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.changeNumberColor_actionPerformed (e);
	}
}

class ConfigureTimer_changeTextColor_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_changeTextColor_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.changeTextColor_actionPerformed (e);
	}
}

class ConfigureTimer_browseImage_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_browseImage_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.browseImage_actionPerformed (e);
	}
}

class MyFileFilter extends javax.swing.filechooser.FileFilter
{

	private static String TYPE_UNKNOWN = "Type Unknown";
	private static String HIDDEN_FILE = "Hidden File";

	private Hashtable filters = null;
	private String description = null;
	private String fullDescription = null;
	private boolean useExtensionsInDescription = true;

	public MyFileFilter ()
	{
		this.filters = new Hashtable ();
	}

	public boolean accept (File f)
	{
		if (f != null)
		{
			if (f.isDirectory ())
			{
				return true;
			}
			String extension = getExtension (f);

			if (extension != null
			    && filters.get (getExtension (f)) != null)
			{
				return true;
			};
		}
		return false;
	}

	public String getExtension (File f)
	{
		if (f != null)
		{
			String filename = f.getName ();
			int i = filename.lastIndexOf ('.');

			if (i > 0 && i < filename.length () - 1)
			{
				return filename.substring (i +
							   1).toLowerCase ();
			};
		}
		return null;
	}

	public void addExtension (String extension)
	{
		if (filters == null)
		{
			filters = new Hashtable (5);
		}
		filters.put (extension.toLowerCase (), this);
		fullDescription = null;
	}

	public String getDescription ()
	{
		if (fullDescription == null)
		{
			if (description == null
			    || isExtensionListInDescription ())
			{
				fullDescription =
					description ==
					null ? "(" : description + " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys ();

				if (extensions != null)
				{
					fullDescription +=
						"." +
						(String) extensions.
						nextElement ();
					while (extensions.hasMoreElements ())
					{
						fullDescription +=
							", ." +
							(String) extensions.
							nextElement ();
					}
				}
				fullDescription += ")";
			}
			else
			{
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	public void setDescription (String description)
	{
		this.description = description;
		fullDescription = null;
	}

	public void setExtensionListInDescription (boolean b)
	{
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	public boolean isExtensionListInDescription ()
	{
		return useExtensionsInDescription;
	}
}

class ConfigureTimer_browseWAV_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_browseWAV_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.browseWAV_actionPerformed (e);
	}
}

class ConfigureTimer_exit_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_exit_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.exit_actionPerformed (e);
	}
}

class ConfigureTimer_numberFonts_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_numberFonts_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.numberFonts_actionPerformed (e);
	}
}

class ConfigureTimer_textFonts_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_textFonts_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.textFonts_actionPerformed (e);
	}
}

class ConfigureTimer_numberFontStyles_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_numberFontStyles_actionAdapter (ConfigureTimer
							adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.numberFontStyles_actionPerformed (e);
	}
}

class ConfigureTimer_textFontStyles_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_textFontStyles_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.textFontStyles_actionPerformed (e);
	}
}

class ConfigureTimer_caption_keyAdapter extends java.awt.event.KeyAdapter
{
	ConfigureTimer adaptee;

	 ConfigureTimer_caption_keyAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void keyTyped (KeyEvent e)
	{
		adaptee.caption_keyTyped (e);
	}
}

class ConfigureTimer_endCaption_keyAdapter extends java.awt.event.KeyAdapter
{
	ConfigureTimer adaptee;

	 ConfigureTimer_endCaption_keyAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void keyTyped (KeyEvent e)
	{
		adaptee.endCaption_keyTyped (e);
	}
}

class ConfigureTimer_minutes_keyAdapter extends java.awt.event.KeyAdapter
{
	ConfigureTimer adaptee;

	 ConfigureTimer_minutes_keyAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void keyTyped (KeyEvent e)
	{
		adaptee.minutes_keyTyped (e);
	}
}

class ConfigureTimer_seconds_keyAdapter extends java.awt.event.KeyAdapter
{
	ConfigureTimer adaptee;

	 ConfigureTimer_seconds_keyAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void keyTyped (KeyEvent e)
	{
		adaptee.seconds_keyTyped (e);
	}
}

class ConfigureTimer_run_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_run_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.run_actionPerformed (e);
	}
}

class ConfigureTimer_displayMS_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_displayMS_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.displayMS_actionPerformed (e);
	}
}

class ConfigureTimer_jMenuOptSave_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_jMenuOptSave_ActionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.jMenuOptSave_actionPerformed (e);
	}
}

class ConfigureTimer_jMenuOptLoad_ActionAdapter implements ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_jMenuOptLoad_ActionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.jMenuOptLoad_actionPerformed (e);
	}
}

class ConfigureTimer_random_actionAdapter implements java.awt.event.
	ActionListener
{
	ConfigureTimer adaptee;

	 ConfigureTimer_random_actionAdapter (ConfigureTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.random_actionPerformed (e);
	}
}
