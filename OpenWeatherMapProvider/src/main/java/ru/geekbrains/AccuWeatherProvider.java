/**
 * Java Core. Homework #7. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 12.02.2022
 */
package ru.geekbrains;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.geekbrains.enums.Periods;

import java.io.IOException;

public class AccuWeatherProvider implements ru.geekbrains.WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String API_VERSION = "v1";
    private static final String TIME_INTERVAL = "daily";
    private static final String FOR_DAY = "1day";
    private static final String FOR_5_DAYS = "5day";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    String cityName = "";

    @Override
    public String getWeatherIn5Days(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.FIVE_DAYS)) {
            HttpUrl url2 = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(TIME_INTERVAL)
                    .addPathSegment(FOR_5_DAYS)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .build();

            Request request2 = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url2)
                    .build();

            Response response2 = client.newCall(request2).execute();

            return getCityName() + "---" + response2.body().string();
        }

        return cityKey;
    }

    @Override
    public String getWeather(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.NOW)) {
            HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(FORECAST_ENDPOINT)
                .addPathSegment(API_VERSION)
                .addPathSegment(TIME_INTERVAL)
                .addPathSegment(FOR_DAY)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();

            Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

            Response response = client.newCall(request).execute();

            return getCityName() + "---" + response.body().string();
        }
        return cityKey;
    }

    public String getCityName() {
        return this.cityName;
    }


    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
            .scheme("http")
            .host(BASE_HOST)
            .addPathSegment("locations")
            .addPathSegment(API_VERSION)
            .addPathSegment("cities")
            .addPathSegment("autocomplete")
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("q", selectedCity)
            .build();

        Request request = new Request.Builder()
            .addHeader("accept", "application/json")
            .url(detectLocationURL)
            .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            this.cityName = cityName;
            String countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
