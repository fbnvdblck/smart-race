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
import be.bulck.smartrace.view.controller.ViewTrackStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The view track stage to view a race track.
 *
 * @author Fabien Vanden Bulck
 */
public class ViewTrackStage extends Stage {

    /** The icon of the view track stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the view track stage. */
    private static final int STAGE_WIDTH = 600;

    /** The height of the view track stage. */
    private static final int STAGE_HEIGHT = 319;

    /** The root layout. */
    private VBox rootLayout;

    /** The parent of the stage. */
    private final TrackManagerStage parentStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The race track to show. */
    private final RaceTrack raceTrack;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(ViewTrackStage.class);


    /**
     * Constructs an instance of view track stage.
     *
     * @param app the smart race JavaFX application
     * @param parentStage the parent of the stage
     * @param raceTrack the race track to show
     */
    public ViewTrackStage(SmartRaceApplication app, TrackManagerStage parentStage, RaceTrack raceTrack) {
        super();
        this.app = app;
        this.parentStage = parentStage;
        this.raceTrack = raceTrack;

        setTitle(raceTrack.getName() + " - " + SmartRace.NAME);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setMinWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setMinHeight(STAGE_HEIGHT);
        setMaxHeight(STAGE_HEIGHT);
        setAlwaysOnTop(true);

        initLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initLayout() {
        try {
            ViewTrackStageController controller = new ViewTrackStageController(raceTrack);
            controller.setApp(app);
            controller.setStage(this);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource("/fxml/viewTrackStage.fxml"));
            loader.setResources(LanguageSupport.getResourceBundle());
            loader.setController(controller);
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
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
