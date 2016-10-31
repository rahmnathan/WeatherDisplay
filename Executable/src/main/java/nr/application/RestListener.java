package nr.application;

import nr.gui.GUIUpdater;
import nr.initialization.PropertyLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RestListener {

    @Autowired
    GUIUpdater guiUpdater;

    @RequestMapping("/commute")
    public void commuteConfig(@RequestParam(value = "start") String startLocation,
                              @RequestParam(value = "end") String endLocation){

        PropertyLoader.setCommuteStartLocation(startLocation);
        PropertyLoader.setCommuteEndLocation(endLocation);
        guiUpdater.updateCommute();
    }

    @RequestMapping("/weather")
    public void weatherConfig(@RequestParam(value = "cityid") String cityId){

        PropertyLoader.setCurrentWeatherCityId(cityId);
        guiUpdater.updateCurrentWeather();
    }
}