package nr.googlecommuteprovider;

import nr.commuteprovider.CommuteProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("GoogleCommute")
public class GoogleCommuteProvider implements CommuteProvider {

    public String getCommuteTime(String startLocation, String endLocation, String key){
        return getTime(getCommuteContent(startLocation, endLocation, key));
    }

    private JSONObject getCommuteContent(String startLocation, String endLocation, String key){
        try {
            String uri = "https://maps.googleapis.com/maps/api/directions/json?origin=" + startLocation +
                    "&destination=" + endLocation + "&key=" + key;
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input = reader.readLine();
            String response = "";

            while(input != null){
                response += input;
                input = reader.readLine();
            }

            return new JSONObject(response);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getTime(JSONObject jsonObject){
        JSONObject firstRoute = ((JSONArray) jsonObject.get("routes")).getJSONObject(0);
        JSONObject legs = ((JSONArray) firstRoute.get("legs")).getJSONObject(0);
        JSONObject duration = (JSONObject) legs.get("duration");
        return duration.getString("text");
    }
}
