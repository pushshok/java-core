/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains.dto;

import ru.geekbrains.enums.Periods;

import java.io.IOException;

public interface WeatherProvider {

    public String getWeather(Periods period) throws IOException;

    public String getWeatherIn5Days(Periods period) throws IOException;

    public String getCityName() throws IOException;

}
