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
import be.bulck.smartrace.view.stage.RaceStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The controller for the race stage.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceStageController extends StageController<RaceStage> {

    /** The menu item to open a race. */
    @FXML
    private MenuItem openMenuItem;

    /** The menu item to update the race. */
    @FXML
    private MenuItem saveMenuItem;

    /** The menu item to close the race. */
    @FXML
    private MenuItem closeMenuItem;

    /** The menu item to quit the application. */
    @FXML
    private MenuItem quitMenuItem;

    /** The menu item for tracks. */
    @FXML
    private MenuItem tracksMenuItem;

    /** The menu item for categories. */
    @FXML
    private MenuItem categoriesMenuItem;

    /** The menu item for racers. */
    @FXML
    private MenuItem racersMenuItem;

    /** The menu item for the preferences. */
    @FXML
    private MenuItem preferencesMenuItem;

    /** The menu item for information about the license of the application/ */
    @FXML
    private MenuItem licenseMenuItem;

    /** The menu item for information about the application. */
    @FXML
    private MenuItem aboutMenuItem;

    /** The button for tracks. */
    @FXML
    private Button tracksButton;

    /** The button for categories. */
    @FXML
    private Button categoriesButton;

    /** The button for racers. */
    @FXML
    private Button racersButton;

    /** The button for preferences. */
    @FXML
    private Button preferencesButton;

    /** The label for the info name. */
    @FXML
    private Label infoNameLabel;

    /** The label for the info location. */
    @FXML
    private Label infoLocationLabel;

    /** The label for the info description. */
    @FXML
    private Label infoDescriptionLabel;

    /** The label for the info state. */
    @FXML
    private Label infoStateLabel;

    /** The titled pane for the timer(s). */
    @FXML
    private TitledPane timerTitledPane;

    /** The race service. */
    private final RaceService raceService;

    /** The race to handle. */
    private Race race;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceStageController.class);

    /**
     * Constructs an instance of the race stage controller.
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    public RaceStageController() throws DataProviderException {
        raceService = RaceServiceFactory.getInstance().getRaceService();
        race = raceService.getRace();
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        initMenuBar();
        initToolBar();
        initInformationPane();
    }

    /**
     * Initializes the menu bar.
     */
    private void initMenuBar() {
        openMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.FOLDER));
        saveMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.SAVE));
        closeMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        quitMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.POWER_OFF));
        tracksMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.ROAD));
        categoriesMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.FLAG));
        racersMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.USERS));
        preferencesMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.COGS));
        licenseMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.GAVEL));
        aboutMenuItem.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE));
    }

    /**
     * Initializes the tool bar.
     */
    private void initToolBar() {
        FontAwesomeIconView tracksIconView = new FontAwesomeIconView(FontAwesomeIcon.ROAD);
        tracksIconView.setSize("2em");
        FontAwesomeIconView categoriesIconView = new FontAwesomeIconView(FontAwesomeIcon.FLAG);
        categoriesIconView.setSize("2em");
        FontAwesomeIconView racersIconView = new FontAwesomeIconView(FontAwesomeIcon.USERS);
        racersIconView.setSize("2em");
        FontAwesomeIconView preferencesIconView = new FontAwesomeIconView(FontAwesomeIcon.COGS);
        preferencesIconView.setSize("2em");

        tracksButton.setCursor(Cursor.HAND);
        tracksButton.setStyle("-fx-background-color: transparent");
        tracksButton.setGraphic(tracksIconView);
        categoriesButton.setCursor(Cursor.HAND);
        categoriesButton.setStyle("-fx-background-color: transparent");
        categoriesButton.setGraphic(categoriesIconView);
        racersButton.setCursor(Cursor.HAND);
        racersButton.setStyle("-fx-background-color: transparent");
        racersButton.setGraphic(racersIconView);
        preferencesButton.setCursor(Cursor.HAND);
        preferencesButton.setStyle("-fx-background-color: transparent");
        preferencesButton.setGraphic(preferencesIconView);
    }

    /**
     * Initializes the information pane.
     */
    private void initInformationPane() {
        infoNameLabel.setText(race.getName());
        infoLocationLabel.setText(race.getLocation());
        infoStateLabel.setText(LanguageSupport.getText("model.race.state." + race.getState().value()));

        if (!race.getDescription().isEmpty()) {
            infoDescriptionLabel.setText(race.getDescription());
        } else {
            infoDescriptionLabel.setText(LanguageSupport.getText("stage.race.view.info.value.none"));
        }

        // Disable the timer titled pane as default
        timerTitledPane.setExpanded(false);
        timerTitledPane.setDisable(true);
    }

    /**
     * Quits the race process.
     */
    @FXML
    private void handleQuit() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LanguageSupport.getText("stage.race.dialog.quit.title"));
        alert.setHeaderText(LanguageSupport.getText("stage.race.dialog.quit.header"));
        alert.setContentText(LanguageSupport.getText("stage.race.dialog.quit.text"));

        ButtonType buttonCancel = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonClose = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.close"), ButtonBar.ButtonData.APPLY);
        ButtonType buttonExit = new ButtonType(LanguageSupport.getText("stage.race.dialog.quit.button.exit"), ButtonBar.ButtonData.APPLY);
        alert.getButtonTypes().setAll(buttonCancel, buttonClose, buttonExit);

        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.isPresent()) {
            if (choice.get() == buttonExit) {
                app.closeRaceStage();

                try {
                    raceService.update(race);
                } catch (DataHandlerException | DataProviderException ex) {
                    log.error(ex.getMessage(), ex);
                }

                app.close();
            } else if (choice.get() == buttonClose) {
                app.closeRaceStage();

                try {
                    raceService.update(race);
                } catch (DataHandlerException | DataProviderException ex) {
                    log.error(ex.getMessage(), ex);
                }

                app.openWelcomeStage();
            }
        }
    }

    /**
     * Opens the preferences.
     */
    @FXML
    private void handleOpenPreferences() {
        app.openPreferencesStage();
    }

    /**
     * Opens the about stage.
     */
    @FXML
    private void handleOpenAbout() {
        app.openAboutStage();
    }

    /**
     * Opens the license stage.
     */
    @FXML
    private void handleOpenLicense() {
        app.openLicenseStage();
    }

    /**
     * Opens the track manager.
     */
    @FXML
    private void handleOpenTrackManager() {
        app.openTrackManagerStage();
    }

    /**
     * Opens the category manager.
     */
    @FXML
    private void handleOpenCategoryManager() {
        app.openCategoryManagerStage();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        handleQuit();
    }
}
