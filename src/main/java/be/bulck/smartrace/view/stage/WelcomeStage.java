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
import be.bulck.smartrace.view.controller.WelcomeStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The welcome stage to create a race or load an existing race.
 *
 * @author Fabien Vanden Bulck
 */
public class WelcomeStage extends Stage {

    /** The title of the welcome stage. */
    private static final String STAGE_TITLE = SmartRace.NAME + " " + SmartRace.VERSION;

    /** The icon of the welcome stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the welcome stage. */
    private static final int STAGE_WIDTH = 600;

    /** The height of the welcome stage. */
    private static final int STAGE_HEIGHT = 400;

    /** The FXML file of the welcome stage. */
    private static final String STAGE_FXML = "/fxml/welcomeStage.fxml";

    /** The root layout. */
    private SplitPane rootLayout;

    /** The change language stage. */
    private ChangeLanguageStage changeLanguageStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(WelcomeStage.class);

    /**
     * Constructs an instance of welcome stage.
     *
     * @param app the smart race JavaFX application
     */
    public WelcomeStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(LanguageSupport.getText("stage.welcome.title") + " - " + STAGE_TITLE);
        getIcons().add(new Image(STAGE_ICON));
        setWidth(STAGE_WIDTH);
        setHeight(STAGE_HEIGHT);
        setResizable(false);

        initLayout();
    }

    /**
     * Initializes the layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource(STAGE_FXML));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            WelcomeStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Opens the change language stage.
     */
    public void openChangeLanguageStage() {
        if (changeLanguageStage == null) {
            changeLanguageStage = new ChangeLanguageStage(app, this);
        }

        if (!changeLanguageStage.isShowing()) {
            changeLanguageStage.show();
            log.info("Change language stage shown");
        }
    }

    /**
     * Closes the change language stage.
     */
    public void closeChangeLanguageStage() {
        if (changeLanguageStage != null && changeLanguageStage.isShowing()) {
            changeLanguageStage.close();
            changeLanguageStage = null;
            log.info("Change language stage closed");
        }
    }

    /**
     * Refreshes the stage.
     */
    public void refresh() {
        setTitle(STAGE_TITLE + " - " + LanguageSupport.getText("stage.welcome.title"));
        initLayout();
    }
}
