package IotDB_test2;

import IotDB_test2.util.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class AppController {
    private SensorDataService sensorDataService;

    @Autowired
    public AppController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;

    }

    @RequestMapping("/kaikki")
    public String getAll(Model model) {
        model.addAttribute("dataa", sensorDataService.findAll());
        return "index";
    }

    @RequestMapping("/avg/{id}")
    public String averages(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("name", id);
        model.addAttribute("average", sensorDataService.averageTempAndRowCount(id));
        return "avg";
    }

    @RequestMapping("/dummy")
    public String fetchSensorDataFromURL(Model model) {

        try {
            sensorDataService.insertNewData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("name","iddqd");
        model.addAttribute("dummy", sensorDataService.latestAddedValueForId("iddqd"));
        model.addAttribute("rowcount", sensorDataService.getRowCount("iddqd"));
        return "dummy";
    }

    @RequestMapping("/allsensors")
    public String allAverages(Model model) {
        model.addAttribute("datas", sensorDataService.allSensors());
        return "kaikkiavg";
    }

    @RequestMapping("/comparetemp/{id}")
    public String compareTemp(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("weather", sensorDataService.getLatestTemp());
        model.addAttribute("datassensor",id);
        model.addAttribute("sensoravg", sensorDataService.latestAddedValueForId(id).getValue());
        model.addAttribute("compare", Math.abs(sensorDataService.compareTemperatures(id)));

        return "compareavg";
    }
}
