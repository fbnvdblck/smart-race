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

package be.bulck.smartrace.dao.provider.sqlite;

import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.provider.RaceProvider;
import be.bulck.smartrace.io.sqlite.SQLiteDatabase;
import be.bulck.smartrace.io.sqlite.SQLiteDatabaseFactory;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.model.RaceDistanceUnit;
import be.bulck.smartrace.model.RaceElevationUnit;
import be.bulck.smartrace.model.RaceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class representing the SQLite race data provider.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceSQLiteProvider implements RaceProvider {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceSQLiteProvider.class);

    @Override
    public Race find() throws DataProviderException {
        final String findQuery = "SELECT * FROM race";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        try (Statement findStatement = database.createStatement()) {
            try (ResultSet races = findStatement.executeQuery(findQuery)) {
                if (races.next()) {
                    UUID raceId = UUID.fromString(races.getString("race_uuid"));
                    String raceName = races.getString("name");
                    String raceLocation = races.getString("location");
                    String raceDescription = races.getString("description");
                    RaceState raceState = RaceState.parse(races.getInt("state"));
                    RaceDistanceUnit raceDistanceUnit = RaceDistanceUnit.parse(races.getInt("distance_unit"));
                    RaceElevationUnit raceElevationUnit = RaceElevationUnit.parse(races.getInt("elevation_unit"));
                    LocalDateTime raceCreationDate = new Timestamp(races.getLong("creation_date")).toLocalDateTime();
                    LocalDateTime raceLastOpeningDate = new Timestamp(races.getLong("last_opening_date")).toLocalDateTime();
                    LocalDateTime raceLastUpdateDate = new Timestamp(races.getLong("last_update_date")).toLocalDateTime();
                    String raceVersion = races.getString("version");

                    Race race = new Race(raceId);
                    race.setName(raceName);
                    race.setLocation(raceLocation);
                    race.setDescription(raceDescription);
                    race.setState(raceState);
                    race.setDistanceUnit(raceDistanceUnit);
                    race.setElevationUnit(raceElevationUnit);
                    race.setCreationDate(raceCreationDate);
                    race.setLastOpeningDate(raceLastOpeningDate);
                    race.setLastUpdateDate(raceLastUpdateDate);
                    race.setVersion(raceVersion);

                    return race;
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public void create(Race race) throws DataProviderException {
        final String insertQuery = "INSERT INTO race (race_uuid, name, location, description, state, distance_unit, elevation_unit, creation_date, last_opening_date, last_update_date, version) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        if (race != null) {
            try (PreparedStatement insertStatement = database.createPreparedStatement(insertQuery)) {
                LocalDateTime now = LocalDateTime.now();
                insertStatement.setString(1, race.getUuid().toString());
                insertStatement.setString(2, race.getName());
                insertStatement.setString(3, race.getLocation());
                insertStatement.setString(4, race.getDescription());
                insertStatement.setInt(5, race.getState().value());
                insertStatement.setInt(6, race.getDistanceUnit().value());
                insertStatement.setInt(7, race.getElevationUnit().value());
                insertStatement.setLong(8, Timestamp.valueOf(now).getTime());
                insertStatement.setLong(9, Timestamp.valueOf(now).getTime());
                insertStatement.setLong(10, Timestamp.valueOf(now).getTime());
                insertStatement.setString(11, race.getVersion());

                insertStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage(), ex);
            }
        }

        else
            throw new DataProviderException("Race creation: the race instance is null");
    }

    @Override
    public void update(Race race) throws DataProviderException {
        final String updateQuery = "UPDATE race SET name = ?, location = ?, description = ?, state = ?, distance_unit = ?, elevation_unit = ?, last_update_date = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        if (race != null) {
            try (PreparedStatement updateStatement = database.createPreparedStatement(updateQuery)) {
                updateStatement.setString(1, race.getName());
                updateStatement.setString(2, race.getLocation());
                updateStatement.setString(3, race.getDescription());
                updateStatement.setInt(4, race.getState().value());
                updateStatement.setInt(5, race.getDistanceUnit().value());
                updateStatement.setInt(6, race.getElevationUnit().value());
                updateStatement.setLong(7, Timestamp.valueOf(race.getLastUpdateDate()).getTime());

                updateStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage(), ex);
            }
        }

        else
            throw new DataProviderException("Race creation: the race instance is null");
    }
}
