package com.radekrates.service;

import com.radekrates.domain.Mail;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.user.UserActivationDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.user.UserConflictException;
import com.radekrates.service.exceptions.user.UserDataConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import com.radekrates.service.generators.UniqueStringChainGenerator;
import com.radekrates.service.mail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class UserServiceDb {
    private UserRepository userRepository;
    private EmailService emailService;
    private UniqueStringChainGenerator generator;

    @Autowired
    public UserServiceDb(UserRepository userRepository, EmailService emailService, UniqueStringChainGenerator generator) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.generator = generator;
    }

    public User saveUser(final User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserConflictException();
        } else {
            String activationCode = generator.createUniqueStringChain();
            user.setActivationCode(activationCode);
            log.info("User has been saved in database: " + user.getEmail());
            emailService.send(new Mail(user.getEmail(), "Dear " + user.getUserFirstName() +
                    ", here is your activation code", activationCode));
            return userRepository.save(user);
        }
    }

    public void activateUser(final UserActivationDto userActivationDto) {
        User user = userRepository.findByEmail(userActivationDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.getActivationCode().equals(userActivationDto.getActivationCodeFront()) && !user.isActive()) {
            user.setActive(true);
            userRepository.save(user);
            log.info(user.getEmail() + " has been activated");
        } else {
            throw new UserDataConflictException();
        }
    }

    public void blockUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.isActive() && !user.isBlocked() && user.getEmail().equals(userEmailDto.getUserEmail())) {
            user.setBlocked(true);
            log.info(user.getEmail() + " has been blocked");
        } else {
            throw new UserDataConflictException();
        }
    }

    public void unblockUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.isActive() && user.isBlocked() && user.getEmail().equals(userEmailDto.getUserEmail())) {
            user.setBlocked(false);
            log.info(user.getEmail() + " has been unblocked");
        } else {
            throw new UserDataConflictException();
        }
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
