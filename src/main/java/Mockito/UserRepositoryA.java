package Mockito;

public interface UserRepositoryA {
    // Reaches into a real SQL database (Slow and heavy)
    String findUserById(int id);
}
