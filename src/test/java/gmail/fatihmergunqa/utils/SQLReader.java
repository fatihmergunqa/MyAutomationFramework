package gmail.fatihmergunqa.utils;

import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A utility class for reading data from a database using JDBC.
 * Provides methods to establish a connection, retrieve data, and close the connection.
 */
public class SQLReader {
    // Database connection, statement, and result set objects
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Establishes a database connection using properties specified in the configuration file.
     * It reads database URL, username, and password from the configuration file.
     */
    public static void getConnection() {
        // Load database configuration settings from a properties file
        Configs.readProperties(Constants.CONFIGURATION_FILEPATH);
        try {
            // Create a connection using credentials from the properties file
            connection = DriverManager.getConnection(
                    Configs.getProperty("db_url"),
                    Configs.getProperty("db_username"),
                    Configs.getProperty("db_password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the given SQL query and retrieves data from the database.
     *
     * @param sql The SQL query to execute.
     * @return A list of maps where each map represents a row of data with column names as keys.
     */
    public static List<Map<String, String>> getData(@Language("SQL") String sql) {
        List<Map<String, String>> data = new ArrayList<>();

        try {
            // Create and execute the SQL statement
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // Get metadata to retrieve column names
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            // Process each row in the result set
            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();

                // Map each column's name and value for the current row
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                }

                // Add the row's data to the list
                data.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Closes the database connection and associated resources.
     * Ensures result set, statement, and connection are closed to prevent resource leaks.
     */
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}