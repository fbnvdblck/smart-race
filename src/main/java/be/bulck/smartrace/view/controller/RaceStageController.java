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

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.service.RaceService;
import be.bulck.smartrace.service.factory.RaceServiceFactory;
import be.bulck.smartrace.view.stage.RaceStage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The controller for the race stage.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceStageController extends StageController<RaceStage> {

    /** The label for the info name. */
    @FXML
    private Label infoNameLabel;

    /** The label for the info location. */
    @FXML
    private Label infoLocationLabel;

    /** The label for the info description. */
    @FXML
    private Label infoDescriptionLabel;

    /** The label for the info state. */
    @FXML
    private Label infoStateLabel;

    /** The race service. */
    private RaceService raceService;

    /** The race to handle. */
    private Race race;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceStageController.class);


    /**
     * Constructs an instance of the race stage controller.
     */
    public RaceStageController() throws DataProviderException {
        raceService = RaceServiceFactory.getInstance().getRaceService();
        race = raceService.getRace();
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        // Loads the information about the race
        infoNameLabel.setText(race.getName());
        infoLocationLabel.setText(race.getLocation());
        infoStateLabel.setText(LanguageSupport.getText("model.race.state." + race.getState().getValue()));

        if (!race.getDescription().isEmpty())
            infoDescriptionLabel.setText(race.getDescription());
        else
            infoDescriptionLabel.setText(LanguageSupport.getText("stage.race.view.info.value.none"));
    }

    /**
     * Quits the race process.
     */
    private void handleQuit() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LanguageSupport.getText("stage.race.dialog.quit.title"));
        alert.setHeaderText(LanguageSupport.getText("stage.race.dialog.quit.header"));
        alert.setContentText(LanguageSupport.getText("stage.race.dialog.quit.text"));

        ButtonType buttonExit = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.exit"));
        ButtonType buttonClose = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.close"));
        ButtonType buttonCancel = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonExit, buttonClose, buttonCancel);

        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == buttonExit) {
            app.closeRaceStage();

            try {
                raceService.save(race);
            } catch (DataHandlerException ex) {
                log.error(ex.getMessage(), ex);
            }

            System.exit(0);
        }

        else if (choice.get() == buttonClose) {
            app.closeRaceStage();

            try {
                raceService.save(race);
            } catch (DataHandlerException ex) {
                log.error(ex.getMessage(), ex);
            }

            app.openWelcomeStage();
        }
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleQuit();
    }
}
