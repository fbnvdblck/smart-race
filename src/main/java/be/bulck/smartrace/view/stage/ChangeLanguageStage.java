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
import be.bulck.smartrace.view.controller.ChangeLanguageStageController;
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
 * The change language stage to change the language of the application.
 *
 * @author Fabien Vanden Bulck
 */
public class ChangeLanguageStage extends Stage {

    /** The title of the change language stage. */
    private static final String STAGE_TITLE = "stage.change-language.title";

    /** The icon of the change language stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the change language stage. */
    private static final int STAGE_WIDTH = 250;

    /** The height of the change language stage. */
    private static final int STAGE_HEIGHT = 95;

    /** The root layout. */
    private VBox rootLayout;

    /** The parent stage. */
    private final WelcomeStage parentStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(ChangeLanguageStage.class);


    /**
     * Constructs an instance of change language stage.
     *
     * @param app the smart race JavaFX application
     * @param parentStage the parent stage
     */
    public ChangeLanguageStage(SmartRaceApplication app, WelcomeStage parentStage) {
        super();
        this.app = app;
        this.parentStage = parentStage;

        setTitle(LanguageSupport.getText(STAGE_TITLE) + " - " + SmartRace.NAME);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);

        initLayout();
    }

    /**
     * Initializes the layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource("/fxml/changeLanguageStage.fxml"));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            ChangeLanguageStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Gets the parent stage.
     *
     * @return the parent stage
     */
    public WelcomeStage getParentStage() {
        return parentStage;
    }
}
