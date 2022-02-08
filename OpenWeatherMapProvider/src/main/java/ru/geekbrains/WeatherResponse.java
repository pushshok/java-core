/**
 * Java Core. Homework #7. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 08.02.2022
 */
package ru.geekbrains;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WeatherResponse {
    private final ObjectMapper objectMapper = new ObjectMapper();


    public String parseWeatherToday(String jsonBody) throws JsonProcessingException {
        String[] data = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Date").asText().split("T");

        JsonNode temperatureNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature/Minimum" +
                "/Value");
        JsonNode textNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Night/IconPhrase");
        JsonNode temperatureDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature" +
                "/Maximum/Value");
        JsonNode textDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Day/IconPhrase");

        return data[0] + ": Day " + textDay + " " + temperatureDay + " / Night " + textNight + " " + temperatureNight;
    }


    public String parseWeather5Days(String jsonBody) throws JsonProcessingException {
        String[] data = objectMapper.readTree(jsonBody).at("/DailyForecasts").asText().split("Date");
        String dataCompose = "";

        for (int i = 0; i < data.length; i++) {

            JsonNode temperatureNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i +
                    "/Temperature/Minimum" +
                    "/Value");
            JsonNode textNight =
                    objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i + "/Night/IconPhrase");
            JsonNode temperatureDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature" +
                    "/Maximum/Value");
            JsonNode textDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Day/IconPhrase");


            dataCompose += data[i] + ": Day " + textDay + " " + temperatureDay + " / Night " + textNight +
                    " " + temperatureNight + "\n";
        }

        return dataCompose;
    }
}
