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

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.lang.LanguageSupport;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.model.RaceDistanceUnit;
import be.bulck.smartrace.model.RaceElevationUnit;
import be.bulck.smartrace.model.RaceTrack;
import be.bulck.smartrace.service.RaceService;
import be.bulck.smartrace.service.RaceTrackService;
import be.bulck.smartrace.service.factory.RaceServiceFactory;
import be.bulck.smartrace.service.factory.RaceTrackServiceFactory;
import be.bulck.smartrace.view.stage.TrackManagerStage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The controller for the track manager stage.
 *
 * @author Fabien Vanden Bulck
 */
public class TrackManagerStageController extends StageController<TrackManagerStage> {

    /** The label for the title. */
    @FXML
    private Label titleLabel;

    /** The table for the raceTracks. */
    @FXML
    private TableView<RaceTrack> trackTableView;

    /** The table column for the track name. */
    @FXML
    private TableColumn<RaceTrack, String> nameTableColumn;

    /** The table column for the track distance. */
    @FXML
    private TableColumn<RaceTrack, Number> distanceTableColumn;

    /** The table column for the track elevation. */
    @FXML
    private TableColumn<RaceTrack, Number> elevationTableColumn;

    /** The table column for the track team size limit. */
    @FXML
    private TableColumn<RaceTrack, Number> teamSizeTableColumn;

    /** The button to add a track. */
    @FXML
    private Button addButton;

    /** The button to view a track. */
    @FXML
    private Button viewButton;

    /** The button to edit a track. */
    @FXML
    private Button editButton;

    /** The button to delete a track. */
    @FXML
    private Button deleteButton;

    /** The race service. */
    private RaceService raceService;

    /** The race track service. */
    private RaceTrackService raceTrackService;

    /** The raceTracks. */
    private ObservableList<RaceTrack> raceTracks;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(TrackManagerStageController.class);


    /**
     * Constructs an instance of the track manager stage controller.
     */
    public TrackManagerStageController() throws DataProviderException {
        raceService = RaceServiceFactory.getInstance().getRaceService();
        raceTrackService = RaceTrackServiceFactory.getInstance().getRaceTrackService();

        raceTracks = FXCollections.observableArrayList(raceTrack -> new Observable[] {
                raceTrack.nameProperty(),
                raceTrack.distanceProperty(),
                raceTrack.elevationProperty(),
                raceTrack.teamSizeLimitProperty(),
                raceTrack.descriptionProperty()
        });
        raceTracks.setAll(raceTrackService.find());

        trackTableViewDataChanges();
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() throws DataProviderException {
        initIcons();
        initTable();
    }

    /**
     * Initializes the icons.
     */
    private void initIcons() {
        FontAwesomeIconView tracksIconView = new FontAwesomeIconView(FontAwesomeIcon.ROAD);
        tracksIconView.setSize("2em");

        titleLabel.setGraphic(tracksIconView);
        addButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PLUS));
        viewButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
        editButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PENCIL));
        deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
    }

    /**
     * Initializes the table.
     */
    private void initTable() throws DataProviderException {
        Race race = raceService.getRace();

        trackTableView.setPlaceholder(new Label(LanguageSupport.getText("stage.track-manager.table.value.none")));

        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        distanceTableColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
        distanceTableColumn.setCellFactory(column -> new TableCell<RaceTrack, Number>() {
            @Override
            public void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty)
                    setText(RaceDistanceUnit.compute(race.getDistanceUnit(), item.floatValue()) + " " + LanguageSupport.getText("model.race.distance-unit." + race.getDistanceUnit().getValue()));
            }
        });
        elevationTableColumn.setCellValueFactory(cellData -> cellData.getValue().elevationProperty());
        elevationTableColumn.setCellFactory(column -> new TableCell<RaceTrack, Number>() {
            @Override
            public void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty)
                    setText(RaceElevationUnit.compute(race.getElevationUnit(), item.floatValue()) + " " + LanguageSupport.getText("model.race.elevation-unit." + race.getElevationUnit().getValue()));
            }
        });
        teamSizeTableColumn.setCellValueFactory(cellData -> cellData.getValue().teamSizeLimitProperty());

        trackTableView.setItems(raceTracks);
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        app.closeTrackManagerStage();
    }

    /**
     * Tracks the table view data changes.
     */
    private void trackTableViewDataChanges() {
        raceTracks.addListener((ListChangeListener<? super RaceTrack>) listener -> {
            boolean alreadyUpdated = false;

            while (listener.next()) {
                try {
                    if (listener.wasAdded()) {
                        for (RaceTrack raceTrackToAdd : listener.getAddedSubList())
                            raceTrackService.create(raceTrackToAdd);
                    }

                    else if (listener.wasUpdated()) {
                        for (RaceTrack raceTrackToEdit : listener.getList())
                            raceTrackService.update(raceTrackToEdit);
                    }

                    else if (listener.wasRemoved()) {
                        for (RaceTrack raceTrackToRemove : listener.getRemoved())
                            raceTrackService.delete(raceTrackToRemove);
                    }
                } catch (DataHandlerException | DataProviderException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        });
    }

    /**
     * Shows the no selection dialog.
     */
    private void showNoSelectionDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(LanguageSupport.getText("stage.track-manager.dialog.table-selection.title"));
        alert.setHeaderText(LanguageSupport.getText("stage.track-manager.dialog.table-selection.header"));
        alert.setContentText(LanguageSupport.getText("stage.track-manager.dialog.table-selection.text"));

        alert.show();
    }

    /**
     * Views a race track.
     */
    @FXML
    private void handleView() {
        RaceTrack existingRaceTrack = trackTableView.getSelectionModel().getSelectedItem();

        if (existingRaceTrack != null)
            stage.openViewTrackStage(existingRaceTrack);
        else
            showNoSelectionDialog();
    }

    /**
     * Creates a race track.
     */
    @FXML
    private void handleCreate() {
        stage.openSetTrackStage(raceTracks, null);
    }

    /**
     * Edits an existing race track.
     */
    @FXML
    private void handleEdit() {
        RaceTrack existingRaceTrack = trackTableView.getSelectionModel().getSelectedItem();

        if (existingRaceTrack != null)
            stage.openSetTrackStage(raceTracks, existingRaceTrack);
        else
            showNoSelectionDialog();
    }

    /**
     * Deletes an existing race track.
     */
    @FXML
    private void handleDelete() {
        RaceTrack existingRaceTrack = trackTableView.getSelectionModel().getSelectedItem();

        if (existingRaceTrack != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(LanguageSupport.getText("stage.track-manager.dialog.delete.title"));
            alert.setHeaderText(LanguageSupport.getText("stage.track-manager.dialog.delete.header"));
            alert.setContentText(LanguageSupport.getText("stage.track-manager.dialog.delete.text"));
            alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.YES);

            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.YES)
                raceTracks.remove(existingRaceTrack);
        }

        else
            showNoSelectionDialog();
    }
}
