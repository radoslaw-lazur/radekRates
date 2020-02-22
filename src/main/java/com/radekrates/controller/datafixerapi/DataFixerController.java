package com.radekrates.controller.datafixerapi;

import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.domain.dto.datafixerio.DataFixerDto;
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
    private DataFixerClient dataFixerClient;

    @Autowired
    public DataFixerController(DataFixerClient dataFixerClient) {
        this.dataFixerClient = dataFixerClient;
    }

    @GetMapping(value = "getDataFixer")
    public DataFixerDto getDataFixerData() {
        log.info("FixerData has been downloaded");
        return dataFixerClient.getDataFixerData();
    }
}
