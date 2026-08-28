package JUnit;

public class DiscountEngineC {
    public double calculateDiscount(int age) {
        if (age < 12) return 0.50; // 50% off for kids
        if (age >= 65) return 0.30; // 30% off for seniors
        return 0.0; // No discount for adults
    }
}
