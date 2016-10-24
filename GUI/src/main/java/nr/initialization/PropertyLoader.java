package nr.initialization;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@Component
public class PropertyLoader {

    // Initial location data - can be configured via rest

    private static String commuteStartLocation = "";
    private static String commuteEndLocation = "";
    private static String currentWeatherCityId = "";
    private static String openWeatherMapKey = "";
    private static String googleCommuteKey = "";

    @PostConstruct
    public void loadProperties() {
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/weatherdisplay.properties");
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            commuteStartLocation = properties.getProperty("start");
            commuteEndLocation = properties.getProperty("end");
            currentWeatherCityId = properties.getProperty("cityID");
            openWeatherMapKey = properties.getProperty("weatherKey");
            googleCommuteKey = properties.getProperty("key");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setCommuteStartLocation(String startLocation){
        commuteStartLocation = startLocation;
    }
    public static void setCommuteEndLocation(String endLocation){
        commuteEndLocation = endLocation;
    }
    public static void setCurrentWeatherCityId(String CityId) {
        currentWeatherCityId = CityId;
    }
    public static String getCommuteStartLocation() {
        return commuteStartLocation;
    }
    public static String getCommuteEndLocation() {
        return commuteEndLocation;
    }
    public static String getCurrentWeatherCityId() {
        return currentWeatherCityId;
    }
    public static String getOpenWeatherMapKey() {
        return openWeatherMapKey;
    }
    public static String getGoogleCommuteKey() {
        return googleCommuteKey;
    }
}
