import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class GUI {

    private static JFrame frame = new JFrame("Weather Display");
    private static JPanel panel = new JPanel();
    private static Color white = new Color(0xFFFFFF);
    private static final int dateTimeHorizontal = 1370;
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

        JLabel currentTemp = new JLabel("Current Temp");
        currentTemp.setText(currentWeather.getTemp() + "°F");
        currentTemp.setLocation(230,20);
        currentTemp.setSize(200,60);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 70));
        currentTemp.setForeground(white);
        panel.add(currentTemp);

        JLabel highLowTemps = new JLabel("High Temp");
        highLowTemps.setText(currentWeather.getLowTemp().split("\\.")[0] + "°F/" + currentWeather.getHighTemp().split("\\.")[0] + "°F");
        highLowTemps.setLocation(265,90);
        highLowTemps.setSize(150,30);
        highLowTemps.setFont(new Font("Serif", Font.BOLD, 20));
        highLowTemps.setForeground(white);
        panel.add(highLowTemps);

        JLabel windSpeed = new JLabel("Wind Info");
        windSpeed.setText(currentWeather.getWindSpeed().split("\\.")[0] + "Mph");
        windSpeed.setLocation(250, 130);
        windSpeed.setSize(150, 50);
        windSpeed.setFont(new Font("Serif", Font.BOLD, 45));
        windSpeed.setForeground(white);
        panel.add(windSpeed);

        JLabel windDirection = new JLabel("Wind Direction");
        windDirection.setText(currentWeather.getWindDirection());
        windDirection.setLocation(270, 190);
        windDirection.setSize(200, 30);
        windDirection.setFont(new Font("Serif", Font.BOLD, 30));
        windDirection.setForeground(white);
        panel.add(windDirection);

        String[] dateTime = LocalDateTime.now().toString().split("T");
        String[] timeStampArray = dateTime[1].split(":");
        Integer hour = Integer.valueOf(timeStampArray[0]);
        String hourString;
        if(hour > 12){
            hour -= 12;
            hourString = "0" + hour;
        } else{
            hourString = String.valueOf(hour);
        }
        String timeStamp = hourString + ":" + timeStampArray[1];
        String[] dateArray = dateTime[0].split("-");
        String date = dateArray[1] + "-" + dateArray[2] + "-" + dateArray[0];

        JLabel currentTime = new JLabel("Current Time");
        currentTime.setSize(500, 200);
        currentTime.setLocation(dateTimeHorizontal, dateTimeVertical);
        currentTime.setText(timeStamp);
        currentTime.setForeground(white);
        currentTime.setFont(new Font("Serif", Font.BOLD, 150));
        panel.add(currentTime);

        JLabel currentDate = new JLabel("Current Date");
        currentDate.setSize(500, 200);
        currentDate.setLocation(dateTimeHorizontal + 85, dateTimeVertical + 110);
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
