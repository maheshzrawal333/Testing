package JUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountEngineCTest {

    private final DiscountEngineC engine = new DiscountEngineC();

    // Look at this magic! We feed it a table of "Age, ExpectedDiscount"
    @ParameterizedTest(name = "User age {0} should get {1} discount")
    @CsvSource({
            "10, 0.50",   // Child
            "11, 0.50",   // Child edge case
            "35, 0.0",    // Adult
            "65, 0.30",   // Senior edge case
            "80, 0.30"    // Senior
    })
    public void testDiscountLogic(int age, double expectedDiscount) {
        // 1. ACT
        double actualDiscount = engine.calculateDiscount(age);

        // 2. ASSERT
        assertEquals(expectedDiscount, actualDiscount, 0.001); // 0.001 is the allowed math delta for doubles
    }
}
