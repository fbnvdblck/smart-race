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
import be.bulck.smartrace.view.controller.SetCategoryStageController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The set category stage to create and edit a race category.
 *
 * @author Fabien Vanden Bulck
 */
public class SetCategoryStage extends Stage {

    /** The creation title of the set category stage. */
    private static final String STAGE_TITLE_CREATE = "stage.set-category.title.create";

    /** The edition title of the set category stage. */
    private static final String STAGE_TITLE_EDIT = "stage.set-category.title.edit";

    /** The icon of the set category stage. */
    private static final String STAGE_ICON = SmartRace.ICON;

    /** The width of the set category stage. */
    private static final int STAGE_WIDTH = 600;

    /** The height of the set category stage. */
    private static final int STAGE_HEIGHT = 194;

    /** The FXML file of the set category stage. */
    private static final String STAGE_FXML = "/fxml/setCategoryStage.fxml";

    /** The form CSS file used by the set category state. */
    private static final String STAGE_FORM_CSS = "/css/forms.css";

    /** The root layout. */
    private VBox rootLayout;

    /** The parent stage. */
    private final CategoryManagerStage parentStage;

    /** The smart race JavaFX application. */
    private final SmartRaceApplication app;

    /** The race categories. */
    private final ObservableList<RaceCategory> raceCategories;

    /** The race category to edit. */
    private final RaceCategory existingRaceCategory;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SetCategoryStage.class);

    /**
     * Constructs an instance of set category stage.
     *
     * @param app the smart race JavaFX application
     * @param parentStage the parent of the stage
     * @param raceCategories the race categories
     * @param existingRaceCategory the race category to edit (or null to create one)
     */
    public SetCategoryStage(SmartRaceApplication app, CategoryManagerStage parentStage, ObservableList<RaceCategory> raceCategories, RaceCategory existingRaceCategory) {
        super();
        this.app = app;
        this.parentStage = parentStage;
        this.raceCategories = raceCategories;
        this.existingRaceCategory = existingRaceCategory;

        setTitle(LanguageSupport.getText(existingRaceCategory != null ? STAGE_TITLE_EDIT : STAGE_TITLE_CREATE) + " - " + SmartRace.NAME);
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
            SetCategoryStageController controller = new SetCategoryStageController(raceCategories, existingRaceCategory);
            controller.setApp(app);
            controller.setStage(this);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SmartRace.class.getResource(STAGE_FXML));
            loader.setResources(LanguageSupport.getResourceBundle());
            loader.setController(controller);
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(SmartRace.class.getResource(STAGE_FORM_CSS).toExternalForm());
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
    public CategoryManagerStage getParentStage() {
        return parentStage;
    }
}
