/*
 * Smart Race
 * Copyright (C) 2015-2017 Fabien Vanden Bulck
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

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.view.stage.LicenseStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The controller for the license stage.
 *
 * @author Fabien Vanden Bulck
 */
public class LicenseStageController extends StageController<LicenseStage> {

    /** The text area for the license. */
    @FXML
    private TextArea licenseTextArea;

    /** The button to close the stage. */
    @FXML
    private Button closeButton;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(LicenseStageController.class);

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        closeButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));

        try {
            String licenseText = new String(Files.readAllBytes(Paths.get(SmartRace.class.getResource("/licenses/gnu_gpl_v3.txt").getPath())));
            licenseTextArea.setText(licenseText);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Closes the stage.
     */
    @FXML
    private void handleClose() {
        app.closeLicenseStage();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleClose();
    }
}
