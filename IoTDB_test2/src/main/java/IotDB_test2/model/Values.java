package IotDB_test2.model;

public class Values {
    private float value;

    public Values() {
    }

    public Values(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Values{" +
                "value=" + value +
                '}';
    }
}
