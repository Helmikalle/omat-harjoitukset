package IotDB_test2.model;

import java.sql.Timestamp;

public class WeatherData {
    private float latitude;
    private float longitude;
    private Timestamp time;
    private float temperature;
    private String units;

    public WeatherData() {
    }

    public WeatherData(float latitude, float longitude, Timestamp time, float temperature, String units) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.temperature = temperature;
        this.units = units;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                ", temperature=" + temperature +
                ", units='" + units + '\'' +
                '}';
    }
}
