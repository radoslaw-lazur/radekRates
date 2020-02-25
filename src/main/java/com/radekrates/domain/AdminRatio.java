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
@Entity(name = "ADMINRATIOS")
public class AdminRatio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ADMINRATIOS_ID")
    private Long id;
    @Column(name = "ADMINRATIOS_KEY")
    private String key;
    @Column(name = "ADMINRATIOS_DATE")
    private LocalDate date;
    @Column(name = "ADMINRATIOS_ACTIVE")
    private boolean active;
    @NotNull
    @Column(name = "ADMINRATIOS_EURPLN")
    private BigDecimal ratioEUR_PLN;
    @NotNull
    @Column(name = "ADMINRATIOS_EURGBP")
    private BigDecimal ratioEUR_GBP;
    @NotNull
    @Column(name = "ADMINRATIOS_EURCHF")
    private BigDecimal ratioEUR_CHF;
    @NotNull
    @Column(name = "ADMINRATIOS_EURUSD")
    private BigDecimal ratioEUR_USD;
    @NotNull
    @Column(name = "ADMINRATIOS_PLNEUR")
    private BigDecimal ratioPLN_EUR;
    @NotNull
    @Column(name = "ADMINRATIOS_PLNGBP")
    private BigDecimal ratioPLN_GBP;
    @NotNull
    @Column(name = "ADMINRATIOS_PLNCHF")
    private BigDecimal ratioPLN_CHF;
    @NotNull
    @Column(name = "ADMINRATIOS_PLNUSD")
    private BigDecimal ratioPLN_USD;
    @NotNull
    @Column(name = "ADMINRATIOS_GBPEUR")
    private BigDecimal ratioGBP_EUR;
    @NotNull
    @Column(name = "ADMINRATIOS_GBPPLN")
    private BigDecimal ratioGBP_PLN;
    @NotNull
    @Column(name = "ADMINRATIOS_GBPCHF")
    private BigDecimal ratioGBP_CHF;
    @NotNull
    @Column(name = "ADMINRATIOS_GBPUSD")
    private BigDecimal ratioGBP_USD;
    @NotNull
    @Column(name = "ADMINRATIOS_CHFEUR")
    private BigDecimal ratioCHF_EUR;
    @NotNull
    @Column(name = "ADMINRATIOS_CHFPLN")
    private BigDecimal ratioCHF_PLN;
    @NotNull
    @Column(name = "ADMINRATIOS_CHFGBP")
    private BigDecimal ratioCHF_GBP;
    @NotNull
    @Column(name = "ADMINRATIOS_CHFUSD")
    private BigDecimal ratioCHF_USD;
    @NotNull
    @Column(name = "ADMINRATIOS_USDEUR")
    private BigDecimal ratioUSD_EUR;
    @NotNull
    @Column(name = "ADMINRATIOS_USDPLN")
    private BigDecimal ratioUSD_PLN;
    @NotNull
    @Column(name = "ADMINRATIOS_USDGBP")
    private BigDecimal ratioUSD_GBP;
    @NotNull
    @Column(name = "ADMINRATIOS_USDCHF")
    private BigDecimal ratioUSD_CHF;
}
