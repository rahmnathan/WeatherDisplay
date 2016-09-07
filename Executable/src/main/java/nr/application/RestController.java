package nr.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/commute")
    public void commuteConfig(@RequestParam(value = "start") String startLocation,
                              @RequestParam(value = "end") String endLocation){

        Application.gui.setCommuteStartLocation(startLocation);
        Application.gui.setCommuteEndLocation(endLocation);
        Application.gui.updateCommute();
    }

    @RequestMapping("/weather")
    public void weatherConfig(@RequestParam(value = "cityid") String cityId){

        Application.gui.setCurrentWeatherCityId(cityId);
        Application.gui.updateCurrentWeather();
    }
}