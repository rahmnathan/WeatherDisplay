package nr.bingbackgroundimageprovider;

import com.google.common.io.ByteStreams;
import nr.backgroundimageprovider.BackgroundImageProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BingBackgroundImageProvider implements BackgroundImageProvider {

    public BingBackgroundImageProvider(){}

    @Override
    public byte[] getBackgroundImage(){

        return getImage(getImageLink());
    }

    private static String getImageLink(){
        String uri = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
        try{
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String reader = br.readLine();
            String content = "";

            while (reader != null){
                content += reader;
                reader = br.readLine();
            }

            connection.disconnect();

            JSONObject json = new JSONObject(content);

            return ((JSONObject) (((JSONArray) json.get("images")).get(0))).get("url").toString();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getImage(String imageLink){
        String uri = "http://www.bing.com" + imageLink;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            return ByteStreams.toByteArray(connection.getInputStream());

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
