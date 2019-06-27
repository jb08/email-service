package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.Message;
import dto.Participants;
import persistence.MessageDao;

@Component
public class ConversationService {

    @Autowired
    MessageDao messageDao;

    /**
     * Get the Messages involving the given participants.
     *
     * @param participants the conversation participants
     * @return the List of Messages
     */
    public List<Message> getConversation(String[] participants) {
        Participants pair = new Participants(participants);
        return messageDao.getMessages(pair.getParticipantOne(), pair.getParticipantTwo());
    }

}
