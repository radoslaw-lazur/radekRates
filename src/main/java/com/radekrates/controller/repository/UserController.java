package com.radekrates.controller.repository;

import com.radekrates.domain.dto.UserDto;
import com.radekrates.mapper.UserMapper;
import com.radekrates.service.UserServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceDb userServiceDb;

    @PostMapping(value = "saveUser")
    public int saveUser(@RequestBody UserDto userDto) {
        if (userServiceDb.isPresent(userMapper.mapToUser(userDto))) {
            return HttpServletResponse.SC_CONFLICT;
        } else {
            userServiceDb.saveUser(userMapper.mapToUser(userDto));
            return HttpServletResponse.SC_CREATED;
        }
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userServiceDb.saveUser(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userServiceDb.deleteUserById(userId);
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return userMapper.mapToUserDto(userServiceDb.getUserById(userId));
    }

    @GetMapping(value = "getUsers")
    public Set<UserDto> getUsers() {
        return userMapper.mapToUserDtoSet(userServiceDb.getAllUsers());
    }

    @DeleteMapping(value = "deleteAllUsers")
    public void deleteAllUsers() {
        userServiceDb.deleteAllUsers();
    }
}
