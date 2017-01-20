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

package be.bulck.smartrace.view.stage;

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.app.SmartRaceApplication;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.RaceTrack;
import be.bulck.smartrace.view.controller.TrackManagerStageController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The track manager stage to handle tracks.
 *
 * @author Fabien Vanden Bulck
 */
public class TrackManagerStage extends Stage {

    /** The title of the track manager stage. */
    private static final String STAGE_TITLE = "stage.track-manager.title";

    /** The icon of the track manager stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the track manager stage. */
    private static final int STAGE_WIDTH = 700;

    /** The height of the track manager stage. */
    private static final int STAGE_HEIGHT = 500;

    /** The FXML file of the track manager stage. */
    private static final String STAGE_FXML = "/fxml/trackManagerStage.fxml";

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The set track stage. */
    private SetTrackStage setTrackStage;

    /** The view track stage. */
    private ViewTrackStage viewTrackStage;

    /** The root layout. */
    private AnchorPane rootLayout;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(TrackManagerStage.class);


    /**
     * Constructs an instance of track manager stage.
     *
     * @param app the smart race JavaFX application
     */
    public TrackManagerStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(LanguageSupport.getText(STAGE_TITLE) + " - " + SmartRace.NAME);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setMinWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setMinHeight(STAGE_HEIGHT);
        initModality(Modality.APPLICATION_MODAL);

        initLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource(STAGE_FXML));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            TrackManagerStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Opens the set track stage.
     *
     * @param raceTracks the race tracks
     * @param existingRaceTrack the race track to edit (or null to create one)
     */
    public void openSetTrackStage(ObservableList<RaceTrack> raceTracks, RaceTrack existingRaceTrack) {
        if (setTrackStage == null) {
            setTrackStage = new SetTrackStage(app, this, raceTracks, existingRaceTrack);
        }

        if (!setTrackStage.isShowing()) {
            setTrackStage.show();
            log.info("Set track stage (" + (existingRaceTrack != null ? "edit" : "create") + ") shown");
        }
    }

    /**
     * Closes the set track stage.
     */
    public void closeSetTrackStage() {
        if (setTrackStage != null && setTrackStage.isShowing()) {
            setTrackStage.close();
            setTrackStage = null;
            log.info("Set track stage closed");
        }
    }

    /**
     * Opens the view track stage.
     *
     * @param raceTrack the race track to view
     */
    public void openViewTrackStage(RaceTrack raceTrack) {
        if (viewTrackStage == null) {
            viewTrackStage = new ViewTrackStage(app, this, raceTrack);
        }

        if (!viewTrackStage.isShowing()) {
            viewTrackStage.show();
            log.info("View track stage shown");
        }
    }

    /**
     * Closes the view track stage.
     */
    public void closeViewTrackStage() {
        if (viewTrackStage != null && viewTrackStage.isShowing()) {
            viewTrackStage.close();
            viewTrackStage = null;
            log.info("View track stage closed");
        }
    }
}
