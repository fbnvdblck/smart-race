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
import be.bulck.smartrace.view.controller.PreferencesStageController;
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
 * The preferences stage to handle settings.
 *
 * @author Fabien Vanden Bulck
 */
public class PreferencesStage extends Stage {

    /** The title of the preferences stage. */
    private static final String STAGE_TITLE = "stage.preferences.title";

    /** The icon of the preferences stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the preferences stage. */
    private static final int STAGE_WIDTH = 500;

    /** The height of the preferences stage. */
    private static final int STAGE_HEIGHT = 180;

    /** The root layout. */
    private VBox rootLayout;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(PreferencesStage.class);


    /**
     * Constructs an instance of preferences stage.
     *
     * @param app the smart race JavaFX application
     */
    public PreferencesStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(LanguageSupport.getText(STAGE_TITLE) + " - " + SmartRace.NAME);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setMinWidth(STAGE_WIDTH);
        setMaxWidth(STAGE_WIDTH * 2);
        setHeight(STAGE_HEIGHT);
        setMinHeight(STAGE_HEIGHT);
        setMaxHeight(STAGE_HEIGHT);
        initModality(Modality.APPLICATION_MODAL);

        initLayout();
    }

    /**
     * Initializes the layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource("/fxml/preferencesStage.fxml"));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            PreferencesStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
