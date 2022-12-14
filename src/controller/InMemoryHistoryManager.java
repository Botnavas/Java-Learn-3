package controller;

import model.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int historySize;
    List<Task> history;

    public InMemoryHistoryManager(int historySize) {
        this.historySize = historySize;
        history = new LinkedList<>();
    }

    @Override
    public void addTask(Task task) {
        history.add(task);
        if (history.size() > historySize) {
            history.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

}
