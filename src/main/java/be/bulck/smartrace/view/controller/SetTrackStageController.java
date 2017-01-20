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

import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.*;
import be.bulck.smartrace.service.RaceService;
import be.bulck.smartrace.service.factory.RaceServiceFactory;
import be.bulck.smartrace.util.form.TypeMatcher;
import be.bulck.smartrace.util.form.ValidatorAlert;
import be.bulck.smartrace.view.stage.SetTrackStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the set track stage.
 *
 * @author Fabien Vanden Bulck
 */
public class SetTrackStageController extends StageController<SetTrackStage> {

    /** The label for the title. */
    @FXML
    private Label titleLabel;

    /** The label for the name of the race track. */
    @FXML
    private Label nameLabel;

    /** The label for the distance of the race track. */
    @FXML
    private Label distanceLabel;

    /** The label for the elevation of the race track. */
    @FXML
    private Label elevationLabel;

    /** The label for the team size limit of the race track. */
    @FXML
    private Label teamSizeLimitLabel;

    /** The label for the description of the race track. */
    @FXML
    private Label descriptionLabel;

    /** The text field for the name of the race track. */
    @FXML
    private TextField nameTextField;

    /** The label for the distance unit. */
    @FXML
    private Label distanceUnitLabel;

    /** The label for the elevation unit. */
    @FXML
    private Label elevationUnitLabel;

    /** The text field for the distance of the race track. */
    @FXML
    private TextField distanceTextField;

    /** The text field for the elevation of the race track. */
    @FXML
    private TextField elevationTextField;

    /** The spinner for the team size limit of the race track. */
    @FXML
    private Slider teamSizeLimitSlider;

    /** The text area for the description of the race track. */
    @FXML
    private TextArea descriptionTextArea;

    /** The button to cancel the creation/edition of a race track. */
    @FXML
    private Button cancelButton;

    /** The button to apply changes of a race track. */
    @FXML
    private Button applyButton;

    /** The race tracks. */
    private final ObservableList<RaceTrack> raceTracks;

    /** The race track to edit. */
    private RaceTrack existingRaceTrack;

    /** The race service. */
    private final RaceService raceService;

    /** The race. */
    private Race race;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SetTrackStage.class);

    /**
     * Constructs an instance of set track stage controller.
     *
     * @param raceTracks the race tracks
     * @param existingRaceTrack the race track to edit (or null to create one)
     */
    public SetTrackStageController(ObservableList<RaceTrack> raceTracks, RaceTrack existingRaceTrack) {
        this.raceTracks = raceTracks;
        this.existingRaceTrack = existingRaceTrack;

        raceService = RaceServiceFactory.getInstance().getRaceService();
        try {
            race = raceService.getRace();
        } catch (DataProviderException ex ){
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        nameLabel.setText(nameLabel.getText() + "*");
        distanceLabel.setText(distanceLabel.getText() + "*");
        teamSizeLimitLabel.setText(teamSizeLimitLabel.getText() + "*");

        cancelButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        applyButton.setGraphic(new FontAwesomeIconView(existingRaceTrack != null ? FontAwesomeIcon.PENCIL : FontAwesomeIcon.PLUS));

        titleLabel.setText(LanguageSupport.getText("stage.set-track.title." + (existingRaceTrack != null ? "edit" : "create")));
        distanceUnitLabel.setText(LanguageSupport.getText("model.race.distance-unit." + race.getDistanceUnit().value()));
        elevationUnitLabel.setText(LanguageSupport.getText("model.race.elevation-unit." + race.getElevationUnit().value()));
        applyButton.setText(LanguageSupport.getText("stage.set-track.button." + (existingRaceTrack != null ? "edit" : "create")));

        if (existingRaceTrack != null) {
            nameTextField.setText(existingRaceTrack.getName());
            distanceTextField.setText(String.valueOf(RaceDistanceUnit.convert(race.getDistanceUnit(), existingRaceTrack.getDistance())));
            elevationTextField.setText(String.valueOf(RaceElevationUnit.convert(race.getElevationUnit(), existingRaceTrack.getElevation())));
            teamSizeLimitSlider.setValue(existingRaceTrack.getTeamSizeLimit());
            descriptionTextArea.setText(existingRaceTrack.getDescription());
        } else {
            distanceTextField.setText(String.valueOf(0.0f));
            elevationTextField.setText(String.valueOf(0.0f));
        }
    }

    /**
     * Checks if the form is valid.
     *
     * @return true if the form is valid
     */
    private boolean formIsValid() {
        final String errorClass = "field-error";
        List<String> errors = new ArrayList<>();
        nameTextField.getStyleClass().removeAll(errorClass);
        distanceTextField.getStyleClass().removeAll(errorClass);
        elevationTextField.getStyleClass().removeAll(errorClass);

        // Name : empty
        if (nameTextField.getText() == null || nameTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.set-track.field.name.validator.empty"));
            nameTextField.getStyleClass().add(errorClass);
        }

        // Distance : empty & number (integer, decimal)
        if (distanceTextField.getText() == null || distanceTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.set-track.field.distance.validator.empty"));
            distanceTextField.getStyleClass().add(errorClass);
        } else if (!TypeMatcher.isNumber(distanceTextField.getText())) {
            errors.add(LanguageSupport.getText("stage.set-track.field.distance.validator.number"));
            distanceTextField.getStyleClass().add(errorClass);
        }

        // Elevation : number (integer, decimal)
        if (elevationTextField.getText() != null && !elevationTextField.getText().isEmpty() && !TypeMatcher.isNumber(elevationTextField.getText())) {
            errors.add(LanguageSupport.getText("stage.set-track.field.elevation.validator.number"));
            elevationTextField.getStyleClass().add(errorClass);
        }

        if (errors.isEmpty()) {
            return true;
        } else {
            Alert alert = new ValidatorAlert(Alert.AlertType.ERROR, LanguageSupport.getText("stage.set-track.dialog.validator.title"), LanguageSupport.getText("stage.set-track.dialog.validator.header"), errors.toArray(new String[errors.size()]));
            alert.show();

            return false;
        }
    }

    /**
     * Creates/edits the race track.
     */
    @FXML
    public void handleApply() {
        if (formIsValid()) {
            boolean newTrack = false;
            String raceTrackName = nameTextField.getText();
            float raceTrackDistance = RaceDistanceUnit.ingest(race.getDistanceUnit(), !distanceTextField.getText().isEmpty() ? Float.parseFloat(distanceTextField.getText().replace(",", ".")) : 0.0f);
            float raceTrackElevation = RaceElevationUnit.ingest(race.getElevationUnit(), !elevationTextField.getText().isEmpty() ? Float.parseFloat(elevationTextField.getText().replace(",", ".")) : 0.0f);
            int raceTrackTeamSizeLimit = (int) teamSizeLimitSlider.getValue();
            String raceTrackDescription = descriptionTextArea.getText();

            if (existingRaceTrack == null) {
                newTrack = true;
                existingRaceTrack = new RaceTrack();
            }

            existingRaceTrack.setName(raceTrackName);
            existingRaceTrack.setDistance(raceTrackDistance);
            existingRaceTrack.setElevation(raceTrackElevation);
            existingRaceTrack.setTeamSizeLimit(raceTrackTeamSizeLimit);
            existingRaceTrack.setDescription(raceTrackDescription);

            if (newTrack) {
                raceTracks.add(existingRaceTrack);
            }

            stage.getParentStage().closeSetTrackStage();
        }
    }

    /**
     * Cancels the process.
     */
    @FXML
    private void handleCancel() {
        stage.getParentStage().closeSetTrackStage();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleCancel();
    }
}
