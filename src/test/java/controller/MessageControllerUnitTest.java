package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void postMessage_Created() {
        Message message = spy(Message.builder()
                .sender(FAKER.artist().name())
                .recipient(FAKER.artist().name())
                .message(FAKER.shakespeare().asYouLikeItQuote())
                .build());

        ResponseEntity<Message> response = messageController.postMessage(message);

        verify(message, times(1)).setId(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void putMessage_updatedMessage_NoContent() {
        Message message = spy(Message.builder()
                .id(UUID.randomUUID())
                .sender(FAKER.artist().name())
                .recipient(FAKER.artist().name())
                .message(FAKER.shakespeare().asYouLikeItQuote())
                .build());

        ResponseEntity<Void> response = messageController.putMessage(message.getId(), message);
        verify(message, times(1)).setId(message.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
