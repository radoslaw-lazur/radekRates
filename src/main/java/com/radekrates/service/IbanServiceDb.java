package com.radekrates.service;

import com.radekrates.domain.Iban;
import com.radekrates.domain.Log;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.iban.IbanToUserDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.repository.IbanRepository;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.iban.IbanConflictException;
import com.radekrates.service.exceptions.iban.IbanDataConflictException;
import com.radekrates.service.exceptions.iban.IbanNotFoundException;
import com.radekrates.service.exceptions.iban.IbanToUserConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import com.radekrates.service.validator.IbanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class IbanServiceDb {
    private IbanRepository ibanRepository;
    private UserRepository userRepository;
    private IbanValidator ibanValidator;
    private LogServiceDb logServiceDb;

    @Autowired
    public IbanServiceDb(IbanRepository ibanRepository, UserRepository userRepository, IbanValidator ibanValidator,
                         LogServiceDb logServiceDb) {
        this.ibanRepository = ibanRepository;
        this.userRepository = userRepository;
        this.ibanValidator = ibanValidator;
        this.logServiceDb = logServiceDb;
    }

    public Iban saveIban(final Iban iban) {
        if (ibanRepository.existsByIbanNumber(iban.getIbanNumber())) {
            throw new IbanConflictException();
        } else if (!ibanValidator.validateIban(iban)) {
            throw new IbanDataConflictException();
        } else {
            log.info("Iban has been saved in database: " + iban.getIbanNumber());
            return ibanRepository.save(iban);
        }
    }

    public Iban updateIban(final Iban iban) {
        if (ibanRepository.existsByIbanNumber(iban.getIbanNumber())) {
            Iban ibanToChange = ibanRepository.findByIbanNumber(iban.getIbanNumber()).orElseThrow(IbanNotFoundException::new);
            ibanToChange.setBankName(iban.getBankName());
            ibanToChange.setBankLocalisation(iban.getBankLocalisation());
            ibanToChange.setCountryCode(iban.getCountryCode());
            ibanToChange.setCurrencyCode(iban.getCurrencyCode());
            log.info("Iban has been updated in database: " + iban.getIbanNumber());
            return ibanRepository.save(ibanToChange);
        } else {
            log.info("Iban has been not found in database: " + iban.getIbanNumber());
            throw new IbanNotFoundException();
        }
    }

    public void saveIbanToUser(final IbanToUserDto ibanToUserDto) {
        User user = userRepository.findByEmail(ibanToUserDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        Iban iban = ibanRepository.findByIbanNumber(ibanToUserDto.getIban()).orElseThrow(IbanNotFoundException::new);
        if (iban.getIbanNumber().equals(ibanToUserDto.getIban()) && user.isActive() && !user.isBlocked()) {
            user.getIbans().add(iban);
            iban.setUser(user);
            userRepository.save(user);
            logServiceDb.saveLog(new Log(user.getEmail(), iban.getIbanNumber() + " is linked", LocalDateTime.now()));
            log.info("Iban " + ibanToUserDto.getIban() + " has been linked to " + ibanToUserDto.getUserEmail());
        } else {
            throw new IbanToUserConflictException();
        }
    }

    public void deleteIbanById(final Long ibanId) {
        if (ibanRepository.findById(ibanId).isPresent()) {
            Iban iban = ibanRepository.findById(ibanId).orElseThrow(IbanNotFoundException::new);
            iban.setUser(null);
            ibanRepository.save(iban);
            ibanRepository.deleteById(ibanId);
            log.info("Iban has been deleted from database - id: " + ibanId);
        } else {
            log.info("Iban is not present in database - id: " + ibanId);
            throw new IbanNotFoundException();
        }
    }

    public Iban getIbanById(final Long ibanId) {
        log.info("Getting iban by id in progress... " + ibanId);
        return ibanRepository.findById(ibanId).orElseThrow(IbanNotFoundException::new);
    }

    public Set<Iban> getAllIbans() {
        log.info("Getting ibans in progress...");
        return ibanRepository.findAll();
    }

    public Set<Iban> getIbansRelatedToUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        log.info("Getting ibans related to: " + userEmailDto.getUserEmail());
        return user.getIbans();
    }

    public void deleteAllIbans() {
        ibanRepository.deleteAll();
    }
}
