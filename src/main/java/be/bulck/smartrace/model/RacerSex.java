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
 * A model enum representing the sex of a racer.
 *
 * @author Fabien Vanden Bulck
 */
public enum RacerSex {
    UNDETERMINED(-1),
    MAN(0),
    WOMEN(1);

    private int value;

    RacerSex(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static RacerSex parse(int value) {
        for (RacerSex sex : RacerSex.values()) {
            if (sex.value() == value) {
                return sex;
            }
        }

        return UNDETERMINED;
    }
}
