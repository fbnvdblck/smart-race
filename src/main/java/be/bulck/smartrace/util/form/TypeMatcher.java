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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class containing type matcher.
 *
 * @author Fabien Vanden Bulck
 */
public class TypeMatcher {

    // The patterns
    private static final Pattern PATTERN_NUMBER = Pattern.compile("^(([1-9][0-9]*([,.][0-9]+)?)|(0([,.][0-9]+)?))$");

    /**
     * Checks if the string provided is an integer or a decimal number.
     *
     * @param string the string to check
     *
     * @return true if the string provided is an integer or a decimal number
     */
    public static boolean isNumber(String string) {
        Matcher matcher = PATTERN_NUMBER.matcher(string);

        return matcher.matches();
    }
}
