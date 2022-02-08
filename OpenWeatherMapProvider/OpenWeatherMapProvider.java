/**
 * Java Core. Homework #6. class OpenweathermapProvider
 * @author Zdibnyak Maxim
 * @version 01.02.2022
 */
package ru.geekbrains;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenWeatherMapProvider implements WeatherProvider {
    private static final String CITY_ID = "536203";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();
    private static final String PATH = "data/2.5/";
    private static final String ENDPOINT_5_DAYS = "forecast";
    private static final String ENDPOINT_TODAY = "weather";

    // Client for request
    public static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();
        return client;
    }

    // Method for today
    public String weatherToday() throws IOException {
        HttpUrl httpUrlToday = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment(PATH+ENDPOINT_TODAY)
                .addQueryParameter("id", CITY_ID)
                .addQueryParameter("appid", API_KEY)
                .build();

        System.out.println(httpUrlToday);
        Request request = new Request.Builder()
                .url(httpUrlToday)
                .build();

        Response response = getClient().newCall(request).execute();
        String jsonBody = response.body().string();

        return jsonBody;
    }

    // Method for 5 days
    public String weatherFor5Days() throws IOException {
        HttpUrl httpUrl5Days = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment(PATH+ENDPOINT_5_DAYS)
                .addQueryParameter("id", CITY_ID)
                .addQueryParameter("appid", API_KEY)
                .build();

        System.out.println(httpUrl5Days);
        Request request = new Request.Builder()
                .url(httpUrl5Days)
                .build();

        Response response = getClient().newCall(request).execute();
        String jsonBody = response.body().string();

        return jsonBody;
    }

    @Override
    public void getWeather(Periods period) throws IOException {

    }
}



