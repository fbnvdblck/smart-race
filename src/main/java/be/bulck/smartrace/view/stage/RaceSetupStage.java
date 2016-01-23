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

package be.bulck.smartrace.view.stage;

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.app.SmartRaceApplication;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.view.controller.RaceSetupStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The race setup stage to create a new race.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceSetupStage extends Stage {

    /** The title of the race setup stage. */
    private static final String STAGE_TITLE = SmartRace.NAME + " " + SmartRace.VERSION;

    /** The icon of the race setup stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the race setup stage. */
    private static final int STAGE_WIDTH = 600;

    /** The height of the race setup stage. */
    private static final int STAGE_HEIGHT = 300;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceSetupStage.class);

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The root layout. */
    private AnchorPane rootLayout;


    /**
     * Constructs an instance of race setup stage.
     *
     * @param app the smart race JavaFX application
     */
    public RaceSetupStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(STAGE_TITLE + " - " + LanguageSupport.getText("stage.race-setup.title"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource("/fxml/raceSetupStage.fxml"));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            RaceSetupStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(SmartRace.class.getResource("/css/forms.css").toExternalForm());
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
