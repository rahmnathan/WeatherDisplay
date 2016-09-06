import com.google.common.io.ByteStreams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherProvider {

    String uri = "http://api.openweathermap.org/data/2.5/weather?id=5045021&units=imperial&appid=eec5d76a469ca1a3b7feb0331b7543f9";

    public CurrentWeather getCurrentWeather(){

        return assembleCurrentWeather(getContent());
    }

    private JSONObject getContent(){
        URL url = null;
        String content = "";

        try {
            url = new URL(uri);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try{
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

    private CurrentWeather assembleCurrentWeather(JSONObject jsonObject){
        CurrentWeather currentWeather = new CurrentWeather();

        try{
            JSONObject weather = (JSONObject) ((JSONArray) jsonObject.get("weather")).get(0);
            currentWeather.setSky((String) weather.get("description"));
            currentWeather.setIcon(getIcon((String) weather.get("icon")));

            JSONObject main = (JSONObject) jsonObject.get("main");
            currentWeather.setTemp((String.valueOf(main.get("temp"))).split("\\.")[0]);
            currentWeather.setHighTemp(String.valueOf(main.get("temp_max")));
            currentWeather.setLowTemp(String.valueOf(main.get("temp_min")));
            currentWeather.setHumidity(String.valueOf(main.get("humidity")));

            JSONObject wind = (JSONObject) jsonObject.get("wind");
            currentWeather.setWindDirection(getWindDirection((Integer) wind.get("deg")));
            currentWeather.setWindSpeed(String.valueOf(wind.get("speed")));

        }catch(JSONException e){
            e.printStackTrace();
        }

        return currentWeather;
    }

    private String getWindDirection(Integer degree){

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
