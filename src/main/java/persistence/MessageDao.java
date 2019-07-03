package persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dto.Message;

public interface MessageDao {

    Optional<Message> find(UUID id);

    List<Message> getMessages(String participantOne, String participantTwo);

    Message create(Message message);

    void update(Message message);
}
