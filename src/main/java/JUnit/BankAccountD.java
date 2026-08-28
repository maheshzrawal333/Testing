package JUnit;

public class BankAccountD {
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        // This is the business rule your test is checking for!
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds!");
        }
        this.balance -= amount;
    }
}
