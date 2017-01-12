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
import be.bulck.smartrace.view.controller.AboutStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The about stage to give information about the application.
 *
 * @author Fabien Vanden Bulck
 */
public class AboutStage extends Stage {

    /** The title of the about stage. */
    private static final String STAGE_TITLE = SmartRace.NAME + " - " + SmartRace.VERSION;

    /** The icon of the about stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the about stage. */
    private static final int STAGE_WIDTH = 400;

    /** The height of the about stage. */
    private static final int STAGE_HEIGHT = 335;

    /** The root layout. */
    private VBox rootLayout;

    /** The smart race JavaFX application. */
    private SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(AboutStage.class);


    /**
     * Constructs an instance of about stage.
     *
     * @param app the smart race JavaFX application
     */
    public AboutStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(STAGE_TITLE);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        initStyle(StageStyle.UNDECORATED);
        setAlwaysOnTop(true);
        setResizable(false);

        initLayout();
    }

    /**
     * Initializes the layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource("/fxml/aboutStage.fxml"));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();
            rootLayout.setOnMouseClicked(event -> app.closeAboutStage());

            AboutStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
