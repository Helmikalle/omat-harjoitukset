package IotDB_test2.DAO;

import IotDB_test2.model.WeatherData;

import java.net.MalformedURLException;

public interface WeatherDAO {
    WeatherData fetchWeatherData() throws MalformedURLException;
}
