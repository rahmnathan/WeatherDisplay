package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProvider;
import nr.commuteprovider.CommuteProvider;
import nr.currentweather.CurrentWeather;
import nr.currentweatherprovider.CurrentWeatherProvider;
import org.apache.commons.lang3.text.WordUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ServiceLoader;

public class GUI {

    static CurrentWeatherProvider currentWeatherProvider;
    static BackgroundImageProvider backgroundImageProvider;
    static CommuteProvider commuteProvider;

    GUIUpdater guiUpdater = new GUIUpdater();
    ServiceProvider serviceProvider = new ServiceProvider();

    static String commuteStartLocation = "44.94638,-93.328981";
    static String commuteEndLocation = "44.807234,-93.355154";
    static String currentWeatherCityId = "5045021";


    static final JFrame frame = new JFrame("Weather Display");
    static final JPanel panel = new JPanel();
    static final Color white = new Color(0xFFFFFF);

    private static final int dateTimeHorizontal = 1400;
    private static final int dateTimeVertical = 20;
    private static final int currentWeatherHorizontal = 245;
    private static final int currentWeatherVertical = 20;

    static JPanel backgroundPane;
    static JLabel currentWeatherIcon;
    static JLabel currentTemp;
    static JLabel highLowTemps;
    static JLabel windSpeed;
    static JLabel windDirection;
    static JLabel currentTime;
    static JLabel weatherDescription;
    static JLabel currentDate;
    static JLabel commuteTime;

    public GUI(){}

    public void startGUI(){

        serviceProvider.initializeDataProviders();

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        byte[] backgroundImage = backgroundImageProvider.getBackgroundImage();
        backgroundPane = new BackgroundPane(backgroundImage);
        backgroundPane.setLayout(new GridLayout());

        panel.setOpaque(false);
        panel.setLayout(null);

        currentWeatherIcon = new JLabel();
        currentWeatherIcon.setLocation(10,10);
        currentWeatherIcon.setSize(250,250);
        panel.add(currentWeatherIcon);

        currentTemp = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp.setLocation(currentWeatherHorizontal, currentWeatherVertical);
        currentTemp.setSize(200,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 70));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        highLowTemps = new JLabel("High Temp", SwingConstants.CENTER);
        highLowTemps.setLocation(currentWeatherHorizontal + 20, currentWeatherVertical + 70);
        highLowTemps.setSize(150,30);
        highLowTemps.setFont(new Font("Serif", Font.BOLD, 20));
        highLowTemps.setForeground(white);
        panel.add(highLowTemps);

        windSpeed = new JLabel("Wind Info", SwingConstants.CENTER);
        windSpeed.setLocation(currentWeatherHorizontal + 15, currentWeatherVertical + 110);
        windSpeed.setSize(200, 50);
        windSpeed.setFont(new Font("Serif", Font.BOLD, 45));
        windSpeed.setForeground(white);
        panel.add(windSpeed);

        windDirection = new JLabel("Wind Direction", SwingConstants.CENTER);
        windDirection.setLocation(currentWeatherHorizontal, currentWeatherVertical + 170);
        windDirection.setSize(200, 30);
        windDirection.setFont(new Font("Serif", Font.BOLD, 30));
        windDirection.setForeground(white);
        panel.add(windDirection);

        weatherDescription = new JLabel("Weather Descripiton", SwingConstants.CENTER);
        weatherDescription.setSize(450, 170);
        weatherDescription.setLocation(0, 240);
        weatherDescription.setForeground(white);
        weatherDescription.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(weatherDescription);

        currentTime = new JLabel("Current Time", SwingConstants.CENTER);
        currentTime.setSize(500, 150);
        currentTime.setLocation(dateTimeHorizontal, dateTimeVertical);
        currentTime.setForeground(white);
        currentTime.setFont(new Font("Serif", Font.BOLD, 150));
        panel.add(currentTime);

        currentDate = new JLabel("Current Date", SwingConstants.CENTER);
        currentDate.setSize(400, 110);
        currentDate.setLocation(dateTimeHorizontal + 50, dateTimeVertical + 130);
        currentDate.setFont(new Font("Serif", Font.BOLD, 50));
        currentDate.setForeground(white);
        guiUpdater.updateDateTime();
        panel.add(currentDate);

        commuteTime = new JLabel("Commute Time", SwingConstants.CENTER);
        commuteTime.setSize(250, 60);
        commuteTime.setLocation(540, 90);
        commuteTime.setFont(new Font("Serif", Font.BOLD, 50));
        commuteTime.setForeground(white);
        guiUpdater.updateCommute();
        panel.add(commuteTime);

        JLabel nathansCommute = new JLabel("Nathan's Commute", SwingConstants.CENTER);
        nathansCommute.setSize(350, 100);
        nathansCommute.setLocation(500, 10);
        nathansCommute.setFont(new Font("Serif", Font.BOLD, 30));
        nathansCommute.setForeground(white);
        panel.add(nathansCommute);

        guiUpdater.updateCurrentWeather();
        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);

        Timer timeUpdater = new Timer(10000, e -> guiUpdater.updateDateTime());

        Timer currentWeatherUpdater = new Timer(1000000, e -> guiUpdater.updateCurrentWeather());

        Timer backgroundImageUpdater = new Timer(10000000, e -> guiUpdater.updateBackgroundImage());

        Timer commuteUpdater = new Timer(100000, e -> guiUpdater.updateCommute());

        backgroundImageUpdater.start();
        currentWeatherUpdater.start();
        commuteUpdater.start();
        timeUpdater.start();
    }

    public void setCommuteStartLocation(String startLocation){
        this.commuteStartLocation = startLocation;
    }
    public void setCommuteEndLocation(String endLocation){
        this.commuteEndLocation = endLocation;
    }
    public void setCurrentWeatherCityId(String currentWeatherCityId){
        this.currentWeatherCityId = currentWeatherCityId;
    }
    public String getCommuteStartLocation(){
        return commuteStartLocation;
    }
    public String getCommuteEndLocation(){
        return commuteEndLocation;
    }
    public String getCurrentWeatherCityId(){
        return currentWeatherCityId;
    }
}
