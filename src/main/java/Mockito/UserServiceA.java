package Mockito;

public class UserServiceA {

    private final UserRepositoryA repository;

    // The Dependency Injection!
    public UserServiceA(UserRepositoryA repository) {
        this.repository = repository;
    }

    public String getUserGreeting(int id) {
        String name = repository.findUserById(id);

        if (name == null) {
            return "User not found!";
        }

        return "Welcome back, " + name.toUpperCase() + "!";
    }
}
