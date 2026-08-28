package Mockito;

import java.util.UUID;

public class PaymentServiceD {

    private final PaymentRepositoryD repository;

    public PaymentServiceD(PaymentRepositoryD repository) {
        this.repository = repository;
    }

    public String processPayment(int amount) {
        try {
            // Attempt to charge the card
            repository.chargeCard(amount);

            // If successful, generate a unique receipt using a STATIC method
            return "RECEIPT-" + UUID.randomUUID().toString();

        } catch (RuntimeException e) {
            // If the bank server crashes, fail gracefully
            return "SYSTEM_DOWN";
        }
    }
}