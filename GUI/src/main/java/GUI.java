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

    public static void startGUI(CurrentWeather currentWeather, byte[] backgroundImage){

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPane = new BackgroundPane(backgroundImage);
        backgroundPane.setLayout(new GridLayout());

        JLabel currentWeatherIcon = new JLabel();
        currentWeatherIcon.setIcon(new ImageIcon(currentWeather.getIcon()));
        currentWeatherIcon.setLocation(30,10);
        currentWeatherIcon.setSize(100,100);

        panel.setOpaque(false);
        panel.setLayout(null);
        panel.add(currentWeatherIcon);

        JLabel currentTemp = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp.setText(currentWeather.getTemp() + "°F");
        currentTemp.setLocation(245,20);
        currentTemp.setSize(200,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 70));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        JLabel highLowTemps = new JLabel("High Temp", SwingConstants.CENTER);
        highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        highLowTemps.setLocation(265,90);
        highLowTemps.setSize(150,30);
        highLowTemps.setFont(new Font("Serif", Font.BOLD, 20));
        highLowTemps.setForeground(white);
        panel.add(highLowTemps);

        JLabel windSpeed = new JLabel("Wind Info", SwingConstants.CENTER);
        windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + " Mph");
        windSpeed.setLocation(260, 130);
        windSpeed.setSize(170, 50);
        windSpeed.setFont(new Font("Serif", Font.BOLD, 45));
        windSpeed.setForeground(white);
        panel.add(windSpeed);

        JLabel windDirection = new JLabel("Wind Direction", SwingConstants.CENTER);
        windDirection.setText(currentWeather.getWindDirection());
        windDirection.setLocation(245, 190);
        windDirection.setSize(200, 30);
        windDirection.setFont(new Font("Serif", Font.BOLD, 30));
        windDirection.setForeground(white);
        panel.add(windDirection);

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

        JLabel currentTime = new JLabel("Current Time", SwingConstants.CENTER);
        currentTime.setSize(500, 150);
        currentTime.setLocation(dateTimeHorizontal, dateTimeVertical);
        currentTime.setText(timeStamp);
        currentTime.setForeground(white);
        currentTime.setFont(new Font("Serif", Font.BOLD, 150));
        panel.add(currentTime);

        JLabel weatherDescription = new JLabel("Weather Descripiton", SwingConstants.CENTER);
        weatherDescription.setSize(450, 100);
        weatherDescription.setLocation(0, 240);
        weatherDescription.setText("<html><center>" + WordUtils.capitalize(currentWeather.getSky()) + "</center></html>");
        weatherDescription.setForeground(white);
        weatherDescription.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(weatherDescription);

        JLabel currentDate = new JLabel("Current Date", SwingConstants.CENTER);
        currentDate.setSize(400, 110);
        currentDate.setLocation(dateTimeHorizontal + 50, dateTimeVertical + 130);
        currentDate.setText(date);
        currentDate.setFont(new Font("Serif", Font.BOLD, 50));
        currentDate.setForeground(white);
        panel.add(currentDate);

        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);
    }
}
