package nr.application;

import nr.gui.GUI;
import nr.gui.GUIUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "nr")
public class Application {

    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");

        ApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(GUI.class).startGUI();
    }
}