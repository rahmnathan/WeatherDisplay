import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;

public class BackgroundPane extends JPanel {

    private byte[] backgroundImage;

    public BackgroundPane(byte[] image){
        this.backgroundImage = image;
    }

    @Override
    public void paintComponent(Graphics g){

        Image image = null;
//
//        super.paintComponent(g);

        try {
            image = ImageIO.read(new ByteArrayInputStream(backgroundImage));
        } catch (Exception e){
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, this);
    }
}
