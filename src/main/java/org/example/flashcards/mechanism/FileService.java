package org.example.flashcards.mechanism;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    private String filename;
    private EntryRepository entryRepository;

    public FileService(String filename, EntryRepository entryRepository) {
        this.filename = filename;
        this.entryRepository = entryRepository;
    }

    public void loadEntries(){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename),
                StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {

                    System.out.println("Each line must contain exactly 3 words separated by commas");

                } else {

                    Entry entry = new Entry(parts[0], parts[1], parts[2]);
                    entryRepository.add(entry);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
