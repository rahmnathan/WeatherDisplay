import javax.swing.*;
import java.awt.*;

public class GUI {

    private static JFrame frame = new JFrame("Weather Display");
    private static JLabel currentWeatherIcon = new JLabel();
    private static JPanel panel = new JPanel();

    public static void startGUI(CurrentWeather currentWeather, byte[] backgroundImage){

        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        BackgroundPane backgroundPane = new BackgroundPane(backgroundImage);
        backgroundPane.setLayout(new BorderLayout());
        frame.add(backgroundPane);

        currentWeatherIcon.setIcon(new ImageIcon(currentWeather.getIcon()));
        currentWeatherIcon.setHorizontalAlignment(JLabel.CENTER);
        backgroundPane.add(currentWeatherIcon);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
