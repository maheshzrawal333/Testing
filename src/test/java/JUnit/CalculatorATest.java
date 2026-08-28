package JUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorATest {

    // Test 1: The Happy Path (Everything works perfectly)
    @Test
    public void testAddTwoNumbers() {
        // 1. ARRANGE
        CalculatorA calculator = new CalculatorA();

        // 2. ACT
        int result = calculator.add(5, 7);

        // 3. ASSERT (Expected value goes first, Actual value goes second)
        assertEquals(12, result, "5 + 7 should equal 12");
    }

    // Test 2: Testing Edge Cases (What happens if we divide by zero?)
    @Test
    public void testDivideByZeroThrowsException() {
        // 1. ARRANGE
        CalculatorA calculator = new CalculatorA();

        // 2. ACT & 3. ASSERT combined using assertThrows
        // We are proving that if we pass 0, the system correctly throws an error.
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
        );

        // We can even assert the error message is correct!
        assertEquals("Cannot divide by zero!", exception.getMessage());
    }
}
