package service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.Message;
import exception.BadRequest;
import exception.NotFound;
import persistence.MessageDao;

@Component
public class MessageService {

    @Autowired
    MessageDao messageDao;

    /**
     * Get the Message with the given id.
     *
     * @param messageId the Message id
     * @return the Message
     * @throws NotFound If a Message with the given id does not exist
     */
    public Message getMessage(UUID messageId) {
        Optional<Message> message = messageDao.find(messageId);
        return message.orElseThrow(NotFound::new);
    }

    /**
     * Create the {@code Message}.
     * @param message the message to create
     */
    public Message postMessage(Message message) {
        message.validate();
        Optional<Message> optMessage = messageDao.find(message.getId());
        if (optMessage.isPresent()) {
           throw new BadRequest(String.format("Message already exists with id: %s", message.getId()));
        } else {
            return messageDao.create(message);
        }
    }

    /**
     * Update the {@code Message}.
     * @param message the message to update
     */
    public void putMessage(Message message) {
        message.validate();
        Optional<Message> optMessage = messageDao.find(message.getId());
        if (!optMessage.isPresent()) {
            throw new NotFound();
        } else {
            messageDao.update(message);
        }
    }
}
