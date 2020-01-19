package com.radekrates.controller.datafixerapi;

import com.radekrates.api.datafixerio.ClientDataFixerIo;
import com.radekrates.service.apireader.DataFixerJsonReader;
import com.radekrates.service.apireader.DataFixerEurBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Component
@RequestMapping("/v1/fixer")
public class DataFixerController {
    @Autowired
    private ClientDataFixerIo clientDataFixerIo;
    private DataFixerJsonReader dataFixerJsonReader = new DataFixerJsonReader();

    @GetMapping(value = "showRates")
    public void showRates() {
        String json = clientDataFixerIo.getJsonString();
        DataFixerEurBase dataFixerEurBase = dataFixerJsonReader.saveRatesBasedOnEur(json);
        System.out.println(dataFixerEurBase.getEur());
        System.out.println(dataFixerEurBase.getChf());
        System.out.println(dataFixerEurBase.getUsd());
        System.out.println(dataFixerEurBase.getPln());
        System.out.println(dataFixerEurBase.getGbp());
    }
}
