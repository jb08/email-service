package persistence.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import dto.User;
import persistence.UserDao;
import persistence.postgres.mapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    protected NamedParameterJdbcOperations jdbcOperations;
    private UserRowMapper userRowMapper = new UserRowMapper();

    @Override
    public Optional<User> find(String name) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("name", name);
        String select = "SELECT * FROM USERS WHERE name = :name";
        List<User> users = jdbcOperations.query(select, namedParameters, userRowMapper);
        return (users.size() > 0) ? Optional.of(users.get(0)) : Optional.empty();
    }
}
