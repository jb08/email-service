package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dto.Message;
import service.MessageService;
import com.github.javafaker.Faker;


@ExtendWith(MockitoExtension.class)
class MessageControllerUnitTest {

    private static final Faker FAKER = new Faker();

    @Mock
    MessageService messageService;

    @InjectMocks
    MessageController messageController;

    @Test
    void getMessage_callsService() {
        UUID messageId = UUID.randomUUID();
        messageController.getMessage(messageId);
        Mockito.verify(messageService, times(1)).getMessage(messageId);
    }

    @Test
    void putMessage_newMessage_Created() {
        Message message = Message.builder()
                .id(UUID.randomUUID())
                .sender(FAKER.artist().name())
                .recipient(FAKER.artist().name())
                .message(FAKER.shakespeare().asYouLikeItQuote())
                .build();

        when(messageService.putMessage(message)).thenReturn(false);
        ResponseEntity<Void> response = messageController.putMessage(message.getId(), message);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void putMessage_updatedMessage_NoContent() {
        Message message = Message.builder()
                .id(UUID.randomUUID())
                .sender(FAKER.artist().name())
                .recipient(FAKER.artist().name())
                .message(FAKER.shakespeare().asYouLikeItQuote())
                .build();

        when(messageService.putMessage(message)).thenReturn(true);
        ResponseEntity<Void> response = messageController.putMessage(message.getId(), message);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
