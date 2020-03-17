package com.radekrates.controller.repository;

import com.radekrates.domain.dto.user.*;
import com.radekrates.mapper.UserMapper;
import com.radekrates.service.UserServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class UserController {
    private UserMapper userMapper;
    private UserServiceDb userServiceDb;

    @Autowired
    public UserController(UserMapper userMapper, UserServiceDb userServiceDb) {
        this.userMapper = userMapper;
        this.userServiceDb = userServiceDb;
    }

    @PostMapping(value = "/users")
    public void saveUser(@RequestBody UserDto userDto) {
        userServiceDb.saveUser(userMapper.mapToUser(userDto));
    }

    @PostMapping(value = "/block")
    public void blockUser(@RequestBody UserEmailDto userEmailDto) {
        userServiceDb.blockUser(userEmailDto);
    }

    @PostMapping(value = "/unblock")
    public void unblockUser(@RequestBody UserEmailDto userEmailDto) {
        userServiceDb.unblockUser(userEmailDto);
    }

    @PostMapping(value = "/validate")
    public UserLogInDto getUserByBody(@RequestBody UserLogInDto userLogInDto) {
        return userServiceDb.getUserByBody(userLogInDto);
    }

    @PostMapping(value = "/dataUser")
    public UserLoggedInDto getDataRelatedToUser(@RequestBody UserLogInDto userLogInDto) {
        return userServiceDb.getDataRelatedToUser(userLogInDto);
    }

    @PostMapping(value = "/password")
    public void remindPassword(@RequestBody UserEmailDto userEmailDto) {
        userServiceDb.getUserPassword(userEmailDto.getUserEmail());
    }

    @PutMapping(value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userServiceDb.saveUser(userMapper.mapToUser(userDto)));
    }

    @GetMapping(value = "/activate/{activationCode}")
    public boolean activateUser(@PathVariable String activationCode) {
        return userServiceDb.activateUser(activationCode);
    }

    @GetMapping(value = "/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userMapper.mapToUserDto(userServiceDb.getUserById(userId));
    }

    @GetMapping(value = "/users")
    public Set<UserDto> getUsers() {
        return userMapper.mapToUserDtoSet(userServiceDb.getAllUsers());
    }

    @DeleteMapping(value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userServiceDb.deleteUserById(userId);
    }

    @DeleteMapping(value = "/users")
    public void deleteAllUsers() {
        userServiceDb.deleteAllUsers();
    }
}
