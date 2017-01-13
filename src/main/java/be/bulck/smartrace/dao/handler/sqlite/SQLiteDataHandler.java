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

package be.bulck.smartrace.dao.handler.sqlite;

import be.bulck.smartrace.SmartRace;
import be.bulck.smartrace.dao.exception.DataProviderException;
import be.bulck.smartrace.dao.exception.DataHandlerException;
import be.bulck.smartrace.dao.handler.DataHandler;
import be.bulck.smartrace.dao.provider.RaceProvider;
import be.bulck.smartrace.dao.provider.sqlite.RaceSQLiteProvider;
import be.bulck.smartrace.io.sqlite.SQLiteDatabase;
import be.bulck.smartrace.io.sqlite.SQLiteDatabaseFactory;
import be.bulck.smartrace.io.sqlite.SQLiteScriptLoader;
import be.bulck.smartrace.model.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * A data handler for SQLite databases.
 *
 * @author Fabien Vanden Bulck
 */
public class SQLiteDataHandler implements DataHandler {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SQLiteDataHandler.class);

    @Override
    public String getName() {
        return "SQLite";
    }

    @Override
    public void create(String filePath) throws DataHandlerException {
        log.debug("SQLite database initialization...");
        SQLiteDatabaseFactory.loadNewDatabase(filePath);
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        // Initializes the database
        try {
            SQLiteScriptLoader.executeSQLFromFile(database, SmartRace.class.getResource("/sqlite/init-database.sql").getPath());
            database.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataHandlerException("The SQLite database can't be initialized");
        }

        log.info("SQLite database initialized");
    }

    @Override
    public void load(String filePath) throws DataHandlerException {
        SQLiteDatabaseFactory.loadNewDatabase(filePath);

        if (SQLiteDatabaseFactory.getDatabase() == null)
            throw new DataHandlerException("Loading file failed");

        notifyOpening();
        save();
    }

    @Override
    public void save() throws DataHandlerException {
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        try {
            notifyUpdate();
            database.commit();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataHandlerException("Saving file failed");
        }
    }

    @Override
    public void close() throws DataHandlerException {
        SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();

        try {
            database.close();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new DataHandlerException("Closing file failed");
        }
    }

    @Override
    public RaceProvider getRaceProvider() {
        return new RaceSQLiteProvider();
    }

    /**
     * Notifies a database update.
     */
    private void notifyUpdate() {
        RaceSQLiteProvider raceSQLiteProvider = new RaceSQLiteProvider();

        try {
            SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
            Race race = raceSQLiteProvider.find();
            race.setLastUpdateDate(LocalDateTime.now());
            raceSQLiteProvider.update(race);
            database.commit();
        } catch (DataProviderException | SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Notifies a database opening.
     */
    private void notifyOpening() {
        RaceSQLiteProvider raceSQLiteProvider = new RaceSQLiteProvider();

        try {
            SQLiteDatabase database = SQLiteDatabaseFactory.getDatabase();
            Race race = raceSQLiteProvider.find();
            race.setLastOpeningDate(LocalDateTime.now());
            raceSQLiteProvider.update(race);
            database.commit();
        } catch (DataProviderException | SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
