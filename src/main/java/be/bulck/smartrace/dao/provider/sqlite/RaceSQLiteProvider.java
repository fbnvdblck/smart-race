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

package be.bulck.smartrace.dao.provider.sqlite;

import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.provider.RaceProvider;
import be.bulck.smartrace.io.sqlite.SQLiteDatabase;
import be.bulck.smartrace.io.sqlite.SQLiteDatabaseFactory;
import be.bulck.smartrace.model.Race;
import be.bulck.smartrace.model.RaceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;

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
        Statement findStatement;

        try {
            findStatement = database.createStatement();
            ResultSet races = findStatement.executeQuery(findQuery);

            if (races.next()) {
                Race race = new Race(races.getString("name"), races.getString("location"));
                race.setDescription(races.getString("description"));
                race.setState(RaceState.parse(races.getInt("state")));
                race.setCreationDate(new Timestamp(races.getLong("creation_date")).toLocalDateTime());
                race.setLastOpeningDate(new Timestamp(races.getLong("last_opening_date")).toLocalDateTime());
                race.setLastUpdateDate(new Timestamp(races.getLong("last_update_date")).toLocalDateTime());
                race.setVersion(races.getString("version"));

                return race;
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage());
        }

        return null;
    }

    @Override
    public void create(Race race) throws DataProviderException {
        final String insertQuery = "INSERT INTO race (name, location, description, state, creation_date, last_opening_date, last_update_date, version) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement insertStatement;

        if (race != null) {
            try {
                LocalDateTime now = LocalDateTime.now();
                insertStatement = database.createPreparedStatement(insertQuery);
                insertStatement.setString(1, race.getName());
                insertStatement.setString(2, race.getLocation());
                insertStatement.setString(3, race.getDescription());
                insertStatement.setInt(4, race.getState().getValue());
                insertStatement.setLong(5, Timestamp.valueOf(now).getTime());
                insertStatement.setLong(6, Timestamp.valueOf(now).getTime());
                insertStatement.setLong(7, Timestamp.valueOf(now).getTime());
                insertStatement.setString(8, race.getVersion());

                insertStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage());
            }
        }

        else
            throw new DataProviderException("The race instance is null");
    }

    @Override
    public void update(Race race) throws DataProviderException {
        final String updateQuery = "UPDATE race SET name = ?, location = ?, description = ?, state = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement updateStatement;

        if (race != null) {
            try {
                updateStatement = database.createPreparedStatement(updateQuery);
                updateStatement.setString(1, race.getName());
                updateStatement.setString(2, race.getLocation());
                updateStatement.setString(3, race.getDescription());
                updateStatement.setInt(4, race.getState().getValue());

                updateStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage());
            }
        }

        else
            throw new DataProviderException("The race instance is null");
    }
}
