package IotDB_test2.util.service;

import IotDB_test2.model.FetchNewSensorData;
import IotDB_test2.model.SensorData;
import IotDB_test2.model.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
@Service
public class UrlAccess {
    public WeatherData fetchWeatherData() throws MalformedURLException {
        WeatherData weatherData = new WeatherData();
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://dummy-sensors.azurewebsites.net/api/weather");
        try {
            weatherData = mapper.readValue(url,WeatherData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData;
    }
    public SensorData fetchNewSensorData() throws IOException {
        FetchNewSensorData newSensorData;
        SensorData datas = new SensorData();
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://dummy-sensors.azurewebsites.net/api/sensor/iddqd");
        try {
            newSensorData = mapper.readValue(url,FetchNewSensorData.class);
            datas.setId(newSensorData.getId());
            datas.setTime(newSensorData.getTimestamp());
            datas.setValue(newSensorData.getData());

        }catch (IOException e) {
            System.out.println("There was an error: " + e.getMessage());
        }
        return datas;
    }
}
