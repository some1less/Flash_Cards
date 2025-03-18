package org.example.flashcards.core;

import org.example.flashcards.ui.ConsoleUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "org.example.flashcards")
@PropertySource("classpath:application.yaml")
public class FlashCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashCardsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ConsoleUI consoleUI) {
        return args -> consoleUI.start();
    }
}
