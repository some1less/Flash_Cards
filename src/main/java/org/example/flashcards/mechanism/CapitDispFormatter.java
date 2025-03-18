package org.example.flashcards.mechanism;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("capital")
public class CapitDispFormatter implements DisplayFormatter{

    @Override
    public String format(Entry entry) {
        return entry.toString().toUpperCase();
    }
}
