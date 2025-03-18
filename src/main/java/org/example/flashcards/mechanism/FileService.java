package org.example.flashcards.mechanism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class FileService implements FileServiceDependency {

    private final String fileName;
    private final IEntryRepository entryRepository;
    private final DisplayFormatter displayFormatter;

    @Autowired
    public FileService(@Value("${pl.edu.pja.tpo02.filename}") String fileName,
                       IEntryRepository entryRepository,
                       DisplayFormatter displayFormatter) {
        this.fileName = fileName;
        this.entryRepository = entryRepository;
        this.displayFormatter = displayFormatter;
    }

    @Override
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(fileName),
                StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Each line must contain exactly 3 words separated by commas");
                } else {
                    Entry entry = new Entry(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    entryRepository.add(entry);
                    System.out.println("Word added: " + displayFormatter.format(entry));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveData(Entry entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(entry.getPolish() + "," + entry.getEnglish() + "," + entry.getGerman());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(String entryLine) {
        String[] parts = entryLine.split(",");
        if (parts.length != 3) {
            System.out.println("Each line must contain exactly 3 words separated by commas");
            return;
        }
        Entry tmp = new Entry(parts[0].trim(), parts[1].trim(), parts[2].trim());
        entryRepository.add(tmp);
        System.out.println("Word added: " + displayFormatter.format(tmp));
        saveData(tmp);
    }
}
