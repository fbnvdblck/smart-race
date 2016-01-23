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

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.handler.DataHandler;
import be.bulck.smartrace.dao.provider.RaceProvider;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.model.RaceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A class representing an implementation of a service to handle races.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceServiceImpl implements RaceService {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceServiceImpl.class);

    /** The data handler. */
    @Autowired
    private DataHandler dataHandler;

    /** The race provider. */
    @Autowired
    private RaceProvider raceProvider;

    @Override
    public void createRace(String filePath, String name, String location, String description) throws DataHandlerException, DataProviderException {
        log.info("Creating race '" + name + " @ " + location + "' ...");
        Race race = new Race(name, location);
        race.setState(RaceState.SETTING_UP);
        race.setDescription(description);
        race.setVersion(SmartRace.VERSION);

        dataHandler.create(filePath);
        raceProvider.create(race);
        dataHandler.save();

        log.info("Race '" + name + " @ " + location + "' created");
    }
}
