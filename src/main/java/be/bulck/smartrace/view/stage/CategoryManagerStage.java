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
import be.bulck.smartrace.model.RaceCategory;
import be.bulck.smartrace.view.controller.CategoryManagerStageController;
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
 * The category manager stage to handle categories.
 *
 * @author Fabien Vanden Bulck
 */
public class CategoryManagerStage extends Stage {

    /** The title of the category manager stage. */
    private static final String STAGE_TITLE = "stage.category-manager.title";

    /** The icon of the category manager stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the category manager stage. */
    private static final int STAGE_WIDTH = 700;

    /** The height of the category manager stage. */
    private static final int STAGE_HEIGHT = 500;

    /** The FXML file of the category manager stage. */
    private static final String STAGE_FXML = "/fxml/categoryManagerStage.fxml";

    /** The root layout. */
    private AnchorPane rootLayout;

    /** The set category stage. */
    private SetCategoryStage setCategoryStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(CategoryManagerStage.class);

    /**
     * Constructs an instance of category manager stage.
     *
     * @param app the smart race JavaFX application
     */
    public CategoryManagerStage(SmartRaceApplication app) {
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
     * Initializes the layout.
     */
    private void initLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource(STAGE_FXML));
            loader.setResources(LanguageSupport.getResourceBundle());
            rootLayout = loader.load();

            CategoryManagerStageController controller = loader.getController();
            controller.setApp(app);
            controller.setStage(this);

            Scene scene = new Scene(rootLayout);
            setScene(scene);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Opens the set category stage.
     *
     * @param raceCategories the race categories
     * @param existingRaceCategory the race category to edit (or null to create one)
     */
    public void openSetCategoryStage(ObservableList<RaceCategory> raceCategories, RaceCategory existingRaceCategory) {
        if (setCategoryStage == null) {
            setCategoryStage = new SetCategoryStage(app, this, raceCategories, existingRaceCategory);
        }

        if (!setCategoryStage.isShowing()) {
            setCategoryStage.show();
            log.info("Set category stage shown");
        }
    }

    /**
     * Closes the set category stage.
     */
    public void closeSetCategoryStage() {
        if (setCategoryStage != null && setCategoryStage.isShowing()) {
            setCategoryStage.close();
            setCategoryStage = null;
            log.info("Set category stage closed");
        }
    }
}
