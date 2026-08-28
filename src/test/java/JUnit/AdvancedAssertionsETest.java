package JUnit;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedAssertionsETest {

    // 1. assertIterableEquals (The List Matcher)
    @Test
    public void testListGeneration() {
        // Imagine this list came from a Database query
        List<String> actualRoles = Arrays.asList("GUEST", "USER", "ADMIN");
        List<String> expectedRoles = Arrays.asList("GUEST", "USER", "ADMIN");

        // This checks if the lists are exactly the same size, with the exact same items, in the exact same order!
        assertIterableEquals(expectedRoles, actualRoles);
    }

    // 2. assertAll (The Object Inspector)
    @Test
    public void testComplexObject() {
        UserE user = new UserE("John", "Doe", 30);

        // THE PROBLEM with normal assertions: If the first one fails, the test stops immediately.
        // You won't know if the other fields are broken until you fix the first one and re-run.

        // THE SOLUTION: assertAll. It runs EVERY assertion and reports ALL failures at the very end.
        assertAll("User Details Verification",
                () -> assertEquals("John", user.getFirstName()),
                () -> assertEquals("Doe", user.getLastName()),
                () -> assertTrue(user.getAge() >= 18, "User must be an adult")
        );
    }

    // 3. assertTimeout (The Performance Guard)
    @Test
    public void testPerformance() {
        // If this algorithm takes longer than 2 seconds, FAIL the test automatically.
        // This prevents developers from accidentally pushing extremely slow code to production.
        assertTimeout(Duration.ofSeconds(2), () -> {

            // Simulating a heavy calculation...
            Thread.sleep(1000); // Takes 1 second (Test will PASS)

        });
    }
}
