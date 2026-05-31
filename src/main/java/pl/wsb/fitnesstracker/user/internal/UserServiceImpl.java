package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already got an ID");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> searchByEmail(String emailFragment) {
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    @Override
    public List<User> getUsersOlderThan(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setBirthdate(updatedUser.getBirthdate());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
}