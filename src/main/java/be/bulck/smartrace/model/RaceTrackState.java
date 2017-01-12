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
 * A model enum representing a race track state.
 *
 * @author Fabien Vanden Bulck
 */
public enum RaceTrackState {
    UNDETERMINED(-1),
    READY(0),
    RUNNING(1),
    FINISHED(2);

    private int value;

    RaceTrackState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RaceTrackState parse(int value) {
        for (RaceTrackState state : RaceTrackState.values()) {
            if (state.getValue() == value)
                return state;
        }

        return UNDETERMINED;
    }
}
