package nr.application;

import nr.backgroundimageprovider.BackgroundImageProvider;
import nr.gui.GUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        byte[] backgroundImage = new BackgroundImageProvider().getBackgroundImage();

        GUI display = new GUI();

        display.startGUI(backgroundImage);

        SpringApplication.run(Application.class, args);
    }
}