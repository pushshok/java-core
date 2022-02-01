/**
 * Java Core. Homework #6. class OpenweathermapProvider
 * @author Zdibnyak Maxim
 * @version 01.02.2022
 */

import okhttp3.OkHttpClient;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenweathermapProvider {

    private static final String CITY_ID = "536203";
    private static final String API_KEY = "f327c038b57a8f182eda6d08d9aa7385";
    private static final String PATH = "data/2.5/";
    private static final String ENDPOINT_5_DAYS = "forecast";
    private static final String ENDPOINT_TODAY = "weather";


    public static void main(String[] args) throws IOException, NoClassDefFoundError {

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();
        // Method for 5 days
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

        Response response = client.newCall(request).execute();
        String jsonBody = response.body().string();
        System.out.println(jsonBody);

        // Method for today
        HttpUrl httpUrlToday = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment(PATH+ENDPOINT_TODAY)
                .addQueryParameter("id", CITY_ID)
                .addQueryParameter("appid", API_KEY)
                .build();

        System.out.println(httpUrlToday);
        request = new Request.Builder()
                .url(httpUrlToday)
                .build();

        response = client.newCall(request).execute();
        jsonBody = response.body().string();
        System.out.println(jsonBody);
    }
}



