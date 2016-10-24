package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProvider;
import nr.commuteprovider.CommuteProvider;
import nr.currentweatherprovider.CurrentWeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

@Component
public class GUI {

    // Data providers

    @Autowired
    @Qualifier("OpenWeather")
    CurrentWeatherProvider currentWeatherProvider;

    @Autowired
    @Qualifier("BingBackground")
    BackgroundImageProvider backgroundImageProvider;

    @Autowired
    @Qualifier("GoogleCommute")
    CommuteProvider commuteProvider;

    // Some layout adjusters

    private final int dateTimeHorizontal = 860;
    private final int dateTimeVertical = 0;
    private final int currentWeatherHorizontal = 220;
    private final int currentWeatherVertical = 20;
    final static int forecastDays = 4;

    // Components of our GUI

    final JFrame frame = new JFrame("Weather Display");
    private final JPanel panel = new JPanel();
    private final Color white = new Color(0xFFFFFF);
    static Map<Integer, Map<String, JLabel>> labelMap = new HashMap<>();

    BackgroundPane backgroundPane = new BackgroundPane();
    WeatherIconLabel currentWeatherIcon = new WeatherIconLabel();
    JLabel currentTemp;
    JLabel highLowTemps;
    JLabel windSpeed;
    JLabel windDirection;
    JLabel currentTime;
    JLabel weatherDescription;
    JLabel currentDate;
    JLabel commuteTime;

    public GUI(){}

    public void startGUI(){

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundPane.setLayout(new GridLayout());

        panel.setOpaque(false);
        panel.setLayout(null);

        currentWeatherIcon.setLocation(0,0);
        currentWeatherIcon.setImageSize(250);
        currentWeatherIcon.setSize(225,225);
        panel.add(currentWeatherIcon);

        currentTemp = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp.setLocation(currentWeatherHorizontal, currentWeatherVertical);
        currentTemp.setSize(220,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 60));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        highLowTemps = new JLabel("High Temp", SwingConstants.CENTER);
        highLowTemps.setLocation(currentWeatherHorizontal + 20, currentWeatherVertical + 70);
        highLowTemps.setSize(170,30);
        highLowTemps.setFont(new Font("Serif", Font.BOLD, 23));
        highLowTemps.setForeground(white);
        panel.add(highLowTemps);

        windSpeed = new JLabel("Wind Info", SwingConstants.CENTER);
        windSpeed.setLocation(currentWeatherHorizontal, currentWeatherVertical + 110);
        windSpeed.setSize(200, 50);
        windSpeed.setFont(new Font("Serif", Font.BOLD, 35));
        windSpeed.setForeground(white);
        panel.add(windSpeed);

        windDirection = new JLabel("Wind Direction", SwingConstants.CENTER);
        windDirection.setLocation(currentWeatherHorizontal, currentWeatherVertical + 170);
        windDirection.setSize(200, 30);
        windDirection.setFont(new Font("Serif", Font.BOLD, 30));
        windDirection.setForeground(white);
        panel.add(windDirection);

        weatherDescription = new JLabel("Weather Descripiton", SwingConstants.CENTER);
        weatherDescription.setSize(400, 170);
        weatherDescription.setLocation(0, 210);
        weatherDescription.setForeground(white);
        weatherDescription.setFont(new Font("Serif", Font.BOLD, 40));
        panel.add(weatherDescription);

        currentTime = new JLabel("Current Time", SwingConstants.CENTER);
        currentTime.setSize(500, 150);
        currentTime.setLocation(dateTimeHorizontal, dateTimeVertical);
        currentTime.setForeground(white);
        currentTime.setFont(new Font("Serif", Font.BOLD, 100));
        panel.add(currentTime);

        currentDate = new JLabel("Current Date", SwingConstants.CENTER);
        currentDate.setSize(400, 110);
        currentDate.setLocation(dateTimeHorizontal + 50, dateTimeVertical + 100);
        currentDate.setFont(new Font("Serif", Font.BOLD, 40));
        currentDate.setForeground(white);
        panel.add(currentDate);

        commuteTime = new JLabel("Commute Time", SwingConstants.CENTER);
        commuteTime.setSize(250, 60);
        commuteTime.setLocation(470, 90);
        commuteTime.setFont(new Font("Serif", Font.BOLD, 40));
        commuteTime.setForeground(white);
        panel.add(commuteTime);

        JLabel commute = new JLabel("Commute", SwingConstants.CENTER);
        commute.setSize(175, 100);
        commute.setLocation(510, 10);
        commute.setFont(new Font("Serif", Font.BOLD, 30));
        commute.setForeground(white);
        panel.add(commute);

        buildForecast();

        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);
    }

    private void buildForecast(){
        int horizontalLocation = 0;
        int verticalLocation = 480;
        int fontSize = 24;

        for(int i = 0; i < forecastDays; i++) {
            Map<String, JLabel> forecastLabelMap = new HashMap<>();

            JLabel day = new JLabel("Day", SwingConstants.CENTER);
            day.setSize(200, 50);
            day.setLocation(horizontalLocation, verticalLocation + 50);
            day.setFont(new Font("Serif", Font.BOLD, fontSize + 3));
            day.setForeground(white);
            forecastLabelMap.put("Day", day);
            panel.add(day);

            WeatherIconLabel forecastIcon = new WeatherIconLabel();
            forecastIcon.setImageSize(180);
            forecastIcon.setSize(180, 180);
            forecastIcon.setLocation(horizontalLocation, verticalLocation + 70);
            forecastLabelMap.put("Icon", forecastIcon);
            panel.add(forecastIcon);

            JLabel highTemp = new JLabel("Temp", SwingConstants.CENTER);
            highTemp.setSize(200, 50);
            highTemp.setLocation(horizontalLocation, verticalLocation + 210);
            highTemp.setFont(new Font("Serif", Font.BOLD, fontSize));
            highTemp.setForeground(white);
            forecastLabelMap.put("Temp", highTemp);
            panel.add(highTemp);

            JLabel description = new JLabel("Description", SwingConstants.CENTER);
            description.setSize(200, 50);
            description.setLocation(horizontalLocation, verticalLocation + 240);
            description.setFont(new Font("Serif", Font.BOLD, fontSize));
            description.setForeground(white);
            forecastLabelMap.put("Description", description);
            panel.add(description);

            JLabel windSpeed = new JLabel("WindSpeed", SwingConstants.CENTER);
            windSpeed.setLocation(horizontalLocation, verticalLocation + 270);
            windSpeed.setSize(200, 50);
            windSpeed.setFont(new Font("Serif", Font.BOLD, fontSize));
            windSpeed.setForeground(white);
            forecastLabelMap.put("WindSpeed", windSpeed);
            panel.add(windSpeed);

            labelMap.put(i, forecastLabelMap);
            horizontalLocation += 180;
        }
    }
}
