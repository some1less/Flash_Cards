package org.example.flashcards.mechanism;

public interface RepositoryContract {

    void add(Entry entry);
    String displayAll();
    Entry getRandomEntry();

}
