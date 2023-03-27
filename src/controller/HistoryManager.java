package controller;

import model.Task;

import java.util.List;

public interface HistoryManager {
    void addTask(Task task);
    List<Task> getHistory();
    void remove(int id);
    void clear();
    String toCsvString();
}
