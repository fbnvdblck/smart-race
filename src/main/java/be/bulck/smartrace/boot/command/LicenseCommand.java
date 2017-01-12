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

package be.bulck.smartrace.boot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class representing a command to show information about the license of the application.
 *
 * @author Fabien Vanden Bulck
 */
public class LicenseCommand extends Command {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(LicenseCommand.class);

    @Override
    public void process(String[] arguments) {
        System.out.println(getLicense());
    }

    /**
     * Gets the license of the application.
     *
     * @return the license of the application
     */
    private String getLicense() {
        String licenseContent = "No available";

        try {
            licenseContent = new String(Files.readAllBytes(Paths.get(LicenseCommand.class.getResource("/licenses/gnu_gpl_v3.txt").getPath())));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }

        return licenseContent;
    }
}
