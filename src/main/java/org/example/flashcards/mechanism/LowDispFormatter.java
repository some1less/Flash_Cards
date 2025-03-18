package org.example.flashcards.mechanism;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("lowercase")
public class LowDispFormatter implements DisplayFormatter {
    @Override
    public String format(Entry entry) {
        return entry.toString().toLowerCase();
    }
}
