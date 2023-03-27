package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int MAX_SIZE;
    private TaskListNode head;
    private TaskListNode tail;
    private HashMap<Integer, TaskListNode> addedTasks;

    public InMemoryHistoryManager(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;

        addedTasks = new HashMap<>();
        tail = new TaskListNode();

        head = tail;
    }

    @Override
    public void addTask(Task task) {
        TaskListNode newTail = new TaskListNode(task, null, tail);

        remove(task.getId());

        if (addedTasks.size() == MAX_SIZE) {
            remove(head.getValue().getId());
        }

        tail.setNext(newTail);
        tail = newTail;

        addedTasks.put(task.getId(), tail);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> data = new ArrayList<>();

        TaskListNode iterator = head;
        while (iterator != null) {
            data.add(iterator.getValue());
            iterator = iterator.getNext();
        }
        return data;
    }

    @Override
    public void remove(int id) {
        if (!addedTasks.containsKey(id)) {
            return;
        }

        Task task = addedTasks.get(id).getValue();
        if (task instanceof EpicTask) {
            for (SubTask subTask : ((EpicTask) task).getSubTasks()) {
                remove(subTask.getId());
            }
        }

        var node = addedTasks.remove(id);

        if (node == head) {
            head = head.getNext();
            head.setPrev(null);
            return;
        }

        if (node == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
            return;
        }

        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    @Override
    public String toCsvString() {
        StringBuilder result = new StringBuilder();

        for (Task task : getHistory()) {
            if (task != null)
            result.append(task.getId()).append(",");
        }

        return  result.toString();
    }

    @Override
    public void clear() {
        addedTasks.clear();
        tail.setPrev(null);
        tail.setValue(null);
        head = tail;
    }
}
