package Mockito;

public interface EmailServiceB {
    // A void method. In real life, this connects to an SMTP server.
    void sendEmail(String emailAddress, String message);
}
