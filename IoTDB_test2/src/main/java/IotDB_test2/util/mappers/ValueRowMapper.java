package IotDB_test2.util.mappers;

import IotDB_test2.model.SensorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueRowMapper implements RowMapper<SensorData> {
    @Override
    public SensorData mapRow(ResultSet resultSet, int i) throws SQLException {
        SensorData values = new SensorData();
        values.setValue(resultSet.getFloat("value"));
        return values;
    }
}
