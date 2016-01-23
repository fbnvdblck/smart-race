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

import javafx.beans.property.*;

import java.time.Instant;
import java.util.UUID;

/**
 * A model class representing a race track.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceTrack implements Comparable<RaceTrack> {

    /** The identifier (UUID) of the race track. */
    private ObjectProperty<UUID> uuid;

    /** The name of the race track. */
    private StringProperty name;

    /** The distance (km) of the race track. */
    private FloatProperty distance;

    /** The elevation (m) of the race track. */
    private FloatProperty elevation;

    /** The description of the race track. */
    private StringProperty description;

    /** The team size limit of the race track. */
    private IntegerProperty teamSizeLimit;

    /** The state of the race track. */
    private ObjectProperty<RaceTrackState> state;

    /** The start time of the race track. */
    private ObjectProperty<Instant> startTime;

    /** The end time of the race track. */
    private ObjectProperty<Instant> endTime;



    /**
     * Constructs an instance of race track.
     */
    public RaceTrack() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        name = new SimpleStringProperty();
        distance = new SimpleFloatProperty();
        elevation = new SimpleFloatProperty();
        description = new SimpleStringProperty();
        teamSizeLimit = new SimpleIntegerProperty(1);
        state = new SimpleObjectProperty<>();
        startTime = new SimpleObjectProperty<>();
        endTime = new SimpleObjectProperty<>();
    }

    /**
     * Constructs an instance of race track.
     *
     * @param uuid the identifier (UUID) of the race track
     */
    public RaceTrack(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of race track.
     *
     * @param name the name of the race track
     * @param distance the distance of the race track
     */
    public RaceTrack(String name, float distance) {
        this();
        this.name.set(name);
        this.distance.set(distance);
    }

    /**
     * Gets the identifier (UUID) of the race track.
     *
     * @return the identifier (UUID) of the race track
     */
    public UUID getUuid() {
        return this.uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the race track.
     *
     * @param uuid the new identifier (UUID) of the race track
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (UUID) of the race track.
     *
     * @return the property for the identifier (UUID) of the race track
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Gets the name of the race track.
     *
     * @return the name of the race track
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the race track.
     *
     * @param name the new name of the race track
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the property for the name of the race track.
     *
     * @return the property for the name of the race track
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the distance (km) of the race track.
     *
     * @return the distance of the race track
     */
    public float getDistance() {
        return distance.get();
    }

    /**
     * Sets the distance (km) of the race track.
     *
     * @param distance the new distance of the race track
     */
    public void setDistance(float distance) {
        this.distance.set(distance);
    }

    /**
     * Gets the property for the distance (km) of the race track.
     *
     * @return the property for the distance of the race track
     */
    public FloatProperty distanceProperty() {
        return distance;
    }

    /**
     * Gets the elevation (m) of the race track.
     *
     * @return the elevation (m) of the race track
     */
    public float getElevation() {
        return elevation.get();
    }

    /**
     * Sets the elevation (m) of the race track.
     *
     * @param elevation the new elevation (m) of the race track
     */
    public void setElevation(float elevation) {
        this.elevation.set(elevation);
    }

    /**
     * Gets the property for the elevation (m) of the race track.
     *
     * @return the property for the elevation (m) of the race track
     */
    public FloatProperty elevationProperty() {
        return elevation;
    }

    /**
     * Gets the description of the race track.
     *
     * @return the description of the race track
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the description of the race track.
     *
     * @param description the new description of the race track
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Gets the property for the description of the race track.
     *
     * @return the property for the description of the race track
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Gets the team size limit of the race track.
     *
     * @return the team size limit of the race track
     */
    public int getTeamSizeLimit() {
        return teamSizeLimit.get();
    }

    /**
     * Sets the team size limit of the race track.
     *
     * @param teamSizeLimit the new team size limit of the race track
     */
    public void setTeamSizeLimit(int teamSizeLimit) {
        this.teamSizeLimit.set(teamSizeLimit);
    }

    /**
     * Gets the property for the team size limit of the race track.
     *
     * @return the property for the team size limit of the race track
     */
    public IntegerProperty teamSizeLimitProperty() {
        return teamSizeLimit;
    }

    /**
     * Gets the state of the race track.
     *
     * @return the state of the race track
     */
    public RaceTrackState getState() {
        return state.get();
    }

    /**
     * Sets the state of the race track.
     *
     * @param state the new state of the race track
     */
    public void setState(RaceTrackState state) {
        this.state.set(state);
    }

    /**
     * Gets the property for the state of the race track.
     *
     * @return the property for the state of the race track
     */
    public ObjectProperty<RaceTrackState> stateProperty() {
        return state;
    }

    /**
     * Gets the start time of the race track.
     *
     * @return the start time of the race track
     */
    public Instant getStartTime() {
        return startTime.get();
    }

    /**
     * Sets the start time of the race track.
     *
     * @param startTime the new start time of the race track
     */
    public void setStartTime(Instant startTime) {
        this.startTime.set(startTime);
    }

    /**
     * Gets the property for the start time of the race track.
     *
     * @return the property for the start time of the race track
     */
    public ObjectProperty<Instant> startTimeProperty() {
        return startTime;
    }

    /**
     * Gets the end time of the race track.
     *
     * @return the end time of the race track
     */
    public Instant getEndTime() {
        return endTime.get();
    }

    /**
     * Sets the end time of the race track.
     *
     * @param endTime the new end time of the race track
     */
    public void setEndTime(Instant endTime) {
        this.endTime.set(endTime);
    }

    /**
     * Gets the property for the end time of the race track.
     *
     * @return the property for the end time of the race track
     */
    public ObjectProperty<Instant> endTimeProperty() {
        return endTime;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RaceTrack && getUuid().equals(((RaceTrack) other).getUuid());
    }

    @Override
    public int compareTo(RaceTrack other) {
        return getName().compareTo(other.getName());
    }
}
