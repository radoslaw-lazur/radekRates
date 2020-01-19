package com.radekrates.mapper;

import com.radekrates.domain.User;
import com.radekrates.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEMail(),
                userDto.getPassword(),
                userDto.getUserFirstName(),
                userDto.getUserLastName(),
                userDto.getAge(),
                userDto.getCountry(),
                userDto.isLoggedIn(),
                userDto.isBlocked()
        );
    }
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getEMail(),
                user.getPassword(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getAge(),
                user.getCountry(),
                user.isLoggedIn(),
                user.isBlocked()
        );
    }
    public Set<UserDto> mapToUserDtoSet(final Set<User> users) {
        return users.stream()
                .map(u -> new UserDto(u.getId(), u.getEMail(), u.getPassword(), u.getUserFirstName(),
                        u.getUserLastName(), u.getAge(), u.getCountry(), u.isLoggedIn(), u.isBlocked()))
                .collect(Collectors.toSet());
    }
}
