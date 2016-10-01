package nr.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

class BackgroundPane extends JPanel {

    private byte[] backgroundImage;

    BackgroundPane(){}

    void setBackgroundImage(byte[] backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (backgroundImage != null) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new ByteArrayInputStream(backgroundImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int x = 0; x < image.getWidth(null); x++) {
                for (int y = 0; y < image.getHeight(null); y++) {
                    Color pixelColor = new Color(image.getRGB(x, y), true);
                    int r = (pixelColor.getRed() + Color.BLACK.getRed()) / 2;
                    int g1 = (pixelColor.getGreen() + Color.BLACK.getGreen()) / 2;
                    int b = (pixelColor.getBlue() + Color.BLACK.getBlue()) / 2;
                    int a = pixelColor.getAlpha();
                    int rgba = (a << 24) | (r << 16) | (g1 << 8) | b;
                    image.setRGB(x, y, rgba);
                }
            }
            g.drawImage(image, 0, 0, this);
        }
    }
}
