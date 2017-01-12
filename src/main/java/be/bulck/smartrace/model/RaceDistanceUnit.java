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
 * A model enum representing the measure unit used for the distance.
 *
 * @author Fabien Vanden Bulck
 */
public enum RaceDistanceUnit {
    UNDETERMINED(-1),
    KM(0),
    MI(1);

    private static final float KM_RATE = 1.0F;
    private static final float MI_RATE = 0.621371F;

    private int value;

    RaceDistanceUnit(int value) { this.value = value; }
    public int getValue() { return value; }

    public static RaceDistanceUnit parse(int value) {
        for (RaceDistanceUnit unit : RaceDistanceUnit.values()) {
            if (unit.getValue() == value)
                return unit;
        }

        return UNDETERMINED;
    }

    public static float compute(RaceDistanceUnit unit, float distance) {
        switch (unit) {
            case KM: return distance * KM_RATE;
            case MI: return distance * MI_RATE;
            default: return -1.0F;
        }
    }

    public static float ingest(RaceDistanceUnit unit, float distance) {
        switch (unit) {
            case KM: return distance / KM_RATE;
            case MI: return distance / MI_RATE;
            default: return -1.0F;
        }
    }
}
