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

package be.bulck.smartrace.service;

import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.handler.DataHandler;
import be.bulck.smartrace.dao.provider.RaceCategoryProvider;
import be.bulck.smartrace.model.RaceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * A class representing an implementation of a service to handle race categories.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceCategoryServiceImpl implements RaceCategoryService {

    /** The data handler. */
    @Autowired
    private DataHandler dataHandler;

    /** The data provider. */
    @Autowired
    private RaceCategoryProvider raceCategoryProvider;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceCategoryServiceImpl.class);


    @Override
    public RaceCategory[] find() throws DataProviderException {
        log.debug("Finding all race categories...");
        return raceCategoryProvider.find();
    }

    @Override
    public RaceCategory find(UUID uuid) throws DataProviderException {
        log.debug("Finding race category with UUID '" + uuid + "'...");
        return raceCategoryProvider.find(uuid);
    }

    @Override
    public RaceCategory findByName(String name) throws DataProviderException {
        log.debug("Finding the race category with the name '" + name + "'...");
        return raceCategoryProvider.findByName(name);
    }

    @Override
    public void create(RaceCategory raceCategory) throws DataHandlerException, DataProviderException {
        log.debug("Creating the race category '" + raceCategory.getName() + "' (" + raceCategory.getUuid() + ")...");
        raceCategoryProvider.create(raceCategory);
        dataHandler.save();
        log.info("Race category '" + raceCategory + "' (" + raceCategory.getUuid() + ") created");
    }

    @Override
    public RaceCategory create(String name) throws DataHandlerException, DataProviderException {
        log.debug("Creating the race category '" + name + "'...");
        RaceCategory raceCategory = new RaceCategory(name);

        raceCategoryProvider.create(raceCategory);
        dataHandler.save();

        log.info("Race category '" + raceCategory + "' (" + raceCategory.getUuid() + ") created");
        return raceCategory;
    }

    @Override
    public void update(RaceCategory raceCategory) throws DataHandlerException, DataProviderException {
        log.debug("Updating the race category '" + raceCategory + "' (" + raceCategory.getUuid() + ")...");
        raceCategoryProvider.update(raceCategory);
        dataHandler.save();
        log.info("Race category '" + raceCategory + "' (" + raceCategory.getUuid() + ") updated");
    }

    @Override
    public void delete(RaceCategory raceCategory) throws DataHandlerException, DataProviderException {
        log.debug("Deleting the race category '" + raceCategory + "' (" + raceCategory.getUuid() + ")...");
        raceCategoryProvider.delete(raceCategory);
        dataHandler.save();
        log.info("Race category '" + raceCategory + "' (" + raceCategory.getUuid() + ") deleted");
    }
}
