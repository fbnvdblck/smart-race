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

package be.bulck.smartrace.model;

/**
 * A model enum representing a race record state.
 *
 * @author Fabien Vanden Bulck
 */
public enum RaceRecordState {
    UNDETERMINED(-1),
    READY(0),
    RUNNING(1),
    FINISHED(2),
    CANCELLED(3);

    private int value;

    RaceRecordState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public RaceRecordState parse(int value) {
        for (RaceRecordState state : RaceRecordState.values()) {
            if (state.getValue() == value)
                return state;
        }

        return RaceRecordState.UNDETERMINED;
    }
}