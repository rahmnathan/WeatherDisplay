import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommuteProvider {

    String uri = "";

    public String getCommute(){

        return getTime(getCommuteContent());
    }

    private JSONObject getCommuteContent(){
        try {
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
