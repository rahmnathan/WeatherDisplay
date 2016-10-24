package nr.openweathermapforecastprovider;

import nr.weatherutils.HttpImageProvider;
import nr.weatherforecastprovider.WeatherSummary;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class OpenWeatherMapForecastProvider {

    JSONObject getJsonFromOpenWeatherMap(int cityId, int days, String key) {
        String finalInput = "";
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=" + cityId + "&cnt=" + days + "&units=imperial&appid=" + key);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input = br.readLine();
            while (input != null) {
                finalInput += input;
                input = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(finalInput);
    }

    List<WeatherSummary> jsonToWeatherSummaries(JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        List<WeatherSummary> summaryList = new ArrayList<>();
        for(Object jsonObject1 : jsonArray) {
            WeatherSummary.Builder builder = WeatherSummary.Builder.newInstance();
            JSONObject object = (JSONObject) jsonObject1;

            JSONObject mainObject = object.getJSONObject("temp");
            builder.setHighTemp(Double.valueOf(mainObject.getDouble("max")).intValue());
            builder.setLowTemp(Double.valueOf(mainObject.get("min").toString()).intValue());

            builder.setHumidity(object.get("humidity").toString());
            builder.setWindSpeed(Double.valueOf(object.getDouble("speed")).intValue());
            builder.setDateTime(object.getLong("dt"));

            JSONObject weatherObject = (JSONObject) object.getJSONArray("weather").get(0);
            builder.setSky(weatherObject.getString("main"));

            String iconUrl = "http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png";
            builder.setIcon(HttpImageProvider.getImageFromHttp(iconUrl));

            summaryList.add(builder.build());
        }
        return summaryList;
    }
}
