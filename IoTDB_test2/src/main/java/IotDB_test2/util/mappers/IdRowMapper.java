package IotDB_test2.util.mappers;

import IotDB_test2.model.Datas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdRowMapper implements RowMapper<Datas> {
    @Override
    public Datas mapRow(ResultSet resultSet, int i) throws SQLException {
        Datas d = new Datas();
        d.setId(resultSet.getString("id"));
        return d;
    }
}
