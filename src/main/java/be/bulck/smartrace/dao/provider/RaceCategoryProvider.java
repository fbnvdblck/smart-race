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

package be.bulck.smartrace.dao.provider;

import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.model.RaceCategory;

import java.util.UUID;

/**
 * An interface representing a data provider of race category.
 *
 * @author Fabien Vanden Bulck
 */
public interface RaceCategoryProvider {

    /**
     * Finds the race categories.
     *
     * @return the race categories
     *
     * @throws DataProviderException
     */
    public RaceCategory[] find() throws DataProviderException;

    /**
     * Finds a race category by his identifier.
     *
     * @param uuid the identifier of the race category to find
     *
     * @return the race category found with the identifier provided
     *
     * @throws DataProviderException
     */
    public RaceCategory find(UUID uuid) throws DataProviderException;

    /**
     * Finds a race category by his name.
     *
     * @param name the name of the race category to find
     *
     * @return the race category found with the name provided
     *
     * @throws DataProviderException
     */
    public RaceCategory findByName(String name) throws DataProviderException;

    /**
     * Creates a race category.
     *
     * @param raceCategory the race category to create
     *
     * @throws DataProviderException
     */
    public void create(RaceCategory raceCategory) throws DataProviderException;

    /**
     * Updates a race category.
     *
     * @param raceCategory the race category to update
     *
     * @throws DataProviderException
     */
    public void update(RaceCategory raceCategory) throws DataProviderException;

    /**
     * Deletes a race category.
     *
     * @param raceCategory the race category to delete
     *
     * @throws DataProviderException
     */
    public void delete(RaceCategory raceCategory) throws DataProviderException;
}
