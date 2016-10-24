package nr.openweathermapforecastprovider;

import nr.weatherforecastprovider.WeatherForecastProvider;
import nr.weatherforecastprovider.WeatherSummary;
import org.json.JSONObject;

import java.util.List;

public class OpenWeatherMapForecastProviderFacade implements WeatherForecastProvider {

    @Override
    public List<WeatherSummary> getWeatherForecast(int days, int cityId, String key) {
        OpenWeatherMapForecastProvider forecastProvider = new OpenWeatherMapForecastProvider();

        JSONObject forecastInJson = forecastProvider.getJsonFromOpenWeatherMap(cityId, days, key);
        return forecastProvider.jsonToWeatherSummaries(forecastInJson);
    }
}
