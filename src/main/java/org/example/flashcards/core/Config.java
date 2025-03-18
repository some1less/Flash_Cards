package org.example.flashcards.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.dictionary.yaml")
@ConfigurationProperties(prefix = "pl.edu.pja.tpo02")
public class Config {

    @Value("${filename}")
    private String filename;

    @Bean
    public String getFilename() {
        return filename;
    }

}
