package IotDB_test2;

import IotDB_test2.DAO.daoimpl.DatasDAOImpl;
import IotDB_test2.DAO.daoimpl.WeatherDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.MalformedURLException;

@Controller
public class Kontrolli {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private DatasDAOImpl dao;
    @Autowired
    private WeatherDAOImpl wdao;

    @RequestMapping("/kaikki")
    public String getAll(Model model) {
        model.addAttribute("dataa", dao.haeKaikki());
        return "index";
    }

    @RequestMapping("/avg/{id}")
    public String averages(Model model, @PathVariable(name = "id") String id) {
        model.addAttribute("name", id);
        model.addAttribute("average", dao.getAvgValue(id));
        model.addAttribute("rowcount", dao.getRowCount(id));
        return "avg";
    }

    @RequestMapping("/dummy")
    public String daotestin(Model model) {
        try {
            dao.createSensor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("name","iddqd");
        model.addAttribute("dummy",dao.selectAdded("iddqd"));
        model.addAttribute("rowcount",dao.getRowCount("iddqd"));
        return "dummy";
    }

    @RequestMapping("/allsensors")
    public String allAverages(Model model) {
        model.addAttribute("datas", dao.sensorit());
        return "kaikkiavg";
    }

    @RequestMapping("/comparetemp/{id}")
    public String compareTemp(Model model, @PathVariable(name = "id") String id) {
        float datasTemp = dao.getAvgValue(id);
        float dif = 0;
        float weather = 0;
        try {
            weather = wdao.fetchWeatherData().getTemperature();
            if (weather > 0){
                dif = weather + datasTemp;
            } else {
                dif = weather - datasTemp;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        model.addAttribute("weather",weather);
        model.addAttribute("datassensor",id);
        model.addAttribute("sensoravg",datasTemp);
        model.addAttribute("compare", Math.abs(dif));

        return "compareavg";
    }
}
