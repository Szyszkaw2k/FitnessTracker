package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    // ZMIANA: Zwracamy pełne dane dla głównego zapytania GET
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    // ZMIANA: Dodajemy nową ścieżkę /simple z okrojonymi danymi
    @GetMapping("/simple")
    public List<BasicUserDto> getSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toBasicUserDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return userMapper.toUserDto(savedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/email")
    public List<UserEmailDto> searchByEmail(@RequestParam String email) {
        return userProvider.searchByEmail(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    // ZMIANA: Zmieniona zmienna na LocalDate zamiast wieku (int)
    @GetMapping("/older/{time}")
    public List<UserDto> searchUsersOlderThan(@PathVariable LocalDate time) {
        return userProvider.getUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
        return userMapper.toUserDto(updatedUser);
    }
}