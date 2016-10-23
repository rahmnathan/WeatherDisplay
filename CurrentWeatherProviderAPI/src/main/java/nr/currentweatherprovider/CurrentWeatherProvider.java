package nr.currentweatherprovider;

public interface CurrentWeatherProvider {

    CurrentWeather getCurrentWeather(String city, String key);
}