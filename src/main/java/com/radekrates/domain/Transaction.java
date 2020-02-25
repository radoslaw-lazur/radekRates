package com.radekrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TRANSACTION_ID")
    private Long id;
    @NotNull
    @Column(name = "TRANSACTION_KEY_CHAIN")
    private String uniqueKeyChain;
    @NotNull
    @Column(name = "TRANSACTION_USER_EMAIL")
    private String userEmail;
    @NotNull
    @Column(name = "TRANSACTION_FROM")
    private String inputIbanNumber;
    @NotNull
    @Column(name = "TRANSACTION_TO")
    private String outputIbanNumber;
    @NotNull
    @Column(name = "TRANSACTION_PAIR_IO")
    private String pairOfCurrencies;
    @NotNull
    @Column(name = "TRANSACTION_INPUT")
    private BigDecimal inputValue;
    @NotNull
    @Column(name = "TRANSACTION_OUTPUT")
    private BigDecimal outputValue;
    @NotNull
    @Column(name = "TRANSACTION_CURRENCY_PURCHASE", columnDefinition = "Decimal(10,4) default '100.0000'")
    private BigDecimal apiCurrencyPurchaseMultiplier;
    @NotNull
    @Column(name = "TRANSACTION_CURRENCY_SALE", columnDefinition = "Decimal(10,4) default '100.0000'")
    private BigDecimal currencySaleMultiplier;
    @NotNull
    @Column(name = "PROFIT")
    private BigDecimal profit;
    @NotNull
    @Column(name = "TRANSACTION_DATE")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Transaction(Long id, String uniqueKeyChain, String userEmail, String inputIbanNumber,
                       String outputIbanNumber, String pairOfCurrencies, BigDecimal inputValue, BigDecimal outputValue,
                       BigDecimal apiCurrencyPurchaseMultiplier, BigDecimal currencySaleMultiplier,
                       BigDecimal profit, LocalDate date) {
        this.id = id;
        this.uniqueKeyChain = uniqueKeyChain;
        this.userEmail = userEmail;
        this.inputIbanNumber = inputIbanNumber;
        this.outputIbanNumber = outputIbanNumber;
        this.pairOfCurrencies = pairOfCurrencies;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
        this.apiCurrencyPurchaseMultiplier = apiCurrencyPurchaseMultiplier;
        this.currencySaleMultiplier = currencySaleMultiplier;
        this.profit = profit;
        this.date = date;
    }
    public Transaction(String uniqueKeyChain, String userEmail, String inputIbanNumber, String outputIbanNumber,
                       String pairOfCurrencies, BigDecimal inputValue, BigDecimal outputValue,
                       BigDecimal apiCurrencyPurchaseMultiplier, BigDecimal currencySaleMultiplier,
                       BigDecimal profit, LocalDate date) {
        this.uniqueKeyChain = uniqueKeyChain;
        this.userEmail = userEmail;
        this.inputIbanNumber = inputIbanNumber;
        this.outputIbanNumber = outputIbanNumber;
        this.pairOfCurrencies = pairOfCurrencies;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
        this.apiCurrencyPurchaseMultiplier = apiCurrencyPurchaseMultiplier;
        this.currencySaleMultiplier = currencySaleMultiplier;
        this.profit = profit;
        this.date = date;
    }
}
