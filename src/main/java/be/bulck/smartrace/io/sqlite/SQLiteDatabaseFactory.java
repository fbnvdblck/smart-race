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

package be.bulck.smartrace.io.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * A class representing the SQLite database factory.
 *
 * @author Fabien Vanden Bulck
 */
public class SQLiteDatabaseFactory {

    /** The SQLite database instance. */
    private static SQLiteDatabase database;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SQLiteDatabaseFactory.class);

    /**
     * Loads a new SQLite database.
     *
     * @param filePath the file path used to build a new SQLite database instance
     */
    public static void loadNewDatabase(String filePath) {
        try {
            if (database != null && database.getConnection() != null && !database.getConnection().isClosed())
                database.close();

            database = new SQLiteDatabase(filePath);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Gets the SQLite database instance (based on file path provided previously).
     *
     * @return the SQLite database instance
     *
     * @see #loadNewDatabase(String)
     */
    public static SQLiteDatabase getDatabase() {
        return database;
    }
}
