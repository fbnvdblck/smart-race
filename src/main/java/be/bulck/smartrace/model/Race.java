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

package be.bulck.smartrace.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

/**
 * A model class representing a race.
 *
 * @author Fabien Vanden Bulck
 */
public class Race {

    /** The name of the race. */
    private StringProperty name;

    /** The location of the race. */
    private StringProperty location;

    /** The description of the race. */
    private StringProperty description;

    /** The state of the race. */
    private ObjectProperty<RaceState> state;

    /** The creation date of the race. */
    private ObjectProperty<LocalDateTime> creationDate;

    /** The last opening date of the race. */
    private ObjectProperty<LocalDateTime> lastOpeningDate;

    /** The last update date of the race. */
    private ObjectProperty<LocalDateTime> lastUpdateDate;

    /** The smart race version used of the race. */
    private StringProperty version;


    /**
     * Constructs an instance of race.
     *
     * @param name the name of the race
     * @param location the location of the race
     */
    public Race(String name, String location) {
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
    }

    /**
     * Gets the name of the race.
     *
     * @return the name of the race
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the race.
     *
     * @param name the new name of the race
     */
    public void setName(String name) {
        if (this.name != null)
            this.name.set(name);
        else
            this.name = new SimpleStringProperty(name);
    }

    /**
     * Gets the property for the name of the race.
     *
     * @return the property for the name of the race
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the location of the race.
     *
     * @return the location of the race
     */
    public String getLocation() {
        return location.get();
    }

    /**
     * Sets the location of the race.
     *
     * @param location the new location of the race
     */
    public void setLocation(String location) {
        if (this.location != null)
            this.location.set(location);
        else
            this.location = new SimpleStringProperty(location);
    }

    /**
     * Gets the property for the location of the race.
     *
     * @return the property for the location of the race
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     * Gets the description of the race.
     *
     * @return the description of the race
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the description of the race.
     *
     * @param description the new description of the race
     */
    public void setDescription(String description) {
        if (this.description != null)
            this.description.set(description);
        else
            this.description = new SimpleStringProperty(description);
    }

    /**
     * Gets the state of the race.
     *
     * @return the state of the race
     */
    public RaceState getState() {
        return state.get();
    }

    /**
     * Sets the state of the race.
     *
     * @param state the new state of the race
     */
    public void setState(RaceState state) {
        if (this.state != null)
            this.state.set(state);
        else
            this.state = new SimpleObjectProperty<>(state);
    }

    /**
     * Gets the property for the state of the race.
     *
     * @return the property for the state of the race
     */
    public ObjectProperty<RaceState> stateProperty() {
        return state;
    }

    /**
     * Gets the creation date of the race.
     *
     * @return the creation date of the race
     */
    public LocalDateTime getCreationDate() {
        return creationDate.get();
    }

    /**
     * Sets the creation date of the race.
     *
     * @param creationDate the new creation date of the race
     */
    public void setCreationDate(LocalDateTime creationDate) {
        if (this.creationDate != null)
            this.creationDate.set(creationDate);
        else
            this.creationDate = new SimpleObjectProperty<>(creationDate);
    }

    /**
     * Gets the property for the creation date of the race.
     *
     * @return the property for the creation date of the race
     */
    public ObjectProperty<LocalDateTime> creationDateProperty() {
        return creationDate;
    }

    /**
     * Gets the last opening date of the race.
     *
     * @return the last opening date of the race
     */
    public LocalDateTime getLastOpeningDate() {
        return lastOpeningDate.get();
    }

    /**
     * Sets the last opening date of the race.
     *
     * @param lastOpeningDate the new last opening date of the race
     */
    public void setLastOpeningDate(LocalDateTime lastOpeningDate) {
        if (this.lastOpeningDate != null)
            this.lastOpeningDate.set(lastOpeningDate);
        else
            this.lastOpeningDate = new SimpleObjectProperty<>(lastOpeningDate);
    }

    /**
     * Gets the property for the last opening date of the race.
     *
     * @return the property for the last opening date of the race
     */
    public ObjectProperty<LocalDateTime> lastOpeningDateProperty() {
        return lastOpeningDate;
    }

    /**
     * Gets the last update date of the race.
     *
     * @return the last update date of the race
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate.get();
    }

    /**
     * Sets the last update date of the race.
     *
     * @param lastUpdateDate the new last update date of the race
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        if (this.lastUpdateDate != null)
            this.lastUpdateDate.set(lastUpdateDate);
        else
            this.lastUpdateDate = new SimpleObjectProperty<>(lastUpdateDate);
    }

    /**
     * Gets the property for the last update date of the race.
     *
     * @return the property for the last update date of the race
     */
    public ObjectProperty<LocalDateTime> lastUpdateDateProperty() {
        return lastUpdateDate;
    }

    /**
     * Gets the smart race version used of the race.
     *
     * @return the smart race version used of the race
     */
    public String getVersion() {
        return version.get();
    }

    /**
     * Sets the smart race version used of the race.
     *
     * @param version the new smart race version used of the race
     */
    public void setVersion(String version) {
        if (this.version != null)
            this.version.set(version);
        else
            this.version = new SimpleStringProperty(version);
    }

    /**
     * Gets the property for the smart race version used of the race.
     *
     * @return the property for the smart race version used of the race
     */
    public StringProperty versionProperty() {
        return version;
    }

    @Override
    public String toString() {
        return getName() + " @ " + getLocation();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Race && getVersion().equals(((Race) other).getVersion());
    }
}
