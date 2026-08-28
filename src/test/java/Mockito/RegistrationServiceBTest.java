package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceBTest {

    @Mock
    private EmailServiceB mockEmailService;

    @InjectMocks
    private RegistrationServiceB registrationService;

    @Test
    public void testEmailIsSentOnRegistration() {
        // 1. ACT
        // We trigger the registration process
        registrationService.registerNewUser("Mahesh", "mahesh@example.com");

        // 2. ASSERT (The Mockito Way)
        // We ask the Mockito spy: "Did the registration service call sendEmail EXACTLY ONE TIME with these exact strings?"
        verify(mockEmailService, times(1)).sendEmail(
                "mahesh@example.com",
                "Welcome to our platform, Mahesh!"
        );
    }
}
