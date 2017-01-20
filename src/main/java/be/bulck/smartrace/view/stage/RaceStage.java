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
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.view.controller.RaceStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The race stage to handle the race.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceStage extends Stage {

    /** The title of the race stage. */
    private static final String STAGE_TITLE = SmartRace.NAME + " " + SmartRace.VERSION;

    /** The icon of the race stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The minimum width of the race stage. */
    private static final int STAGE_WIDTH = 800;

    /** The minimum height of the race stage. */
    private static final int STAGE_HEIGHT = 400;

    /** The FXML file of the race stage. */
    private static final String STAGE_FXML = "/fxml/raceStage.fxml";

    /** The race to handle. */
    private final Race race;

    /** The root layout. */
    private BorderPane rootLayout;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceStage.class);

    /**
     * Constructs an instance of race stage.
     *
     * @param app the smart race JavaFX application
     * @param race the race to handle
     */
    public RaceStage(SmartRaceApplication app, Race race) {
        super();
        this.app = app;
        this.race = race;

        setTitle(race.getName() + " @ " + race.getLocation() + " - " + STAGE_TITLE);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setMinWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setMinHeight(STAGE_HEIGHT);

        initLayout();
    }

    /**
     * Gets the race.
     *
     * @return the race
     */
    public Race getRace() {
        return race;
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

            RaceStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
