package com.amendola.api_park.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;


@Configuration
public class SpringTimezoneConfig {

    @PostConstruct
    public void TimeZoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Cuiaba"));
    }
}
