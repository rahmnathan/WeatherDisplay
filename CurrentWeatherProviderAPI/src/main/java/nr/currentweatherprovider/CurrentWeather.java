package nr.currentweatherprovider;

public class CurrentWeather {

    private final String sky;
    private final String temp;
    private final String highTemp;
    private final String lowTemp;
    private final String windSpeed;
    private final String windDirection;
    private final byte[] icon;

    private CurrentWeather(String sky, String temp, String highTemp, String lowTemp, String windSpeed, String windDirection,
                           byte[] icon){
        this.sky = sky;
        this.temp = temp;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
    }

    public byte[] getIcon() {
        return icon;
    }

    public String getSky() {
        return sky;
    }

    public String getTemp() {
        return temp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public static class Builder{

        private String sky;
        private String temp;
        private String highTemp;
        private String lowTemp;
        private String windSpeed;
        private String windDirection;
        private byte[] icon;

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder sky(String sky){
            this.sky = sky;
            return this;
        }
        public Builder temp(String temp){
            this.temp = temp;
            return this;
        }
        public Builder highTemp(String highTemp){
            this.highTemp = highTemp;
            return this;
        }
        public Builder lowTemp(String lowTemp){
            this.lowTemp = lowTemp;
            return this;
        }
        public Builder windSpeed(String windSpeed){
            this.windSpeed = windSpeed;
            return this;
        }
        public Builder windDirection(String windDirection){
            this.windDirection = windDirection;
            return this;
        }
        public Builder icon(byte[] icon){
            this.icon = icon;
            return this;
        }
        public CurrentWeather build(){
            return new CurrentWeather(sky, temp, highTemp, lowTemp, windSpeed, windDirection, icon);
        }
    }
}
