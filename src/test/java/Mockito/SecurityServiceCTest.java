package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceCTest {

    @Mock
    private AuditRepositoryC mockRepository;

    @InjectMocks
    private SecurityServiceC securityService;

    // 1. Create the Net! We specify exactly what type of object we want to catch.
    @Captor
    private ArgumentCaptor<AuditLogC> logCaptor;

    @Test
    public void testAuditLogIsCreatedOnLogin() {
        // 2. ACT
        securityService.login("Mahesh");

        // 3. CAPTURE
        // We verify the save method was called, but instead of passing an object, we throw the net!
        verify(mockRepository).save(logCaptor.capture());

        // 4. INSPECT
        // We reel the net back in to get the hidden object.
        AuditLogC capturedLog = logCaptor.getValue();

        // 5. ASSERT (Using pure JUnit on the captured object)
        assertEquals("Mahesh", capturedLog.getUsername());
        assertEquals("LOGIN_SUCCESS", capturedLog.getAction());
    }
}
