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

package be.bulck.smartrace.dao.provider;

import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.model.RaceTrack;

import java.util.UUID;

/**
 * An interface representing a data provider of race track.
 *
 * @author Fabien Vanden Bulck
 */
public interface RaceTrackProvider {

    /**
     * Finds the race tracks.
     *
     * @return the race tracks
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    RaceTrack[] find() throws DataProviderException;

    /**
     * Finds a race track by his identifier.
     *
     * @param uuid the identifier of the race track to find
     *
     * @return the race track found with the identifier provided
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    RaceTrack find(UUID uuid) throws DataProviderException;

    /**
     * Finds a race track by his name.
     *
     * @param name the name of the race track to find
     *
     * @return the race track found with the name provided
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    RaceTrack findByName(String name) throws DataProviderException;

    /**
     * Creates a race track.
     *
     * @param raceTrack the race track to create
     *
     * @throws DataProviderException an exception occurs if a data provider problem occurs
     */
    void create(RaceTrack raceTrack) throws DataProviderException;

    /**
     * Updates a race track.
     *
     * @param raceTrack the race track to update
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    void update(RaceTrack raceTrack) throws DataProviderException;

    /**
     * Deletes a race track.
     *
     * @param raceTrack the race track to delete
     *
     * @throws DataProviderException an exception thrown if a data provider problem occurs
     */
    void delete(RaceTrack raceTrack) throws DataProviderException;
}
