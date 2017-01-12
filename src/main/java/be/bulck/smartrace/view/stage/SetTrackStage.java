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
import be.bulck.smartrace.view.controller.SetTrackStageController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The set track stage to create or edit a race track.
 *
 * @author Fabien Vanden Bulck
 */
public class SetTrackStage extends Stage {

    /** The creation title of the set track stage. */
    private static final String STAGE_TITLE_CREATE = "stage.set-track.title.create";

    /** The edition title of the set track stage. */
    private static final String STAGE_TITLE_EDIT = "stage.set-track.title.edit";

    /** The icon of the set track stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the set track stage. */
    private static final int STAGE_WIDTH = 600;

    /** The height of the set track stage. */
    private static final int STAGE_HEIGHT = 335;

    /** The FXML file of the set track stage. */
    private static final String STAGE_FXML = "/fxml/setTrackStage.fxml";

    /** The root layout. */
    private VBox rootLayout;

    /** The parent of the stage. */
    private TrackManagerStage parentStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The race tracks. */
    private ObservableList<RaceTrack> raceTracks;

    /** The race track to edit. */
    private RaceTrack existingRaceTrack;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SetTrackStage.class);


    /**
     * Constructs an instance of set track stage.
     *
     * @param app the smart race JavaFX application
     * @param parentStage the parent of the stage
     * @param raceTracks the race tracks
     * @param existingRaceTrack the race track to edit (or null to create one)
     */
    public SetTrackStage(SmartRaceApplication app, TrackManagerStage parentStage, ObservableList<RaceTrack> raceTracks, RaceTrack existingRaceTrack) {
        super();
        this.app = app;
        this.parentStage = parentStage;
        this.raceTracks = raceTracks;
        this.existingRaceTrack = existingRaceTrack;

        setTitle(LanguageSupport.getText((existingRaceTrack != null ? STAGE_TITLE_EDIT : STAGE_TITLE_CREATE)) + " - " + SmartRace.NAME);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setMinWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setMinHeight(STAGE_HEIGHT);
        setMaxHeight(STAGE_HEIGHT);

        initLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initLayout() {
        try {
            SetTrackStageController controller = new SetTrackStageController(raceTracks, existingRaceTrack);
            controller.setApp(app);
            controller.setStage(this);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource(STAGE_FXML));
            loader.setResources(LanguageSupport.getResourceBundle());
            loader.setController(controller);
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(SmartRace.class.getResource("/css/forms.css").toExternalForm());
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Gets the parent of the stage.
     *
     * @return the parent of the stage
     */
    public TrackManagerStage getParentStage() {
        return parentStage;
    }
}
