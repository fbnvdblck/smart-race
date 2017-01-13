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
import be.bulck.smartrace.view.stage.ViewTrackStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The controller for the view track stage.
 *
 * @author Fabien Vanden Buclk
 */
public class ViewTrackStageController extends StageController<ViewTrackStage> {

    /** The label for the title. */
    @FXML
    private Label titleLabel;

    /** The label for the name. */
    @FXML
    private Label nameLabel;

    /** The label for the distance. */
    @FXML
    private Label distanceLabel;

    /** The label for the elevation. */
    @FXML
    private Label elevationLabel;

    /** The label for the team size limit. */
    @FXML
    private Label teamSizeLimitLabel;

    /** The label for the description. */
    @FXML
    private Label descriptionLabel;

    /** The label for the state. */
    @FXML
    private Label stateLabel;

    /** The label for the start time. */
    @FXML
    private Label startTimeLabel;

    /** The label for the end time. */
    @FXML
    private Label endTimeLabel;

    /** The race service. */
    private final RaceService raceService;

    /** The race. */
    private Race race;

    /** The race track to show. */
    private final RaceTrack raceTrack;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(ViewTrackStageController.class);


    /**
     * Constructs an instance of view track stage controller.
     *
     * @param raceTrack the race track to show
     */
    public ViewTrackStageController(RaceTrack raceTrack) {
        this.raceTrack = raceTrack;
        this.raceService = RaceServiceFactory.getInstance().getRaceService();

        try {
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
        titleLabel.setText(raceTrack.getName());
        nameLabel.setText(raceTrack.getName());
        distanceLabel.setText(RaceDistanceUnit.compute(race.getDistanceUnit(), raceTrack.getDistance()) + " " + LanguageSupport.getText("model.race.distance-unit." + race.getDistanceUnit().getValue()));
        teamSizeLimitLabel.setText(String.valueOf(raceTrack.getTeamSizeLimit()));
        stateLabel.setText(LanguageSupport.getText("model.race.state." + raceTrack.getState().getValue()));

        if (raceTrack.getElevation() != 0.0F)
            elevationLabel.setText(RaceElevationUnit.compute(race.getElevationUnit(), raceTrack.getElevation()) + " " + LanguageSupport.getText("model.race.elevation-unit." + race.getElevationUnit().getValue()));

        if (raceTrack.getDescription() != null)
            descriptionLabel.setText(raceTrack.getDescription());

        if (raceTrack.getState() == RaceTrackState.RUNNING || raceTrack.getState() == RaceTrackState.FINISHED)
            startTimeLabel.setText(raceTrack.getStartTime().toString());
        if (raceTrack.getState() == RaceTrackState.FINISHED)
            endTimeLabel.setText(raceTrack.getEndTime().toString());
    }

    /**
     * Closes the viewer.
     */
    @FXML
    private void handleClose() {
        stage.getParentStage().closeViewTrackStage();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleClose();
    }
}
