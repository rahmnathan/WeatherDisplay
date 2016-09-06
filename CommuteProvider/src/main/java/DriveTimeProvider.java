import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DriveTimeProvider {

    String uri = "https://maps.googleapis.com/maps/api/directions/json?origin=44.94638,-93.328981&destination=44.807234,-93.355154&key=AIzaSyCJNMBAobikR_slei0YiXZdqCgdvoFHO_Q";

    public JSONObject getCommute(){
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
}
