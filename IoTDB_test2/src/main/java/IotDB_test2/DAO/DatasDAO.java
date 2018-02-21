package IotDB_test2.DAO;


import IotDB_test2.model.Datas;

import java.io.IOException;
import java.util.List;


public interface DatasDAO {
    List<Datas> findAll();
    void insertDataToDB () throws IOException;
    List<Datas> selectAdded(String id);
    List<Datas> sensors();
    List<Datas> valueObj(String id);
    List<Datas> getRowCountById(String id);
}
