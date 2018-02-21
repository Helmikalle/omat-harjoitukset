package IotDB_test2.DAO.daoimpl;

import IotDB_test2.DAO.DatasDAO;
import IotDB_test2.model.Datas;
import IotDB_test2.util.mappers.DataRowMapper;
import IotDB_test2.util.mappers.IdRowMapper;
import IotDB_test2.util.mappers.ValueRowMapper;
import IotDB_test2.util.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class DatasDAOImpl implements DatasDAO {
    @Autowired @Lazy
    private SensorDataService sDS;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Datas> findAll() {
        String sql = "SELECT * FROM datas limit 10000";
        return jdbcTemplate.query(sql, new DataRowMapper());
    }

    @Override
    public void insertDataToDB() throws IOException {
        Datas datas;
        datas = sDS.createSensor();
        jdbcTemplate.update("INSERT INTO datas (id, time, value) VALUES (?,?,?)",
                datas.getId(),datas.getTime(),datas.getValue());
        System.out.println("saved");
    }

    @Override
    public List<Datas> selectAdded(String id) {
        String sql = "SELECT id,value,time FROM datas WHERE id = ? AND time IN (SELECT MAX(time) FROM datas)";
        return jdbcTemplate.query(sql, new DataRowMapper(), id);
    }
    @Override
    public List<Datas> sensors() {
        String sql = "SELECT DISTINCT(id) FROM datas";
        return jdbcTemplate.query(sql,new IdRowMapper());
    }

    @Override
    public List<Datas> valueObj(String id) {
        String sqlQuery = "SELECT value FROM datas WHERE id = ?";
        return jdbcTemplate.query(sqlQuery, new ValueRowMapper(), id);
    }

    @Override
    public List<Datas> getRowCountById(String id) {
        String sql = "SELECT id FROM datas WHERE id = ?";
        return jdbcTemplate.query(sql, new IdRowMapper(), id);
    }
}
