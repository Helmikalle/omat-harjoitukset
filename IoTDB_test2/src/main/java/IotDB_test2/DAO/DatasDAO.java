package IotDB_test2.DAO;


import IotDB_test2.model.SensorData;

import java.io.IOException;
import java.util.List;


public interface DatasDAO {
    List<SensorData> findAll();
    void insertDataToDB () throws IOException;
    List<SensorData> selectAdded(String id);
    List<SensorData> sensors();
    List<SensorData> valueObj(String id);
    List<SensorData> getRowCountById(String id);
}
