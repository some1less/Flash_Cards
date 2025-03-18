package org.example.flashcards.core;

import org.example.flashcards.mechanism.Entry;

public interface ControllerContract {

    String displayAll();
    void add(Entry entry);
    Entry getRandomEntry();

}
