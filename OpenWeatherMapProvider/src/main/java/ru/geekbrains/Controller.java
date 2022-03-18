/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains;

import ru.geekbrains.dto.AccuWeatherProvider;
import ru.geekbrains.dto.DatabaseRepositorySQLiteImpl;
import ru.geekbrains.dto.WeatherProvider;
import ru.geekbrains.dto.WeatherResponse;
import ru.geekbrains.entity.WeatherData;
import ru.geekbrains.enums.Functionality;
import ru.geekbrains.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
        variantResult.put(3, Functionality.GET_WEATHER_FROM_DB);
    }

    public void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
            case GET_WEATHER_FROM_DB:
                getWeatherFromDB();
                break;
        }
    }

    public void getWeatherIn5Days() throws IOException, SQLException {
        WeatherResponse weatherResponse2 = new WeatherResponse();
        WeatherData[] arr;
        arr = weatherResponse2.parseWeather5Days(weatherProvider.getWeatherIn5Days(Periods.FIVE_DAYS));

        for (int i = 0; i < arr.length; i++) {
            DatabaseRepositorySQLiteImpl dataSave = new DatabaseRepositorySQLiteImpl();
            dataSave.saveWeatherData((WeatherData) arr[i]);
            System.out.println(arr[i]);
        }
    }

    public void getCurrentWeather() throws IOException, SQLException {
        WeatherResponse weatherResponse = new WeatherResponse();
        Object object = weatherResponse.parseWeatherToday(weatherProvider.getWeather(Periods.NOW));
        DatabaseRepositorySQLiteImpl dataSave = new DatabaseRepositorySQLiteImpl();
        dataSave.saveWeatherData((WeatherData) object);
        System.out.println(object);

    }

    public void getWeatherFromDB() throws IOException {
        DatabaseRepositorySQLiteImpl dataSave = new DatabaseRepositorySQLiteImpl();
        System.out.println(dataSave.getAllSavedData());
    }
}
