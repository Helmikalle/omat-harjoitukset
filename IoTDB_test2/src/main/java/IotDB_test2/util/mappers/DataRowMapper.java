package IotDB_test2.util.mappers;

import IotDB_test2.model.SensorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRowMapper implements RowMapper<SensorData> {
    @Override
    public SensorData mapRow(ResultSet resultSet, int i) throws SQLException {
        SensorData data = new SensorData();
        data.setId(resultSet.getString("id"));
        data.setTime(resultSet.getTimestamp("time"));
        data.setValue(resultSet.getFloat("value"));
        return data;
    }
}
