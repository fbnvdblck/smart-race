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

package be.bulck.smartrace.util.form;

import javafx.scene.control.Alert;

/**
 * A class representing an alert for validator.
 *
 * @author Fabien Vanden Bulck
 */
public class ValidatorAlert extends Alert {

    /**
     * Constructs an instance of alert for validator.
     *
     * @param alertType the alert type
     */
    public ValidatorAlert(AlertType alertType) {
        super(alertType);
    }

    /**
     * Constructs an instance of alert for validator.
     *
     * @param alertType the alert type
     * @param title the title
     * @param headerText the header
     * @param errors the errors
     */
    public ValidatorAlert(AlertType alertType, String title, String headerText, String[] errors) {
        super(alertType);
        setTitle(title);
        setHeaderText(headerText);

        StringBuilder stringBuilder = new StringBuilder();
        for (String error : errors)
            stringBuilder.append("- ").append(error).append(".\n");
        setContentText(stringBuilder.toString());
    }
}
