package JUnit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartBTest {

    private ShoppingCartB cart;

    // Runs EXACTLY ONCE before anything else. Great for booting up a heavy database.
    @BeforeAll
    public static void globalSetup() {
        System.out.println("--- Booting up Test Suite ---");
    }

    // Runs BEFORE EVERY SINGLE TEST. This guarantees zero test contamination!
    @BeforeEach
    public void setupFreshCart() {
        System.out.println("Creating a brand new, empty cart for the next test.");
        cart = new ShoppingCartB();
    }

    // @DisplayName allows you to write readable names instead of ugly method names
    @Test
    @DisplayName("Adding one item should increase cart size to 1")
    public void testAddItem() {
        cart.addItem("Apple");
        assertEquals(1, cart.getItemCount());
    }

    @Test
    @DisplayName("A fresh cart should always start with 0 items")
    public void testEmptyCart() {
        // Because of @BeforeEach, we know the "Apple" from the previous test isn't here!
        assertEquals(0, cart.getItemCount());
    }

    // Runs AFTER EVERY SINGLE TEST. Great for memory cleanup.
    @AfterEach
    public void cleanUp() {
        System.out.println("Cleaning up resources...\n");
        cart.clear();
    }
}