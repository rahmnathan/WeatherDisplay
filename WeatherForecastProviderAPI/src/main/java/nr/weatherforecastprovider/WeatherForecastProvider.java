package nr.weatherforecastprovider;

import java.util.List;

public interface WeatherForecastProvider {

    List<WeatherSummary> getWeatherForecast(int days, int cityId, String key);
}
