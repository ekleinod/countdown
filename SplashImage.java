import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SplashImage extends JDialog
{
	String imagePath = new String ();

	public SplashImage ()
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
		this.addMouseListener (new
				       SplashImage_this_mouseAdapter (this));
		Dimension screenSize =
			Toolkit.getDefaultToolkit ().getScreenSize ();
		 this.setSize ((int) screenSize.getWidth () + 10,
			       (int) screenSize.getHeight () + 35);
		 this.setLocation (-5, -30);
		 this.getContentPane ().setLayout (null);
		 this.setModal (true);
	}

	public void paint (Graphics g)
	{
		super.paint (g);
		ImageIcon image = new ImageIcon (imagePath);
		Dimension screenSize =
			Toolkit.getDefaultToolkit ().getScreenSize ();
		if (screenSize.getHeight () <
		    image.getImage ().getHeight (this)
		    && screenSize.getWidth () <
		    image.getImage ().getWidth (this))
			this.getContentPane ().getGraphics ().
				drawImage (image.getImage (), 0, 0,
					   (int) screenSize.getWidth () + 3,
					   (int) screenSize.getHeight (),
					   this);
		else
			this.getContentPane ().getGraphics ().
				drawImage (image.getImage (),
					   (int) (screenSize.getWidth () -
						  image.getImage ().
						  getWidth (this)) / 2,
					   (int) (screenSize.getHeight () -
						  image.getImage ().
						  getHeight (this)) / 2,
					   this);
	}

	void this_mouseClicked (MouseEvent e)
	{
		this.dispose ();
	}

}

class SplashImage_this_mouseAdapter extends java.awt.event.MouseAdapter
{
	SplashImage adaptee;

	 SplashImage_this_mouseAdapter (SplashImage adaptee)
	{
		this.adaptee = adaptee;
	}
	public void mouseClicked (MouseEvent e)
	{
		adaptee.this_mouseClicked (e);
	}
}
