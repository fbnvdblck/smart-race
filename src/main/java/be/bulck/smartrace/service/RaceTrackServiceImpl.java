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
import be.bulck.smartrace.dao.handler.DataHandler;
import be.bulck.smartrace.dao.provider.RaceTrackProvider;
import be.bulck.smartrace.model.RaceTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * A class representing an implementation of a service to handle race tracks.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceTrackServiceImpl implements RaceTrackService {

    /** The data handler. */
    @Autowired
    private DataHandler dataHandler;

    /** The race track provider. */
    @Autowired
    private RaceTrackProvider raceTrackProvider;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceTrackServiceImpl.class);


    @Override
    public RaceTrack[] find() throws DataProviderException {
        log.debug("Finding all race tracks...");
        return raceTrackProvider.find();
    }

    @Override
    public RaceTrack find(UUID uuid) throws DataProviderException {
        log.debug("Finding the race track with the UUID '" + uuid + "'...");
        return raceTrackProvider.find(uuid);
    }

    @Override
    public RaceTrack findByName(String name) throws DataProviderException {
        log.debug("Finding the race track with the name '" + name + "'...");
        return raceTrackProvider.findByName(name);
    }

    @Override
    public void create(RaceTrack raceTrack) throws DataHandlerException, DataProviderException {
        log.debug("Creating the race track '" + raceTrack + "' (" + raceTrack.getUuid() + ")...");
        raceTrackProvider.create(raceTrack);
        dataHandler.save();
        log.info("Race track '" + raceTrack + "' (" + raceTrack.getUuid() + ") created");
    }

    @Override
    public RaceTrack create(String name, float distance, float elevation, String description, int teamSizeLimit) throws DataHandlerException, DataProviderException {
        log.debug("Creating the race track '" + name + "'...");
        RaceTrack raceTrack = new RaceTrack(name, distance);
        raceTrack.setElevation(elevation);
        raceTrack.setDescription(description);
        raceTrack.setTeamSizeLimit(teamSizeLimit);

        raceTrackProvider.create(raceTrack);
        dataHandler.save();

        log.info("Race track '" + name + "' (" + raceTrack.getUuid() + ") created");
        return raceTrack;
    }

    @Override
    public void update(RaceTrack raceTrack) throws DataHandlerException, DataProviderException {
        log.debug("Updating the race track '" + raceTrack + "' (" + raceTrack.getUuid() + ")...");
        raceTrackProvider.update(raceTrack);
        dataHandler.save();
        log.info("Race track '" + raceTrack + "' (" + raceTrack.getUuid() + ") updated");
    }

    @Override
    public void delete(RaceTrack raceTrack) throws DataHandlerException, DataProviderException {
        log.debug("Deleting the race track '" + raceTrack + "' (" + raceTrack.getUuid() + ")...");
        raceTrackProvider.delete(raceTrack);
        dataHandler.save();
        log.info("Race track '" + raceTrack + "' (" + raceTrack.getUuid() + ") deleted");
    }
}
