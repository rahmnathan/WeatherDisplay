package nr.openweathermapcurrentweatherprovider;

import com.google.common.io.ByteStreams;
import nr.currentweatherprovider.CurrentWeather;
import nr.currentweatherprovider.CurrentWeatherProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("OpenWeather")
public class OpenWeatherMapCurrentWeatherProvider implements CurrentWeatherProvider {

    public OpenWeatherMapCurrentWeatherProvider(){}

    @Override
    public CurrentWeather getCurrentWeather(String cityId){
        return assembleCurrentWeather(getContent(cityId));
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
                    .windDirection(getWindDirection((Number) wind.get("deg")))
                    .windSpeed(String.valueOf(wind.get("speed")))
                    .sky((String) weather.get("description"))
                    .icon(getIcon((String) weather.get("icon")));

            currentWeather = builder.build();
        }catch(JSONException e){
            e.printStackTrace();
        }
        return currentWeather;
    }

    private JSONObject getContent(String cityId){
        URL url;
        String content = "";
        String key = "";
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

    private String getWindDirection(Number degreeInput){
        Integer degree = degreeInput.intValue();
        if(degree < 22.5 || degree > 337.5){
            return "North";
        } else if (degree < 67.5){
            return "NE";
        } else if (degree < 117.5){
            return "East";
        } else if (degree < 157.5){
            return "SE";
        } else if (degree < 202.5){
            return "South";
        } else if (degree < 247.5){
            return "SW";
        } else if (degree < 292.5){
            return "West";
        } else if (degree < 337.5){
            return "NW";
        } else{
            return "N/A";
        }
    }

    private byte[] getIcon(String iconCode){
        String imageUri = "http://openweathermap.org/img/w/" + iconCode + ".png";
        URL imageURL;
        try {
            imageURL = new URL(imageUri);
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            InputStream inputStream = connection.getInputStream();
            return ByteStreams.toByteArray(inputStream);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
