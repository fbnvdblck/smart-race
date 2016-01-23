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

package be.bulck.smartrace.boot;

import be.bulck.smartrace.boot.command.Command;
import be.bulck.smartrace.util.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A class to handle the boot of the application.
 *
 * @author Fabien Vanden Bulck
 */
public class BootHandler {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(BootHandler.class);

    /** The configuration of boot. */
    private BootConfiguration configuration;


    /**
     * Constructs an instance of boot handler.
     */
    public BootHandler() {
        log.info("Loading boot configuration...");
        this.configuration = (BootConfiguration) SpringUtil.getBean("bootConfiguration");
        log.info(configuration.getCommands().length + " boot command(s) loaded");
    }

    /**
     * Gets the configuration of boot.
     *
     * @return the configuration of boot
     */
    public BootConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Catches the arguments provided at the launch of the application to process the right command in consequence.
     *
     * @param arguments the arguments provided at the launch of the application
     */
    public void processCommand(String[] arguments) {
        if (arguments != null && arguments.length > 0) {
            String commandKey = arguments[0];

            if (!commandKey.equals("help")) {
                for (Command command : configuration.getCommands()) {
                    if (command.getKey().equals(commandKey)) {
                        log.info("Boot command '" + command.getName() + "' called for key '" + commandKey + "'");
                        System.out.println("\n");
                        command.process(arguments);
                        return;
                    }
                }

                log.warn("Boot command not found for key '" + commandKey + "'");
            }

            printAvailableBootCommands();
        }
    }

    /**
     * Prints the available boot commands.
     */
    private void printAvailableBootCommands() {
        System.out.println("\n*** Available boot commands ***");

        for (Command command : configuration.getCommands())
            System.out.println("\t" + command.getKey() + " | " + command.getName() + " - " + command.getDescription());

        System.out.println("\n");
    }

}
