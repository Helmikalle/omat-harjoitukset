package IotDB_test2.model;

import java.sql.Timestamp;

public class Datas {
    private String id;
    private Timestamp time;
    private float value;

    public Datas() {
    }

    public Datas(String id, Timestamp time, float value) {
        this.id = id;
        this.time = time;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Datas{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", value=" + value +
                '}';
    }
}
