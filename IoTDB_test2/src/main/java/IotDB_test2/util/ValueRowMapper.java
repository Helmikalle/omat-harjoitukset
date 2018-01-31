package IotDB_test2.util;

import IotDB_test2.model.Values;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Values values = new Values();
        values.setValue(resultSet.getFloat("value"));
        return values;
    }
}
