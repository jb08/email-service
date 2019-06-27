package dto;

import java.time.Instant;
import java.util.UUID;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("Message Dto")
public class Message {
    private UUID id;
    private String sender;
    private String recipient;
    private String message;

    private Instant createdAt;
    private Instant updatedAt;

    public void validate() {
        if (id == null) throw new BadRequest("Id may not be null");
        if (sender == null || sender.isEmpty()) throw new BadRequest("Sender may not be null or empty");
        if (recipient == null || recipient.isEmpty()) throw new BadRequest("Recipient may not be null or empty");
        if (message == null || message.isEmpty()) throw new BadRequest("Message may not be null or empty");
    }
}
