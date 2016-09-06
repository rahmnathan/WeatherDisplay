public class Executor {

    public static void main(String[] args) {

        byte[] backgroundImage = new BackgroundImageProvider().getBackgroundImage();

        CurrentWeather currentWeather = new CurrentWeatherProvider().getCurrentWeather();

        GUI display = new GUI();

        display.startGUI(currentWeather, backgroundImage);

    }
}
