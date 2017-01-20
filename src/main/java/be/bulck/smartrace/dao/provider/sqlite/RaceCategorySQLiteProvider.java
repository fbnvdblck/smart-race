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
import be.bulck.smartrace.dao.provider.RaceCategoryProvider;
import be.bulck.smartrace.io.sqlite.SQLiteDatabase;
import be.bulck.smartrace.io.sqlite.SQLiteDatabaseFactory;
import be.bulck.smartrace.model.RaceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class representing the SQLite race category provider.
 *
 * @author Fabien Vanden Bulck
 */
public class RaceCategorySQLiteProvider implements RaceCategoryProvider {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(RaceCategorySQLiteProvider.class);

    @Override
    public RaceCategory[] find() throws DataProviderException {
        final String findQuery = "SELECT * FROM race_category";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
        List<RaceCategory> categories = new ArrayList<>();

        try (Statement findStatement = database.createStatement()) {
            try (ResultSet rows = findStatement.executeQuery(findQuery)) {
                while (rows.next()) {
                    categories.add(createObjectFromResultSet(rows));
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage(), ex);
        }

        return categories.toArray(new RaceCategory[categories.size()]);
     }

    @Override
    public RaceCategory find(UUID uuid) throws DataProviderException {
        final String findQuery = "SELECT * FROM race_category WHERE race_category_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        try (PreparedStatement findStatement = database.createPreparedStatement(findQuery)) {
            findStatement.setString(1, uuid.toString());

            try (ResultSet row = findStatement.executeQuery()) {
                if (row.next()) {
                    return createObjectFromResultSet(row);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public RaceCategory findByName(String name) throws DataProviderException {
        final String findQuery = "SELECT * FROM race_category WHERE name = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        try (PreparedStatement findStatement = database.createPreparedStatement(findQuery)) {
            findStatement.setString(1, name);

            try (ResultSet row = findStatement.executeQuery()) {
                if (row.next()) {
                    return createObjectFromResultSet(row);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataProviderException(ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public void create(RaceCategory raceCategory) throws DataProviderException {
        final String insertQuery = "INSERT INTO race_category (race_category_uuid, name, description) VALUES (?, ?, ?)";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        if (raceCategory != null) {
            try (PreparedStatement insertStatement = database.createPreparedStatement(insertQuery)) {
                insertStatement.setString(1, raceCategory.getUuid().toString());
                insertStatement.setString(2, raceCategory.getName());
                insertStatement.setString(3, raceCategory.getDescription());

                insertStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage(), ex);
            }
        }

        else {
            throw new DataProviderException("Race category creation: the race category instance is null");
        }
    }

    @Override
    public void update(RaceCategory raceCategory) throws DataProviderException {
        final String updateQuery = "UPDATE race_category SET name = ?, description = ? WHERE race_category_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        if (raceCategory != null) {
            try (PreparedStatement updateStatement = database.createPreparedStatement(updateQuery)) {
                updateStatement.setString(1, raceCategory.getName());
                updateStatement.setString(2, raceCategory.getDescription());
                updateStatement.setString(3, raceCategory.getUuid().toString());

                updateStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage(), ex);
            }
        }

        else {
            throw new DataProviderException("Race category update: The race category instance is null");
        }
    }

    @Override
    public void delete(RaceCategory raceCategory) throws DataProviderException {
        final String deleteQuery = "DELETE FROM race_category WHERE race_category_uuid = ?";
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        if (raceCategory != null) {
            try (PreparedStatement deleteStatement = database.createPreparedStatement(deleteQuery)) {
                deleteStatement.setString(1, raceCategory.getUuid().toString());

                deleteStatement.executeUpdate();
            } catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
                throw new DataProviderException(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Creates and hydrates an object from a result set.
     *
     * @param resultSet the result set
     *
     * @return the object hydrated
     *
     * @throws SQLException an exception thrown if a SQL problem occurs
     */
    private RaceCategory createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        UUID raceCategoryId = UUID.fromString(resultSet.getString("race_category_uuid"));
        String raceCategoryName = resultSet.getString("name");
        String raceCategoryDescription = resultSet.getString("description");

        RaceCategory raceCategory = new RaceCategory(raceCategoryId);
        raceCategory.setName(raceCategoryName);
        raceCategory.setDescription(raceCategoryDescription);

        return raceCategory;
    }
}
