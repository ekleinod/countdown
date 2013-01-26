import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.applet.*;
import java.net.*;
import java.net.URL;
import java.io.*;
import javax.sound.sampled.*;
import java.util.Random;

public class DisplayTimer extends JDialog
{
	public JLabel minutes = new JLabel ();
	public JLabel colonLabel = new JLabel ();
	public JLabel caption = new JLabel ();
	public boolean overtime = false;
	public String endCaption = new String ();
	public boolean decreasing = true;
	int count = 0;
	int countLimit = 1000;
	String wav = new String ();
	public boolean displayingTenths = false;	//displaying tenths of seconds.
	boolean paused = false;
	boolean showMS = false;
	WAVThread t = new WAVThread ();

	Timer timer =
		new Timer (48,
			   new DisplayTimer_updateTime_ActionAdapter (this));
	JLabel currTime = new JLabel ();
	JLabel seconds = new JLabel ();
	JLabel colon = new JLabel ();
	JLabel minsLabel = new JLabel ();
	JLabel secsLabel = new JLabel ();

	boolean check = true;
	boolean loop = false;
	boolean randomColor = false;
	int counter = 0;
	int colorChangeInterval = 0;
	int oldCount = 0;

	Random r = new Random (200000);

	File currentDir = new File (System.getProperty ("user.dir"));
	static Clip newClip;

	public DisplayTimer ()
	{
		try
		{
			jbInit ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	private void jbInit () throws Exception
	{
		Dimension screenSize =
			Toolkit.getDefaultToolkit ().getScreenSize ();
		 this.setSize ((int) screenSize.getWidth () + 10,
			       (int) screenSize.getHeight () + 35);
		 this.setLocation (-5, -30);
		 minutes.setHorizontalAlignment (SwingConstants.RIGHT);
		 minutes.setHorizontalTextPosition (SwingConstants.RIGHT);
		 minutes.setText ("");
		 minutes.setVerticalAlignment (SwingConstants.CENTER);
		 minutes.setVerticalTextPosition (SwingConstants.CENTER);
		 minutes.setBounds (new Rectangle (84, 104, 335, 164));
		 this.setResizable (false);
		 this.getContentPane ().setLayout (null);

		 currTime.setFont (new java.awt.Font ("Dialog", 0, 25));
		 currTime.setBounds (new Rectangle (25, 0, 50, 25));
		 currTime.setSize (this.getWidth () - 50, 25);
		 currTime.setLocation (25, this.getHeight () - 115);

		 colonLabel.setFont (new java.awt.Font ("Dialog", 0, 30));
		 colonLabel.setHorizontalAlignment (SwingConstants.CENTER);
		 colonLabel.setHorizontalTextPosition (SwingConstants.CENTER);
		 colonLabel.setText (":");
		 colonLabel.setBounds (new Rectangle (10, 10, 8, 39));
		 colonLabel.setSize (this.getWidth (), 30);
		 colonLabel.setLocation (0,
					 (int) (Toolkit.getDefaultToolkit ().
						getScreenSize ().
						getHeight () / 3 +
						this.getHeight () / 3));

		 minsLabel.setFont (new java.awt.Font ("Dialog", 0, 30));
		 minsLabel.setHorizontalAlignment (SwingConstants.RIGHT);
		 minsLabel.setHorizontalTextPosition (SwingConstants.RIGHT);
		 minsLabel.setText ("Minutes");
		 minsLabel.setBounds (new Rectangle (0, 45, 50, 30));
		 minsLabel.setSize (this.getWidth () / 2 - 50, 30);
		 minsLabel.setLocation (0,
					(int) (Toolkit.getDefaultToolkit ().
					       getScreenSize ().getHeight () /
					       3 + this.getHeight () / 3));

		 secsLabel.setFont (new java.awt.Font ("Dialog", 0, 30));
		 secsLabel.setHorizontalAlignment (SwingConstants.LEFT);
		 secsLabel.setHorizontalTextPosition (SwingConstants.LEFT);
		 secsLabel.setText ("Seconds");
		 secsLabel.setBounds (new Rectangle (0, 0, 400, 300));
		 secsLabel.setSize (this.getWidth () / 2, 30);
		 secsLabel.setLocation (this.getWidth () / 2 + 50,
					(int) (Toolkit.getDefaultToolkit ().
					       getScreenSize ().getHeight () /
					       3 + this.getHeight () / 3));

		 caption.setHorizontalAlignment (SwingConstants.CENTER);
		 caption.setHorizontalTextPosition (SwingConstants.CENTER);
		 caption.setText ("");
		 caption.setBounds (new Rectangle (93, 22, 281, 98));

		 seconds.setHorizontalAlignment (SwingConstants.LEFT);
		 seconds.setHorizontalTextPosition (SwingConstants.LEFT);
		 seconds.setText ("");
		 seconds.setBounds (new Rectangle (30, 30, 0, 0));
		 colon.setFont (new java.awt.Font ("Dialog", 0, 120));
		 colon.setHorizontalAlignment (SwingConstants.CENTER);
		 colon.setHorizontalTextPosition (SwingConstants.CENTER);
		 colon.setText (":");
		 colon.setBounds (new Rectangle (20, 20, 33, 152));

		 this.addMouseListener (new
					DisplayTimer_this_mouseAdapter
					(this));
		 this.setDefaultCloseOperation (javax.swing.WindowConstants.
						DISPOSE_ON_CLOSE);
		 this.addKeyListener (new
				      DisplayTimer_this_keyAdapter (this));
		 this.setModal (true);
		 this.addWindowListener (new
					 DisplayTimer_this_windowAdapter
					 (this));
		 this.getContentPane ().setLayout (null);
		 this.setDefaultCloseOperation (javax.swing.WindowConstants.
						DISPOSE_ON_CLOSE);
		 this.setEnabled (true);
		 this.getContentPane ().add (minutes, null);
		 this.getContentPane ().add (colonLabel, null);
		 this.getContentPane ().add (caption, null);
		 this.getContentPane ().add (currTime, null);
		 this.getContentPane ().add (seconds, null);
		 this.getContentPane ().add (colon, null);
		 this.getContentPane ().add (minsLabel, null);
		 this.getContentPane ().add (secsLabel, null);
		 timer.setRepeats (true);
		 timer.start ();
		Date date = new Date ();
		 oldCount = (int) date.getTime ();
		 currTime.setText (date.toString ());
		 currTime.setVisible (false);
	}

	void IncrementBySecond ()
	{
		Integer sec = new Integer (seconds.getText ());
		Integer min = new Integer (minutes.getText ());
		int temp1 = sec.intValue ();
		int temp2 = min.intValue ();

		temp1++;
		if (temp1 == 60)
		{
			temp1 = 0;
			temp2++;
		}
		sec = new Integer (temp1);
		min = new Integer (temp2);
		minutes.setText (min.toString ());
		seconds.setText (sec.toString ());
		if (seconds.getText ().length () == 1)
			seconds.setText ("0" + seconds.getText ());
	}

	void DecrementBySecond ()
	{
		Integer sec = new Integer (seconds.getText ());
		Integer min = new Integer (minutes.getText ());
		int temp1 = sec.intValue ();
		int temp2 = min.intValue ();

		temp1--;
		if (temp1 == -1)
		{
			temp1 = 59;
			temp2--;
		}
		sec = new Integer (temp1);
		min = new Integer (temp2);
		minutes.setText (min.toString ());
		seconds.setText (sec.toString ());
		if (seconds.getText ().length () == 1)
			seconds.setText ("0" + seconds.getText ());
	}

	public void updateTime_actionPerformed (ActionEvent e)
	{
		if (paused)
			return;

		Date date = new Date ();

		currTime.setText (date.toString ());
		count += date.getTime () - oldCount;

		if (randomColor)
		{
			counter += date.getTime () - oldCount;
			if (counter >= colorChangeInterval * 1000)
			{
				counter -= colorChangeInterval * 1000;
				this.getContentPane ().
					setBackground (new
						       Color (r.nextInt ()));
				caption.setForeground (new
						       Color (r.nextInt ()));
				int temp = r.nextInt ();

				colon.setForeground (new Color (temp));
				colonLabel.setForeground (new Color (temp));
				minutes.setForeground (new Color (temp));
				seconds.setForeground (new Color (temp));
				minsLabel.setForeground (new Color (temp));
				secsLabel.setForeground (new Color (temp));
			}
		}

		oldCount = (int) date.getTime ();

		Integer sec = new Integer (seconds.getText ());
		Integer min = new Integer (minutes.getText ());
		int temp1 = sec.intValue ();
		int temp2 = min.intValue ();

		if (temp2 == 0 && temp1 == 0)
		{
			decreasing = false;
			displayingTenths = false;
			minsLabel.setText ("Minutes");
			secsLabel.setText ("Seconds");
			seconds.setText ("00");
			minutes.setText ("0");
			caption.setText (endCaption);
			if (check)
			{
				t.loop = loop;
				t.wav = wav;
				check = false;
				t.start ();
			}
		}

		if (decreasing)
		{
			if (showMS)
			{
				if (displayingTenths)
				{
					if (count >= 100)
					{
						count -= 100;

						temp1--;
						if (temp1 == -1)
						{
							temp2--;
							temp1 = 9;
						}
						sec = new Integer (temp1);
						min = new Integer (temp2);
						minutes.setText (min.
								 toString ());
						seconds.setText (sec.
								 toString ());
						if (minutes.getText ().
						    length () == 1)
							minutes.setText ("0" +
									 minutes.
									 getText
									 ());

						if (temp2 == 0 && temp1 == 0)
						{
							decreasing = false;
							displayingTenths = false;
							minsLabel.
								setText
								("Minutes");
							secsLabel.
								setText
								("Seconds");
							seconds.setText
								("00");
							minutes.setText ("0");
							caption.setText
								(endCaption);
							if (check)
							{
								t.loop = loop;
								t.wav = wav;
								check = false;
								t.start ();
							}
						}
					}
				}
				else if (count >= 1000)
				{
					count -= 1000;
					DecrementBySecond ();

					if (temp2 == 1 && temp1 == 0)
					{
						displayingTenths = true;
						minsLabel.setText ("Seconds");
						secsLabel.
							setText
							("Tenths");
						seconds.setText ("0");
						minutes.setText ("60");
					}
				}
			}
			else if (count >= 1000)
			{
				count -= 1000;
				DecrementBySecond ();

				if (temp2 == 0 && temp1 == 0)
				{
					decreasing = false;
					caption.setText (endCaption);
					if (check)
					{
						t.loop = loop;
						t.wav = wav;
						check = false;
						t.start ();
					}
				}
			}
		}
		else if (overtime && count >= 1000)
		{
			count -= 1000;
			IncrementBySecond ();
		}
	}

	void this_mouseClicked (MouseEvent e)
	{
		this.dispose ();
	}

	void this_keyTyped (KeyEvent e)
	{
		if (e.getKeyChar () == 'p' || e.getKeyChar () == 'P')
			paused = !paused;
	}

	void this_windowClosed (WindowEvent e)
	{
		timer.stop ();
		if (newClip != null)
			newClip.stop ();
	}

}

class DisplayTimer_updateTime_ActionAdapter implements ActionListener
{
	DisplayTimer adaptee;

	 DisplayTimer_updateTime_ActionAdapter (DisplayTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void actionPerformed (ActionEvent e)
	{
		adaptee.updateTime_actionPerformed (e);
	}
}

class DisplayTimer_this_mouseAdapter extends java.awt.event.MouseAdapter
{
	DisplayTimer adaptee;

	 DisplayTimer_this_mouseAdapter (DisplayTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void mouseClicked (MouseEvent e)
	{
		adaptee.this_mouseClicked (e);
	}
}

class DisplayTimer_this_keyAdapter extends java.awt.event.KeyAdapter
{
	DisplayTimer adaptee;

	 DisplayTimer_this_keyAdapter (DisplayTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void keyTyped (KeyEvent e)
	{
		adaptee.this_keyTyped (e);
	}
}

class DisplayTimer_this_windowAdapter extends java.awt.event.WindowAdapter
{
	DisplayTimer adaptee;

	 DisplayTimer_this_windowAdapter (DisplayTimer adaptee)
	{
		this.adaptee = adaptee;
	}
	public void windowClosed (WindowEvent e)
	{
		adaptee.this_windowClosed (e);
	}
}

class WAVThread extends Thread
{
	String wav = new String ();
	boolean loop = false;
	File currentDir = new File (System.getProperty ("user.dir"));

	public void run ()
	{
		if (wav.length () > 0)
		{
			File soundFile = new File (wav);	// File object for filename

			 try
			{
				AudioInputStream source =
					AudioSystem.
					getAudioInputStream (soundFile);
				 DataLine.Info clipInfo =
					new DataLine.Info (Clip.class,
							   source.
							   getFormat ());
				if (AudioSystem.isLineSupported (clipInfo))
				{
					DisplayTimer.newClip = (Clip) AudioSystem.getLine (clipInfo);	// Create the clip
					DisplayTimer.newClip.open (source);
					if (loop)
						DisplayTimer.newClip.
							loop (DisplayTimer.
							      newClip.
							      LOOP_CONTINUOUSLY);
					else
						DisplayTimer.newClip.start ();
				}
				else
				{
					JOptionPane.showMessageDialog (null,
								       "Clip Not Supported",
								       "Clip Not Supported",
								       JOptionPane.
								       WARNING_MESSAGE);
				}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog (null,
							       "Error Playing Clip",
							       "Clip Error",
							       JOptionPane.
							       WARNING_MESSAGE);
			}
		}
	}
}
