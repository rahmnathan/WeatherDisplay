import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class GUI {

    private static JFrame frame = new JFrame("Weather Display");
    private static JPanel panel = new JPanel();

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
        currentTemp.setText(currentWeather.getTemp() + "F");
        currentTemp.setLocation(90,120);
        currentTemp.setSize(200,50);
        currentTemp.setFont(new Font("Serif", Font.BOLD, 40));
        currentTemp.setForeground(new Color(0xFFFFFF));
        panel.add(currentTemp);

        JLabel highTemp = new JLabel("High Temp");
        highTemp.setText(currentWeather.getHighTemp() + "F");
        highTemp.setLocation(5,110);
        highTemp.setSize(100,30);
        highTemp.setFont(new Font("Serif", Font.BOLD, 20));
        highTemp.setForeground(new Color(0xFFFFFF));
        panel.add(highTemp);

        JLabel currentTime = new JLabel("Current Time");
        String timeStamp = LocalDateTime.now().toString();
        currentTime.setSize(600, 200);
        currentTime.setLocation(500, 50);
        currentTime.setText(timeStamp);
        currentTime.setForeground(new Color(0xFFFFFF));
        currentTime.setFont(new Font("Serif", Font.BOLD, 80));
        panel.add(currentTime);

        backgroundPane.add(panel);

        frame.add(backgroundPane);
        frame.pack();
        frame.setVisible(true);
    }
}
