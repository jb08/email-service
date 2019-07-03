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

    @Autowired
    UserService userService;

    /**
     * Get the Messages involving the given participants.
     *
     * @param participants the conversation participants
     * @return the List of Messages
     */
    public List<Message> getConversation(String[] participants) {
        Participants pair = new Participants(participants);
        String participantOne = pair.getParticipantOne();
        String participantTwo = pair.getParticipantTwo();
        userService.findAndValidateUser(participantOne);
        userService.findAndValidateUser(participantTwo);

        return messageDao.getMessages(participantOne, participantTwo);
    }

}
