import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;

/**
 * This is the Countdown Timer Version&nbsp;0.2: gui.
 * <p>
 * The Countdown Timer counts from a given time backwards to zero. Reaching zero
 * it can play a sound, count upwards or end itself.
 * </p>
 * <p>
 * This class is the gui.
 * </p>
 *
 * @author Mustafa Hamdani (V 0.1), Ekkart Kleinod (V 0.2-0.3)
 * @version 0.3
 * @since 0.1
 */
public class ConfigureTimer extends JFrame {

	/** serial ID of java 1.5. */
	private static final long serialVersionUID = 7623148751924534339L;

	/** Configuration. */
	private Properties prpConfiguration = new Properties();

	private JPanel contentPane;

	JMenuBar jMenuBar1 = new JMenuBar();

	JMenu jMenuFile = new JMenu();

	JMenuItem jMenuFileExit = new JMenuItem();

	JMenu jMenuHelp = new JMenu();

	JMenuItem jMenuHelpShortcut = new JMenuItem();

	JMenuItem jMenuHelpAbout = new JMenuItem();

	JPanel pnlTexts = new JPanel();

	private JTextField txtRunningText = new JTextField();

	private JLabel lblCurrentTime = new JLabel();

	Timer timer = new Timer(1000,
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblCurrentTime.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).format(new Date()));
				}
			});

	private JTextField txtStopText = new JTextField();

	private JTextField txtBackgroundPath = new JTextField();

	private JButton btnBackgroundImage = new JButton();

	private JCheckBox chkMaximizeBackground = new JCheckBox();

	private JPanel pnlTime = new JPanel();

	private JPanel pnlButtons = new JPanel();

	private JPanel pnlOptions = new JPanel();

	private JCheckBox chkDisplayCurrentTime = new JCheckBox();

	private JCheckBox chkDisplayOvertime = new JCheckBox();

	private JCheckBox chkDisplayDescription = new JCheckBox();

	private JPanel pnlFonts = new JPanel();

	private JPanel pnlPreview = new JPanel();

	private JLabel lblPreviewNumber = new JLabel();

	private JLabel lblPreviewText = new JLabel();

	private JButton btnChangeNumberColor = new JButton();

	private JButton btnChangeTextColor = new JButton();

	private JButton btnChangeBackgroundColor = new JButton();

	private JPanel pnlBackground = new JPanel();

	private JPanel pnlStart = new JPanel();

	private JTextField txtSplashPath = new JTextField();

	private JButton btnBrowseSplash = new JButton();

	private JButton btnBrowseWAV = new JButton();

	private JTextField txtWAVPath = new JTextField();

	private JPanel pnlStop = new JPanel();

	private JButton btnRun = new JButton();

	private JButton btnExit = new JButton();

	JMenu jMenuOptions = new JMenu();

	JMenuItem jMenuOptSave = new JMenuItem();

	JMenuItem jMenuOptLoad = new JMenuItem();

	ComboBoxModel phyFonts1 = new DefaultComboBoxModel(GraphicsEnvironment
			.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

	ComboBoxModel phyFonts2 = new DefaultComboBoxModel(GraphicsEnvironment
			.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

	private JComboBox cboNumberFontList = new JComboBox(phyFonts1);

	private JComboBox cboTextFontList = new JComboBox(phyFonts2);

	private JComboBox cboNumberFontStyleList = new JComboBox();

	private JComboBox cboTextFontStyleList = new JComboBox();

	private JTextField txtMinutes = new JTextField();

	private JTextField txtSeconds = new JTextField();

	private JCheckBox chkDisplayDeciSeconds = new JCheckBox();

	private JCheckBox chkDisposeOnStop = new JCheckBox();

	private JCheckBox chkLoopWAV = new JCheckBox();

	private JCheckBox chkMaximizeSplash = new JCheckBox();

	private JCheckBox chkRandomBackground = new JCheckBox();

	private JSpinner spnBackgroundInterval = new JSpinner(new SpinnerNumberModel(1, 1, 60, 1));
	
	private JTabbedPane tabConfig = new JTabbedPane(JTabbedPane.TOP);
	
	private JPanel pnlTabTime = new JPanel();

	private JPanel pnlTabFontColor = new JPanel();

	private JPanel pnlTabStart = new JPanel();

	private JPanel pnlTabStop = new JPanel();

	/**
	 * Constructor: start Frame-Initialization.
	 *
	 * @param strFile preference file; null if none given
	 */
	public ConfigureTimer(String strFile) {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
			if (strFile != null) {
				loadConfiguration(strFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 */
	private void jbInit() throws Exception {

        // Menus
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApplication();
			}
		});
		jMenuOptSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveConfiguration();
			}
		});
		jMenuOptLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadConfiguration();
			}
		});
		jMenuHelp.setText("Help");
		jMenuHelpShortcut.setText("Shortcut Keys");
		jMenuHelpShortcut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelpShortcut();
			}
		});
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelpAbout();
			}
		});
		jMenuOptions.setText("Options");
		jMenuOptSave.setText("Save Configuration");
		jMenuOptLoad.setText("Load Configuration");
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpShortcut);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuOptions);
		jMenuBar1.add(jMenuHelp);
		jMenuOptions.add(jMenuOptSave);
		jMenuOptions.add(jMenuOptLoad);
		this.setJMenuBar(jMenuBar1);

		// Current time
		lblCurrentTime.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).format(new Date()));

		// Time
		txtMinutes.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkKeys(e, 2, true);
			}
		});
		txtSeconds.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkKeys(e, 2, true);
			}
		});
		pnlTime.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Time"));
		pnlTime.setLayout(new GridBagLayout());
		pnlTime.setToolTipText("Enter the countdown time.");
		GridBagHandler.addComponent(pnlTime, new JLabel("Minutes"), 0, 0);
		GridBagHandler.addComponent(pnlTime, txtMinutes, 1, 0);
		GridBagHandler.addComponent(pnlTime, new JLabel("Seconds"), 2, 0);
		GridBagHandler.addComponent(pnlTime, txtSeconds, 3, 0);

		// Texts
		txtRunningText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkKeys(e, 30, false);
			}
		});
		txtStopText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				checkKeys(e, 30, false);
			}
		});
		btnBackgroundImage.setText("Browse");
		btnBackgroundImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseImage(txtBackgroundPath);
			}
		});
		chkMaximizeBackground.setText("Maximize background image");
		pnlTexts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Texts and Background Image"));
		pnlTexts.setLayout(new GridBagLayout());
		pnlTexts.setToolTipText("Enter the displayed texts and optionally a background image.");
		GridBagHandler.addComponent(pnlTexts, new JLabel("Caption during Countdown (30 characters max.)"), 0, 0, 0, 1);
		GridBagHandler.addComponent(pnlTexts, txtRunningText, 0, 1, 0, 1);
		GridBagHandler.addComponent(pnlTexts, new JLabel("Caption to display at end of Countdown (30 characters max.)"), 0, 2, 0, 1);
		GridBagHandler.addComponent(pnlTexts, txtStopText, 0, 3, 0, 1);
		GridBagHandler.addComponent(pnlTexts, new JLabel("Background Image"), 0, 4);
		GridBagHandler.addComponent(pnlTexts, txtBackgroundPath, 1, 4);
		GridBagHandler.addComponent(pnlTexts, btnBackgroundImage, 2, 4);
		GridBagHandler.addComponent(pnlTexts, chkMaximizeBackground, 0, 5, 0, 1);

		// fonts
		cboTextFontList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFontPreview(lblPreviewText, cboTextFontList, cboTextFontStyleList);
			}
		});
		cboNumberFontList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFontPreview(lblPreviewNumber, cboNumberFontList, cboNumberFontStyleList);
			}
		});
		cboNumberFontStyleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFontPreview(lblPreviewNumber, cboNumberFontList, cboNumberFontStyleList);
			}
		});
		cboTextFontStyleList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFontPreview(lblPreviewText, cboTextFontList, cboTextFontStyleList);
			}
		});
		cboNumberFontStyleList.addItem("Regular");
		cboNumberFontStyleList.addItem("Bold");
		cboNumberFontStyleList.addItem("Italics");
		cboTextFontStyleList.addItem("Regular");
		cboTextFontStyleList.addItem("Bold");
		cboTextFontStyleList.addItem("Italics");
		pnlFonts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Fonts"));
		pnlFonts.setLayout(new GridBagLayout());
		pnlFonts.setToolTipText("Select fonts.");
		GridBagHandler.addComponent(pnlFonts, new JLabel("Number Font"), 0, 0);
		GridBagHandler.addComponent(pnlFonts, cboNumberFontList, 1, 0);
		GridBagHandler.addComponent(pnlFonts, cboNumberFontStyleList, 2, 0);
		GridBagHandler.addComponent(pnlFonts, new JLabel("Text Font"), 0, 1);
		GridBagHandler.addComponent(pnlFonts, cboTextFontList, 1, 1);
		GridBagHandler.addComponent(pnlFonts, cboTextFontStyleList, 2, 1);

		// preview panel
		lblPreviewNumber.setText("12:34");
		lblPreviewText.setText("ABCD");
		lblPreviewNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreviewText.setHorizontalAlignment(SwingConstants.CENTER);
		// set opaque, so we can see the background color
		lblPreviewNumber.setOpaque(true);
		lblPreviewText.setOpaque(true);
		pnlPreview.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview"));
		pnlPreview.setLayout(new GridBagLayout());
		pnlPreview.setToolTipText("See preview of selected fonts and colors.");
		GridBagHandler.addComponent(pnlPreview, lblPreviewNumber, 0, 0);
		GridBagHandler.addComponent(pnlPreview, lblPreviewText, 0, 1);

		// colors
		btnChangeNumberColor.setText("Change Color");
		btnChangeNumberColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPreviewNumber.setForeground(JColorChooser.showDialog(null,
						"Choose the Number Color", lblPreviewNumber.getForeground()));
			}
		});
		btnChangeTextColor.setText("Change Color");
		btnChangeTextColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPreviewText.setForeground(JColorChooser.showDialog(null,
						"Choose the Text Color", lblPreviewText.getForeground()));
			}
		});
		btnChangeBackgroundColor.setText("Change Color");
		btnChangeBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color clrTemp = JColorChooser.showDialog(null,
						"Choose the Background Color", lblPreviewNumber.getBackground());
				lblPreviewNumber.setBackground(clrTemp);
				lblPreviewText.setBackground(clrTemp);
			}
		});
		chkRandomBackground.setText("Random Color");
		pnlBackground.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Colors"));
		pnlBackground.setLayout(new GridBagLayout());
		pnlBackground.setToolTipText("Select colors.");
		GridBagHandler.addComponent(pnlBackground, new JLabel("Number Color"), 0, 0);
		GridBagHandler.addComponent(pnlBackground, btnChangeNumberColor, 1, 0, 0, 1);
		GridBagHandler.addComponent(pnlBackground, new JLabel("Text Color"), 0, 1);
		GridBagHandler.addComponent(pnlBackground, btnChangeTextColor, 1, 1, 0, 1);
		GridBagHandler.addComponent(pnlBackground, new JLabel("Background Color"), 0, 2);
		GridBagHandler.addComponent(pnlBackground, btnChangeBackgroundColor, 1, 2, 0, 1);
		GridBagHandler.addComponent(pnlBackground, chkRandomBackground, 0, 3);
		GridBagHandler.addComponent(pnlBackground, new JLabel("Interval (in seconds)"), 1, 3);
		GridBagHandler.addComponent(pnlBackground, spnBackgroundInterval, 2, 3);

		// splash panel
		btnBrowseSplash.setText("Browse");
		btnBrowseSplash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseImage(txtSplashPath);
			}
		});
		chkMaximizeSplash.setText("Maximize Splash Screen");
		pnlStart.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Timer Start"));
		pnlStart.setLayout(new GridBagLayout());
		pnlStart.setToolTipText("Select start options.");
		GridBagHandler.addComponent(pnlStart, new JLabel("Splash Screen Graphic"), 0, 0);
		GridBagHandler.addComponent(pnlStart, txtSplashPath, 1, 0);
		GridBagHandler.addComponent(pnlStart, btnBrowseSplash, 2, 0);
		GridBagHandler.addComponent(pnlStart, chkMaximizeSplash, 0, 1, 0, 1);

		// stop panel
		btnBrowseWAV.setText("Browse");
		btnBrowseWAV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseWAV();
			}
		});
		chkLoopWAV.setText("Play in Loop");
		chkDisposeOnStop.setText("Exit program at stop");
		pnlStop.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Timer Stop"));
		pnlStop.setLayout(new GridBagLayout());
		pnlStop.setToolTipText("Select stop options.");
		GridBagHandler.addComponent(pnlStop, new JLabel("WAV to Play"), 0, 0);
		GridBagHandler.addComponent(pnlStop, txtWAVPath, 1, 0);
		GridBagHandler.addComponent(pnlStop, btnBrowseWAV, 2, 0);
		GridBagHandler.addComponent(pnlStop, chkLoopWAV, 3, 0);
		GridBagHandler.addComponent(pnlStop, chkDisposeOnStop, 0, 1);

		// button panel
		btnRun.setText("Run");
		btnRun.setMnemonic(KeyEvent.VK_R);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTimer();
			}
		});
		btnExit.setText("Exit");
		btnExit.setMnemonic(KeyEvent.VK_X);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApplication();
			}
		});
		pnlButtons.setLayout(new GridBagLayout());
		pnlButtons.setToolTipText("Start timer or exit application.");
		GridBagHandler.addComponent(pnlButtons, btnRun, 0, 0);
		GridBagHandler.addComponent(pnlButtons, btnExit, 1, 0);

		// options
		chkDisplayCurrentTime.setText("Display current time");
		chkDisplayOvertime.setText("Count up for overtime");
		chkDisplayDeciSeconds.setText("Display tenths of second");
		chkDisplayDescription.setText("Display counter description");
		pnlOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Options"));
		pnlOptions.setLayout(new GridBagLayout());
		pnlOptions.setToolTipText("Select options.");
		GridBagHandler.addComponent(pnlOptions, chkDisplayCurrentTime, 0, 0);
		GridBagHandler.addComponent(pnlOptions, chkDisplayOvertime, 1, 0);
		GridBagHandler.addComponent(pnlOptions, chkDisplayDeciSeconds, 0, 1);
		GridBagHandler.addComponent(pnlOptions, chkDisplayDescription, 1, 1);
		
		// tabbed panel
		pnlTabTime.setLayout(new GridBagLayout());
		GridBagHandler.addComponent(pnlTabTime, pnlTime, 0, 0);
		GridBagHandler.addComponent(pnlTabTime, pnlTexts, 0, 1);
		GridBagHandler.addComponent(pnlTabTime, pnlOptions, 0, 2);
		
		pnlTabFontColor.setLayout(new GridBagLayout());
		GridBagHandler.addComponent(pnlTabFontColor, pnlFonts, 0, 0);
		GridBagHandler.addComponent(pnlTabFontColor, pnlBackground, 0, 1);
		GridBagHandler.addComponent(pnlTabFontColor, pnlPreview, 0, 2);
		
		pnlTabStart.setLayout(new GridBagLayout());
		GridBagHandler.addComponent(pnlTabStart, pnlStart, 0, 0);
		
		pnlTabStop.setLayout(new GridBagLayout());
		GridBagHandler.addComponent(pnlTabStop, pnlStop, 0, 0);
		
		tabConfig.addTab("time and texts", pnlTabTime);
		tabConfig.addTab("look", pnlTabFontColor);
		tabConfig.addTab("start", pnlTabStart);
		tabConfig.addTab("stop", pnlTabStop);

		// main panel
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(lblCurrentTime, BorderLayout.NORTH);
		contentPane.add(tabConfig, BorderLayout.CENTER);
		contentPane.add(pnlButtons, BorderLayout.SOUTH);

		// Timer settings
		timer.setRepeats(true);
		timer.start();

		// Framesize, -title, -position (centered)
		this.pack();
		this.setTitle(CountdownTimer.APP_NAME + " - Version " + CountdownTimer.APP_VERSION);
		this.setLocationRelativeTo(null);

		// close with own method
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
					closeApplication();
				}
            });

	}

	/**
	 * Closes the application.
	 */
	public void closeApplication() {
		System.exit(0);
	}

	/**
	 * Show help message about shortcuts.
	 */
	private void showHelpShortcut() {
		JOptionPane.showMessageDialog(this,
				"Pause / Resume         P\nClose Window            Alt+F4\n",
				"Shortcut Keys", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Show about message.
	 */
	private void showHelpAbout() {
		JOptionPane.showMessageDialog(
			this,
			CountdownTimer.APP_NAME + "\n" +
			"Version " + CountdownTimer.APP_VERSION + "\n\n" +
			"Web site: http://www.sourceforge.net/projects/countdown/",
			"About the program",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Selects file for saving the configuration and saves it.
	 */
	private void saveConfiguration() {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();
		String strFile = null;

		// select file to save to
		filter.addExtension("ct");
		filter.setDescription("Countdown-Timer configuration");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		// save configuration
		try {
			strFile = fileChooser.getSelectedFile().toString();
			if (!strFile.endsWith(".ct")) {
				strFile += ".ct";
			}
			FileOutputStream stmOut = new FileOutputStream(strFile);

			// get configuration info
			getConfiguration();
			prpConfiguration.storeToXML(stmOut, "Countdown-Configuration");
			stmOut.close();
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(this, "File '" + strFile + "' could not be saved",
					"File Save Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Gets values of configuration.
	 */
	private void getConfiguration() {
		prpConfiguration = new Properties();
		prpConfiguration.put("txtRunning", txtRunningText.getText());
		prpConfiguration.put("txtStop", txtStopText.getText());
		prpConfiguration.put("txtBackgroundImage", txtBackgroundPath.getText());
		prpConfiguration.put("chkMaximizeBackground", (chkMaximizeBackground.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("txtMinutes", txtMinutes.getText());
		prpConfiguration.put("txtSeconds", txtSeconds.getText());
		prpConfiguration.put("chkDisplayCurrent", (chkDisplayCurrentTime.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("chkDisplayOvertime", (chkDisplayOvertime.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("chkDisplayDeci", (chkDisplayDeciSeconds.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("chkDisplayDescription", (chkDisplayDescription.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("cboNumberFont", cboNumberFontList.getSelectedItem());
		prpConfiguration.put("cboNumberFontStyle", Integer.toString(cboNumberFontStyleList.getSelectedIndex()));
		prpConfiguration.put("cboTextFont", cboTextFontList.getSelectedItem());
		prpConfiguration.put("cboTextFontStyle", Integer.toString(cboTextFontStyleList.getSelectedIndex()));
		prpConfiguration.put("clrNumber", Integer.toString(lblPreviewNumber.getForeground().getRGB()));
		prpConfiguration.put("clrText", Integer.toString(lblPreviewText.getForeground().getRGB()));
		prpConfiguration.put("clrBack", Integer.toString(lblPreviewNumber.getBackground().getRGB()));
		prpConfiguration.put("txtSplash", txtSplashPath.getText());
		prpConfiguration.put("chkMaximizeSplash", (chkMaximizeSplash.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("chkRandom", (chkRandomBackground.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("intInterval", spnBackgroundInterval.getValue().toString());
		prpConfiguration.put("txtWAV", txtWAVPath.getText());
		prpConfiguration.put("chkLoop", (chkLoopWAV.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
		prpConfiguration.put("chkDispose", (chkDisposeOnStop.isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString()));
	}

	/**
	 * Selects file for loading the configuration and loads it.
	 */
	private void loadConfiguration() {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();

		filter.addExtension("ct");
		filter.setDescription("Countdown-Timer configuration");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;

		loadConfiguration(fileChooser.getSelectedFile().toString());
	}

	/**
	 * Loads configuration from given file.
	 *
	 * @param strFile Filename
	 */
	private void loadConfiguration(String strFile) {
		try {
			FileInputStream stmIn = new FileInputStream(strFile);
			prpConfiguration.loadFromXML(stmIn);
			stmIn.close();
			txtRunningText.setText(prpConfiguration.getProperty("txtRunning"));
			txtStopText.setText(prpConfiguration.getProperty("txtStop"));
			txtBackgroundPath.setText(prpConfiguration.getProperty("txtBackgroundImage"));
			chkMaximizeBackground.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkMaximizeBackground")));
			txtMinutes.setText(prpConfiguration.getProperty("txtMinutes"));
			txtSeconds.setText(prpConfiguration.getProperty("txtSeconds"));
			chkDisplayCurrentTime.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayCurrent")));
			chkDisplayOvertime.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayOvertime")));
			chkDisplayDeciSeconds.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDeci")));
			chkDisplayDescription.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkDisplayDescription")));
			cboNumberFontList.setSelectedItem(prpConfiguration.getProperty("cboNumberFont"));
			cboNumberFontStyleList.setSelectedIndex(Integer.parseInt(prpConfiguration.getProperty("cboNumberFontStyle")));
			cboTextFontList.setSelectedItem(prpConfiguration.getProperty("cboTextFont"));
			cboTextFontStyleList.setSelectedIndex(Integer.parseInt(prpConfiguration.getProperty("cboTextFontStyle")));
			lblPreviewNumber.setForeground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrNumber"))));
			lblPreviewText.setForeground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrText"))));
			lblPreviewNumber.setBackground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrBack"))));
			lblPreviewText.setBackground(new Color(Integer.parseInt(prpConfiguration.getProperty("clrBack"))));
			txtSplashPath.setText(prpConfiguration.getProperty("txtSplash"));
			chkMaximizeSplash.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkMaximizeSplash")));
			chkRandomBackground.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkRandom")));
			spnBackgroundInterval.setValue(Integer.valueOf(prpConfiguration.getProperty("intInterval")));
			txtWAVPath.setText(prpConfiguration.getProperty("txtWAV"));
			chkLoopWAV.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkLoop")));
			chkDisposeOnStop.setSelected(Boolean.parseBoolean(prpConfiguration.getProperty("chkDispose")));
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(this, "File '" + strFile + "' could not be loaded",
					"File Load Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Browses for a splash image.
	 * 
	 * @param txtTarget target for selected filename
	 */
	private void browseImage(JTextComponent txtTarget) {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();

		filter.addExtension("jpg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("Images");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			txtTarget.setText(fileChooser.getSelectedFile().toString());
		}
	}

	/**
	 * Browses for a wav file.
	 */
	private void browseWAV() {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();

		filter.addExtension("wav");
		filter.setDescription("WAV File");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			txtWAVPath.setText(fileChooser.getSelectedFile().toString());
		}
	}

	/**
	 * Set font preview for given label.
	 *
	 * @param lblTarget preview label
	 * @param cboFont font list
	 * @param cboStyle style list
	 */
	private void setFontPreview(JLabel lblTarget, JComboBox cboFont, JComboBox cboStyle) {
		lblTarget.setFont(new Font(cboFont.getSelectedItem().toString(), cboStyle.getSelectedIndex(), 15));
	}

	/**
	 * Check typed keys, consume if maximum is reached, sort out wrong keys.
	 * <br />
	 * I (EK) don't know, what the '8' is.
	 * <br />
	 * Consumes (ignores) typed key if a number is needed and not typed
	 * or if the maximum length is exceeded.
	 * The if-construction isn't compact but readable and functional.
	 * Numbers are not allowed to be greater than 59.
	 *
	 * @param e key event
	 * @param iMax maximum length of text
	 * @param bIsNumber true == only numbers; false == all keys
	 */
	private void checkKeys(KeyEvent e, int iMax, boolean bIsNumber) {
		String strText = ((JTextComponent) e.getSource()).getText();
		if ((int) e.getKeyChar() != 8) {
			if (strText.length() >= iMax) {
				e.consume();
			} else if (bIsNumber) {
				if (!Character.isDigit(e.getKeyChar()) || ((strText.length() >= 1) && (strText.charAt(0) >= '6'))) {
					e.consume();
				}
			}
		}
	}

	/**
	 * Starts and shows the timer display.
	 */
	private void showTimer() {

		// displaying the splash image.
		if (txtSplashPath.getText().length() > 0) {
			SplashImage splash = new SplashImage(txtSplashPath.getText(), chkMaximizeSplash.isSelected());
			splash.setVisible(true);
		}

		// displaying the timer.
		getConfiguration();
		DisplayTimer theTimer = new DisplayTimer(prpConfiguration);
		theTimer.setVisible(true);
		if (Boolean.parseBoolean(prpConfiguration.getProperty("chkDispose"))) {
			closeApplication();
		}

	}

}

class MyFileFilter extends FileFilter {

	private Hashtable<String, FileFilter> filters = null;

	private String description = null;

	private String fullDescription = null;

	private boolean useExtensionsInDescription = true;

	public MyFileFilter() {
		this.filters = new Hashtable<String, FileFilter>();
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);

			if (extension != null && filters.get(getExtension(f)) != null) {
				return true;
			}
			;
		}
		return false;
	}

	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');

			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
			;
		}
		return null;
	}

	public void addExtension(String extension) {
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	}

	public String getDescription() {
		if (fullDescription == null) {
			if (description == null || isExtensionListInDescription()) {
				fullDescription = description == null ? "(" : description
						+ " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys();

				if (extensions != null) {
					fullDescription += "." + (String) extensions.nextElement();
					while (extensions.hasMoreElements()) {
						fullDescription += ", ."
								+ (String) extensions.nextElement();
					}
				}
				fullDescription += ")";
			} else {
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	public void setDescription(String description) {
		this.description = description;
		fullDescription = null;
	}

	public void setExtensionListInDescription(boolean b) {
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	public boolean isExtensionListInDescription() {
		return useExtensionsInDescription;
	}
}
