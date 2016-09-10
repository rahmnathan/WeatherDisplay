package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProviderInterface;
import nr.commuteprovider.CommuteProvider;
import nr.currentweatherprovider.CurrentWeatherProvider;

import javax.swing.*;
import java.awt.*;

public class GUI {

    static CurrentWeatherProvider currentWeatherProvider;
    static BackgroundImageProviderInterface backgroundImageProvider;
    static CommuteProvider commuteProvider;

    private final ServiceProvider serviceProvider = new ServiceProvider();

    static String commuteStartLocation = "44.94638,-93.328981";
    static String commuteEndLocation = "44.807234,-93.355154";
    static String currentWeatherCityId = "5045021";


    static final JFrame frame = new JFrame("Weather Display");
    static final JPanel panel = new JPanel();
    private static final Color white = new Color(0xFFFFFF);

    private static final int dateTimeHorizontal = 860;
    private static final int dateTimeVertical = 0;
    private static final int currentWeatherHorizontal = 220;
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
        currentWeatherIcon.setLocation(0,0);
        currentWeatherIcon.setSize(180,180);
        panel.add(currentWeatherIcon);

        currentTemp = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp.setLocation(currentWeatherHorizontal, currentWeatherVertical);
        currentTemp.setSize(200,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 60));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        highLowTemps = new JLabel("High Temp", SwingConstants.CENTER);
        highLowTemps.setLocation(currentWeatherHorizontal + 20, currentWeatherVertical + 70);
        highLowTemps.setSize(150,30);
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
        GUIUpdater.updateDateTime();
        panel.add(currentDate);

        commuteTime = new JLabel("Commute Time", SwingConstants.CENTER);
        commuteTime.setSize(250, 60);
        commuteTime.setLocation(470, 90);
        commuteTime.setFont(new Font("Serif", Font.BOLD, 40));
        commuteTime.setForeground(white);
        GUIUpdater.updateCommute();
        panel.add(commuteTime);

        JLabel commute = new JLabel("Commute", SwingConstants.CENTER);
        commute.setSize(175, 100);
        commute.setLocation(510, 10);
        commute.setFont(new Font("Serif", Font.BOLD, 30));
        commute.setForeground(white);
        panel.add(commute);

        GUIUpdater.updateCurrentWeather();
        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);

        Timer timeUpdater = new Timer(10000, e -> GUIUpdater.updateDateTime());

        Timer currentWeatherUpdater = new Timer(1000000, e -> GUIUpdater.updateCurrentWeather());

        Timer backgroundImageUpdater = new Timer(10000000, e -> GUIUpdater.updateBackgroundImage());

        Timer commuteUpdater = new Timer(100000, e -> GUIUpdater.updateCommute());

        backgroundImageUpdater.start();
        currentWeatherUpdater.start();
        commuteUpdater.start();
        timeUpdater.start();
    }

    public void setCommuteStartLocation(String startLocation){
        commuteStartLocation = startLocation;
    }
    public void setCommuteEndLocation(String endLocation){
        commuteEndLocation = endLocation;
    }
    public void setCurrentWeatherCityId(String CityId) {
        currentWeatherCityId = CityId;
    }
}
