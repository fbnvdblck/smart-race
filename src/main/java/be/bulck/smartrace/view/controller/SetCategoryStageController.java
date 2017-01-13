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

package be.bulck.smartrace.view.controller;

import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.RaceCategory;
import be.bulck.smartrace.util.form.ValidatorAlert;
import be.bulck.smartrace.view.stage.SetCategoryStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the set category stage.
 *
 * @author Fabien Vanden Bulck
 */
public class SetCategoryStageController extends StageController<SetCategoryStage> {

    /** The label for the title. */
    @FXML
    private Label titleLabel;

    /** The label for the name. */
    @FXML
    private Label nameLabel;

    /** The text field for the name. */
    @FXML
    private TextField nameTextField;

    /** The text area for the description. */
    @FXML
    private TextArea descriptionTextArea;

    /** The button to cancel the creation/edition of the race category. */
    @FXML
    private Button cancelButton;

    /** The button to apply changes of a race category. */
    @FXML
    private Button applyButton;

    /** The race categories. */
    private final ObservableList<RaceCategory> raceCategories;

    /** The race category to edit. */
    private RaceCategory existingRaceCategory;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SetCategoryStageController.class);


    /**
     * Constructs an instance of set category stage controller.
     *
     * @param raceCategories the race categories
     * @param existingRaceCategory the race category to edit (or null to create one)
     */
    public SetCategoryStageController(ObservableList<RaceCategory> raceCategories, RaceCategory existingRaceCategory) {
        this.raceCategories = raceCategories;
        this.existingRaceCategory = existingRaceCategory;
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        titleLabel.setText(LanguageSupport.getText("stage.set-category.title." + (existingRaceCategory != null ? "edit" : "create")));
        nameLabel.setText(nameLabel.getText() + "*");
        cancelButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        applyButton.setText(LanguageSupport.getText("stage.set-category.button." + (existingRaceCategory != null ? "edit" : "create")));
        applyButton.setGraphic(new FontAwesomeIconView(existingRaceCategory != null ? FontAwesomeIcon.PENCIL : FontAwesomeIcon.PLUS));

        if (existingRaceCategory != null) {
            nameTextField.setText(existingRaceCategory.getName());
            descriptionTextArea.setText(existingRaceCategory.getDescription());
        }
    }

    /**
     * Checks if the form is valid.
     *
     * @return true if the form is valid
     */
    private boolean formIsValid() {
        final String errorClass = "field-error";
        List<String> errors = new ArrayList<>();
        nameTextField.getStyleClass().removeAll(errorClass);

        // Name : empty
        if (nameTextField.getText() == null || nameTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.set-category.field.name.validator.empty"));
            nameTextField.getStyleClass().add(errorClass);
        }

        if (errors.isEmpty()) {
            return true;
        }

        else {
            Alert alert = new ValidatorAlert(Alert.AlertType.ERROR, LanguageSupport.getText("stage.set-category.dialog.validator.title"), LanguageSupport.getText("stage.set-category.dialog.validator.header"), errors.toArray(new String[errors.size()]));
            alert.show();

            return false;
        }
    }

    /**
     * Creates/edits the race category.
     */
    @FXML
    private void handleApply() {
        if (formIsValid()) {
            boolean newCategory = false;

            if (existingRaceCategory == null) {
                newCategory = true;
                existingRaceCategory = new RaceCategory(nameTextField.getText());
            }

            else
                existingRaceCategory.setName(nameTextField.getText());

            if (descriptionTextArea.getText() != null && !descriptionTextArea.getText().isEmpty())
                existingRaceCategory.setDescription(descriptionTextArea.getText());

            if (newCategory)
                raceCategories.add(existingRaceCategory);

            stage.getParentStage().closeSetCategoryStage();
        }
    }

    /**
     * Cancels the process.
     */
    @FXML
    private void handleCancel() {
        stage.getParentStage().closeSetCategoryStage();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleCancel();
    }
}
