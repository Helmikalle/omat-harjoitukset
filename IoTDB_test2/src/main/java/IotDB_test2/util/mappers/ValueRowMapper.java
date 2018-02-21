package IotDB_test2.util.mappers;

import IotDB_test2.model.Datas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValueRowMapper implements RowMapper<Datas> {
    @Override
    public Datas mapRow(ResultSet resultSet, int i) throws SQLException {
        Datas values = new Datas();
        values.setValue(resultSet.getFloat("value"));
        return values;
    }
}
