package Mockito;

public interface PaymentRepositoryD {
    // Connects to a bank (Visa/Mastercard)
    boolean chargeCard(int amount);
}
