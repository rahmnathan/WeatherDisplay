package nr.weatherutils;

public class WindDirection {

    public static String degreesToWindDirection(Number degrees){
        Integer degree = degrees.intValue();
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
}
