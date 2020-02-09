package com.radekrates.mapper;

import com.radekrates.domain.User;
import com.radekrates.domain.dto.user.UserDto;
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
                userDto.isActive(),
                userDto.isBlocked()
        );
    }
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getAge(),
                user.getCountry(),
                user.isActive(),
                user.isBlocked()
        );
    }
    public Set<UserDto> mapToUserDtoSet(final Set<User> users) {
        return users.stream()
                .map(u -> new UserDto(u.getId(), u.getEmail(), u.getPassword(), u.getUserFirstName(),
                        u.getUserLastName(), u.getAge(), u.getCountry(), u.isActive(), u.isBlocked()))
                .collect(Collectors.toSet());
    }
}
