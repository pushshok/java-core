/**
 * Java Core. Homework #7. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 12.02.2022
 */
package ru.geekbrains;

import ru.geekbrains.enums.Functionality;
import ru.geekbrains.enums.Periods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
    }

    public void onUserInput(String input) throws IOException {
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
        }
    }

    public void getWeatherIn5Days() throws IOException {
        WeatherResponse weatherResponse2 = new WeatherResponse();
        System.out.println(weatherResponse2.parseWeather5Days(weatherProvider.getWeatherIn5Days(Periods.FIVE_DAYS)));
    }

    public void getCurrentWeather() throws IOException {
        WeatherResponse weatherResponse = new WeatherResponse();
        System.out.println(weatherResponse.parseWeatherToday(weatherProvider.getWeather(Periods.NOW)));

    }


}
