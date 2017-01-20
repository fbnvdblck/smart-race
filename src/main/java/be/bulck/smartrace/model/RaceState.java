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

package be.bulck.smartrace.model;

/**
 * A model enum representing a race state.
 *
 * @author Fabien Vanden Bulck
 */
public enum RaceState {
    UNDETERMINED(-1),
    SETTING_UP(0),
    READY(1),
    RUNNING(2),
    FINISHED(3);

    private int value;

    RaceState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static RaceState parse(int value) {
        for (RaceState state : RaceState.values()) {
            if (state.value() == value) {
                return state;
            }
        }

        return UNDETERMINED;
    }
}
