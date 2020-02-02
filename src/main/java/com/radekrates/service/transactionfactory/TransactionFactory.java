package com.radekrates.service.transactionfactory;

import com.radekrates.api.datafixerio.calculation.CurrrencyCalculator;
import com.radekrates.api.datafixerio.calculation.PlnBase;
import com.radekrates.domain.Iban;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import com.radekrates.repository.IbanRepository;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.IbanNotFoundException;
import com.radekrates.service.IbanServiceDb;
import com.radekrates.service.UserNotFoundException;
import com.radekrates.service.UserServiceDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransactionFactory {
    @Autowired
    private UserServiceDb userServiceDb;
    @Autowired
    private IbanServiceDb ibanServiceDb;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IbanRepository ibanRepository;
    @Autowired
    private CurrrencyCalculator currrencyCalculator;

    public Transaction createTransaction(String inputIbanNumber, String outputIbanNumber,
                                         String pairOfCurrencies, BigDecimal inputValue) {

        //User userDb = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        Iban inputIbanDb = ibanRepository.findByIbanNumber(inputIbanNumber).orElseThrow(IbanNotFoundException::new);
        Iban outputIbanDb = ibanRepository.findByIbanNumber(outputIbanNumber).orElseThrow(IbanNotFoundException::new);

        if (pairOfCurrencies.equals("PLN-EUR")) {
            PlnBase plnBase = currrencyCalculator.calculatePlnBase();
            BigDecimal purchaseEur = plnBase.getEur();
            BigDecimal saleEur = purchaseEur.multiply(new BigDecimal("1.05"));
            BigDecimal profitEur = saleEur.subtract(purchaseEur);
            return new Transaction(
                    inputIbanDb.getIbanNumber(),
                    outputIbanDb.getIbanNumber(),
                    pairOfCurrencies,
                    inputValue,
                    calculateOutputValue(inputValue, saleEur),
                    plnBase.getEur(),
                    saleEur,
                    profitEur.multiply(inputValue),
                    plnBase.getDate()
            );
        }
        return new Transaction();
    }

    private BigDecimal calculateOutputValue(BigDecimal inputValue, BigDecimal multiplier) {
        return inputValue.multiply(multiplier);
    }
}
