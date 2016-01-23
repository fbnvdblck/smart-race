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

import be.bulck.smartrace.app.SmartRaceApplication;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The stage controller.
 *
 * @author Fabien Vanden Bulck
 */
public abstract class StageController<T> {

    /** The smart race JavaFX application. */
    protected SmartRaceApplication app;

    /** The stage concerned by the controller. */
    protected T stage;


    /**
     * Sets the smart race JavaFX application.
     *
     * @param app the new smart race JavaFX application
     */
    public void setApp(SmartRaceApplication app) {
        this.app = app;
    }

    /**
     * Sets the stage concerned by the controller.
     */
    public void setStage(T stage) {
        this.stage = stage;
        ((Stage) stage).setOnCloseRequest((event) -> performOnExit(event));
    }

    /**
     * Performs on exit request.
     *
     * @param event the window event
     */
    protected void performOnExit(WindowEvent event) {}
}
