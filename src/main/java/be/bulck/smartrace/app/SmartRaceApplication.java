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

package be.bulck.smartrace.app;

import be.bulck.smartrace.splash.SplashStage;
import be.bulck.smartrace.view.stage.RaceSetupStage;
import be.bulck.smartrace.view.stage.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The smart race JavaFX application.
 *
 * @author Fabien Vanden Bulck
 */
public class SmartRaceApplication extends Application {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SmartRaceApplication.class);

    /** The splash stage. */
    private SplashStage splashStage;

    /** The welcome stage. */
    private WelcomeStage welcomeStage;

    /** The race setup stage. */
    private RaceSetupStage raceSetupStage;


    @Override
    public void start(Stage stage) throws Exception {
        splashStage = new SplashStage(this);
        splashStage.start();
    }

    /**
     * Opens the welcome stage.
     */
    public void openWelcomeStage() {
        if (welcomeStage == null)
            welcomeStage = new WelcomeStage(this);

        if (!welcomeStage.isShowing()) {
            welcomeStage.show();
            log.info("Welcome stage shown");
        }
    }

    /**
     * Closes the welcome stage.
     */
    public void closeWelcomeStage() {
        if (welcomeStage != null && welcomeStage.isShowing()) {
            welcomeStage.close();
            welcomeStage = null;
            log.info("Welcome stage closed");
        }
    }

    /**
     * Opens the race setup stage.
     */
    public void openRaceSetupStage() {
        if (raceSetupStage == null)
            raceSetupStage = new RaceSetupStage(this);

        if (!raceSetupStage.isShowing()) {
            raceSetupStage.show();
            log.info("Race setup stage shown");
        }
    }

    /**
     * Closes the race setup stage.
     */
    public void closeRaceSetupStage() {
        if (raceSetupStage != null && raceSetupStage.isShowing()) {
            raceSetupStage.close();
            raceSetupStage = null;
            log.info("Race setup stage closed");
        }
    }
}
