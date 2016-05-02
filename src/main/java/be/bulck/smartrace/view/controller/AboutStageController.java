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

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.view.stage.AboutStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * The controller of the about stage.
 *
 * @author Fabien Vanden Bulck
 */
public class AboutStageController extends StageController<AboutStage> {

    /** The anchor pane for the image. */
    @FXML
    private AnchorPane appImageAnchorPane;

    /** The label for the version. */
    @FXML
    private Label versionLabel;

    /** The label for the author information. */
    @FXML
    private Label authorLabel;

    /** The label for the repository. */
    @FXML
    private Label repositoryLabel;

    /** The label for the license. */
    @FXML
    private Label licenseLabel;

    /** The image of the about stage. */
    private static final String ABOUT_IMAGE = "/images/splash/smart-race.png";


    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        ImageView imageView = new ImageView(new Image(ABOUT_IMAGE));
        appImageAnchorPane.getChildren().add(imageView);

        versionLabel.setText(SmartRace.NAME + " " + SmartRace.VERSION + " (" + SmartRace.CODE_NAME + ")");
        authorLabel.setText(SmartRace.AUTHOR_NAME + " <" + SmartRace.AUTHOR_EMAIL + ">");
        repositoryLabel.setText(SmartRace.REPOSITORY);
        licenseLabel.setText("GNU GPLv3 (Open Source)");
    }
}
