package dto;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("Conversation Participants Dto")
public class Participants {
    private String participantOne;
    private String participantTwo;

    public Participants(String[] participants){
        if (participants.length != 2) {
            throw new BadRequest(
                    String.format("Participants must include exactly two names, this included %s.",
                    participants.length));
        }
        participantOne = participants[0];
        participantTwo = participants[1];
    }
}
