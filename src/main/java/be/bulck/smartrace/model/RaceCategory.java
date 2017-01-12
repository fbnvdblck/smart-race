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

import javafx.beans.property.*;

import java.util.UUID;

/**
 * A model class representing a race category.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceCategory implements Comparable<RaceCategory> {

    /** The identifier (UUID) of the race category. */
    private ObjectProperty<UUID> uuid;

    /** The name of the race category. */
    private StringProperty name;

    /** The description of the race category. */
    private StringProperty description;


    /**
     * Constructs an instance of race category.
     */
    public RaceCategory() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        name = new SimpleStringProperty();
        description = new SimpleStringProperty();
    }

    /**
     * Constructs an instance of race category.
     *
     * @param uuid the identifier (UUID) of the race category
     */
    public RaceCategory(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of race category.
     *
     * @param name the name of the race category
     */
    public RaceCategory(String name) {
        this();
        this.name.set(name);
    }

    /**
     * Gets the identifier (UUID) of the race category.
     *
     * @return the identifier (UUID) of the race category
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the race category.
     *
     * @param uuid the new identifier (UUID) of the race category
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (UUID) of the race category.
     *
     * @return the property for the identifier (UUID) of the race category
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Gets the name of the race category.
     *
     * @return the name of the race category
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the race category.
     *
     * @param name the new name of the race category
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the property for the name of the race category.
     *
     * @return the property for the name of the race category
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the description of the race category.
     *
     * @return the description of the race category
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the description of the race category.
     *
     * @param description the new description of the race category
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Gets the property for the description of the race category.
     *
     * @return the property for the description of the race category
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RaceCategory && getUuid().equals(((RaceCategory) other).getUuid());
    }

    @Override
    public int compareTo(RaceCategory o) {
        return getName().compareTo(o.getName());
    }
}
