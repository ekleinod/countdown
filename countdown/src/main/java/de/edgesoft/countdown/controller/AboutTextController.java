package de.edgesoft.countdown.controller;

import de.edgesoft.countdown.Countdown;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

/**
 * Controller for about text.
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
public class AboutTextController {

	/**
	 * Email link.
	 *
	 * @param theEvent action event
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@SuppressWarnings("static-method")
	@FXML
	private void handleEmailLinkAction(final ActionEvent theEvent) {
		Countdown.hostServices.showDocument(String.format("mailto:%s", ((Hyperlink) theEvent.getTarget()).getText()));
	}

	/**
	 * Web link.
	 *
	 * @param theEvent action event
	 *
	 * @version 0.3.0
	 * @since 0.3.0
	 */
	@SuppressWarnings("static-method")
	@FXML
	private void handleWebLinkAction(final ActionEvent theEvent) {
		Countdown.hostServices.showDocument(((Hyperlink) theEvent.getTarget()).getText());
	}

}

/* EOF */
