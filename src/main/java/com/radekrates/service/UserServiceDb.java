package com.radekrates.service;

import com.radekrates.domain.Log;
import com.radekrates.domain.Mail;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.domain.dto.user.UserLogInDto;
import com.radekrates.domain.dto.user.UserLoggedInDto;
import com.radekrates.mapper.IbanMapper;
import com.radekrates.mapper.TransactionMapper;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.user.UserConflictException;
import com.radekrates.service.exceptions.user.UserDataConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import com.radekrates.service.generators.UniqueStringChainGenerator;
import com.radekrates.service.mail.EmailService;
import com.radekrates.service.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class UserServiceDb {
    private UserRepository userRepository;
    private EmailService emailService;
    private UniqueStringChainGenerator generator;
    private IbanMapper ibanMapper;
    private TransactionMapper transactionMapper;
    private UserValidator userValidator;
    private LogServiceDb logServiceDb;
    private static final String ACTIVATION_LINK = "http://localhost:8080/v1/activate/";

    @Autowired
    public UserServiceDb(UserRepository userRepository, EmailService emailService, UniqueStringChainGenerator generator,
                         IbanMapper ibanMapper, TransactionMapper transactionMapper, UserValidator userValidator,
                         LogServiceDb logServiceDb) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.generator = generator;
        this.ibanMapper = ibanMapper;
        this.transactionMapper = transactionMapper;
        this.userValidator = userValidator;
        this.logServiceDb = logServiceDb;
    }

    public User saveUser(final User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserConflictException();
        } else if (!userValidator.validateUserEmail(user.getEmail()) || !userValidator.validatePassword(user.getPassword())) {
            throw new UserDataConflictException();
        } else {
            String activationCode = generator.createUniqueStringChain();
            user.setActivationCode(activationCode);
            logServiceDb.saveLog(new Log(user.getEmail(), "Saved in database", LocalDateTime.now()));
            log.info("User has been saved in database: " + user.getEmail());
            emailService.sendActivationLink(new Mail(user.getEmail(), "Dear " + user.getUserFirstName() +
                    ", here is your activation link", ACTIVATION_LINK + activationCode), user);
            return userRepository.save(user);
        }
    }


    public boolean activateUser(final String activationCode) {
        User user = userRepository.findByActivationCode(activationCode).orElseThrow(UserNotFoundException::new);
        if (user.getActivationCode().equals(activationCode) && !user.isActive()) {
            user.setActive(true);
            userRepository.save(user);
            logServiceDb.saveLog(new Log(user.getEmail(), "Activation", LocalDateTime.now()));
            log.info(user.getEmail() + " has been activated");
            return true;
        } else {
            throw new UserDataConflictException();
        }
    }

    public void getUserPassword(final String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        if (!user.isBlocked() && user.isActive()) {
            logServiceDb.saveLog(new Log(user.getEmail(), "Forgot password", LocalDateTime.now()));
            emailService.sendForgottenPassword(new Mail(userEmail, user.getUserFirstName() +
                    ", here is your forgotten password", ""), user);
        } else {
            throw  new UserDataConflictException();
        }
    }

    public void blockUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.isActive() && !user.isBlocked() && user.getEmail().equals(userEmailDto.getUserEmail())) {
            user.setBlocked(true);
            logServiceDb.saveLog(new Log(user.getEmail(), "Blocked", LocalDateTime.now()));
            log.info(user.getEmail() + " has been blocked");
        } else {
            throw new UserDataConflictException();
        }
    }

    public void unblockUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.isActive() && user.isBlocked() && user.getEmail().equals(userEmailDto.getUserEmail())) {
            user.setBlocked(false);
            logServiceDb.saveLog(new Log(user.getEmail(), "Unblocked", LocalDateTime.now()));
            log.info(user.getEmail() + " has been unblocked");
        } else {
            throw new UserDataConflictException();
        }
    }

    public UserLoggedInDto getDataRelatedToUser(final UserLogInDto userLogInDto) {
        User user = userRepository.findByEmail(userLogInDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if (user.isActive() && !user.isBlocked() && user.getEmail().equals(userLogInDto.getUserEmail())
                && user.getPassword().equals(userLogInDto.getPassword())) {
            return new UserLoggedInDto(
                    user.getEmail(),
                    user.getUserFirstName(),
                    user.getUserLastName(),
                    ibanMapper.mapToIbanDtoSet(user.getIbans()),
                    transactionMapper.mapToTransactionDtoSet(user.getTransactions())
            );
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

    public UserLogInDto getUserByBody(final UserLogInDto userLogInDto) {
        log.info("Getting user to validate in progress... " + userLogInDto.getUserEmail());
        if (userRepository.existsByEmailAndPassword(userLogInDto.getUserEmail(), userLogInDto.getPassword())) {
            logServiceDb.saveLog(new Log(userLogInDto.getUserEmail(), "Logged in", LocalDateTime.now()));
            log.info(userLogInDto.getUserEmail() + " is validated");
            User user = userRepository.findByEmail(userLogInDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
            return new UserLogInDto(1L, user.getEmail(), user.getPassword());
        } else {
            logServiceDb.saveLog(new Log(userLogInDto.getUserEmail(), "Logging in denied", LocalDateTime.now()));
            log.info(userLogInDto.getUserEmail() + " is not validated");
            throw new UserDataConflictException();
        }
    }

    public Set<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
