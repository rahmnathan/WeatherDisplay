package nr.weatherutils;

import com.google.common.io.ByteStreams;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpImageProvider {

    public static byte[] getImageFromHttp(String imageUrl){
        try {
            URL imageURLString = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) imageURLString.openConnection();
            InputStream inputStream = connection.getInputStream();
            return ByteStreams.toByteArray(inputStream);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
