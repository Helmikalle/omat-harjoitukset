package IotDB_test2.util;

import IotDB_test2.model.Datas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Datas d = new Datas();
        d.setId(resultSet.getString("id"));
        return d;
    }
}
