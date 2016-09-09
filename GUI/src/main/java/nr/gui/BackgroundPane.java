package nr.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;

class BackgroundPane extends JPanel {

    private final byte[] backgroundImage;

    BackgroundPane(byte[] image){
        this.backgroundImage = image;
    }

    @Override
    public void paintComponent(Graphics g){

        Image image = null;

        try {
            image = ImageIO.read(new ByteArrayInputStream(backgroundImage));
        } catch (Exception e){
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, this);
    }
}
