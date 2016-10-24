This project is a display showing weather in your current location, the current time, and the current commute time to from/to
any given destination. I currently have it running on a 16" screen. I'm not sure how it will scale to others. Weather is provided by OpenWeatherMap; commute is provided by google; and background image is provided by Bing. Screenshots/pictures can be found here --> http://imgur.com/a/XdPYu .

To use:

1) Clone this repo into the desired directory

2) Create a weatherdisplay.properties file under the WeatherDisplay/Executable/src/main/resources/  directory

3) Enter properties as follows, each on their own line, with no spaces:

  start=(commute-start-coordinates)
  
  end=(commute-end-coordinates)
  
  key=(google-maps-key)
  
  weatherKey=(open-weather-map-key)
  
  cityID=(open-weather-map-city-ID)

4) Save the file

5) In the WeatherDisplay directory, run "mvn clean install"

6) In the WeatherDisplay/Executable directory, run "mvn spring-boot:run" and the application will launch
