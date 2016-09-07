package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProvider;
import nr.commuteprovider.CommuteProvider;
import nr.currentweather.CurrentWeather;
import nr.currentweatherprovider.CurrentWeatherProvider;
import org.apache.commons.lang3.text.WordUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.ServiceLoader;

public class GUI {

    private CurrentWeatherProvider currentWeatherProvider;
    private BackgroundImageProvider backgroundImageProvider;
    private CommuteProvider commuteProvider;

    private String commuteStartLocation = "44.94638,-93.328981";
    private String commuteEndLocation = "44.807234,-93.355154";
    private String currentWeatherCityId = "5045021";


    private static JFrame frame = new JFrame("Weather Display");
    private static JPanel panel = new JPanel();
    private static Color white = new Color(0xFFFFFF);

    private static final int dateTimeHorizontal = 1400;
    private static final int dateTimeVertical = 20;
    private static final int currentWeatherHorizontal = 245;
    private static final int currentWeatherVertical = 20;

    private JPanel backgroundPane;
    private JLabel currentWeatherIcon;
    private JLabel currentTemp;
    private JLabel highLowTemps;
    private JLabel windSpeed;
    private JLabel windDirection;
    private JLabel currentTime;
    private JLabel weatherDescription;
    private JLabel currentDate;
    private JLabel commuteTime;

    public GUI(){}

    public void startGUI(){

        initializeDataProviders();

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
        updateDateTime();
        panel.add(currentDate);

        commuteTime = new JLabel("Commute Time", SwingConstants.CENTER);
        commuteTime.setSize(250, 60);
        commuteTime.setLocation(540, 90);
        commuteTime.setFont(new Font("Serif", Font.BOLD, 50));
        commuteTime.setForeground(white);
        updateCommute();
        panel.add(commuteTime);

        JLabel nathansCommute = new JLabel("Nathan's Commute", SwingConstants.CENTER);
        nathansCommute.setSize(350, 100);
        nathansCommute.setLocation(500, 10);
        nathansCommute.setFont(new Font("Serif", Font.BOLD, 30));
        nathansCommute.setForeground(white);
        panel.add(nathansCommute);

        updateCurrentWeather();
        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);

        Timer timeUpdater = new Timer(10000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime();
            }
        });

        Timer currentWeatherUpdater = new Timer(1000000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentWeather();
            }
        });

        Timer backgroundImageUpdater = new Timer(10000000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBackgroundImage();
            }
        });

        Timer commuteUpdater = new Timer(100000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCommute();
            }
        });

        backgroundImageUpdater.start();
        currentWeatherUpdater.start();
        commuteUpdater.start();
        timeUpdater.start();
    }

    public void updateDateTime(){

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

        currentDate.setText(date);

        currentTime.setText(timeStamp);
        frame.repaint();
    }

    public void updateCurrentWeather(){
        CurrentWeather currentWeather = currentWeatherProvider.getCurrentWeather(currentWeatherCityId);
        currentTemp.setText(currentWeather.getTemp() + "°F");
        windDirection.setText(currentWeather.getWindDirection());
        panel.remove(currentWeatherIcon);
        currentWeatherIcon = new CurrentWeatherIconLabel(currentWeather.getIcon());
        currentWeatherIcon.setLocation(10,10);
        currentWeatherIcon.setSize(250,250);
        panel.add(currentWeatherIcon);
        highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        frame.repaint();
    }

    private void updateBackgroundImage(){
        byte[] backgroundImage = backgroundImageProvider.getBackgroundImage();

        frame.remove(backgroundPane);
        backgroundPane = new BackgroundPane(backgroundImage);
        backgroundPane.setLayout(new GridLayout());
        backgroundPane.add(panel);
        frame.add(backgroundPane);
        frame.repaint();
    }

    public void updateCommute(){
        String commute = commuteProvider.getCommuteTime(commuteStartLocation, commuteEndLocation);
        Integer commuteInteger = Integer.valueOf(commute.substring(0, commute.length()-5));
        commuteTime.setText(commute);
        if(commuteInteger < 23){
            commuteTime.setForeground(new Color(0x00DD00));
        } else if (commuteInteger < 30){
            commuteTime.setForeground(new Color(0xC98311));
        } else {
            commuteTime.setForeground(new Color(0xDD0003));
        }
        frame.repaint();
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

    private void initializeDataProviders(){
        ServiceLoader<CurrentWeatherProvider> currentWeatherProviders = ServiceLoader.load(CurrentWeatherProvider.class);
        Iterator<CurrentWeatherProvider> currentWeatherProviderIterator = currentWeatherProviders.iterator();
        if(!currentWeatherProviderIterator.hasNext()){
            System.out.println("No current weather providers available");
            System.exit(1);
        }
        currentWeatherProvider = currentWeatherProviderIterator.next();

        ServiceLoader<BackgroundImageProvider> backgroundImageProviders = ServiceLoader.load(BackgroundImageProvider.class);
        Iterator<BackgroundImageProvider> backgroundImageProviderIterator = backgroundImageProviders.iterator();
        if(!backgroundImageProviderIterator.hasNext()){
            System.out.println("No background image provider available");
            System.exit(1);
        }
        backgroundImageProvider = backgroundImageProviderIterator.next();

        ServiceLoader<CommuteProvider> commuteProviders = ServiceLoader.load(CommuteProvider.class);
        Iterator<CommuteProvider> commuteProviderIterator = commuteProviders.iterator();
        if(!commuteProviderIterator.hasNext()){
            System.out.println("No commute provider available");
            System.exit(1);
        }
        commuteProvider = commuteProviderIterator.next();
    }
}
