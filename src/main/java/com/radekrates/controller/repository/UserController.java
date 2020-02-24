package com.radekrates.controller.repository;

import com.radekrates.domain.dto.user.*;
import com.radekrates.mapper.UserMapper;
import com.radekrates.service.UserServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private UserMapper userMapper;
    private UserServiceDb userServiceDb;

    @Autowired
    public UserController(UserMapper userMapper, UserServiceDb userServiceDb) {
        this.userMapper = userMapper;
        this.userServiceDb = userServiceDb;
    }

    @PostMapping(value = "saveUser")
    public void saveUser(@RequestBody UserDto userDto) {
        userServiceDb.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userServiceDb.saveUser(userMapper.mapToUser(userDto)));
    }

    @GetMapping(value = "activateUser")
    public void activateUser(@RequestParam String activationCode) {
        userServiceDb.activateUser(activationCode);
    }

    @GetMapping(value = "blockUser")
    public void blockUser(@RequestBody UserEmailDto userEmailDto) {
        userServiceDb.blockUser(userEmailDto);
    }

    @GetMapping(value = "unblockUser")
    public void unblockUser(@RequestBody UserEmailDto userEmailDto) {
        userServiceDb.unblockUser(userEmailDto);
    }

    @GetMapping(value = "logIn")
    public UserLoggedInDto logIn(@RequestBody UserLogInDto userLogInDto) {
        return userServiceDb.logIn(userLogInDto);
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
