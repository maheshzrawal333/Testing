package Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdvancedMockitoDTest {

    @Mock
    private PaymentRepositoryD mockRepository;

    @InjectMocks
    private PaymentServiceD paymentService;

    // --- CONCEPT 3: The @Spy (The Hybrid Object) ---
    // A @Mock is 100% fake. A @Spy is a REAL object, but we can selectively fake parts of it!
    @Spy
    private List<String> spyList = new ArrayList<>();

    // --- CONCEPT 1 & 2: thenThrow() and anyInt() ---
    @Test
    public void testBankServerCrash() {
        // ARRANGE:
        // 1. anyInt() - "I don't care what amount they pass in."
        // 2. thenThrow() - "Do not return a value. Throw a catastrophic exception instead!"
        when(mockRepository.chargeCard(anyInt())).thenThrow(new RuntimeException("Visa Server Offline!"));

        // ACT: We try to process a payment of $500
        String result = paymentService.processPayment(500);

        // ASSERT: Our code should catch the exception and return our graceful error message.
        assertEquals("SYSTEM_DOWN", result);
    }

    // --- CONCEPT 4: Mocking Static Methods ---
    @Test
    public void testStaticMethodMocking() {
        // ARRANGE: The Bank works perfectly this time.
        when(mockRepository.chargeCard(anyInt())).thenReturn(true);

        // A static method belongs to the Universe, not an object.
        // We MUST use a "try-with-resources" block so the fake UUID generator deletes itself after the test.
        // If we don't, we break the UUID generator for the rest of the application!
        UUID fakeUuid = UUID.fromString("00000000-0000-0000-0000-000000000099");

        try (MockedStatic<UUID> mockedUuidClass = mockStatic(UUID.class)) {

            // Train the static class: "If ANYONE calls randomUUID(), return my fake 99 UUID."
            mockedUuidClass.when(UUID::randomUUID).thenReturn(fakeUuid);

            // ACT
            String result = paymentService.processPayment(100);

            // ASSERT: The dynamic UUID is now completely frozen and predictable!
            assertEquals("RECEIPT-00000000-0000-0000-0000-000000000099", result);
        }
    }

    // --- PROVING THE @SPY ---
    @Test
    public void testSpyHybridBehavior() {
        // 1. REAL BEHAVIOR: We add data, and it actually stores it in RAM.
        spyList.add("Apple");
        spyList.add("Banana");
        assertEquals(2, spyList.size());

        // 2. FAKE BEHAVIOR: We hijack the size() method.
        // Note the syntax switch: When spying, use doReturn().when() instead of when().thenReturn()
        doReturn(999).when(spyList).size();

        // 3. ASSERT: The list only has 2 items, but it lies and says 999!
        assertEquals(999, spyList.size());

        // 4. BUT the real data is still there!
        assertEquals("Apple", spyList.get(0));
    }
}
