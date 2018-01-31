package IotDB_test2.DAO;


import IotDB_test2.model.Averages;
import IotDB_test2.model.Datas;

import java.io.IOException;
import java.util.List;


public interface DatasDAO {
    List<Averages> sensorit();
    List<Datas> haeKaikki();
    float getAvgValue(String id);
    int getRowCount(String id);
    void createSensor() throws IOException;
    List<Datas> selectAdded(String id);
    float getLatestValue(String id);
}
