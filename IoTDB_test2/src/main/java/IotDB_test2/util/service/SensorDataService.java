package IotDB_test2.util.service;

import IotDB_test2.DAO.daoimpl.DatasDAOImpl;
import IotDB_test2.DAO.daoimpl.WeatherDAOImpl;
import IotDB_test2.model.Averages;
import IotDB_test2.model.Datas;
import IotDB_test2.model.FetchNewSensorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataService{
    @Autowired @Lazy
    private DatasDAOImpl datasDAO;
    @Autowired
    private WeatherDAOImpl weatherDAO;


    public Datas createSensor() throws IOException {
        FetchNewSensorData newSensorData;
        Datas datas = new Datas();
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://dummy-sensors.azurewebsites.net/api/sensor/iddqd");
        try {
            newSensorData = mapper.readValue(url,FetchNewSensorData.class);
            datas.setId(newSensorData.getId());
            datas.setTime(newSensorData.getTimestamp());
            datas.setValue(newSensorData.getData());

        }catch (IOException e) {
            System.out.println("There was an error" + e.getMessage());
        }
        return datas;
    }

    public Averages averageObj(String id) {
        Averages avgObj = new Averages();
        List<Datas> datasList = datasDAO.valueObj(id);
        float avgValue = 0;
        for (Datas avgValue1 : datasList) {
            avgValue += avgValue1.getValue();
        }
        avgObj.setAverageValue(avgValue / datasList.size());
        avgObj.setRowCount(datasList.size());
        return avgObj;
    }

    public List<Averages> allSensors() {
        List<Averages> sensorsAvgList = new ArrayList<>();
        for (Datas aSensorList : datasDAO.sensors()) {
            Averages avg = new Averages();
            avg.setId(aSensorList.getId());
            avg.setAverageValue(getAvgValue(aSensorList.getId()));
            avg.setRowCount(getRowCount(aSensorList.getId()));
            sensorsAvgList.add(avg);
        }
        return sensorsAvgList;
    }
    public int getRowCount(String id) {
        return datasDAO.getRowCountById(id).size();
    }

    public float getAvgValue(String id) {
        float avgValue = 0;
        List<Datas> valueList = datasDAO.valueObj(id);
        for (Datas avgValue1 : valueList) {
            avgValue += avgValue1.getValue();
        }
        return avgValue / valueList.size();
    }

    public float getLatestTemp() {
        float weather = 0;
        try {
            weather = weatherDAO.fetchWeatherData().getTemperature();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return weather;
    }
    public float compareTemperatures(String id){
        float dif;
        float datasTemp = latestAddedValueForId(id).getValue();
        dif = datasTemp - getLatestTemp();
        return dif;
    }
    public Datas latestAddedValueForId(String id) {
        Datas datasObj = new Datas();
        List<Datas> list = datasDAO.selectAdded(id);
        for (Datas dataList:list) {
            datasObj.setValue(dataList.getValue());
            datasObj.setId(dataList.getId());
            datasObj.setTime(dataList.getTime());
        }
        return datasObj;
    }
}
