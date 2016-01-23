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

package be.bulck.smartrace;

import be.bulck.smartrace.app.SmartRaceApplication;
import be.bulck.smartrace.boot.BootHandler;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main class and the entry point of this application. Everything starts here. It contains also several information about the project.
 *
 * @author Fabien Vanden Bulck
 */
public class SmartRace {

    /** The name of the application. */
    public static final String NAME = "Smart Race";

    /** The version of the application. */
    public static final String VERSION = "0.1";

    /** The code name of this version of the application. */
    public static final String CODE_NAME = "Siegfried";

    /** The application icon. */
    public static final String ICON = "/images/icons/smart-race.ico";

    /** The main author name of the application. */
    public static final String AUTHOR_NAME = "Fabien Vanden Bulck";

    /** The main author email of the application. */
    public static final String AUTHOR_EMAIL = "fabien@bulck.be";

    /** The repository of the application. */
    public static final String REPOSITORY = "https://github.com/fvandenbulck/smart-race";

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SmartRace.class);


    public static void main(String[] args) {
        showApplicationInformation();

        BootHandler bootHandler = new BootHandler();
        bootHandler.processCommand(args);

        Application.launch(SmartRaceApplication.class, args);
    }

    /**
     * Shows main information about the application.
     */
    private static void showApplicationInformation() {
        System.out.println(NAME + " " + VERSION + " (" + CODE_NAME + ")\n");
        showApplicationShortLicense();
        System.out.println("Contact: " + AUTHOR_EMAIL);
        System.out.println(NAME + " repository: " + REPOSITORY);
        System.out.println("\n---\n");

        log.info(NAME + " is starting...");
    }

    /**
     * Shows the short license version of the application.
     */
    private static void showApplicationShortLicense() {
        System.out.println(NAME + " Copyright (C) 2015-2016 " + AUTHOR_NAME);
        System.out.println("This program comes with ABSOLUTELY NO WARRANTY.\n" +
                           "This is free software, and you are welcome to redistribute it\n" +
                           "under certain conditions.\n" +
                           "For further information about the license, start the \n" +
                           "application with the argument 'license'.\n");
    }
}
