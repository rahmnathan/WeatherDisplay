package nr.openweathermapcurrentweatherprovider;

import nr.currentweatherprovider.CurrentWeather;
import nr.currentweatherprovider.CurrentWeatherProvider;
import nr.weatherutils.HttpImageProvider;
import nr.weatherutils.WindDirection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("OpenWeather")
public class OpenWeatherMapCurrentWeatherProvider implements CurrentWeatherProvider {

    public OpenWeatherMapCurrentWeatherProvider(){}

    @Override
    public CurrentWeather getCurrentWeather(String cityId, String key){
        return assembleCurrentWeather(getContent(cityId, key));
    }

    private CurrentWeather assembleCurrentWeather(JSONObject jsonObject){
        CurrentWeather currentWeather = null;

        try{
            JSONObject weather = (JSONObject) ((JSONArray) jsonObject.get("weather")).get(0);
            JSONObject main = (JSONObject) jsonObject.get("main");
            JSONObject wind = (JSONObject) jsonObject.get("wind");
            CurrentWeather.Builder builder = CurrentWeather.Builder.newInstance();

            builder
                    .highTemp(String.valueOf(main.get("temp_max")))
                    .lowTemp(String.valueOf(main.get("temp_min")))
                    .temp((String.valueOf(main.get("temp"))).split("\\.")[0])
                    .windDirection(WindDirection.degreesToWindDirection((Number) wind.get("deg")))
                    .windSpeed(String.valueOf(wind.get("speed")))
                    .sky((String) weather.get("description"));

            String iconUrl = "http://openweathermap.org/img/w/" + weather.getString("icon") + ".png";
            builder.icon(HttpImageProvider.getImageFromHttp(iconUrl));

            currentWeather = builder.build();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return currentWeather;
    }

    private JSONObject getContent(String cityId, String key){
        URL url;
        String content = "";
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + cityId +
                    "&units=imperial&appid=" + key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String reader = br.readLine();

            while (reader != null){
                content += reader;
                reader = br.readLine();
            }

            br.close();
            connection.disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }
        return new JSONObject(content);
    }
}
