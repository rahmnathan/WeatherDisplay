package nr.gui;

import nr.currentweatherprovider.CurrentWeather;
import org.apache.commons.lang3.text.WordUtils;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GUIUpdater {

    private GUI gui;

    public void registerObserver(GUI gui){
        this.gui = gui;
    }

    public void startTimedUpdates(){

        Runnable dateTimeUpdater = this::updateDateTime;
        Runnable currentWeatherUpdater = this::updateCurrentWeather;
        Runnable backgroundImageUpdater = this::updateBackgroundImage;
        Runnable commuteUpdater = this::updateCommute;

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(dateTimeUpdater, 0, 10, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(currentWeatherUpdater, 0, 5, TimeUnit.MINUTES);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(backgroundImageUpdater, 0, 2, TimeUnit.HOURS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(commuteUpdater, 0, 3, TimeUnit.MINUTES);
    }

    private void updateDateTime(){

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

        gui.currentDate.setText(date);

        gui.currentTime.setText(timeStamp);
        gui.frame.repaint();
    }

    private void updateCurrentWeather(){
        CurrentWeather currentWeather = gui.currentWeatherProvider.getCurrentWeather(gui.currentWeatherCityId);
        gui.currentTemp.setText(currentWeather.getTemp() + "°F");
        gui.windDirection.setText(currentWeather.getWindDirection());
        gui.panel.remove(gui.currentWeatherIcon);
        gui.currentWeatherIcon = new CurrentWeatherIconLabel(currentWeather.getIcon());
        gui.currentWeatherIcon.setLocation(10,10);
        gui.currentWeatherIcon.setSize(250,250);
        gui.panel.add(gui.currentWeatherIcon);
        gui.highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        gui.windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        gui.weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        gui.frame.repaint();
    }

    private void updateBackgroundImage(){
        byte[] backgroundImage = gui.backgroundImageProvider.getBackgroundImage();

        gui.frame.remove(gui.backgroundPane);
        gui.backgroundPane = new BackgroundPane(backgroundImage);
        gui.backgroundPane.setLayout(new GridLayout());
        gui.backgroundPane.add(gui.panel);
        gui.frame.add(gui.backgroundPane);
        gui.frame.repaint();
    }

    private void updateCommute(){
        String commute = gui.commuteProvider.getCommuteTime(gui.commuteStartLocation, gui.commuteEndLocation);
        gui.commuteTime.setText("<html><center>" + commute + "</center><html>");
        gui.frame.repaint();
    }
}
