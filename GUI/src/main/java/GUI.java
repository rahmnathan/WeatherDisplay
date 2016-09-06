import org.apache.commons.lang3.text.WordUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class GUI {

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


    public void startGUI(CurrentWeather currentWeather, byte[] backgroundImage){

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPane = new BackgroundPane(backgroundImage);
        backgroundPane.setLayout(new GridLayout());

        currentWeatherIcon = new CurrentWeatherIconLabel(currentWeather.getIcon());
        currentWeatherIcon.setLocation(10,10);
        currentWeatherIcon.setSize(250,250);

        panel.setOpaque(false);
        panel.setLayout(null);
        panel.add(currentWeatherIcon);

        currentTemp = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp.setText(currentWeather.getTemp() + "°F");
        currentTemp.setLocation(currentWeatherHorizontal, currentWeatherVertical);
        currentTemp.setSize(200,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 70));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        highLowTemps = new JLabel("High Temp", SwingConstants.CENTER);
        highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        highLowTemps.setLocation(currentWeatherHorizontal + 20, currentWeatherVertical + 70);
        highLowTemps.setSize(150,30);
        highLowTemps.setFont(new Font("Serif", Font.BOLD, 20));
        highLowTemps.setForeground(white);
        panel.add(highLowTemps);

        windSpeed = new JLabel("Wind Info", SwingConstants.CENTER);
        windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        windSpeed.setLocation(currentWeatherHorizontal + 15, currentWeatherVertical + 110);
        windSpeed.setSize(200, 50);
        windSpeed.setFont(new Font("Serif", Font.BOLD, 45));
        windSpeed.setForeground(white);
        panel.add(windSpeed);

        windDirection = new JLabel("Wind Direction", SwingConstants.CENTER);
        windDirection.setText(currentWeather.getWindDirection());
        windDirection.setLocation(currentWeatherHorizontal, currentWeatherVertical + 170);
        windDirection.setSize(200, 30);
        windDirection.setFont(new Font("Serif", Font.BOLD, 30));
        windDirection.setForeground(white);
        panel.add(windDirection);

        currentTime = new JLabel("Current Time", SwingConstants.CENTER);
        currentTime.setSize(500, 150);
        currentTime.setLocation(dateTimeHorizontal, dateTimeVertical);
        updateTime();
        currentTime.setForeground(white);
        currentTime.setFont(new Font("Serif", Font.BOLD, 150));
        panel.add(currentTime);

        weatherDescription = new JLabel("Weather Descripiton", SwingConstants.CENTER);
        weatherDescription.setSize(450, 170);
        weatherDescription.setLocation(0, 240);
        weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        weatherDescription.setForeground(white);
        weatherDescription.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(weatherDescription);

        currentDate = new JLabel("Current Date", SwingConstants.CENTER);
        currentDate.setSize(400, 110);
        currentDate.setLocation(dateTimeHorizontal + 50, dateTimeVertical + 130);
        updateDate();
        currentDate.setFont(new Font("Serif", Font.BOLD, 50));
        currentDate.setForeground(white);
        panel.add(currentDate);

        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateTime(){

        String[] dateTime = LocalDateTime.now().toString().split("T");
        String[] timeStampArray = dateTime[1].split(":");
        Integer hour = Integer.valueOf(timeStampArray[0]);
        if(hour > 12) {
            hour -= 12;
        }
        String hourString = String.valueOf(hour);
        String timeStamp = hourString + ":" + timeStampArray[1];

        currentTime.setText(timeStamp);
        repaint();
    }

    public void updateDate(){
        String[] dateTime = LocalDateTime.now().toString().split("T");
        String[] dateArray = dateTime[0].split("-");
        String date = dateArray[1] + "-" + dateArray[2] + "-" + dateArray[0];

        currentDate.setText(date);
        repaint();
    }

    private void repaint(){
        frame.repaint();
    }
}
