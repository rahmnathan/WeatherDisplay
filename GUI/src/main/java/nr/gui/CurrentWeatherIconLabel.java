package nr.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;

class CurrentWeatherIconLabel extends JLabel {

    private final byte[] image;

    CurrentWeatherIconLabel(byte[] image){
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g){

        try {
            Image newImage = ImageIO.read(new ByteArrayInputStream(image));
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics2D.drawImage(newImage, 0, 0, 250, 250, null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
