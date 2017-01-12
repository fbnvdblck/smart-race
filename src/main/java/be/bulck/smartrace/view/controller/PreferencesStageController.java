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

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.model.RaceDistanceUnit;
import be.bulck.smartrace.model.RaceElevationUnit;
import be.bulck.smartrace.service.RaceService;
import be.bulck.smartrace.service.factory.RaceServiceFactory;
import be.bulck.smartrace.view.stage.PreferencesStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * The controller for the preferences stage.
 *
 * @author Fabien Vanden Bulck
 */
public class PreferencesStageController extends StageController<PreferencesStage> {

    /** The combo box for the distance unit. */
    @FXML
    private ComboBox<RaceDistanceUnit> distanceUnitComboBox;

    /** The combo box for the elevation unit. */
    @FXML
    private ComboBox<RaceElevationUnit> elevationUnitComboBox;

    /** The button to close the preferences. */
    @FXML
    private Button closeButton;

    /** THe button to save the preferences. */
    @FXML
    private Button saveButton;

    /** The race service. */
    private RaceService raceService;

    /** The race. */
    private Race race;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(PreferencesStageController.class);


    /**
     * Constructs an instance of preferences stage controller.
     */
    public PreferencesStageController() {
        try {
            raceService = RaceServiceFactory.getInstance().getRaceService();
            race = raceService.getRace();
        } catch (DataProviderException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        closeButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        saveButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHECK));

        initComboBox();
    }

    /**
     * Initializes the combo box.
     */
    private void initComboBox() {
        // Distance unit
        distanceUnitComboBox.setConverter(new StringConverter<RaceDistanceUnit>() {
            @Override
            public String toString(RaceDistanceUnit distanceUnit) {
                return LanguageSupport.getText("model.race.distance-unit." + distanceUnit.getValue());
            }

            @Override
            public RaceDistanceUnit fromString(String raw) {
                return null;
            }
        });
        ObservableList<RaceDistanceUnit> distanceUnits = FXCollections.observableArrayList(RaceDistanceUnit.values());
        distanceUnits.remove(RaceDistanceUnit.UNDETERMINED);
        distanceUnitComboBox.setItems(distanceUnits);
        distanceUnitComboBox.getSelectionModel().select(race.getDistanceUnit());

        // Elevation unit
        elevationUnitComboBox.setConverter(new StringConverter<RaceElevationUnit>() {
            @Override
            public String toString(RaceElevationUnit elevationUnit) {
                return LanguageSupport.getText("model.race.elevation-unit." + elevationUnit.getValue());
            }

            @Override
            public RaceElevationUnit fromString(String string) {
                return null;
            }
        });
        ObservableList<RaceElevationUnit> raceElevationUnits = FXCollections.observableArrayList(RaceElevationUnit.values());
        raceElevationUnits.remove(RaceElevationUnit.UNDERTERMINED);
        elevationUnitComboBox.setItems(raceElevationUnits);
        elevationUnitComboBox.getSelectionModel().select(race.getElevationUnit());
    }

    /**
     * Closes the preferences.
     */
    @FXML
    private void handleClose() {
        app.closePreferencesStage();
    }

    /**
     * Saves the preferences.
     */
    @FXML
    private void handleSave() {
        race.setDistanceUnit(distanceUnitComboBox.getSelectionModel().getSelectedItem());
        race.setElevationUnit(elevationUnitComboBox.getSelectionModel().getSelectedItem());

        try {
            raceService.update(race);
        } catch (DataHandlerException | DataProviderException ex) {
            log.error(ex.getMessage(), ex);
        }

        handleClose();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        app.closePreferencesStage();
    }
}
