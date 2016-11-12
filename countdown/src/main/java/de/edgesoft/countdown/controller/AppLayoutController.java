package de.edgesoft.countdown.controller;

import java.io.File;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import de.edgesoft.countdown.Resources;
import de.edgesoft.edgeutils.EdgeUtilsException;
import de.edgesoft.edgeutils.commons.Info;
import de.edgesoft.edgeutils.files.JAXBFiles;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for application layout.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of countdown.
 *
 * countdown is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * countdown is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with countdown.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.3.0
 * @since 0.3.0
 */
public class AppLayoutController {

	/**
	 * Application icon.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	public static final Image ICON = Resources.loadImage("images/icon-32.png");

	/**
	 * App border pane.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private BorderPane appPane;

	/**
	 * Menu item program -> preferences.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuProgramPreferences;

	/**
	 * Menu item program -> quit.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuProgramQuit;

	/**
	 * Menu item file -> new.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private MenuItem mnuFileNew;

	/**
	 * Menu item file -> open.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private MenuItem mnuFileOpen;

	/**
	 * Menu item file -> save.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private MenuItem mnuFileSave;

	/**
	 * Menu item file -> save as.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private MenuItem mnuFileSaveAs;

	/**
	 * Menu item referee -> overview.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuRefereeOverview;

	/**
	 * Menu item referee -> communication.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuRefereeCommunication;

	/**
	 * Menu item statistics -> data.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuStatisticsData;

	/**
	 * Menu item help -> about.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private MenuItem mnuHelpAbout;

	/**
	 * Main toolbar.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private ToolBar barMain;

	/**
	 * Button program -> quit.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private Button btnProgramQuit;

	/**
	 * Button referee -> overview.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private Button btnRefereeOverview;

	/**
	 * Button referee -> communication.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private Button btnRefereeCommunication;

	/**
	 * Button statistics -> data.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private Button btnStatisticsData;


	/**
	 * Primary stage.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private Stage primaryStage = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private void initialize() {

		// icons
		mnuProgramPreferences.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/configure.png")));
		mnuProgramQuit.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/application-exit.png")));
		btnProgramQuit.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/application-exit.png")));

		mnuFileNew.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-new.png")));
		mnuFileOpen.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-open.png")));
		mnuFileSave.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-save.png")));
		mnuFileSaveAs.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-save-as.png")));

		mnuRefereeOverview.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-referees.png")));
		btnRefereeOverview.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-referees.png")));
		mnuRefereeCommunication.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/mail-mark-unread.png")));
		btnRefereeCommunication.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/mail-mark-unread.png")));

		mnuStatisticsData.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-bar.png")));
		btnStatisticsData.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-bar.png")));

		mnuHelpAbout.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/help-about.png")));

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param thePrimaryStage primary stage
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	public void initController(final Stage thePrimaryStage) {

		primaryStage = thePrimaryStage;

        // set icon
		primaryStage.getIcons().add(ICON);

        // Show the scene containing the root layout.
        Scene scene = new Scene(appPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        // resize to last dimensions
    	primaryStage.setX(Double.parseDouble(Prefs.get(PrefKey.STAGE_X)));
    	primaryStage.setY(Double.parseDouble(Prefs.get(PrefKey.STAGE_Y)));

    	primaryStage.setWidth(Double.parseDouble(Prefs.get(PrefKey.STAGE_WIDTH)));
    	primaryStage.setHeight(Double.parseDouble(Prefs.get(PrefKey.STAGE_HEIGHT)));

    	primaryStage.setMaximized(Boolean.parseBoolean(Prefs.get(PrefKey.MAXIMIZED)));

		// if changed, save bounds to preferences
		primaryStage.xProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_X, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_WIDTH, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.yProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_Y, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_HEIGHT, Double.toString(newValue.doubleValue()));
			}
		});

		primaryStage.maximizedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			Prefs.put(PrefKey.MAXIMIZED, Boolean.toString(newValue.booleanValue()));
		});

        // set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
        	event.consume();
        	handleProgramExit();
        });

		// finally, we can initialize the data
		initData();

    }

	/**
	 * Initializes the data.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private void initData() {

		if (AppModel.getFilename().isEmpty()) {
			newData();
		} else {
			openData(Prefs.get(PrefKey.FILE));
		}

    }

	/**
	 * Creates new data.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private void newData() {

		de.edgesoft.refereemanager.jaxb.RefereeManager dtaRefMan = new ObjectFactory().createRefereeManager();

		Info info = new de.edgesoft.edgeutils.commons.ObjectFactory().createInfo();

		info.setCreated(LocalDateTime.now());
		info.setModified(LocalDateTime.now());
		info.setAppversion(RefereeManager.VERSION);
		info.setDocversion(RefereeManager.VERSION);
		info.setCreator(RefereeManager.class.getCanonicalName());

		dtaRefMan.setInfo(info);

		Content content = new ObjectFactory().createContent();
		dtaRefMan.setContent(content);

		AppModel.setData(dtaRefMan);
		AppModel.setFilename(null);
		AppModel.setModified(false);

		setAppTitle();

    }

	/**
	 * Sets the app title.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	public void setAppTitle() {

		String sFile = Prefs.get(PrefKey.FILE).isEmpty() ?
				"" :
				String.format(" - %s", Boolean.parseBoolean(Prefs.get(PrefKey.TITLE_FULLPATH)) ?
						Paths.get(Prefs.get(PrefKey.FILE)).toAbsolutePath().toString() :
						Paths.get(Prefs.get(PrefKey.FILE)).getFileName().toString());

		primaryStage.setTitle(String.format("Referee Manager%s%s",
				sFile,
				AppModel.isModified() ? " *" : ""
				));

    }

	/**
	 * Loads data from the given file.
	 *
	 * @param theFilename filename
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private void openData(final String theFilename) {

		try {

			de.edgesoft.refereemanager.jaxb.RefereeManager dtaRefMan = JAXBFiles.unmarshal(theFilename, de.edgesoft.refereemanager.jaxb.RefereeManager.class);

			AppModel.setData(dtaRefMan);
			AppModel.setFilename(theFilename);
			AppModel.setModified(false);

			setAppTitle();

		} catch (EdgeUtilsException e) {

	        AlertUtils.createAlert(AlertType.ERROR, primaryStage,
	        		"Datenfehler",
	        		"Ein Fehler ist beim Laden der Referee-Manager-Daten aufgetreten.",
	        		MessageFormat.format("{0}\nDas Programm wird ohne Daten fortgeführt.", e.getMessage()))
	        .showAndWait();

	        newData();

		}

    }

	/**
	 * Program menu preferences.
	 *
	 * Opens the preferences edit dialog.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private void handleProgramPreferences() {

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("PreferencesDialog");
    	AnchorPane preferencesDialog = (AnchorPane) pneLoad.getKey();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Einstellungen");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);

        Scene scene = new Scene(preferencesDialog);
        dialogStage.setScene(scene);

        // initialize controller
        PreferencesDialogController controller = pneLoad.getValue().getController();
        controller.initController(this, dialogStage, null);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        setAppTitle();

	}

	/**
	 * Program menu exit.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	public void handleProgramExit() {
		if (checkModified()) {
			Platform.exit();
		}
	}

	/**
	 * File menu new.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private void handleFileNew() {
		if (checkModified()) {
			newData();
		}
	}

	/**
	 * File menu open.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private void handleFileOpen() {

		if (checkModified()) {

			FileChooser fileChooser = new FileChooser();

			fileChooser.setTitle("Referee-Manager-Datei öffnen");
	        fileChooser.getExtensionFilters().addAll(
	        		new FileChooser.ExtensionFilter("Referee-Manager-Dateien (*.refman, *.xml)", "*.refman", "*.xml"),
	        		new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
	        		);
	        if (!Prefs.get(PrefKey.PATH).isEmpty()) {
	        	fileChooser.setInitialDirectory(new File(Prefs.get(PrefKey.PATH)));
	        }

	        File file = fileChooser.showOpenDialog(primaryStage);

	        if (file != null) {
	            openData(file.getPath());
	        }

		}

	}

	/**
	 * File menu save.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    public void handleFileSave() {
        if (Prefs.get(PrefKey.FILE).isEmpty()) {
        	handleFileSaveAs();
        } else {
        	saveData(Prefs.get(PrefKey.FILE));
        }
    }

	/**
	 * File menu save as.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    private void handleFileSaveAs() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Referee-Manager-Datei speichern");
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Referee-Manager-Dateien (*.refman, *.xml)", "*.refman", "*.xml"),
        		new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
        		);
        if (!Prefs.get(PrefKey.PATH).isEmpty()) {
        	fileChooser.setInitialDirectory(new File(Prefs.get(PrefKey.PATH)));
        }

        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
        	if (!file.getName().contains(".")) {
        		file = new File(String.format("%s.refman", file.getPath()));
        	}
            saveData(file.getPath());
        }

    }

	/**
	 * Help menu about.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    private void handleHelpAbout() {

        Alert alert = AlertUtils.createAlert(AlertType.INFORMATION, primaryStage,
        		"Über \"Referee-Manager\"",
        		MessageFormat.format("Referee-Manager Version {0}", RefereeManager.VERSION),
        		null
        		);

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("AboutText");
    	VBox aboutText = (VBox) pneLoad.getKey();
    	alert.getDialogPane().contentProperty().set(aboutText);

        alert.setGraphic(new ImageView(Resources.loadImage("images/icon-64.png")));
        alert.showAndWait();

    }

	/**
	 * Menu referee -> overview.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    private void handleRefereeOverview() {

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeOverview");
    	AnchorPane refOverview = (AnchorPane) pneLoad.getKey();

        // Set event overview into the center of root layout.
        appPane.setCenter(refOverview);

        // Give the controller access to the app.
        RefereeOverviewController ctlRefOverview = pneLoad.getValue().getController();
        ctlRefOverview.initController(this);

    }

	/**
	 * Menu referee -> communication.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    private void handleRefereeCommunication() {

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeCommunication");
    	BorderPane pneCommunication = (BorderPane) pneLoad.getKey();

        // Set event overview into the center of root layout.
        appPane.setCenter(pneCommunication);

        // Give the controller access to the app.
        RefereeCommunicationController ctlRefCommunication = pneLoad.getValue().getController();
        ctlRefCommunication.initController(this);

    }

	/**
	 * Menu statistics -> data.
	 *
	 * Shows the data statistics.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
    private void handleStatisticsData() {

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("Statistics");
    	AnchorPane statistics = (AnchorPane) pneLoad.getKey();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistik");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);

        Scene scene = new Scene(statistics);
        dialogStage.setScene(scene);

        // Set the events into the controller.
        StatisticsController controller = pneLoad.getValue().getController();
        controller.fillStatistics();
        controller.setDialogStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.show();

    }

	/**
	 * Check if data is modified, show corresponding dialog, save data if needed.
	 *
	 * @return did user select continue (true) or cancel (false)?
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private boolean checkModified() {

		boolean doContinue = true;

		if (AppModel.isModified()) {

	    	Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, primaryStage,
	    			"Nicht gespeicherte Änderungen",
	    			"Sie haben Änderungen durchgeführt, die noch nicht gespeichert wurden.",
	    			"Wollen Sie die geänderten Daten speichern, nicht speichern oder wollen Sie den gesamten Vorgang abbrechen?");

	        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

	        Optional<ButtonType> result = alert.showAndWait();
	        if (result.isPresent()) {
    			if (result.get() == ButtonType.YES) {
    				handleFileSave();
    				doContinue = true;
    			}
    			if (result.get() == ButtonType.NO) {
    				doContinue = true;
    			}
    			if (result.get() == ButtonType.CANCEL) {
    				doContinue = false;
    			}
	        }

		}

		return doContinue;

	}

	/**
	 * Saves the data.
	 *
	 * @param theFilename filename
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	private void saveData(final String theFilename) {

		try {

			AppModel.getData().getInfo().setModified(LocalDateTime.now());
			AppModel.getData().getInfo().setAppversion(RefereeManager.VERSION);
			AppModel.getData().getInfo().setDocversion(RefereeManager.VERSION);
			AppModel.getData().getInfo().setCreator(RefereeManager.class.getCanonicalName());

//			((ContentModel) AppModel.getData().getContent()).sortEvents();

//			if (ctlEventOverview != null) {
//				ctlEventOverview.setTableItems();
//			}

			JAXBFiles.marshal(new ObjectFactory().createRefereemanager(AppModel.getData()), theFilename, null);

			AppModel.setFilename(theFilename);
			AppModel.setModified(false);

		} catch (EdgeUtilsException e) {

	        AlertUtils.createAlert(AlertType.ERROR, primaryStage,
	        		"Datenfehler",
	        		"Ein Fehler ist beim Speichern der Referee-Manager-Daten aufgetreten.",
	        		MessageFormat.format("{0}\nDie Daten wurden nicht gespeichert.", e.getMessage()))
	        .showAndWait();

		}

		setAppTitle();

    }

	/**
	 * Returns primary stage.
	 *
	 * @return primary stage
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

/* EOF */
