package org.example.flashcards.mechanism;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("original")
public class OrigDispFormatter implements DisplayFormatter {

    @Override
    public String format(Entry entry) {
        return entry.toString();
    }
}
