package nr.gui;

import nr.currentweather.CurrentWeather;
import org.apache.commons.lang3.text.WordUtils;

import java.awt.*;
import java.time.LocalDateTime;

public class GUIUpdater {

    static void updateDateTime(){

        String[] dateTime = LocalDateTime.now().toString().split("T");
        String[] timeStampArray = dateTime[1].split(":");
        Integer hour = Integer.valueOf(timeStampArray[0]);
        if(hour > 12) {
            hour -= 12;
        }
        String hourString = String.valueOf(hour);
        String timeStamp = hourString + ":" + timeStampArray[1];
        String[] dateArray = dateTime[0].split("-");
        String date = dateArray[1] + "-" + dateArray[2] + "-" + dateArray[0];

        GUI.currentDate.setText(date);

        GUI.currentTime.setText(timeStamp);
        GUI.frame.repaint();
    }

    public static void updateCurrentWeather(){
        CurrentWeather currentWeather = GUI.currentWeatherProvider.getCurrentWeather(GUI.currentWeatherCityId);
        GUI.currentTemp.setText(currentWeather.getTemp() + "°F");
        GUI.windDirection.setText(currentWeather.getWindDirection());
        GUI.panel.remove(GUI.currentWeatherIcon);
        GUI.currentWeatherIcon = new CurrentWeatherIconLabel(currentWeather.getIcon());
        GUI.currentWeatherIcon.setLocation(10,10);
        GUI.currentWeatherIcon.setSize(250,250);
        GUI.panel.add(GUI.currentWeatherIcon);
        GUI.highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        GUI.windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        GUI.weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        GUI.frame.repaint();
    }

    static void updateBackgroundImage(){
        byte[] backgroundImage = GUI.backgroundImageProvider.getBackgroundImage();

        GUI.frame.remove(GUI.backgroundPane);
        GUI.backgroundPane = new BackgroundPane(backgroundImage);
        GUI.backgroundPane.setLayout(new GridLayout());
        GUI.backgroundPane.add(GUI.panel);
        GUI.frame.add(GUI.backgroundPane);
        GUI.frame.repaint();
    }

    public static void updateCommute(){
        String commute = GUI.commuteProvider.getCommuteTime(GUI.commuteStartLocation, GUI.commuteEndLocation);
        GUI.commuteTime.setText("<html><center>" + commute + "</center><html>");
        GUI.frame.repaint();
    }
}
