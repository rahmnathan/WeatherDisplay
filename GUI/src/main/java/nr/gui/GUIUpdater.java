package nr.gui;

import com.google.api.services.calendar.model.Event;
import nr.currentweatherprovider.CurrentWeather;
import nr.google.calendar.GoogleCalendar;
import nr.initialization.PropertyLoader;
import nr.weatherforecastprovider.WeatherForecastProvider;
import nr.weatherutils.DayOfWeekProvider;
import nr.openweathermapforecastprovider.OpenWeatherMapForecastProviderFacade;
import nr.weatherforecastprovider.WeatherSummary;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class GUIUpdater {

    @Autowired
    private GUI gui;

    private GoogleCalendar calendar = new GoogleCalendar();

    @Scheduled(fixedDelay = 5000, initialDelay = 2000)
    private void updateDateTime(){
        TimestampAssembler timestampAssembler = new TimestampAssembler();

        if(gui.currentDate != null)
            gui.currentDate.setText(timestampAssembler.getDate());

        if(gui.currentTime != null)
            gui.currentTime.setText(timestampAssembler.getTime());

        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 500000, initialDelay = 2000)
    public void updateCurrentWeather(){
        CurrentWeather currentWeather = gui.currentWeatherProvider.getCurrentWeather(PropertyLoader.getCurrentWeatherCityId(),
                PropertyLoader.getOpenWeatherMapKey());

        gui.currentTemp.setText(currentWeather.getTemp() + "°F");
        gui.windDirection.setText(currentWeather.getWindDirection());
        gui.highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        gui.windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        gui.weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        gui.currentWeatherIcon.setImage(currentWeather.getIcon());
        gui.currentWeatherIcon.repaint();
        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 5000000, initialDelay = 2000)
    private void updateBackgroundImage(){
        byte[] backgroundImage = gui.backgroundImageProvider.getBackgroundImage();
        gui.backgroundPane.setBackgroundImage(backgroundImage);
        gui.backgroundPane.repaint();
        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 500000, initialDelay = 2000)
    public void updateCommute() {
        String commute = gui.commuteProvider.getCommuteTime(PropertyLoader.getCommuteStartLocation(),
                PropertyLoader.getCommuteEndLocation(), PropertyLoader.getGoogleCommuteKey());

        gui.commuteTime.setText("<html><center>" + commute + "</center><html>");
        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 5000000, initialDelay = 2000)
    public void updateForecast(){
        WeatherForecastProvider providerFacade = new OpenWeatherMapForecastProviderFacade();
        List<WeatherSummary> weatherSummaryList = providerFacade.getWeatherForecast(GUI.forecastDays,
                Integer.valueOf(PropertyLoader.getCurrentWeatherCityId()), PropertyLoader.getOpenWeatherMapKey());

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = 0;
        for(WeatherSummary summary : weatherSummaryList) {
            Map<String, JLabel> entries = GUI.labelMap.get(dayOfWeek);

            JLabel day = entries.get("Day");
            calendar.setTime(new Date(summary.getDateTime() * 1000));
            day.setText(DayOfWeekProvider.getDayOfWeekFromInt(calendar.get(Calendar.DAY_OF_WEEK)));
            day.repaint();

            WeatherIconLabel forecastIcon = (WeatherIconLabel) entries.get("Icon");
            forecastIcon.setImage(summary.getIcon());
            forecastIcon.repaint();

            JLabel highTemp = entries.get("Temp");
            highTemp.setText(String.valueOf(summary.getHighTemp()) + "°F/" + summary.getLowTemp() + "°F");
            highTemp.repaint();

            JLabel description = entries.get("Description");
            description.setText(summary.getSky());
            description.repaint();

            JLabel windSpeed = entries.get("WindSpeed");
            windSpeed.setText(String.valueOf(summary.getWindSpeed()) + " MPH");
            windSpeed.repaint();

            dayOfWeek++;
        }
    }

//    @Scheduled(fixedDelay = 10000, initialDelay = 2000)
//    public void updateCalendar(){
//        try {
//            calendar.initializeCalendar();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        Event first = calendar.getEventList().get(0);
//        gui.calendarEvent.setText(first.getStart().getDateTime().toString().substring(0, 10) + "\n" + first.getSummary());
//        gui.frame.repaint();
//    }
}
