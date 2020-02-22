package com.radekrates.service;

import com.radekrates.domain.Iban;
import com.radekrates.domain.User;
import com.radekrates.repository.IbanRepository;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.iban.IbanConflictException;
import com.radekrates.service.exceptions.iban.IbanNotFoundException;
import com.radekrates.service.exceptions.iban.IbanToUserConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class IbanServiceDb {
    private IbanRepository ibanRepository;
    private UserRepository userRepository;

    @Autowired
    public IbanServiceDb(IbanRepository ibanRepository, UserRepository userRepository) {
        this.ibanRepository = ibanRepository;
        this.userRepository = userRepository;
    }

    public Iban saveIban(final Iban iban) {
        if (ibanRepository.existsByIbanNumber(iban.getIbanNumber())) {
            throw new IbanConflictException();
        } else {
            log.info("Iban has been saved in database: " + iban.getIbanNumber());
            return ibanRepository.save(iban);
        }
    }

    public void saveIbanToUser(final String userEmail, final String ibanNumber) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        Iban iban = ibanRepository.findByIbanNumber(ibanNumber).orElseThrow(IbanNotFoundException::new);
        if (iban.getIbanNumber().equals(ibanNumber)) {
            user.getIbans().add(iban);
            iban.setUser(user);
            userRepository.save(user);
            log.info("Iban " + ibanNumber + " has been linked to " + userEmail);
        } else {
            throw new IbanToUserConflictException();
        }
    }

    public void deleteIbanById(final Long ibanId) {
        if (ibanRepository.findById(ibanId).isPresent()) {
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

    public Set<Iban> getIbansRelatedToUser(final String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        log.info("Getting ibans related to: " + userEmail);
        return user.getIbans();
    }

    public void deleteAllIbans() {
        ibanRepository.deleteAll();
    }

    public long countAll() {
        return ibanRepository.count();
    }
}
