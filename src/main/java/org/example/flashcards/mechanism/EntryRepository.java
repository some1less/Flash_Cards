package org.example.flashcards.mechanism;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository {

    private final List<Entry> entries = new ArrayList<>();

    public void add (Entry entry) {
        entries.add(entry);
    }

    public List<Entry> displayAll(){
        return entries;
    }

}
