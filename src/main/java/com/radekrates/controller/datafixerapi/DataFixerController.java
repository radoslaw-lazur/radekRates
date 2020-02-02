package com.radekrates.controller.datafixerapi;

import com.radekrates.api.datafixerio.calculation.*;
import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.domain.datafixerio.dto.DataFixerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/dataFixer")
public class DataFixerController {
    @Autowired
    private DataFixerClient dataFixerClient;

    @Autowired
    private CurrrencyCalculator currrencyCalculator;

    @GetMapping(value = "getDataFixer")
    public DataFixerDto getDataFixerData() {
        log.info("FixerData has been downloaded");
        return dataFixerClient.getDataFixerData();
    }

    @GetMapping(value = "getEurBase")
    public void getEurBase() {
        EurBase eurBase = currrencyCalculator.calculateEurBase();
        PlnBase plnBase = currrencyCalculator.calculatePlnBase();
        GbpBase gbpBase = currrencyCalculator.calculateGbpBase();
        ChfBase chfBase = currrencyCalculator.calculateChfBase();
        UsdBase usdBase = currrencyCalculator.calculateUsdBase();
        System.out.println(eurBase.getDate());
        System.out.println(eurBase.getEur());
        System.out.println(eurBase.getPln());
        System.out.println(eurBase.getGbp());
        System.out.println(eurBase.getChf());
        System.out.println(eurBase.getUsd());
        System.out.println();
        System.out.println(plnBase.getDate());
        System.out.println(plnBase.getEur());
        System.out.println(plnBase.getPln());
        System.out.println(plnBase.getGbp());
        System.out.println(plnBase.getChf());
        System.out.println(plnBase.getUsd());
        System.out.println();
        System.out.println(gbpBase.getDate());
        System.out.println(gbpBase.getEur());
        System.out.println(gbpBase.getPln());
        System.out.println(gbpBase.getGbp());
        System.out.println(gbpBase.getChf());
        System.out.println(gbpBase.getUsd());
        System.out.println();
        System.out.println(chfBase.getDate());
        System.out.println(chfBase.getEur());
        System.out.println(chfBase.getPln());
        System.out.println(chfBase.getGbp());
        System.out.println(chfBase.getChf());
        System.out.println(chfBase.getUsd());
        System.out.println();
        System.out.println(usdBase.getDate());
        System.out.println(usdBase.getEur());
        System.out.println(usdBase.getPln());
        System.out.println(usdBase.getGbp());
        System.out.println(usdBase.getChf());
        System.out.println(usdBase.getUsd());
    }
}
