package com.radekrates.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CompanyDetails {
    @Value("${info.app.name}")
    private String appName;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.company.email}")
    private String mail;
    @Value("${info.app.company.phone}")
    private String mobile;
}
