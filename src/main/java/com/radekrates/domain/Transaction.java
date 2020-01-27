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
    @Column(name = "TRANSACTION_FROM")
    private String from;
    @NotNull
    @Column(name = "TRANSACTION_TO")
    private String to;
    @NotNull
    @Column(name = "TRANSACTION_PAIR_IO")
    private String pairIO;
    @NotNull
    @Column(name = "TRANSACTION_INPUT")
    private BigDecimal input;
    @NotNull
    @Column(name = "TRANSACTION_OUTPUT")
    private BigDecimal output;
    @NotNull
    @Column(name = "TRANSACTION_DATE")
    private LocalDate date;
    @NotNull
    @Column(name = "TRANSACTION_SUCCESSFUL")
    private boolean isSuccessful;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Transaction(Long id, String from, String to, String pairIO, BigDecimal input, BigDecimal output,
                       LocalDate date, boolean isSuccessful) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.pairIO = pairIO;
        this.input = input;
        this.output = output;
        this.date = date;

        this.isSuccessful = isSuccessful;
    }
    public Transaction(String from, String to, String pairIO, BigDecimal input, BigDecimal output, LocalDate date,
                       boolean isSuccessful) {
        this.from = from;
        this.to = to;
        this.pairIO = pairIO;
        this.input = input;
        this.output = output;
        this.date = date;
        this.isSuccessful = isSuccessful;
    }
}
