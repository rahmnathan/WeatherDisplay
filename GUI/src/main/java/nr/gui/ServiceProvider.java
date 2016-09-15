package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProvider;
import nr.commuteprovider.CommuteProvider;
import nr.currentweatherprovider.CurrentWeatherProvider;

import java.util.Iterator;
import java.util.ServiceLoader;

class ServiceProvider {

    void initializeDataProviders(GUI gui){
        ServiceLoader<CurrentWeatherProvider> currentWeatherProviders = ServiceLoader.load(CurrentWeatherProvider.class);
        Iterator<CurrentWeatherProvider> currentWeatherProviderIterator = currentWeatherProviders.iterator();
        if(!currentWeatherProviderIterator.hasNext()){
            System.out.println("No current weather providers available");
            System.exit(1);
        }
        gui.currentWeatherProvider = currentWeatherProviderIterator.next();

        ServiceLoader<BackgroundImageProvider> backgroundImageProviders = ServiceLoader.load(BackgroundImageProvider.class);
        Iterator<BackgroundImageProvider> backgroundImageProviderIterator = backgroundImageProviders.iterator();
        if(!backgroundImageProviderIterator.hasNext()){
            System.out.println("No background image provider available");
            System.exit(1);
        }
        gui.backgroundImageProvider = backgroundImageProviderIterator.next();

        ServiceLoader<CommuteProvider> commuteProviders = ServiceLoader.load(CommuteProvider.class);
        Iterator<CommuteProvider> commuteProviderIterator = commuteProviders.iterator();
        if(!commuteProviderIterator.hasNext()){
            System.out.println("No commute provider available");
            System.exit(1);
        }
        gui.commuteProvider = commuteProviderIterator.next();
    }
}
