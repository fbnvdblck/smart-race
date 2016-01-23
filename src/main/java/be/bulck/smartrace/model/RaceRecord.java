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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A model class representing a race record.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceRecord implements Comparable<RaceRecord> {

    /** The identifier (UUID) of the race record. */
    private ObjectProperty<UUID> uuid;

    /** The number of the race record. */
    private IntegerProperty number;

    /** The racer team of the race record. */
    private ObjectProperty<RacerTeam> team;

    /** The race track of the race record. */
    private ObjectProperty<RaceTrack> track;

    /** The race categories of the race record. */
    private ObjectProperty<List<RaceCategory>> categories;

    /** The state of the race record. */
    private ObjectProperty<RaceRecordState> state;

    /** The end time of the race record. */
    private ObjectProperty<Instant> endTime;


    /**
     * Constructs an instance of race record.
     */
    public RaceRecord() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        number = new SimpleIntegerProperty();
        team = new SimpleObjectProperty<>();
        track = new SimpleObjectProperty<>();
        categories = new SimpleObjectProperty<>(new ArrayList<>());
        state = new SimpleObjectProperty<>();
        endTime = new SimpleObjectProperty<>();
    }

    /**
     * Constructs an instance of race record.
     *
     * @param uuid the identifier (UUID) of the race record
     */
    public RaceRecord(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of race record.
     *
     * @param number the number of the race record
     * @param team the racer team of the race record
     * @param track the race track of the race record
     */
    public RaceRecord(int number, RacerTeam team, RaceTrack track) {
        this();
        this.number.set(number);
        this.team.set(team);
        this.track.set(track);
    }

    /**
     * Gets the identifier (UUID) of the race record.
     *
     * @return the identifier (UUID) of the race record
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the race record.
     *
     * @param uuid the new identifier (UUID) of the race record
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (ID) of the race record.
     *
     * @return the property for the identifier (ID) of the race record
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Gets the number of the race record.
     *
     * @return the number of the race record
     */
    public int getNumber() {
        return number.get();
    }

    /**
     * Sets the number of the race record.
     *
     * @param number the new number of the race record
     */
    public void setNumber(int number) {
        this.number.set(number);
    }

    /**
     * Gets the property for the number of the race record.
     *
     * @return the property for the number of the race record
     */
    public IntegerProperty numberProperty() {
        return number;
    }

    /**
     * Gets the racer team of the race record.
     *
     * @return the racer team of the race record
     */
    public RacerTeam getTeam() {
        return team.get();
    }

    /**
     * Sets the racer team of the race record.
     *
     * @param team the new racer team of the race record
     */
    public void setTeam(RacerTeam team) {
        this.team.set(team);
    }

    /**
     * Gets the property for the racer team of the race record.
     *
     * @return the property for the racer team of the race record
     */
    public ObjectProperty<RacerTeam> teamProperty() {
        return team;
    }

    /**
     * Gets the race track of the race record.
     *
     * @return the race track of the race record
     */
    public RaceTrack getTrack() {
        return track.get();
    }

    /**
     * Sets the race track of the race record.
     *
     * @param track the new race track of the race record
     */
    public void setTrack(RaceTrack track) {
        this.track.set(track);
    }

    /**
     * Gets the property for the race track of the race record.
     *
     * @return the property for the race track of the race record
     */
    public ObjectProperty<RaceTrack> trackProperty() {
        return track;
    }

    /**
     * Gets the race categories of the race record.
     *
     * @return the race categories of the race record
     */
    public RaceCategory[] getCategories() {
        return categories.get().toArray(new RaceCategory[categories.get().size()]);
    }

    /**
     * Sets the race categories list of the race record.
     *
     * @param categories the new race categories list of the race record
     */
    public void setCategories(List<RaceCategory> categories) {
        this.categories.set(categories);
    }

    /**
     * Gets the property for the race categories of the race record.
     *
     * @return the property for the race categories of the race record
     */
    public ObjectProperty<List<RaceCategory>> categoriesProperty() {
        return categories;
    }

    /**
     * Adds a category to the race record.
     *
     * @param category the category to add
     */
    public void addCategory(RaceCategory category) {
        if (!categories.get().contains(category))
            categories.get().add(category);
    }

    /**
     * Removes a category from the race record.
     *
     * @param category the category to remove
     */
    public void removeCategory(RaceCategory category) {
        if (categories.get().contains(category))
            categories.get().remove(category);
    }

    /**
     * Gets the state of the race record.
     *
     * @return the state of the race record
     */
    public RaceRecordState getState() {
        return state.get();
    }

    /**
     * Sets the state of the race record.
     *
     * @param state the new state of the race record
     */
    public void setState(RaceRecordState state) {
        this.state.set(state);
    }

    /**
     * Gets the property for the state of the race record.
     *
     * @return the property for the state of the race record
     */
    public ObjectProperty<RaceRecordState> stateProperty() {
        return state;
    }

    /**
     * Gets the end time of the race record.
     *
     * @return the end time of the race record
     */
    public Instant getEndTime() {
        return endTime.get();
    }

    /**
     * Sets the end time of the race record.
     *
     * @param endTime the new end time of the race record
     */
    public void setEndTime(Instant endTime) {
        this.endTime.set(endTime);
    }

    /**
     * Gets the property for the end time of the race record.
     *
     * @return the property for the end time of the race record
     */
    public ObjectProperty<Instant> endTimeProperty() {
        return endTime;
    }

    @Override
    public String toString() {
        return getTeam().getName() + " [" + getNumber() + "]";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RaceRecord && getUuid().equals(((RaceRecord) other).getUuid());
    }

    @Override
    public int compareTo(RaceRecord o) {
        return getTeam().getName().compareTo(o.getTeam().getName());
    }
}
