package com.tagdsearch.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "contract-search-service")
public class ContractSearchServiceConfigData {
    private String version;
}
