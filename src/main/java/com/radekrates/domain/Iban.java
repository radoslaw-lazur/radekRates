package com.radekrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "IBANS")
public class Iban {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "IBAN_ID")
    private Long id;
    @NotNull
    @Column(name = "IBAN_BANK_NAME")
    private String bankName;
    @NotNull
    @Column(name = "IBAN_BANK_LOCALISATION")
    private String bankLocalisation;
    @NotNull
    @Column(name = "IBAN_COUNTRY_CODE")
    private String countryCode;
    @NotNull
    @Column(name = "IBAN_CURRENCY_CODE")
    private String currencyCode;
    @NotNull
    @Column(name = "IBAN_IBAN_NUMBER")
    private String ibanNumber;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Iban(Long id, String bankName, String bankLocalisation, String countryCode, String currencyCode,
                String ibanNumber) {
        this.id = id;
        this.bankName = bankName;
        this.bankLocalisation = bankLocalisation;
        this.countryCode = countryCode;
        this.currencyCode =currencyCode;
        this.ibanNumber = ibanNumber;
    }
    public Iban(String bankName, String bankLocalisation, String countryCode, String currencyCode, String ibanNumber) {
        this.bankName = bankName;
        this.bankLocalisation = bankLocalisation;
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
        this.ibanNumber = ibanNumber;
    }
}
