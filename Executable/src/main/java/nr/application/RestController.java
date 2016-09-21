package nr.application;

import nr.gui.GUI;
import nr.gui.GUIUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
class RestController {

    @Autowired
    GUI gui;
    @Autowired
    GUIUpdater guiUpdater;

    @RequestMapping("/commute")
    public void commuteConfig(@RequestParam(value = "start") String startLocation,
                              @RequestParam(value = "end") String endLocation){

        gui.setCommuteStartLocation(startLocation);
        gui.setCommuteEndLocation(endLocation);
        guiUpdater.updateCommute();
    }

    @RequestMapping("/weather")
    public void weatherConfig(@RequestParam(value = "cityid") String cityId){

        gui.setCurrentWeatherCityId(cityId);
        guiUpdater.updateCurrentWeather();
    }
}
