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

import java.time.LocalDate;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * A model class representing a racer.
 *
 * @author Fabien Vanden Bulck
 */
public class Racer implements Comparable<Racer> {

    /** The identifier (UUID) of the racer. */
    private ObjectProperty<UUID> uuid;

    /** The last name of the racer. */
    private StringProperty lastName;

    /** The first name of the racer. */
    private StringProperty firstName;

    /** The sex of the racer. */
    private ObjectProperty<RacerSex> sex;

    /** The birthday date of the racer. */
    private ObjectProperty<LocalDate> birthdayDate;

    /** The comments of the racer. */
    private StringProperty comments;


    /**
     * Constructs an instance of racer.
     */
    public Racer() {
        uuid = new SimpleObjectProperty<>(UUID.randomUUID());
        lastName = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        sex = new SimpleObjectProperty<>();
        birthdayDate = new SimpleObjectProperty<>();
        comments = new SimpleStringProperty();
    }

    /**
     * Constructs an instance of racer.
     *
     * @param uuid the identifier (UUID) of the racer
     */
    public Racer(UUID uuid) {
        this();
        this.uuid.set(uuid);
    }

    /**
     * Constructs an instance of racer.
     *
     * @param lastName the last name of the racer
     * @param firstName the first name of the racer
     * @param sex the sex of the racer
     * @param birthdayDate the birthday date of the racer
     */
    public Racer(String lastName, String firstName, RacerSex sex, LocalDate birthdayDate) {
        this();
        this.lastName.set(lastName);
        this.firstName.set(firstName);
        this.sex.set(sex);
        this.birthdayDate.set(birthdayDate);
    }

    /**
     * Gets the identifier (UUID) of the racer.
     *
     * @return the identifier (UUID) of the racer
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * Sets the identifier (UUID) of the racer.
     *
     * @param uuid the new identifier (UUID) of the racer
     */
    public void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }

    /**
     * Gets the property for the identifier (UUID) of the racer.
     *
     * @return the property for the identifier (UUID) of the racer
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Gets the last name of the racer.
     *
     * @return the last name of the racer
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Sets the last name of the racer.
     *
     * @param lastName the new last name of the racer
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Gets the property for the last name of the racer.
     *
     * @return the property for the last name of the racer
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Gets the first name of the racer.
     *
     * @return the first name of the racer
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Sets the first name of the racer.
     *
     * @param firstName the new first name of the racer
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Gets the property for the first name of the racer.
     *
     * @return the property fot the first name of the racer
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Gets the full name (first name and last name concatenated) of the racer.
     *
     * @return the full name (first name and last name concatenated) of the racer
     */
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * Gets the sex of the racer.
     *
     * @return the sex of the racer.
     */
    public RacerSex getSex() {
        return sex.get();
    }

    /**
     * Sets the sex of the racer.
     *
     * @param sex the new sex of the racer
     */
    public void setSex(RacerSex sex) {
        this.sex.set(sex);
    }

    /**
     * Gets the property for the sex of the racer.
     *
     * @return the property for the sex of the racer
     */
    public ObjectProperty<RacerSex> sexProperty() {
        return sex;
    }

    /**
     * Gets the birthday date of the racer.
     *
     * @return the birthday date of the racer
     */
    public LocalDate getBirthdayDate() {
        return birthdayDate.get();
    }

    /**
     * Sets the birthday date of the racer.
     *
     * @param birthdayDate the new birthday date of the racer
     */
    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate.set(birthdayDate);
    }

    /**
     * Gets the property for the birthday date of the racer.
     *
     * @return the property for the birthday date of the racer
     */
    public ObjectProperty<LocalDate> birthdayDateProperty() {
        return birthdayDate;
    }

    /**
     * Gets the comments of the racer.
     *
     * @return the comments of the racer
     */
    public String getComments() {
        return comments.get();
    }

    /**
     * Sets the comments of the racer.
     *
     * @param comments the new comments of the racer
     */
    public void setComments(String comments) {
        this.comments.set(comments);
    }

    /**
     * Gets the property for the comments of the racer.
     *
     * @return the property for the comments of the racer
     */
    public StringProperty commentsProperty() {
        return comments;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Racer racer = (Racer) o;

        if (!uuid.equals(racer.uuid)) return false;
        if (lastName != null ? !lastName.equals(racer.lastName) : racer.lastName != null) return false;
        if (firstName != null ? !firstName.equals(racer.firstName) : racer.firstName != null) return false;
        if (sex != null ? !sex.equals(racer.sex) : racer.sex != null) return false;
        return birthdayDate != null ? birthdayDate.equals(racer.birthdayDate) : racer.birthdayDate == null;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthdayDate != null ? birthdayDate.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Racer other) {
        return getFullName().compareTo(other.getFullName());
    }
}
