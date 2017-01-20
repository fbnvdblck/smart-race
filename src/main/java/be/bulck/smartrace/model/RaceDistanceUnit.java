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
 * The system saves distances in kilometers.
 *
 * @author Fabien Vanden Bulck
 */
public enum RaceDistanceUnit {
    UNDETERMINED(-1),
    KM(0),
    MI(1);

    private static final float KM_RATE = 1.0f;
    private static final float MI_RATE = 0.621371f;

    private int value;

    RaceDistanceUnit(int value) { this.value = value; }

    public int value() { return value; }

    public static RaceDistanceUnit parse(int value) {
        for (RaceDistanceUnit unit : RaceDistanceUnit.values()) {
            if (unit.value() == value) {
                return unit;
            }
        }

        return UNDETERMINED;
    }

    /**
     * Converts an distance to chosen measure unit.
     *
     * @param unit the chosen measure unit
     * @param distance the distance retrieved by the system (kilometers) which will be converted
     *
     * @return the distance converted depending of chosen measure unit
     */
    public static float convert(RaceDistanceUnit unit, float distance) {
        switch (unit) {
            case KM: return distance * KM_RATE;
            case MI: return distance * MI_RATE;
            default: return -1.0F;
        }
    }

    /**
     * Ingests an distance which is in specific measure unit.
     *
     * @param unit the current measure unit of the distance
     * @param distance the distance in specific measure unit which will be ingested
     *
     * @return the distance in system measure unit (kilometers), ready to be directly ingested
     */
    public static float ingest(RaceDistanceUnit unit, float distance) {
        switch (unit) {
            case KM: return distance / KM_RATE;
            case MI: return distance / MI_RATE;
            default: return -1.0F;
        }
    }
}
