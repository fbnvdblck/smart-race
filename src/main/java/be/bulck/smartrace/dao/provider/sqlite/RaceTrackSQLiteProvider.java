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
import be.bulck.smartrace.dao.provider.RaceTrackProvider;
import be.bulck.smartrace.io.sqlite.SQLiteDatabase;
import be.bulck.smartrace.io.sqlite.SQLiteDatabaseFactory;
import be.bulck.smartrace.model.RaceTrack;
import be.bulck.smartrace.model.RaceTrackState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class representing the SQLite race track provider.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceTrackSQLiteProvider implements RaceTrackProvider {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceTrackSQLiteProvider.class);


    @Override
    public RaceTrack[] find() throws DataProviderException {
        List<RaceTrack> tracks = new ArrayList<>();

        final String findQuery = "SELECT * FROM race_track";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        Statement findStatement;

        try {
            findStatement = database.createStatement();
            ResultSet rows = findStatement.executeQuery(findQuery);

            while (rows.next())
                tracks.add(createObjectFromResultSet(rows));
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage());
        }

        return tracks.toArray(new RaceTrack[tracks.size()]);
    }

    @Override
    public RaceTrack find(UUID uuid) throws DataProviderException {
        final String findQuery = "SELECT * FROM race_track WHERE race_track_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement findStatement;

        try {
            findStatement = database.createPreparedStatement(findQuery);
            findStatement.setString(1, uuid.toString());
            ResultSet row = findStatement.executeQuery();

            if (row.next()) {
                return createObjectFromResultSet(row);
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage());
        }

        return null;
    }

    @Override
    public RaceTrack findByName(String name) throws DataProviderException {
        final String findQuery = "SELECT * FROM race_track WHERE name = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement findStatement;

        try {
            findStatement = database.createPreparedStatement(findQuery);
            findStatement.setString(1, name);
            ResultSet row = findStatement.executeQuery();

            if (row.next())
                return createObjectFromResultSet(row);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage());
        }

        return null;
    }

    @Override
    public void create(RaceTrack raceTrack) throws DataProviderException {
        final String insertQuery = "INSERT INTO race_track (race_track_uuid, name, distance, elevation, description, team_size_limit, state) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement insertStatement;

        if (raceTrack != null) {
            try {
                insertStatement = database.createPreparedStatement(insertQuery);
                insertStatement.setString(1, raceTrack.getUuid().toString());
                insertStatement.setString(2, raceTrack.getName());
                insertStatement.setFloat(3, raceTrack.getDistance());
                insertStatement.setFloat(4, raceTrack.getElevation());
                insertStatement.setString(5, raceTrack.getDescription());
                insertStatement.setInt(6, raceTrack.getTeamSizeLimit());
                insertStatement.setInt(7, raceTrack.getState().getValue());

                insertStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage());
            }
        }

        else
            throw new IllegalArgumentException("The race track instance is null");
    }

    @Override
    public void update(RaceTrack raceTrack) throws DataProviderException {
        final String updateQuery = "UPDATE race_track SET name = ?, distance = ?, elevation = ?, description = ?, team_size_limit = ?, state = ?, start_time = ?, end_time = ? WHERE race_track_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement updateStatement;

        if (raceTrack != null) {
            try {
                updateStatement = database.createPreparedStatement(updateQuery);
                updateStatement.setString(1, raceTrack.getName());
                updateStatement.setFloat(2, raceTrack.getDistance() != 0.0F ? raceTrack.getDistance() : null);
                updateStatement.setFloat(3, raceTrack.getElevation() != 0.0F ? raceTrack.getElevation() : null);
                updateStatement.setString(4, raceTrack.getDescription());
                updateStatement.setInt(5, raceTrack.getTeamSizeLimit());
                updateStatement.setInt(6, raceTrack.getState().getValue());

                if (raceTrack.getStartTime() != null)
                    updateStatement.setLong(7, Timestamp.from(raceTrack.getStartTime()).getTime());
                if (raceTrack.getEndTime() != null)
                    updateStatement.setLong(8, Timestamp.from(raceTrack.getEndTime()).getTime());

                updateStatement.setString(9, raceTrack.getUuid().toString());

                updateStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage());
            }
        }

        else
            throw new IllegalArgumentException("The race track instance is null");
    }

    @Override
    public void delete(RaceTrack raceTrack) throws DataProviderException {
        final String deleteQuery = "DELETE FROM race_track WHERE race_track_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        PreparedStatement deleteStatement;

        if (raceTrack != null) {
            try {
                deleteStatement = database.createPreparedStatement(deleteQuery);
                deleteStatement.setString(1, raceTrack.getUuid().toString());

                deleteStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage());
            }
        }

        else
            throw new IllegalArgumentException("The race track instance is null");
    }

    /**
     * Creates and hydrates an object from a result set.
     *
     * @param resultSet the result set
     *
     * @return the object hydrated
     *
     * @throws SQLException
     */
    private RaceTrack createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        RaceTrack raceTrack = new RaceTrack(UUID.fromString(resultSet.getString("race_track_uuid")));
        raceTrack.setName(resultSet.getString("name"));
        raceTrack.setDistance(resultSet.getFloat("distance"));
        raceTrack.setElevation(resultSet.getFloat("elevation"));
        raceTrack.setDescription(resultSet.getString("description"));
        raceTrack.setTeamSizeLimit(resultSet.getInt("team_size_limit"));
        raceTrack.setState(RaceTrackState.parse(resultSet.getInt("state")));
        raceTrack.setStartTime(new Timestamp(resultSet.getLong("start_time")).toInstant());
        raceTrack.setEndTime(new Timestamp(resultSet.getLong("end_time")).toInstant());

        return raceTrack;
    }
}
