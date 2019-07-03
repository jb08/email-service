package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dto.User;
import exception.BadRequest;
import persistence.UserDao;

import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    private static final Faker FAKER = new Faker();

    @Test
    void findAndValidateUser_noUser_throws() {
        String name = FAKER.artist().name();
        when(userDao.find(name)).thenReturn(Optional.empty());

        Exception e = assertThrows(BadRequest.class, () -> userService.findAndValidateUser(name));
        assertEquals(String.format("User not found with name %s", name), e.getMessage());
    }

    @Test
    void findAndValidateUser_userExists_doesntThrow() {
        String name = FAKER.artist().name();
        when(userDao.find(name)).thenReturn(
                Optional.of(
                        User.builder()
                                .name(name)
                                .email(FAKER.internet().emailAddress())
                                .build()));

        userService.findAndValidateUser(name);
    }

}
