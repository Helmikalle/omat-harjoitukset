package IotDB_test2.DAO.daoimpl;

import IotDB_test2.DAO.WeatherDAO;
import IotDB_test2.model.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
@Service
public class WeatherDAOImpl implements WeatherDAO {
    @Override
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
}
