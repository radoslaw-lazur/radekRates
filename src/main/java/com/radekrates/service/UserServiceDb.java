package com.radekrates.service;

import com.radekrates.domain.User;
import com.radekrates.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class UserServiceDb {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(final User user) {
        log.info("User has been saved in database: " + user.getEmail());
        return userRepository.save(user);
    }
    public void deleteUserById(final Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            log.info("User has been deleted from database - id: " + userId);
        } else {
            log.info("User is not present in database - id: " + userId);
        }
    }
    public User getUserById(final Long userId) {
        log.info("Getting user by id in progress... " + userId);
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public Set<User> getAllUsers() {
        log.info("Getting users in progress...");
        return userRepository.findAll();
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
    public long countAll() {
        return userRepository.count();
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
