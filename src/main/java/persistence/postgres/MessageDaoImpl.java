package persistence.postgres;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import dto.Message;
import exception.NotFound;
import persistence.MessageDao;
import persistence.postgres.mapper.MessageRowMapper;

@Component
public class MessageDaoImpl implements MessageDao {

    @Autowired
    protected NamedParameterJdbcOperations jdbcOperations;
    private MessageRowMapper messageRowMapper = new MessageRowMapper();

    @Override
    public Optional<Message> find(UUID id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        String select = "SELECT * FROM MESSAGES WHERE id = :id";
        List<Message> messages = jdbcOperations.query(select, namedParameters, messageRowMapper);
        return (messages.size() > 0) ? Optional.of(messages.get(0)) : Optional.empty();
    }

    @Override
    public List<Message> getMessages(String participantOne, String participantTwo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("participantOne", participantOne)
                .addValue("participantTwo", participantTwo);
        String select = "SELECT * " +
                "FROM MESSAGES " +
                "WHERE (sender = :participantOne AND recipient = :participantTwo) " +
                "OR (sender = :participantTwo AND recipient = :participantOne) " +
                "ORDER BY created_at ASC";
        List<Message> messages = jdbcOperations.query(select, namedParameters, messageRowMapper);
        return messages;
    }

    @Override
    public Message create(Message message) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(message);
        String insert = "INSERT INTO MESSAGES VALUES (:id, :sender, :recipient, :message)";
        jdbcOperations.update(insert, namedParameters);
        return find(message.getId()).orElseThrow(NotFound::new);
    }

    @Override
    public void update(Message message) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(message);
        String update = "UPDATE MESSAGES " +
                "SET sender = :sender, recipient = :recipient, message = :message, updated_at = clock_timestamp() " +
                "WHERE id = :id";
        jdbcOperations.update(update, namedParameters);
    }
}
