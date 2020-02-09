package com.radekrates.service;

import com.radekrates.domain.User;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.user.UserConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
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
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserConflictException();
        } else {
            log.info("User has been saved in database: " + user.getEmail());
            return userRepository.save(user);
        }
    }

    public User getUserEmail(final User user) {
        return userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);
    }

    public boolean isPresent(final User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    public void deleteUserById(final Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            log.info("User has been deleted from database - id: " + userId);
        } else {
            log.info("User is not present in database - id: " + userId);
            throw new UserNotFoundException();
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
}
