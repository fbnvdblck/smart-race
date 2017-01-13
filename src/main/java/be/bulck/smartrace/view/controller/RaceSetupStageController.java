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

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.service.RaceService;
import be.bulck.smartrace.service.factory.RaceServiceFactory;
import be.bulck.smartrace.util.form.ValidatorAlert;
import be.bulck.smartrace.view.stage.RaceSetupStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The controller for the race setup stage.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceSetupStageController extends StageController<RaceSetupStage> {

    /** The label for the file of the race. */
    @FXML
    private Label fileLabel;

    /** The label for the name of the race. */
    @FXML
    private Label nameLabel;

    /** The label for the location of the race. */
    @FXML
    private Label locationLabel;

    /** The label for the description of the race. */
    @FXML
    private Label descriptionLabel;

    /** The text field for the file of the race. */
    @FXML
    private TextField fileTextField;

    /** The text field for the name of the race. */
    @FXML
    private TextField nameTextField;

    /** The text field for the location of the race. */
    @FXML
    private TextField locationTextField;

    /** The text area for the description of the race. */
    @FXML
    private TextArea descriptionTextArea;

    /** The button to cancel the creation process. */
    @FXML
    private Button cancelButton;

    /** The button to create the race. */
    @FXML
    private Button createButton;

    /** The race service. */
    private final RaceService raceService;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceSetupStageController.class);


    /**
     * Constructs an instance of a race setup stage controller.
     */
    public RaceSetupStageController() {
        raceService = RaceServiceFactory.getInstance().getRaceService();
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        fileLabel.setText(fileLabel.getText() + "*");
        nameLabel.setText(nameLabel.getText() + "*");
        locationLabel.setText(locationLabel.getText() + "*");

        cancelButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        createButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHECK));

        fileTextField.setFocusTraversable(false);
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        event.consume();
        handleCancel();
    }

    /**
     * Cancels the creation process.
     */
    @FXML
    private void handleCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(LanguageSupport.getText("stage.race-setup.dialog.cancel.title"));
        alert.setHeaderText(LanguageSupport.getText("stage.race-setup.dialog.cancel.header"));
        alert.setContentText(LanguageSupport.getText("stage.race-setup.dialog.cancel.text"));
        ButtonType noButton = new ButtonType(LanguageSupport.getText("stage.race-setup.dialog.cancel.button.no"));
        ButtonType yesButton = new ButtonType(LanguageSupport.getText("stage.race-setup.dialog.cancel.button.yes"));
        alert.getButtonTypes().setAll(noButton, yesButton);

        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent() && response.get() == yesButton) {
            app.closeRaceSetupStage();
            app.openWelcomeStage();
        }
    }

    /**
     * Opens the file chooser
     */
    @FXML
    private void handleOpenFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(LanguageSupport.getText("stage.race-setup.dialog.filechooser.title"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Smart race file (*.db)", "*.db"));

        if (!fileTextField.getText().isEmpty()) {
            int lastSlashIndex = fileTextField.getText().lastIndexOf("/");
            String directory = fileTextField.getText().substring(0, lastSlashIndex);
            String fileName = fileTextField.getText().substring(lastSlashIndex + 1, fileTextField.getText().length());

            fileChooser.setInitialDirectory(new File(directory));
            fileChooser.setInitialFileName(fileName);
        }

        else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setInitialFileName("MyRace");
        }

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            if (file.exists()) {
                file.delete();
            }

            fileTextField.setText(file.getAbsolutePath());
        }

        nameTextField.requestFocus();
    }

    /**
     * Creates the race.
     */
    @FXML
    private void handleCreateRace() {
        if (formIsValid()) {
            try {
                Race race = raceService.create(fileTextField.getText(), nameTextField.getText(), locationTextField.getText(), descriptionTextArea.getText());
                app.closeRaceSetupStage();
                app.openRaceStage(race);
            } catch (DataHandlerException | DataProviderException ex) {
                log.error(ex.getMessage(), ex);
            }
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
        fileTextField.getStyleClass().removeAll(errorClass);
        nameTextField.getStyleClass().removeAll(errorClass);
        locationTextField.getStyleClass().removeAll(errorClass);

        if (fileTextField.getText() == null || fileTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.race-setup.field.file.validator.empty"));
            fileTextField.getStyleClass().add(errorClass);
        }

        if (nameTextField.getText() == null || nameTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.race-setup.field.name.validator.empty"));
            nameTextField.getStyleClass().add(errorClass);
        }

        if (locationTextField.getText() == null || locationTextField.getText().isEmpty()) {
            errors.add(LanguageSupport.getText("stage.race-setup.field.location.validator.empty"));
            locationTextField.getStyleClass().add(errorClass);
        }

        if (errors.isEmpty()) {
            return true;
        }

        else {
            Alert alert = new ValidatorAlert(Alert.AlertType.ERROR, LanguageSupport.getText("stage.race-setup.dialog.validator.title"), LanguageSupport.getText("stage.race-setup.dialog.validator.header"), errors.toArray(new String[errors.size()]));

            alert.showAndWait();
            return false;
        }
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleCancel();
    }
}
