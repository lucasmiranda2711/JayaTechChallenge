package com.jaya.challenge.rest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest")
@Getter
@Setter
public class RestConfiguration {

    private String access_key;

}
