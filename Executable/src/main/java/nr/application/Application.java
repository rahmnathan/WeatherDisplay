package nr.application;

import nr.gui.GUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static GUI gui;

    public static void main(String[] args) {

        gui = new GUI();

        gui.startGUI();

        SpringApplication.run(Application.class, args);
    }
}