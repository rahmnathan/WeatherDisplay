package nr.bingbackgroundimageprovider;

import nr.backgroundimageprovider.BackgroundImageProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Service("BingBackground")
public class BingBackgroundImageProvider implements BackgroundImageProvider {
    private final Logger logger = Logger.getLogger(BingBackgroundImageProvider.class.getName());

    public BingBackgroundImageProvider(){}

    @Override
    public byte[] getBackgroundImage(){
        String uri = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
        HttpURLConnection connection = null;
        try{
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e){
            logger.severe(e.toString());
        }

        if(connection != nul){

        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String reader = br.readLine();
            String content = "";

            while (reader != null) {
                content += reader;
                reader = br.readLine();
            }
            connection.disconnect();

            JSONObject json = new JSONObject(content);
            return HttpImageProvider.getImageFromHttp("http://www.bing.com" +
                    ((JSONObject) (((JSONArray) json.get("images")).get(0))).get("url").toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
