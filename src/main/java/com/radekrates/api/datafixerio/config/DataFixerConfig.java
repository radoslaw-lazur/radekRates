package com.radekrates.api.datafixerio.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DataFixerConfig {
    @Value("${datafixerio.endpoint.prod}")
    private String dataFixerApiEndpoint;
    @Value("${datafixerio.app.key}")
    private String dataFixerApiKey;
    @Value("${datafixerio.app.format}")
    private String dataFixerFormat;
}
