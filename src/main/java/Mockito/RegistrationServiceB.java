package Mockito;

public class RegistrationServiceB {

    private final EmailServiceB emailService;

    public RegistrationServiceB(EmailServiceB emailService) {
        this.emailService = emailService;
    }

    public void registerNewUser(String username, String email) {
        System.out.println("Saving " + username + " to database...");

        // We want to test that THIS exact line gets executed!
        emailService.sendEmail(email, "Welcome to our platform, " + username + "!");
    }
}