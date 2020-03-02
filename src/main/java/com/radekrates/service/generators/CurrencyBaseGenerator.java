package com.radekrates.service.generators;

import com.radekrates.service.datafixerio.calculation.CurrencyBase;
import com.radekrates.service.datafixerio.calculation.CurrrencyCalculator;
import org.springframework.stereotype.Service;

@Service
public class CurrencyBaseGenerator {
    private CurrrencyCalculator currrencyCalculator;
    private final String EUR = "EUR";
    private final String PLN = "PLN";
    private final String GBP = "GBP";
    private final String CHF = "CHF";
    private final String USD = "USD";


    public CurrencyBaseGenerator(CurrrencyCalculator currrencyCalculator) {
        this.currrencyCalculator = currrencyCalculator;
    }

    public String generateCurrencyBaseString() {
        String eurBase = createEurBaseString(currrencyCalculator.createLiveCurrencyBase(EUR));
        String plnBase = createPlnBaseString(currrencyCalculator.createLiveCurrencyBase(PLN));
        String gbpBase = createGbpBaseString(currrencyCalculator.createLiveCurrencyBase(GBP));
        String chfBase = createChfBaseString(currrencyCalculator.createLiveCurrencyBase(CHF));
        String usdBase = createUsdBaseString(currrencyCalculator.createLiveCurrencyBase(USD));
        return eurBase + plnBase + gbpBase + chfBase + usdBase;
    }

    private String createEurBaseString(CurrencyBase base) {
        String EUR_BASE = "1 EUR - ";
        return "EUR base:" + "\n" +
                EUR_BASE + base.getPln() + " " + PLN + "\n" +
                EUR_BASE + base.getGbp() + " " + GBP + "\n" +
                EUR_BASE + base.getChf() + " " + CHF + "\n" +
                EUR_BASE + base.getUsd() + " " + USD + "\n" + "\n";
    }

    private String createPlnBaseString(CurrencyBase base) {
        String PLN_BASE = "1 PLN - ";
        return "PLN base:" + "\n" +
                PLN_BASE + base.getEur() + " " +  EUR + "\n" +
                PLN_BASE + base.getGbp() + " " +  GBP + "\n" +
                PLN_BASE + base.getChf() + " " +  CHF + "\n" +
                PLN_BASE + base.getUsd() + " " +  USD + "\n" + "\n";
    }

    private String createGbpBaseString(CurrencyBase base) {
        String GBP_BASE = "1 GBP - ";
        return "GBP base:" + "\n" +
                GBP_BASE + base.getEur() + " " +  EUR + "\n" +
                GBP_BASE + base.getPln() + " " +  PLN + "\n" +
                GBP_BASE + base.getChf() + " " +  CHF + "\n" +
                GBP_BASE + base.getUsd() + " " +  USD + "\n" + "\n";
    }

    private String createChfBaseString(CurrencyBase base) {
        String CHF_BASE = "1 CHF - ";
        return "CHF base:" + "\n" +
                CHF_BASE + base.getEur() + " " +  EUR + "\n" +
                CHF_BASE + base.getPln() + " " +  PLN + "\n" +
                CHF_BASE + base.getGbp() + " " +  GBP + "\n" +
                CHF_BASE + base.getUsd() + " " +  USD + "\n" + "\n";
    }

    private String createUsdBaseString(CurrencyBase base) {
        String USD_BASE = "1 USD - ";
        return "USD base:" + "\n" +
                USD_BASE + base.getEur() + " " +  EUR + "\n" +
                USD_BASE + base.getPln() + " " +  PLN + "\n" +
                USD_BASE + base.getGbp() + " " +  GBP + "\n" +
                USD_BASE + base.getChf() + " " +  CHF + "\n" + "\n";
    }
}
