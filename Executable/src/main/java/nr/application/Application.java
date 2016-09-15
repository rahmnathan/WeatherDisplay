package nr.application;

import nr.gui.GUI;
import nr.gui.GUIUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        GUI gui = new GUI();
        GUIUpdater guiUpdater = new GUIUpdater();

        guiUpdater.registerObserver(gui);

        gui.startGUI();

        guiUpdater.startTimedUpdates();

        SpringApplication.run(Application.class, args);
    }
}