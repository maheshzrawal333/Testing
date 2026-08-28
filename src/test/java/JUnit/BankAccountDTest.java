package JUnit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bank Account Specification")
public class BankAccountDTest {

    private BankAccountD account;

    // Parent Setup: Runs before EVERY test in the entire file
    @BeforeEach
    public void setupParent() {
        account = new BankAccountD();
    }

    @Nested
    @DisplayName("When account is brand new")
    class WhenNew {

        @Test
        @DisplayName("Balance should be exactly 0")
        public void testInitialBalance() {
            assertEquals(0, account.getBalance());
        }

        @Test
        @DisplayName("Withdrawing money should throw an error")
        public void testWithdrawFails() {
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(50));
        }
    }

    @Nested
    @DisplayName("When account has sufficient funds")
    class WhenFunded {

        // Child Setup: Runs ONLY for tests inside this specific group!
        // It runs right after the Parent Setup finishes.
        @BeforeEach
        public void setupChild() {
            account.deposit(1000);
        }

        @Test
        @DisplayName("Withdrawing 200 should leave 800")
        public void testWithdrawSuccess() {
            account.withdraw(200);
            assertEquals(800, account.getBalance());
        }
    }
}
