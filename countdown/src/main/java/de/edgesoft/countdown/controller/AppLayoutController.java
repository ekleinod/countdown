package de.edgesoft.countdown.controller;

import de.edgesoft.countdown.Resources;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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
	 * Text field time.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	private TextField txtTime;
	

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
//		btnProgramQuit.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/application-exit.png")));
		
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
//    	primaryStage.setX(Double.parseDouble(Prefs.get(PrefKey.STAGE_X)));
//    	primaryStage.setY(Double.parseDouble(Prefs.get(PrefKey.STAGE_Y)));
//
//    	primaryStage.setWidth(Double.parseDouble(Prefs.get(PrefKey.STAGE_WIDTH)));
//    	primaryStage.setHeight(Double.parseDouble(Prefs.get(PrefKey.STAGE_HEIGHT)));
//
//    	primaryStage.setMaximized(Boolean.parseBoolean(Prefs.get(PrefKey.MAXIMIZED)));
//
//		// if changed, save bounds to preferences
//		primaryStage.xProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//			if (!primaryStage.isMaximized()) {
//				Prefs.put(PrefKey.STAGE_X, Double.toString(newValue.doubleValue()));
//			}
//		});
//		primaryStage.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//			if (!primaryStage.isMaximized()) {
//				Prefs.put(PrefKey.STAGE_WIDTH, Double.toString(newValue.doubleValue()));
//			}
//		});
//		primaryStage.yProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//			if (!primaryStage.isMaximized()) {
//				Prefs.put(PrefKey.STAGE_Y, Double.toString(newValue.doubleValue()));
//			}
//		});
//		primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//			if (!primaryStage.isMaximized()) {
//				Prefs.put(PrefKey.STAGE_HEIGHT, Double.toString(newValue.doubleValue()));
//			}
//		});
//
//		primaryStage.maximizedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//			Prefs.put(PrefKey.MAXIMIZED, Boolean.toString(newValue.booleanValue()));
//		});
//
        // set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
        	event.consume();
        	handleProgramExit();
        });

    }

	/**
	 * Start.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	public void handleStart() {
		System.out.println("start");
	}

	/**
	 * Program menu exit.
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@FXML
	public void handleProgramExit() {
		Platform.exit();
	}

}

/* EOF */
