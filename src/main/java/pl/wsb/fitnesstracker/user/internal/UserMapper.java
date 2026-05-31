package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

@Component
class UserMapper {

    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    BasicUserDto toBasicUserDto(User user) {
        return new BasicUserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }
}