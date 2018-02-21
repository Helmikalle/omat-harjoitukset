package IotDB_test2.util.mappers;

import IotDB_test2.model.Datas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataRowMapper implements RowMapper<Datas> {
    @Override
    public Datas mapRow(ResultSet resultSet, int i) throws SQLException {
        Datas data = new Datas();
        data.setId(resultSet.getString("id"));
        data.setTime(resultSet.getTimestamp("time"));
        data.setValue(resultSet.getFloat("value"));
        return data;
    }
}
