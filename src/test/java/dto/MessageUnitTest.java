package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.BadRequest;

import com.github.javafaker.Faker;

class MessageUnitTest {

    private static final Faker FAKER = new Faker();

    @Test
    void validate_nullId_Throws() {
        Message message = Message.builder()
                .id(null)
                .sender(FAKER.artist().name())
                .recipient(FAKER.artist().name())
                .message(FAKER.company().catchPhrase())
                .build();

        Exception e = assertThrows(BadRequest.class, message::validate);
        assertEquals("Id may not be null", e.getMessage());
    }
}
