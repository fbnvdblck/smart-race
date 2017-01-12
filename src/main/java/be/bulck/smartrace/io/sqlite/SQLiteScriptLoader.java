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

package be.bulck.smartrace.io.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An class representing a SQLite script loader.
 *
 * @author Fabien Vanden Bulck
 */
public class SQLiteScriptLoader {

    /** The logger. */
    private static final Logger log = LoggerFactory.getLogger(SQLiteScriptLoader.class);

    /**
     * Gets SQL queries from a SQL file.
     *
     * @param filePath the file path of the SQL file
     *
     * @return an array of SQL queries
     */
    public static String[] getSQLQueriesFromFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String buffer;

            while ((buffer = bufferedReader.readLine()) != null) {
                if (buffer.length() == 0 || buffer.startsWith("--"))
                    continue;

                stringBuilder.append(buffer);
            }

            bufferedReader.close();
            fileReader.close();

            return stringBuilder.toString().split(";");
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }

        return new String[0];
    }

    /**
     * Executes SQL queries from a SQL file.
     *
     * @param database the SQL database where the SQL queries must be executed
     * @param filePath the file path of the SQL file
     */
    public static void executeSQLFromFile(SQLiteDatabase database, String filePath) {
        String[] queries = getSQLQueriesFromFile(filePath);

        try {
            Statement statement = database.createStatement();

            for (String query : queries)
                statement.execute(query);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
