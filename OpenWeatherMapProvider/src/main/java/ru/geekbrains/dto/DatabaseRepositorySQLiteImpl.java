/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains.dto;

import ru.geekbrains.ApplicationGlobalState;
import ru.geekbrains.dto.DatabaseRepository;
import ru.geekbrains.entity.WeatherData;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "city TEXT NOT NULL, " +
        "date_time TEXT NOT NULL, " +
        "weather_day_text TEXT NOT NULL, " +
        "temperature_day REAL NOT NULL, " +
        "weather_night_text TEXT NOT NULL, " +
        "temperature_night REAL NOT NULL);";

    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_day_text, temperature_day, "
            + "weather_night_text, temperature_night) VALUES (?,?,?,?,?,?)";

    String selectWeatherQuery = "SELECT * FROM weather WHERE city = ";

    String selectAllWeatherQuery = "SELECT * FROM weather;";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        createTableIfNotExists();
        try (Connection connection = getConnection();
        PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getTextDay());
            saveWeather.setDouble(4, weatherData.getTemperatureDay());
            saveWeather.setString(5, weatherData.getTextNight());
            saveWeather.setDouble(6, weatherData.getTemperatureNight());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherData> getAllSavedData(String city) throws IOException {

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectWeatherQuery + city + ";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IOException("Not implemented exception");
    }

    @Override
    public String getAllSavedData() {

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(selectAllWeatherQuery);
            String result = "";

            ResultSet resultSet = statement.executeQuery(selectAllWeatherQuery);
            // В данном случае result set выгружает всю результирующую выборку
            while (resultSet.next()) {
                result +=
                        resultSet.getInt(1) + " | " +
                                resultSet.getString(2) + " | " +
                                resultSet.getString(3) + " | " +
                                resultSet.getString(4) + " | " +
                                resultSet.getDouble(5) + " | " +
                                resultSet.getString(6) + " | " +
                                resultSet.getDouble(7);
                result += "\n";
            }

            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {
        return filename;
    }
}
