package com.radekrates.service.validator;

import com.radekrates.domain.Iban;
import org.springframework.stereotype.Service;

@Service
public class IbanValidator {
    protected boolean isFirstLetter;
    protected boolean isSecondLetter;

    public boolean validateIban(final Iban iban) {
        return (iban.getCurrencyCode().equals("EUR") || iban.getCurrencyCode().equals("PLN") ||
                iban.getCurrencyCode().equals("GBP") || iban.getCurrencyCode().equals("CHF") ||
                iban.getCurrencyCode().equals("USD")) && (iban.getIbanNumber().length() == 20
                && iban.getCountryCode().length() == 2 && validateLetter(iban.getIbanNumber()));
    }

    private boolean validateLetter(String validatedIbanNumber) {
        int countedLetters = 0;
        for (int i = 0; i < validatedIbanNumber.length(); i++) {
            if (Character.isLetter(validatedIbanNumber.charAt(i))) {
                countedLetters++;
            }
        }
        isFirstLetter = Character.isLetter(validatedIbanNumber.charAt(0));
        isSecondLetter = Character.isLetter(validatedIbanNumber.charAt(1));
        return countedLetters == 2 && isFirstLetter && isSecondLetter;
    }
}





