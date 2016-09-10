package nr.gui;

import nr.backgroundimageprovider.BackgroundImageProviderInterface;
import nr.commuteprovider.CommuteProvider;
import nr.currentweatherprovider.CurrentWeatherProvider;

import java.util.Iterator;
import java.util.ServiceLoader;

class ServiceProvider {

    void initializeDataProviders(){
        ServiceLoader<CurrentWeatherProvider> currentWeatherProviders = ServiceLoader.load(CurrentWeatherProvider.class);
        Iterator<CurrentWeatherProvider> currentWeatherProviderIterator = currentWeatherProviders.iterator();
        if(!currentWeatherProviderIterator.hasNext()){
            System.out.println("No current weather providers available");
            System.exit(1);
        }
        GUI.currentWeatherProvider = currentWeatherProviderIterator.next();

        ServiceLoader<BackgroundImageProviderInterface> backgroundImageProviders = ServiceLoader.load(BackgroundImageProviderInterface.class);
        Iterator<BackgroundImageProviderInterface> backgroundImageProviderIterator = backgroundImageProviders.iterator();
        if(!backgroundImageProviderIterator.hasNext()){
            System.out.println("No background image provider available");
            System.exit(1);
        }
        GUI.backgroundImageProvider = backgroundImageProviderIterator.next();

        ServiceLoader<CommuteProvider> commuteProviders = ServiceLoader.load(CommuteProvider.class);
        Iterator<CommuteProvider> commuteProviderIterator = commuteProviders.iterator();
        if(!commuteProviderIterator.hasNext()){
            System.out.println("No commute provider available");
            System.exit(1);
        }
        GUI.commuteProvider = commuteProviderIterator.next();
    }
}
