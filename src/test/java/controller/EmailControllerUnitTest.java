package controller;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static utils.TestUtils.buildEmail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dto.Email;
import service.EmailService;

@ExtendWith(MockitoExtension.class)
class EmailControllerUnitTest {

    @Mock
    EmailService emailService;

    @InjectMocks
    EmailController emailController;

    @Test
    void post_callsEmailService_Valid() {
        Email email = spy(buildEmail());
        emailController.post(email);
        verify(email, times(1)).validate();
        verify(emailService, times(1)).sendEmail(email);
    }
}
