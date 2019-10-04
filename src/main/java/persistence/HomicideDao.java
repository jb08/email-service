package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import dto.HomicideRecord;

@Component
public class HomicideDao {

    @Autowired
    NamedParameterJdbcOperations jdbcOperations;
    HomicideRecordRowMapper homicideRecordRowMapper = new HomicideRecordRowMapper();

    public List<HomicideRecord> getHomicides() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<HomicideRecord> output = jdbcOperations.query("SELECT * FROM homicide_records;", namedParameters, homicideRecordRowMapper);
        return output;
    }

    class HomicideRecordRowMapper implements RowMapper<HomicideRecord> {

        @Override
        public HomicideRecord mapRow(ResultSet rs, int rowNum) throws SQLException {

            return HomicideRecord.builder()
                    .id(rs.getLong("id"))
                    .countyOrArea(rs.getString("country_or_area"))
                    .year(rs.getString("year"))
                    .count(rs.getInt("count"))
                    .rate(rs.getDouble("rate"))
                    .source(rs.getString("source"))
                    .sourceType(rs.getString("source_type"))
                    .build();
        }
    }
}
