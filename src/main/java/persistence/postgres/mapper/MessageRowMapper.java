package persistence.postgres.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import dto.Message;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Message.builder()
                .id(UUID.fromString(rs.getString("ID")))
                .sender(rs.getString("SENDER"))
                .recipient(rs.getString("RECIPIENT"))
                .message(rs.getString("MESSAGE"))
                .createdAt(rs.getTimestamp("CREATED_AT").toInstant())
                .updatedAt(rs.getTimestamp("UPDATED_AT").toInstant())
                .build();
    }
}
