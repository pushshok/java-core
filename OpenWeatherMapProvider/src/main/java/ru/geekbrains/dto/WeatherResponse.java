/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.geekbrains.entity.WeatherData;
import java.util.List;


public class WeatherResponse {

    private String city;
    private String date;
    private String textDay;
    private Double temperatureDay;
    private String textNight;
    private Double temperatureNight;

    private final ObjectMapper objectMapper = new ObjectMapper();
    public double toCelsium(String temperature) {
        double celsium;
        celsium = (Double.parseDouble(temperature) - 32 ) * 5/9;

        return celsium;
    }

    public Object parseWeatherToday(String responseBody) throws JsonProcessingException {
        String[] answer = responseBody.split("---");
        String jsonBody = answer[1];

        String[] date = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Date").asText().split("T");

        JsonNode temperatureNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature/Minimum" +
                "/Value");
        JsonNode textNight = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Night/IconPhrase");
        JsonNode temperatureDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Temperature" +
                "/Maximum/Value");
        JsonNode textDay = objectMapper.readTree(jsonBody).at("/DailyForecasts/0/Day/IconPhrase");

        this.city = answer[0];
        this.date = date[0];
        this.textDay = textDay.asText();
        this.temperatureDay = toCelsium(temperatureDay.asText());
        this.textNight = textNight.asText();
        this.temperatureNight = toCelsium(temperatureNight.asText());

        WeatherData weatherData = new WeatherData(this.city, this.date, this.textDay, this.temperatureDay,
                this.textNight, this.temperatureNight);

        return weatherData;
    }


    public WeatherData[] parseWeather5Days(String responseBody) throws JsonProcessingException {
        String[] answer = responseBody.split("---");
        WeatherData[] arr = new WeatherData[5];
        String jsonBody = answer[1];
        List<String> data = objectMapper.readTree(jsonBody).at("/DailyForecasts").findValuesAsText("Date");
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

            arr[i] = new WeatherData(answer[0], date[0], textDay.asText(), toCelsium(temperatureDay.asText()),
                    textNight.asText(), toCelsium(temperatureNight.asText()));
        }

        return arr;
    }
}
