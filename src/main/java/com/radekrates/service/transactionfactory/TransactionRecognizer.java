package com.radekrates.service.transactionfactory;

import com.radekrates.domain.AdminRatio;
import com.radekrates.repository.AdminRatioRepository;
import com.radekrates.service.exceptions.transaction.AdminKeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionRecognizer {
    private AdminRatioRepository adminRatioRepository;

    @Autowired
    public TransactionRecognizer(AdminRatioRepository adminRatioRepository) {
        this.adminRatioRepository = adminRatioRepository;
    }

    public BigDecimal recognizeCurrencyRatioPair(String currencyPair, String adminKey) {
        AdminRatio adminRatio = adminRatioRepository.findByKey(adminKey).orElseThrow(AdminKeyNotFoundException::new);
        switch (currencyPair) {
            case "EUR-PLN":
                return adminRatio.getRatioEUR_PLN();
            case "EUR-GBP":
                return adminRatio.getRatioEUR_GBP();
            case "EUR-CHF":
                return adminRatio.getRatioEUR_CHF();
            case "EUR-USD":
                return adminRatio.getRatioEUR_USD();
            case "PLN-EUR":
                return adminRatio.getRatioPLN_EUR();
            case "PLN-GBP":
                return adminRatio.getRatioPLN_GBP();
            case "PLN-CHF":
                return adminRatio.getRatioPLN_CHF();
            case "PLN-USD":
                return adminRatio.getRatioPLN_USD();
            case "GBP-EUR":
                return adminRatio.getRatioGBP_EUR();
            case "GBP-PLN":
                return adminRatio.getRatioGBP_PLN();
            case "GBP-CHF":
                return adminRatio.getRatioGBP_CHF();
            case "GBP-USD":
                return adminRatio.getRatioGBP_USD();
            case "CHF-EUR":
                return adminRatio.getRatioCHF_EUR();
            case "CHF-PLN":
                return adminRatio.getRatioCHF_PLN();
            case "CHF-GBP":
                return adminRatio.getRatioCHF_GBP();
            case "CHF-USD":
                return adminRatio.getRatioCHF_USD();
            case "USD-EUR":
                return adminRatio.getRatioUSD_EUR();
            case "USD-PLN":
                return adminRatio.getRatioUSD_PLN();
            case "USD-GBP":
                return adminRatio.getRatioUSD_GBP();
            case "USD-CHF":
                return adminRatio.getRatioUSD_CHF();
            default:
                return new BigDecimal("1");
        }
    }
}
