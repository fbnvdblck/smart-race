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

package be.bulck.smartrace.splash;

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.app.SmartRaceApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * the splash stage of the application.
 *
 * @author Fabien Vanden Bulck
 */
public class SplashStage extends Stage {

    /** The title of the splash stage. */
    private static final String SPLASH_TITLE = SmartRace.NAME + " " + SmartRace.VERSION;

    /** The icon of the splash stage. */
    private static final String SPLASH_ICON = SmartRace.ICON;

    /** The width of the splash stage. */
    private static final int SPLASH_WIDTH = 400;

    /** The height of the splash stage. */
    private static final int SPLASH_HEIGHT = 200;

    /** The image of the splash stage. */
    private static final String SPLASH_IMAGE = "/images/splash/smart-race.png";

    /** The timeout (ms) of the splash stage. */
    private static final int SPLASH_TIMEOUT = 5000;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SplashStage.class);

    /** The smart race JavaFX application. */
    private SmartRaceApplication app;

    /** The timer task. */
    private TimerTask timerTask;

    /** The root layout. */
    private AnchorPane rootLayout;


    /**
     * Constructs an instance of splash stage.
     *
     * @param app the smart race JavaFX application
     */
    public SplashStage(SmartRaceApplication app) {
        super();
        this.app = app;

        setTitle(SPLASH_TITLE);
        getIcons().add(new Image(SPLASH_ICON));
        setWidth(SPLASH_WIDTH);
        setHeight(SPLASH_HEIGHT);
        initStyle(StageStyle.TRANSPARENT);
        setAlwaysOnTop(true);
        setResizable(false);

        initLayout();
    }

    /**
     * Initializes the layout.
     */
    private void initLayout() {
        ImageView splash = new ImageView(new Image(SPLASH_IMAGE));
        splash.setOnMouseClicked((event) -> {
            if (timerTask != null)
                timerTask.cancel();

            performOnExit();
        });

        rootLayout = new AnchorPane();
        rootLayout.getChildren().add(splash);
        setScene(new Scene(rootLayout));
    }

    /**
     * Starts the splash stage. The splash stage is hidden automatically after x seconds.
     *
     * @see #SPLASH_TIMEOUT
     */
    public void start() {
        show();
        log.info("Splash stage shown");

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> performOnExit());
            }
        };

        new Timer().schedule(timerTask, SPLASH_TIMEOUT);
    }

    /**
     * Performs on exit.
     */
    private void performOnExit() {
        close();
        log.info("Splash stage closed");

        app.openWelcomeStage();
    }
}
