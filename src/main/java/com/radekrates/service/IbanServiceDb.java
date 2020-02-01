package com.radekrates.service;

import com.radekrates.domain.Iban;
import com.radekrates.repository.IbanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class IbanServiceDb {
    @Autowired
    private IbanRepository ibanRepository;

    public Iban saveIban(final Iban iban) {
        log.info("Iban has been saved in database: " + iban.getIbanNumber());
        return ibanRepository.save(iban);
    }
    public void deleteIbanById(final Long ibanId) {
        if (ibanRepository.findById(ibanId).isPresent()) {
            ibanRepository.deleteById(ibanId);
            log.info("Iban has been deleted from database - id: " + ibanId);
        } else {
            log.info("Iban is not present in database - id: " + ibanId);
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
    public void deleteAllIbans() {
        ibanRepository.deleteAll();
    }
    public long countAll() {
        return ibanRepository.count();
    }
    public List<Iban> getIbansByIbanNumber(String ibanNumber) {
        return ibanRepository.findByIbanNumber(ibanNumber);
    }
}
