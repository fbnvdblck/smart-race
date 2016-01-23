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

import java.sql.*;

/**
 * A class representing a SQLite database.
 *
 * @author Fabien Vanden Bulck
 */
public class SQLiteDatabase {

    /** The driver for the connection to the SQLite database. */
    private static final String DRIVER = "org.sqlite.JDBC";

    /** The file path of the SQLite database. */
    private String filePath;

    /** The connection to the SQLite database. */
    private Connection connection;

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SQLiteDatabase.class);


    /**
     * Constructs an instance of SQLite database.
     *
     * @param filePath the file path of the SQLite database
     */
    public SQLiteDatabase(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            try {
                Class.forName(DRIVER);
                this.filePath = filePath;
                connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
                connection.setAutoCommit(false);
            } catch (ClassNotFoundException | SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Gets the file path of the SQLite database.
     *
     * @return the file path of the SQLite database
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets the connection to the SQLite database.
     *
     * @return the connection to the SQLite database
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Creates a SQL statement.
     *
     * @return a SQL statement
     *
     * @throws SQLException
     */
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Creates a prepared SQL statement.
     *
     * @param sql the SQL instructions
     *
     * @return a prepared SQL statement
     *
     * @throws SQLException
     */
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Commits the changes (transaction).
     *
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollbacks the changes (transaction).
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Closes the SQLite database. Please commit or rollback before.
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    @Override
    public String toString() {
        return getFilePath();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SQLiteDatabase && getFilePath().equals(((SQLiteDatabase) other).getFilePath());
    }
}
