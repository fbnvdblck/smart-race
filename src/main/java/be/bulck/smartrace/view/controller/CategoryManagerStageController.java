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
import be.bulck.smartrace.model.RaceCategory;
import be.bulck.smartrace.service.RaceCategoryService;
import be.bulck.smartrace.service.factory.RaceCategoryServiceFactory;
import be.bulck.smartrace.view.stage.CategoryManagerStage;
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
 * The controller for the category manager stage.
 *
 * @author Fabien Vanden Bulck
 */
public class CategoryManagerStageController extends StageController<CategoryManagerStage> {

    /** The label for the title. */
    @FXML
    private Label titleLabel;

    /** The table for the race categories. */
    @FXML
    private TableView<RaceCategory> categoryTableView;

    /** The table column for the category name. */
    @FXML
    private TableColumn<RaceCategory, String> nameTableColumn;

    /** The table column for the category description. */
    @FXML
    private TableColumn<RaceCategory, String> descriptionTableColumn;

    /** The button to add a category. */
    @FXML
    private Button addButton;

    /** The button to edit a category. */
    @FXML
    private Button editButton;

    /** The button to delete a category. */
    @FXML
    private Button deleteButton;

    /** The race categories. */
    private ObservableList<RaceCategory> raceCategories;

    /** The race category service. */
    private RaceCategoryService raceCategoryService;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(CategoryManagerStageController.class);


    /**
     * Constructs an instance of the category manager stage controller.
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    public CategoryManagerStageController() throws DataProviderException {
        raceCategoryService = RaceCategoryServiceFactory.getInstance().raceCategoryService();

        raceCategories = FXCollections.observableArrayList(raceCategory -> new Observable[] {
            raceCategory.nameProperty(),
            raceCategory.descriptionProperty()
        });
        raceCategories.setAll(raceCategoryService.find());

        trackTableViewDataChanges();
    }

    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        initIcons();
        initTable();
    }

    /**
     * Initializes the icons.
     */
    private void initIcons() {
        FontAwesomeIconView categoriesIconView = new FontAwesomeIconView(FontAwesomeIcon.FLAG);
        categoriesIconView.setSize("2em");

        titleLabel.setGraphic(categoriesIconView);
        addButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PLUS));
        editButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PENCIL));
        deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
    }

    /**
     * Initializes the table.
     */
    private void initTable() {
        categoryTableView.setPlaceholder(new Label(LanguageSupport.getText("stage.category-manager.table.value.none")));

        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionTableColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        categoryTableView.setItems(raceCategories);
    }

    /**
     * Tracks the table view data changes.
     */
    private void trackTableViewDataChanges() {
        raceCategories.addListener((ListChangeListener<? super RaceCategory>) listener -> {
            while (listener.next()) {
                try {
                    if (listener.wasAdded()) {
                        for (RaceCategory categoryToAdd : listener.getAddedSubList())
                            raceCategoryService.create(categoryToAdd);
                    }

                    else if (listener.wasUpdated()) {
                        for (RaceCategory categoryToUpdate : listener.getList())
                            raceCategoryService.update(categoryToUpdate);
                    }

                    else if (listener.wasRemoved()) {
                        for (RaceCategory categoryToRemove : listener.getRemoved())
                            raceCategoryService.delete(categoryToRemove);
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
        alert.setTitle(LanguageSupport.getText("stage.category-manager.dialog.table-selection.title"));
        alert.setHeaderText(LanguageSupport.getText("stage.category-manager.dialog.table-selection.header"));
        alert.setContentText(LanguageSupport.getText("stage.category-manager.dialog.table-selection.text"));

        alert.show();
    }

    /**
     * Creates a race category.
     */
    @FXML
    private void handleCreate() {
        stage.openSetCategoryStage(raceCategories, null);
    }

    /**
     * Edits an existing race category.
     */
    @FXML
    private void handleEdit() {
        RaceCategory existingRaceCategory = categoryTableView.getSelectionModel().getSelectedItem();

        if (existingRaceCategory != null)
            stage.openSetCategoryStage(raceCategories, existingRaceCategory);
        else
            showNoSelectionDialog();
    }

    /**
     * Deletes an existing race category.
     */
    @FXML
    private void handleDelete() {
        RaceCategory existingRaceCategory = categoryTableView.getSelectionModel().getSelectedItem();

        if (existingRaceCategory != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(LanguageSupport.getText("stage.category-manager.dialog.delete.title"));
            alert.setHeaderText(LanguageSupport.getText("stage.category-manager.dialog.delete.header"));
            alert.setContentText(LanguageSupport.getText("stage.category-manager.dialog.delete.text"));
            ButtonType cancelButton = new ButtonType(LanguageSupport.getText("stage.category-manager.dialog.delete.button.cancel"));
            ButtonType yesButton = new ButtonType(LanguageSupport.getText("stage.category-manager.dialog.delete.button.yes"));
            alert.getButtonTypes().setAll(cancelButton, yesButton);

            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == yesButton)
                raceCategories.remove(existingRaceCategory);
        }

        else
            showNoSelectionDialog();
    }

    @Override
    protected void performOnExit(WindowEvent event) {
        event.consume();
        app.closeCategoryManagerStage();
    }
}
