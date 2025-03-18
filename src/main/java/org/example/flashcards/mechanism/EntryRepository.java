package org.example.flashcards.mechanism;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class EntryRepository implements RepositoryContract, IEntryRepository {

    private final List<Entry> entries = new ArrayList<>();;

    @Override
    public void add(Entry entry) {
        entries.add(entry);
    }

    @Override
    public String displayAll(){

        if (entries.isEmpty()) {
            return "Dictionary is empty";
        }

        StringBuilder sb = new StringBuilder();
        for (Entry entry : entries){
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Entry getRandomEntry() {
        if (entries.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return entries.get(random.nextInt(entries.size()));

    }

}
