package nr.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;

class WeatherIconLabel extends JLabel {

    private byte[] image;
    private int size;

    void setImage(byte[] image) {
        this.image = image;
    }

    void setImageSize(int size){
        this.size = size;
    }

    @Override
    public void paintComponent(Graphics g){

        if(image != null) {
            try {
                Image newImage = ImageIO.read(new ByteArrayInputStream(image));
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                graphics2D.drawImage(newImage, 0, 0, size, size, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
