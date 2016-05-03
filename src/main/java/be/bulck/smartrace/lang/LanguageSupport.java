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

package be.bulck.smartrace.lang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class to handle some languages.
 *
 * @author Fabien Vanden Bulck
 */
public class LanguageSupport {

    /** The current locale. */
    private static Locale locale = Locale.getDefault();

    /** The resource bundle instance. */
    private static ResourceBundle resourceBundle;


    /**
     * Gets the current locale.
     *
     * @return the current locale
     */
    public static Locale getLocale() {
        return locale;
    }

    /**
     * Sets the current locale.
     *
     * @param locale the new current locale
     */
    public static void setLocale(Locale locale) {
        LanguageSupport.locale = locale;
        resourceBundle = null;
    }

    /**
     * Gets the resource bundle.
     *
     * @return the resource bundle
     */
    public static ResourceBundle getResourceBundle() {
        if (resourceBundle == null)
            resourceBundle = ResourceBundle.getBundle("languages.Messages", locale, new UTF8ResourceBundleControl());

        return resourceBundle;
    }

    /**
     * Gets the text translated for a key (depends on the locale).
     *
     * @param key the key used to give the text translated
     *
     * @return the text translated
     */
    public static String getText(String key) {
        return getResourceBundle().getString(key);
    }
}
