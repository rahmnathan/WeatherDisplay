package nr.currentweatherprovider;

import nr.currentweather.CurrentWeather;

public interface CurrentWeatherProvider {

    CurrentWeather getCurrentWeather(String city);
}