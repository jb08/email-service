package dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

class IdentityUnitTest {

    private final Faker faker = new Faker();
    private String firstName;
    private String middleName;
    private String lastName;

    @BeforeEach
    void setup() {
        firstName = faker.name().firstName();
        middleName = faker.name().lastName();
        lastName = faker.name().lastName();
    }


    @Test
    void full_name_valid() {
        Identity identity = Identity.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build();

        assertEquals(
                String.format("%s %s %s", firstName, middleName, lastName),
                identity.fullname());
    }

    @Test
    void full_name_no_middle() {
        middleName = null;

        Identity identity = Identity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        assertEquals(
                String.format("%s %s", firstName, lastName),
                identity.fullname());
    }
}
