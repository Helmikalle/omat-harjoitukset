package IotDB_test2.model;

import java.sql.Timestamp;

public class FetchNewSensorData {
    private String Id;
    private Timestamp timestamp;
    private float data;

    public FetchNewSensorData() {
    }

    public FetchNewSensorData(String id, Timestamp timestamp, float data) {
        Id = id;
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getData() {
        return data;
    }

    public void setData(float valueData) {
        this.data = valueData;
    }

    @Override
    public String toString() {
        return "DummyData{" +
                "Id='" + Id + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
