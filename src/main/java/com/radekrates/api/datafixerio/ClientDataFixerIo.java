package com.radekrates.api.datafixerio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class ClientDataFixerIo {
    @Autowired
    private RestTemplate restTemplate;

    public String getJsonString() {
        return restTemplate.getForObject(getUri(), String.class);
    }
    private URI getUri() {
        return UriComponentsBuilder.fromHttpUrl("http://data.fixer.io/api/latest?access_" +
                "key=a5d06ee9f4b7580747fb225aa140fc72&format=1").build().encode().toUri();
    }
}
