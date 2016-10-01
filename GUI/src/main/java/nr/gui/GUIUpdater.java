package nr.gui;

import nr.currentweatherprovider.CurrentWeather;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GUIUpdater {

    @Autowired
    private GUI gui;

    @Scheduled(fixedDelay = 5000)
    private void updateDateTime(){
        TimestampAssembler timestampAssembler = new TimestampAssembler();

        if(gui.currentDate != null)
            gui.currentDate.setText(timestampAssembler.getDate());

        if(gui.currentTime != null)
            gui.currentTime.setText(timestampAssembler.getTime());

        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 500000)
    public void updateCurrentWeather(){
        CurrentWeather currentWeather = gui.currentWeatherProvider.getCurrentWeather(gui.currentWeatherCityId);
        gui.currentTemp.setText(currentWeather.getTemp() + "°F");
        gui.windDirection.setText(currentWeather.getWindDirection());
        gui.highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        gui.windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        gui.weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        gui.currentWeatherIcon.setImage(currentWeather.getIcon());
        gui.currentWeatherIcon.repaint();
        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 5000000)
    private void updateBackgroundImage(){
        byte[] backgroundImage = gui.backgroundImageProvider.getBackgroundImage();

        gui.backgroundPane.setBackgroundImage(backgroundImage);
        gui.backgroundPane.repaint();
        gui.frame.repaint();
    }

    @Scheduled(fixedDelay = 500000)
    public void updateCommute() {
        String commute = gui.commuteProvider.getCommuteTime(gui.commuteStartLocation, gui.commuteEndLocation);
        gui.commuteTime.setText("<html><center>" + commute + "</center><html>");
        gui.frame.repaint();
    }
}
