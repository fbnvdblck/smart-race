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
import be.bulck.smartrace.model.Race;

/**
 * An interface representing a data provider of race.
 */
public interface RaceProvider {

    /**
     * Finds the race.
     *
     * @return the race
     *
     * @throws DataProviderException
     */
    public Race find() throws DataProviderException;

    /**
     * Creates a race.
     *
     * @param race the race to create
     *
     * @throws DataProviderException
     */
    public void create(Race race) throws DataProviderException;

    /**
     * Updates a race.
     *
     * @param race the race to update
     */
    public void update(Race race) throws DataProviderException;

}
