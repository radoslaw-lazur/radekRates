package com.radekrates.mapper;

import com.radekrates.domain.User;
import com.radekrates.domain.dto.user.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {
    @Autowired
    private UserMapper userMapper;
    private User user;
    private UserDto userDto;

    @Before
    public void init() {
        user = new User(
                1L,
                "email",
                "password",
                "userFirstName",
                "userLastName",
                30,
                "country",
                "activationCode",
                true,
                false
        );
        userDto = new UserDto(
                1L,
                "emailDto",
                "passwordDto",
                "userFirstNameDto",
                "userLastNameDto",
                30,
                "countryDto",
                "activationCodeDto",
                true,
                false
        );
    }

    @Test
    public void testMapToUserDto() {
        //Given
        //When
        UserDto mappedUserDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals(user.getId(), mappedUserDto.getId());
        assertEquals(user.getEmail(), mappedUserDto.getEmail());
        assertEquals(user.getUserFirstName(), mappedUserDto.getUserFirstName());
        assertEquals(user.getUserLastName(), mappedUserDto.getUserLastName());
        assertEquals(user.getAge(), mappedUserDto.getAge());
        assertEquals(user.getCountry(), mappedUserDto.getCountry());
        assertEquals(user.getActivationCode(), mappedUserDto.getActivationCode());
        assertTrue(user.isActive() && mappedUserDto.isActive());
        assertTrue(!user.isBlocked() && !mappedUserDto.isBlocked());
    }

    @Test
    public void testMapToUser() {
        //Given
        //When
        User mappedUser = userMapper.mapToUser(userDto);
        //Then
        assertEquals(userDto.getId(), mappedUser.getId());
        assertEquals(userDto.getEmail(), mappedUser.getEmail());
        assertEquals(userDto.getUserFirstName(), mappedUser.getUserFirstName());
        assertEquals(userDto.getUserLastName(), mappedUser.getUserLastName());
        assertEquals(userDto.getAge(), mappedUser.getAge());
        assertEquals(userDto.getCountry(), mappedUser.getCountry());
        assertEquals(userDto.getActivationCode(), mappedUser.getActivationCode());
        assertTrue(userDto.isActive() && mappedUser.isActive());
        assertFalse(userDto.isBlocked() && mappedUser.isBlocked());
    }

    @Test
    public void testMapToUserDtoSet() {
        //Given
        Set<User> users = new HashSet<>();
        users.add(user);
        //When
        Set<UserDto> userDtos = userMapper.mapToUserDtoSet(users);
        //Then
        assertEquals(users.iterator().next().getId(), userDtos.iterator().next().getId());
        assertEquals(users.iterator().next().getEmail(), userDtos.iterator().next().getEmail());
        assertEquals(users.iterator().next().getUserFirstName(), userDtos.iterator().next().getUserFirstName());
        assertEquals(users.iterator().next().getUserLastName(), userDtos.iterator().next().getUserLastName());
        assertEquals(users.iterator().next().getAge(), userDtos.iterator().next().getAge());
        assertEquals(users.iterator().next().getCountry(), userDtos.iterator().next().getCountry());
        assertEquals(users.iterator().next().getActivationCode(), userDtos.iterator().next().getActivationCode());
        assertTrue(users.iterator().next().isActive() && userDtos.iterator().next().isActive());
        assertFalse(users.iterator().next().isBlocked() && userDtos.iterator().next().isBlocked());
    }
}
