/**
 * Java Core. Homework #7. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 12.02.2022
 */
package ru.geekbrains;

import ru.geekbrains.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {

    public String getWeather(Periods period) throws IOException;

    public String getWeatherIn5Days(Periods period) throws IOException;

    public String getCityName();
}
