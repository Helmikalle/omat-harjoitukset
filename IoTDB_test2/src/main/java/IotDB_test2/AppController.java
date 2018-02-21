package IotDB_test2;

import IotDB_test2.DAO.daoimpl.DatasDAOImpl;
import IotDB_test2.util.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class AppController {
    private SensorDataService sDS;
    private DatasDAOImpl dao;


    @Autowired
    public AppController(SensorDataService sDS, DatasDAOImpl dao) {
        this.sDS = sDS;
        this.dao = dao;
    }

    @RequestMapping("/kaikki")
    public String getAll(Model model) {
        model.addAttribute("dataa", dao.findAll());
        return "index";
    }

    @RequestMapping("/avg/{id}")
    public String averages(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("name", id);
        model.addAttribute("average", sDS.averageObj(id));
        return "avg";
    }

    @RequestMapping("/dummy")
    public String fetchSensorDataFromURL(Model model) {

        try {
            dao.insertDataToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("name","iddqd");
        model.addAttribute("dummy",sDS.latestAddedValueForId("iddqd"));
        model.addAttribute("rowcount",sDS.getRowCount("iddqd"));
        return "dummy";
    }

    @RequestMapping("/allsensors")
    public String allAverages(Model model) {
        model.addAttribute("datas", sDS.allSensors());
        return "kaikkiavg";
    }

    @RequestMapping("/comparetemp/{id}")
    public String compareTemp(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("weather",sDS.getLatestTemp());
        model.addAttribute("datassensor",id);
        model.addAttribute("sensoravg",sDS.latestAddedValueForId(id).getValue());
        model.addAttribute("compare", Math.abs(sDS.compareTemperatures(id)));

        return "compareavg";
    }
}
