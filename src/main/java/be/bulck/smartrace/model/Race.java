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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A model class representing a race.
 *
 * @author Fabien Vanden Bulck
 */
public class Race implements Comparable<Race> {

    /** The identifier (UUID) of the race. */
    private ObjectProperty<UUID> uuid;

    /** The name of the race. */
    private StringProperty name;

    /** The location of the race. */
    private StringProperty location;

    /** The description of the race. */
    private StringProperty description;

    /** The state of the race. */
    private ObjectProperty<RaceState> state;

    /** The measure unit for distance. */
    private ObjectProperty<RaceDistanceUnit> distanceUnit;

    /** The measure unit for elevation. */
    private ObjectProperty<RaceElevationUnit> elevationUnit;

    /** The creation date of the race. */
    private ObjectProperty<LocalDateTime> creationDate;

    /** The last opening date of the race. */
    private ObjectProperty<LocalDateTime> lastOpeningDate;

    /** The last update date of the race. */
    private ObjectProperty<LocalDateTime> lastUpdateDate;

    /** The smart race version used for the race. */
    private StringProperty version;


    /**
     * Constructs an instance of race.
     */
    public Race() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        name = new SimpleStringProperty();
        location = new SimpleStringProperty();
        description = new SimpleStringProperty();
        state = new SimpleObjectProperty<>();
        distanceUnit = new SimpleObjectProperty<>();
        elevationUnit = new SimpleObjectProperty<>();
        creationDate = new SimpleObjectProperty<>();
        lastOpeningDate = new SimpleObjectProperty<>();
        lastUpdateDate = new SimpleObjectProperty<>();
        version = new SimpleStringProperty();
    }

    /**
     * Constructs an instance of race.
     *
     * @param uuid the identifier (UUID) of the race
     */
    public Race(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of race.
     *
     * @param name the name of the race
     * @param location the location of the race
     */
    public Race(String name, String location) {
        this();
        this.name.set(name);
        this.location.set(location);
    }

    /**
     * Gets the identifier (UUID) of the race.
     *
     * @return the identifier (UUID) of the race
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the race.
     *
     * @param uuid the new identifier (UUID) of the race
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (UUID) of the race.
     *
     * @return the property for the identifier (UUID) of the race
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
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
        this.name.set(name);
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
        this.location.set(location);
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
        this.description.set(description);
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
        this.state.set(state);
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
     * Gets the measure unit for distance of the race.
     *
     * @return the measure unit for distance of the race
     */
    public RaceDistanceUnit getDistanceUnit() {
        return distanceUnit.get();
    }

    /**
     * Sets the measure unit for distance of the race.
     *
     * @param distanceUnit the new measure unit for distance of the race
     */
    public void setDistanceUnit(RaceDistanceUnit distanceUnit) {
        this.distanceUnit.set(distanceUnit);
    }

    /**
     * Gets the property for the measure unit for distance of the race.
     *
     * @return the property for the measure unit for distance of the rae
     */
    public ObjectProperty<RaceDistanceUnit> distanceUnitProperty() {
        return distanceUnit;
    }

    /**
     * Gets the measure unit for elevation of the race.
     *
     * @return the measure unit for elevation of the race
     */
    public RaceElevationUnit getElevationUnit() {
        return elevationUnit.get();
    }

    /**
     * Sets the measure unit for elevation of the race.
     *
     * @param elevationUnit the new measure unit for elevation of the race
     */
    public void setElevationUnit(RaceElevationUnit elevationUnit) {
        this.elevationUnit.set(elevationUnit);
    }

    /**
     * Gets the property for the measure unit for elevation of the race.
     *
     * @return the property for the measure unit for elevation of the race
     */
    public ObjectProperty<RaceElevationUnit> elevationUnitProperty() {
        return elevationUnit;
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
        this.creationDate.set(creationDate);
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
        this.lastOpeningDate.set(lastOpeningDate);
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
        this.lastUpdateDate.set(lastUpdateDate);
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
        this.version.set(version);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (!uuid.equals(race.uuid)) return false;
        if (name != null ? !name.equals(race.name) : race.name != null) return false;
        if (location != null ? !location.equals(race.location) : race.location != null) return false;
        if (creationDate != null ? !creationDate.equals(race.creationDate) : race.creationDate != null) return false;
        return version != null ? version.equals(race.version) : race.version == null;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Race o) {
        return getName().compareTo(o.getName());
    }
}
