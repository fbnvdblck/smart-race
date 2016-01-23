/*
 * Smart Race
 * Copyright (C) 2015-2016 Fabien Vanden Bulck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.bulck.smartrace.view.controller;

import be.bulck.smartrace.view.stage.WelcomeStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

/**
 * The controller for the welcome stage.
 *
 * @author Fabien Vanden Bulck
 */
public class WelcomeStageController extends StageController<WelcomeStage> {

    /** The button to create a race. */
    @FXML
    private Button createButton;

    /** The button to load an existing race. */
    @FXML
    private Button loadButton;


    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        FontAwesomeIconView createIconView = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
        createIconView.setSize("8em");
        FontAwesomeIconView loadIconView = new FontAwesomeIconView(FontAwesomeIcon.UPLOAD);
        loadIconView.setSize("8em");

        createButton.setCursor(Cursor.HAND);
        createButton.setStyle("-fx-background-color: transparent");
        createButton.setGraphic(createIconView);
        loadButton.setCursor(Cursor.HAND);
        loadButton.setStyle("-fx-background-color: transparent");
        loadButton.setGraphic(loadIconView);
    }

    /**
     * Switches to create race stage.
     */
    @FXML
    private void handleCreateRace() {
        app.closeWelcomeStage();
        app.openRaceSetupStage();
    }
}
