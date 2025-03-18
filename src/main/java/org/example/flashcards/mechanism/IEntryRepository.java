package org.example.flashcards.mechanism;

import java.util.List;

public interface IEntryRepository {

    void add(Entry entry);

    Entry getRandomEntry();

    List<Entry> getAll();
}
