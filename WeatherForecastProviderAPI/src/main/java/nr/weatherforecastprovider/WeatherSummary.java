package nr.weatherforecastprovider;

public class WeatherSummary {
    private final String sky;
    private final Integer highTemp;
    private final Integer lowTemp;
    private final Integer windSpeed;
    private final byte[] icon;
    private final String humidity;
    private final Long dateTime;

    private WeatherSummary(String sky, Integer highTemp, Integer lowTemp, Integer windSpeed, byte[] icon, String humidity, Long dateTime) {
        this.sky = sky;
        this.highTemp = highTemp;
        this.windSpeed = windSpeed;
        this.icon = icon;
        this.humidity = humidity;
        this.lowTemp = lowTemp;
        this.dateTime = dateTime;
    }

    public String getSky() {
        return sky;
    }

    public Integer getHighTemp() {
        return highTemp;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public byte[] getIcon() {
        return icon;
    }

    public String getHumidity() {
        return humidity;
    }

    public Integer getLowTemp() {
        return lowTemp;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public static class Builder {
        private String sky;
        private Integer highTemp;
        private Integer windSpeed;
        private byte[] icon;
        private String humidity;
        private Integer lowTemp;
        private Long dateTime;

        public static Builder newInstance(){
            return new Builder();
        }

        public Builder setSky(String sky) {
            this.sky = sky;
            return this;
        }

        public Builder setHighTemp(Integer temp) {
            this.highTemp = temp;
            return this;
        }

        public Builder setHumidity(String humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setWindSpeed(Integer windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public Builder setIcon(byte[] icon) {
            this.icon = icon;
            return this;
        }

        public Builder setLowTemp(Integer lowTemp) {
            this.lowTemp = lowTemp;
            return this;
        }

        public Builder setDateTime(Long dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public WeatherSummary build(){
            return new WeatherSummary(sky, highTemp, lowTemp, windSpeed, icon, humidity, dateTime);
        }
    }

}
