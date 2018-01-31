package IotDB_test2.model;

public class Averages {
    private String id;
    private int rowCount;
    private float averageValue;

    public Averages() {
    }

    public Averages(String id, int rowCount, float averageValue) {
        this.id = id;
        this.rowCount = rowCount;
        this.averageValue = averageValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public float getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(float averageValue) {
        this.averageValue = averageValue;
    }

    @Override
    public String toString() {
        return "Averages{" +
                "id='" + id + '\'' +
                ", rowCount=" + rowCount +
                ", averageValue=" + averageValue +
                '}';
    }
}
