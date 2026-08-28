package Mockito;

public class SecurityServiceC {

    private final AuditRepositoryC repository;

    public SecurityServiceC(AuditRepositoryC repository) {
        this.repository = repository;
    }

    public void login(String username) {
        System.out.println("Authenticating user: " + username);

        // This object is hidden inside the method! How do we test its contents?
        AuditLogC log = new AuditLogC(username, "LOGIN_SUCCESS");

        repository.save(log);
    }
}
