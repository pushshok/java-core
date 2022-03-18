/**
 * Java Core. Homework #8. App OpenWeatherMapProvider
 * @author Zdibnyak Maxim
 * @version 24.02.2022
 */
package ru.geekbrains.entity;

public class WeatherData {
    private String city;
    private String localDate;
    private String textDay;
    private Double temperatureDay;
    private String textNight;
    private Double temperatureNight;

    public WeatherData() {
    }

    public WeatherData(String city, String localDate, String textDay, Double temperatureDay, String textNight,
                       Double temperatureNight) {
        this.city = city;
        this.localDate = localDate;
        this.textDay = textDay;
        this.temperatureDay = temperatureDay;
        this.textNight = textNight;
        this.temperatureNight = temperatureNight;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public Double getTemperatureDay() {
        return temperatureDay;
    }

    public void setTemperatureDay(Double temperatureDay) {
        this.temperatureDay = temperatureDay;
    }

    public String getTextNight() {
        return textNight;
    }

    public void setTextNight(String textNight) {
        this.textNight = textNight;
    }

    public Double getTemperatureNight() {
        return temperatureNight;
    }

    public void setTemperatureNight(Double temperatureNight) {
        this.temperatureNight = temperatureNight;
    }

    @Override
    public String toString() {
        return "City " + city + " - " + localDate + ": " + textDay + ", Day temparature " + temperatureDay + " C, "
                + textNight + " , Night temparature " + temperatureNight + " C.";
    }
}
