package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int historySize;
    List<Task> history;
    CustomLinkedList<Task> improvedHistory;
    HashMap<Integer, LinkedListNode<Task>> addedTasks;

    public InMemoryHistoryManager(int historySize) {
        this.historySize = historySize;
        history = new LinkedList<>();
        improvedHistory = new CustomLinkedList<>();
        addedTasks = new HashMap<>();
    }

    @Override
    public void addTask(Task task) {
        history.add(task);
        if (history.size() > historySize) {
            history.remove(0);
        }

        improvedHistory.addAtEnd(task);
        if (addedTasks.get(task.getId()) != null) {
            improvedHistory.remove(addedTasks.get(task.getId()));
        }
        addedTasks.put(task.getId(), improvedHistory.getLastNode());

        if (improvedHistory.getSize() > historySize) {
            addedTasks.remove(((Task)improvedHistory.getHead().getValue()).getId());
            improvedHistory.removeHead();
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return improvedHistory.getAllData();
    }

    @Override
    public void remove(int id) {
        if (addedTasks.containsKey(id)) {
            if (addedTasks.get(id).getValue() instanceof EpicTask) {
                for (SubTask subTask : ((EpicTask)addedTasks.get(id).getValue()).getSubTasks()) {
                    improvedHistory.remove(addedTasks.get(subTask.getId()));
                }
                improvedHistory.remove(addedTasks.get(id));
            } else {
                improvedHistory.remove(addedTasks.get(id));
            }
            addedTasks.remove(id);
        }
    }

    @Override
    public void clear() {
        addedTasks.clear();
        improvedHistory.clear();
    }

}
