package org.example.flashcards.mechanism;

public interface FileServiceDependency {
    void loadData();
    void saveData(Entry entry);
    void addData(String entryLine);
}
