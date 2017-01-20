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

/**
 * A class representing a command called at the launch of the application.
 *
 * @author Fabien Vanden Bulck
 */
public abstract class Command {

    /** The key of the command. */
    private String key;

    /** The name of the command. */
    private String name;

    /** The description of the command. */
    private String description;

    /**
     * Gets the key of the command.
     *
     * @return the key of the command
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the command.
     *
     * @param key the new key of the command
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the name of the command.
     *
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the command.
     *
     * @param name the new name of the command
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the command.
     *
     * @return the description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the command.
     *
     * @param description the new description of the command
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Processes the command caught.
     *
     * @param arguments the additional arguments
     */
    public abstract void process(String[] arguments);
}
