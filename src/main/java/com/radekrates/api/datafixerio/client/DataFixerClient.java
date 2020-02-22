package com.radekrates.api.datafixerio.client;

import com.radekrates.api.datafixerio.config.DataFixerConfig;
import com.radekrates.domain.dto.datafixerio.DataFixerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class DataFixerClient {
    private RestTemplate restTemplate;
    private DataFixerConfig dataFixerConfig;

    @Autowired
    public DataFixerClient(RestTemplate restTemplate, DataFixerConfig dataFixerConfig) {
        this.restTemplate = restTemplate;
        this.dataFixerConfig = dataFixerConfig;
    }

    public DataFixerDto getDataFixerData() {
        try {
            return ofNullable(restTemplate.getForObject(getDataFixerURL(), DataFixerDto.class))
                    .orElseThrow(RuntimeException::new);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new DataFixerDto();
        }
    }

    private URI getDataFixerURL() {
        return UriComponentsBuilder.fromHttpUrl(dataFixerConfig.getDataFixerApiEndpoint()
                + dataFixerConfig.getDataFixerApiKey()
                + dataFixerConfig.getDataFixerFormat())
                .build().encode().toUri();
    }
}
