package IotDB_test2.util.mappers;

import IotDB_test2.model.SensorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdRowMapper implements RowMapper<SensorData> {
    @Override
    public SensorData mapRow(ResultSet resultSet, int i) throws SQLException {
        SensorData d = new SensorData();
        d.setId(resultSet.getString("id"));
        return d;
    }
}
