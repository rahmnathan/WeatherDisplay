public class Executor {

    public static void main(String[] args) {

        byte[] backgroundImage = new BackgroundImageProvider().getBackgroundImage();

        GUI display = new GUI();

        display.startGUI(backgroundImage);
    }
}
