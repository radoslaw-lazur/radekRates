package com.radekrates.api.datafixerio.client;

import com.radekrates.api.datafixerio.config.DataFixerConfig;
import com.radekrates.domain.datafixerio.dto.DataFixerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class DataFixerClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DataFixerConfig dataFixerConfig;

    public DataFixerDto getDataFixerData() {
        return restTemplate.getForObject(getDataFixerURL(), DataFixerDto.class);
    }

    private URI getDataFixerURL() {
        return UriComponentsBuilder.fromHttpUrl(dataFixerConfig.getDataFixerApiEndpoint()
                + dataFixerConfig.getDataFixerApiKey()
                + dataFixerConfig.getDataFixerFormat())
                .build().encode().toUri();
    }
}
