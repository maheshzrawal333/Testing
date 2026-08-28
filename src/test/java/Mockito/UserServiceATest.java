package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// 1. Tell JUnit to activate the Mockito engine
@ExtendWith(MockitoExtension.class)
public class UserServiceATest {

    // 2. The Hollow Clone (Fake Database)
    @Mock
    private UserRepositoryA mockRepository;

    // 3. The Real Object (Mockito will magically inject the fake database into this!)
    @InjectMocks
    private UserServiceA userService;

    @Test
    public void testUserGreetingSuccess() {
        // 4. ARRANGE (Train the mock!)
        // "If anyone asks for user ID 1, do not connect to a database. Just return 'Mahesh'."
        when(mockRepository.findUserById(1)).thenReturn("Mahesh");

        // 5. ACT
        String result = userService.getUserGreeting(1);

        // 6. ASSERT (Did the service correctly capitalize the mock data?)
        assertEquals("Welcome back, MAHESH!", result);
    }

    @Test
    public void testUserNotFound() {
        // 4. ARRANGE (Train the mock for a failure scenario)
        // "If anyone asks for user ID 99, return null."
        when(mockRepository.findUserById(99)).thenReturn(null);

        // 5. ACT
        String result = userService.getUserGreeting(99);

        // 6. ASSERT
        assertEquals("User not found!", result);
    }
}