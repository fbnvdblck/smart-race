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

package be.bulck.smartrace.service;

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.model.RaceTrack;

import java.util.UUID;

/**
 * An interface representing a service to handle race tracks.
 *
 * @author Fabien Vanden Bulck
 */
public interface RaceTrackService {
    /**
     * Finds the race tracks.
     *
     * @return the race tracks
     *
     * @throws DataProviderException
     */
    public RaceTrack[] find() throws DataProviderException;

    /**
     * Finds a race track by his identifier.
     *
     * @param uuid the identifier of the race track to find
     *
     * @return the race track found with the identifier provided
     *
     * @throws DataProviderException
     */
    public RaceTrack find(UUID uuid) throws DataProviderException;

    /**
     * Finds a race track by his name.
     *
     * @param name the name of the race track to find
     *
     * @return the race track found with the name provided
     *
     * @throws DataProviderException
     */
    public RaceTrack findByName(String name) throws DataProviderException;

    /**
     * Creates a race track.
     *
     * @param raceTrack the race track to create
     *
     * @throws DataHandlerException
     * @throws DataProviderException
     */
    public void create(RaceTrack raceTrack) throws DataHandlerException, DataProviderException;

    /**
     * Creates a race track.
     *
     * @param name the name of the race track
     * @param distance the distance of the race track
     * @param elevation the elevation of the race track
     * @param description the description of the race track
     * @param teamSizeLimit the team size limit of the race track
     *
     * @return the race track created
     *
     * @throws DataHandlerException
     * @throws DataProviderException
     */
    public RaceTrack create(String name, float distance, float elevation, String description, int teamSizeLimit) throws DataHandlerException, DataProviderException;

    /**
     * Updates a race track.
     *
     * @param raceTrack the race track to update
     *
     * @throws DataHandlerException
     * @throws DataProviderException
     */
    public void update(RaceTrack raceTrack) throws DataHandlerException, DataProviderException;

    /**
     * Deletes a race track.
     *
     * @param raceTrack the race track to delete
     *
     * @throws DataHandlerException
     * @throws DataProviderException
     */
    public void delete(RaceTrack raceTrack) throws DataHandlerException, DataProviderException;
}
