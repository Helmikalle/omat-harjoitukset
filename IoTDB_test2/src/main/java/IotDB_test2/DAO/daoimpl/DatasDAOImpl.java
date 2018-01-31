package IotDB_test2.DAO.daoimpl;

import IotDB_test2.DAO.DatasDAO;
import IotDB_test2.model.Averages;
import IotDB_test2.model.Datas;
import IotDB_test2.model.DummyData;
import IotDB_test2.model.Values;
import IotDB_test2.util.DataRowMapper;
import IotDB_test2.util.IdRowMapper;
import IotDB_test2.util.ValueRowMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatasDAOImpl implements DatasDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Averages> sensorit() {
        String sql = "SELECT DISTINCT(id) FROM datas";
        List<Datas> sensoriLista = jdbcTemplate.query(sql,new IdRowMapper());
        List<Averages> sensoritList = new ArrayList<>();

        for (int i = 0; i < sensoriLista.size() ; i++) {
            Averages avg = new Averages();
            avg.setId(sensoriLista.get(i).getId());
            avg.setAverageValue(getAvgValue(sensoriLista.get(i).getId()));
            avg.setRowCount(getRowCount(sensoriLista.get(i).getId()));
            sensoritList.add(avg);
        }
        return sensoritList;
    }

    @Override

    public List<Datas> haeKaikki() {
        String sql = "SELECT * FROM datas limit 10000";
        List<Datas> heaKaikki = jdbcTemplate.query(sql, new DataRowMapper());
        return heaKaikki;
    }
    @Override
    public float getAvgValue(String id) {
        float avgValue = 0;
        String sql = "SELECT value FROM datas where id = ?";
        List<Values> avgValues = jdbcTemplate.query(sql, new Object[]{id}, new ValueRowMapper());
        for (int i = 0; i < avgValues.size(); i++) {
            avgValue += avgValues.get(i).getValue();
        }
        float value = avgValue / avgValues.size();
        return value;
    }
    @Override
    public int getRowCount(String id) {
        String sql = "SELECT id FROM datas WHERE id = ?";
        List<Datas> rowCountList = jdbcTemplate.query(sql, new Object[]{ id }, new IdRowMapper());
        return rowCountList.size();
    }

    @Override
    public void createSensor() throws IOException {
        DummyData datas;
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://dummy-sensors.azurewebsites.net/api/sensor/iddqd");
            try {
                    datas = mapper.readValue(url,DummyData.class);
                    String id = datas.getId();
                    Timestamp timestamp = datas.getTimestamp();
                    float value = datas.getData();
                    jdbcTemplate.update("INSERT INTO datas (id, time, value) VALUES (?,?,?)",
                            new Object[] { id, timestamp, value });
                    System.out.println("saved");
            }catch (IOException e) {
                System.out.println("There was an error" + e.getMessage());
            }
    }

    @Override
    public List<Datas> selectAdded(String id) {
        String sql = "SELECT id,value,time FROM datas WHERE id = ? AND time IN (SELECT MAX(time) FROM datas)";
        List<Datas> lastAdded = jdbcTemplate.query(sql, new Object[] { id }, new DataRowMapper());
        return lastAdded;
    }

    @Override
    public float getLatestValue(String id) {
        String sql = "SELECT id,value,time FROM datas WHERE id = ? AND time IN (SELECT MAX(time) FROM datas WHERE id = ?);";
        List<Values> latestValue = jdbcTemplate.query(sql, new Object[] { id, id }, new ValueRowMapper());

        return latestValue.get(0).getValue();
    }
}
