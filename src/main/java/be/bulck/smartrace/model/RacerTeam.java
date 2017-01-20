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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A model class representing a racer team.
 *
 * @author Fabien Vanden Bulck
 */
public class RacerTeam implements Comparable<RacerTeam> {

    /** The identifier (UUID) of the racer team. */
    private ObjectProperty<UUID> uuid;

    /** The name of the racer team. */
    private StringProperty name;

    /** The racers of the racer team. */
    private ObjectProperty<List<Racer>> racers;


    /**
     * Constructs an instance of racer team.
     */
    public RacerTeam() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        name = new SimpleStringProperty();
        racers = new SimpleObjectProperty<>(new ArrayList<>());
    }

    /**
     * Constructs an instance of racer team.
     *
     * @param uuid the identifier (UUID) of the racer team
     */
    public RacerTeam(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of racer team.
     *
     * @param name the name of the racer team
     */
    public RacerTeam(String name) {
        this();
        this.name.set(name);
    }

    /**
     * Gets the identifier (UUID) of the racer team.
     *
     * @return the identifier (UUID) of the racer team
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the racer team.
     *
     * @param uuid the new identifier (UUID) of the racer team
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (UUID) of the racer team.
     *
     * @return the property for the identifier (UUID) of the racer team
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Gets the name of the racer team.
     *
     * @return the name of the racer team
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets the name of the racer team.
     *
     * @param name the new name of the racer team
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets the property for the name of the team.
     *
     * @return the property for the name of the team
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Gets the racers of the racer team.
     *
     * @return the racers of the racer team
     */
    public Racer[] getRacers() {
        return racers.get().toArray(new Racer[racers.get().size()]);
    }

    /**
     * Sets the racers list of the racer team.
     *
     * @param racers the new racers list of the racer team
     */
    public void setRacers(List<Racer> racers) {
        this.racers.set(racers);
    }

    /**
     * Gets the property for the racers of the racer team.
     *
     * @return the property for the racers of the racer team
     */
    public ObjectProperty<List<Racer>> racersProperty() {
        return racers;
    }

    /**
     * Adds a racer to the racer team.
     *
     * @param racer the racer to add
     */
    public void addRacer(Racer racer) {
        if (!racers.get().contains(racer)) {
            racers.get().add(racer);
        }
    }

    /**
     * Removes a racer from the racer team.
     *
     * @param racer the racer to remove
     */
    public void removeRacer(Racer racer) {
        if (racers.get().contains(racer)) {
            racers.get().remove(racer);
        }
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RacerTeam racerTeam = (RacerTeam) o;

        if (!uuid.equals(racerTeam.uuid)) return false;
        return name != null ? name.equals(racerTeam.name) : racerTeam.name == null;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(RacerTeam other) {
        return getName().compareTo(other.getName());
    }
}
