package Mockito;

import java.time.LocalDateTime;

public class AuditLogC {
    private final String username;
    private final String action;
    private final LocalDateTime timestamp;

    public AuditLogC(String username, String action) {
        this.username = username;
        this.action = action;
        this.timestamp = LocalDateTime.now(); // Dynamic data!
    }

    public String getUsername() { return username; }
    public String getAction() { return action; }
}
