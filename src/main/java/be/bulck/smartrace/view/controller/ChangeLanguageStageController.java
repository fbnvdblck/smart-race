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

package be.bulck.smartrace.view.controller;

import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.view.stage.ChangeLanguageStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

import java.util.Locale;

/**
 * The controller fot the change language stage.
 *
 * @author Fabien Vanden Bulck
 */
public class ChangeLanguageStageController extends StageController<ChangeLanguageStage> {

    /** The combo box for the language. */
    @FXML
    private ComboBox<Locale> languageComboBox;

    /** The button to cancel. */
    @FXML
    private Button cancelButton;

    /** The button to apply. */
    @FXML
    private Button applyButton;

    /** The available locales. */
    private ObservableList<Locale> availableLanguages;


    /**
     * Constructs an instance of change language stage controller.
     */
    public ChangeLanguageStageController() {
        availableLanguages = FXCollections.observableArrayList(Locale.getDefault(), Locale.ENGLISH, Locale.FRENCH);
    }

    /**
     * Initializes the controller
     */
    @FXML
    private void initialize() {
        cancelButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        applyButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHECK));

        languageComboBox.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale locale) {
                return locale.getDisplayName();
            }

            @Override
            public Locale fromString(String string) {
                return null;
            }
        });
        languageComboBox.setItems(availableLanguages);
        languageComboBox.getSelectionModel().select(LanguageSupport.getLocale());
    }

    /**
     * Closes the stage.
     */
    @FXML
    private void handleCancel() {
        stage.getParentStage().closeChangeLanguageStage();
    }

    /**
     * Applies the changes.
     */
    @FXML
    private void handleApply() {
        LanguageSupport.setLocale(languageComboBox.getSelectionModel().getSelectedItem());
        stage.getParentStage().closeChangeLanguageStage();
        stage.getParentStage().refresh();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleCancel();
    }
}
