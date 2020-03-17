package com.radekrates.service;

import com.radekrates.domain.AdminRatio;
import com.radekrates.repository.AdminRatioRepository;
import com.radekrates.service.generators.UniqueStringChainGenerator;
import com.radekrates.service.transactionfactory.TransactionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Service
public class AdminRatioServiceDb {
    private AdminRatioRepository adminRatioRepository;
    private UniqueStringChainGenerator generatorKey;
    private TransactionFactory transactionFactory;

    @Autowired
    public AdminRatioServiceDb(AdminRatioRepository adminRatioRepository, UniqueStringChainGenerator generatorKey,
                               TransactionFactory transactionFactory) {
        this.adminRatioRepository = adminRatioRepository;
        this.generatorKey = generatorKey;
        this.transactionFactory = transactionFactory;
    }

    public void saveAdminRatio(final AdminRatio adminRatio) {
        String generatedKey = generatorKey.createUniqueStringChain();
        transactionFactory.setTemporaryAdminKey(generatedKey);
        adminRatio.setKey(generatedKey);
        adminRatio.setDate(LocalDate.now());
        adminRatio.setActive(true);
        Set<AdminRatio> adminRatiosSet = adminRatioRepository.findAll();
        adminRatiosSet.forEach(i -> i.setActive(false));
        log.info("Ratios have been saved in database: " + LocalDate.now() + " key: " + generatedKey);
        adminRatioRepository.save(adminRatio);
    }

    public Set<AdminRatio> getRatios() {
        return adminRatioRepository.findAll();
    }

    public void deleteAll() {
        adminRatioRepository.deleteAll();
        log.info("Ratios have been deleted!");
    }
}
