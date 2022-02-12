/**
 * Java Core. Homework #7. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 12.02.2022
 */
package ru.geekbrains;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;


public class WeatherResponse {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private WeatherProvider weatherProvider = new AccuWeatherProvider();

    public double toCelsium(String temperature) {
        double celsium;
        celsium = (Double.parseDouble(temperature) - 32 ) * 5/9;

        return celsium;
    }

    public String parseWeatherToday(String responseBody) throws JsonProcessingException {
        String[] answer = responseBody.split("---");
        String jsonBody = answer[1];

        String[] data = objectMapper.readTree(jsonBody).at("/DailyForecasts/0").asText().split("T");

        JsonNode temperatureNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature/Minimum" +
                "/Value");
        JsonNode textNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Night/IconPhrase");
        JsonNode temperatureDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature" +
                "/Maximum/Value");
        JsonNode textDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Day/IconPhrase");

        return  answer[0] + ": Day " + textDay + " " + String.format("%.1f",toCelsium(temperatureDay.asText())) +
                " / Night " + textNight + " " + String.format("%.1f",toCelsium(temperatureNight.asText()));
    }


    public String parseWeather5Days(String responseBody) throws JsonProcessingException {
        String[] answer = responseBody.split("---");
        String jsonBody = answer[1];
        List<String> data = objectMapper.readTree(jsonBody).at("/DailyForecasts").findValuesAsText("Date");
        String dataCompose = "";
        for (int i = 0; i < data.size(); i++) {
            String[] date =
                    objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i + "/Date").asText().split("T");
            JsonNode temperatureNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i +
                    "/Temperature/Minimum" +
                    "/Value");

            JsonNode textNight =
                    objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i + "/Night/IconPhrase");
            JsonNode temperatureDay =
                    objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i + "/Temperature" +
                    "/Maximum/Value");
            JsonNode textDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/" + i + "/Day/IconPhrase");

            dataCompose += answer[0] + ": Day " + textDay + " " +
                    String.format("%.1f",toCelsium(temperatureDay.asText())) + " / Night " + textNight +
                    " " + String.format("%.1f",toCelsium(temperatureNight.asText())) + "\n";
        }

        return dataCompose;
    }
}
