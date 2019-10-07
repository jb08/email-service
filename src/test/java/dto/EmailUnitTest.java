package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.buildEmail;

import org.junit.jupiter.api.Test;

import exception.BadRequest;

import com.github.javafaker.Faker;

class EmailUnitTest {

    private final Faker faker = new Faker();

    @Test
    void validate_nullFields_Throws() {
        Email email = buildEmail();

        email.setTo(null);
        Exception e = assertThrows(BadRequest.class, email::validate);
        assertEquals("To may not be null.", e.getMessage());
        email.setTo(faker.internet().emailAddress());

        email.setToName(null);
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals("To_name may not be null.", e.getMessage());
        email.setToName(faker.name().fullName());

        email.setFrom(null);
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals("From may not be null.", e.getMessage());
        email.setFrom(faker.internet().emailAddress());

        email.setFromName(null);
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals("From_name may not be null.", e.getMessage());
        email.setFromName(faker.name().fullName());

        email.setSubject(null);
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals("Subject may not be null.", e.getMessage());
        email.setSubject(faker.company().buzzword());

        email.setBody(null);
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals("Body may not be null.", e.getMessage());
    }

    @Test
    void validate_invalidEmail_Throws() {
        Email email = buildEmail();

        email.setTo(faker.name().lastName());
        Exception e = assertThrows(BadRequest.class, email::validate);
        assertEquals(String.format("Email is invalid: %s.", email.getTo()), e.getMessage());
        email.setTo(faker.internet().emailAddress());

        email.setFrom(faker.name().lastName());
        e = assertThrows(BadRequest.class, email::validate);
        assertEquals(String.format("Email is invalid: %s.", email.getFrom()), e.getMessage());
    }
}
