/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains.dto;

import ru.geekbrains.entity.WeatherData;
import ru.geekbrains.dto.WeatherResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Репозиторий для работы
public interface DatabaseRepository {

    boolean saveWeatherData(WeatherData weatherData) throws SQLException;

    String getAllSavedData() throws IOException;

    List<WeatherData> getAllSavedData(String city) throws IOException;
}
