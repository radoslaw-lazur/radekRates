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
    @Column(name = "IBAN_BANKNAME")
    private String bankName;
    @NotNull
    @Column(name = "IBAN_BANKLOCALISATION")
    private String bankLocalisation;
    @NotNull
    @Column(name = "IBAN_IBANNUMBER")
    private String ibanNumber;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Iban(Long id, String bankName, String bankLocalisation, String ibanNumber) {
        this.id = id;
        this.bankName = bankName;
        this.bankLocalisation = bankLocalisation;
        this.ibanNumber = ibanNumber;
    }
    public Iban(String bankName, String bankLocalisation, String ibanNumber) {
        this.bankName = bankName;
        this.bankLocalisation = bankLocalisation;
        this.ibanNumber = ibanNumber;
    }
}
