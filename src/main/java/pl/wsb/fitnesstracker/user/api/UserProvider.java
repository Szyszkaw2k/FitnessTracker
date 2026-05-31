package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {
    List<User> findAllUsers();
    Optional<User> getUser(Long id);
    List<User> searchByEmail(String emailFragment);
    List<User> getUsersOlderThan(LocalDate date);
}
