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

package be.bulck.smartrace.dao.handler;

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.provider.RaceProvider;

/**
 * An interface representing a data handler.
 *
 * @author Fabien Vanden Bulck
 */
public interface DataHandler {

    /**
     * Gets the name of the data handler.
     *
     * @return the name of the data handler
     */
    String getName();

    /**
     * Creates the file which will contain the data.
     *
     * @param filePath the path of the file which will contain the data
     *
     * @throws DataHandlerException an exception thrown if a data handler problem occurs
     */
    void create(String filePath) throws DataHandlerException;

    /**
     * Loads a file which contains the data.
     *
     * @param filePath the path of file which contains the data
     *
     * @throws DataHandlerException an exception thrown if a data handler problem occurs
     */
    void load(String filePath) throws DataHandlerException;

    /**
     * Saves the current data in the file.
     *
     * @throws DataHandlerException an exception thrown if a data handler problem occurs
     */
    void save() throws DataHandlerException;

    /**
     * Closes the file which contains the data.
     *
     * @throws DataHandlerException an exception thrown if a data handler problem occurs
     */
    void close() throws DataHandlerException;

    /**
     * Gets the race provider.
     */
    RaceProvider getRaceProvider();
}
