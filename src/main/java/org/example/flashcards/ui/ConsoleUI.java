package org.example.flashcards.ui;

import org.example.flashcards.mechanism.FileService;
import org.example.flashcards.mechanism.IEntryRepository;
import org.example.flashcards.mechanism.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private final FileService fileService;
    private final IEntryRepository entryRepository;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleUI(FileService fileService, IEntryRepository entryRepository) {
        this.fileService = fileService;
        this.entryRepository = entryRepository;
    }

    public void start() {

        fileService.loadData();
        int choice = 0;
        do {
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            switch (choice) {
                case 1:
                    addNewWord();
                    break;
                case 2:
                    displayAllWords();
                    break;
                case 3:
                    startTest();
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n==== Flashcards Menu ====");
        System.out.println("1. Add new word");
        System.out.println("2. Display all words");
        System.out.println("3. Test yourself");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addNewWord() {
        System.out.println("Enter a word (format: polish,english,german): ");
        String entryLine = scanner.nextLine();
        // The FileService.addData() method will validate and add the entry
        fileService.addData(entryLine);
    }

    private void displayAllWords() {
        List<Entry> entries = entryRepository.getAll();
        if (entries.isEmpty()) {
            System.out.println("No words in the dictionary.");
        } else {
            System.out.println("\n--- Dictionary Entries ---");
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    private void startTest() {
        Entry entry = entryRepository.getRandomEntry();
        if (entry == null) {
            System.out.println("No words available for testing.");
            return;
        }
        System.out.println("Translate the following Polish word: " + entry.getPolish());
        System.out.print("Enter English translation: ");
        String answerEnglish = scanner.nextLine().trim();
        System.out.print("Enter German translation: ");
        String answerGerman = scanner.nextLine().trim();

        boolean englishCorrect = answerEnglish.equalsIgnoreCase(entry.getEnglish());
        boolean germanCorrect = answerGerman.equalsIgnoreCase(entry.getGerman());

        if (englishCorrect && germanCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct translations are: English: "
                    + entry.getEnglish() + ", German: " + entry.getGerman());
        }
    }
}
