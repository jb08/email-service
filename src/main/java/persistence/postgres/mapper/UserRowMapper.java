package persistence.postgres.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dto.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return User.builder()
                .name(rs.getString("NAME"))
                .email(rs.getString("EMAIL"))
                .build();
    }
}
