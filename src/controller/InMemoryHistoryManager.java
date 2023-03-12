package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int MAX_SIZE;
    private LinkedListNode<Task> head;
    private LinkedListNode<Task> tail;
    private HashMap<Integer, LinkedListNode<Task>> addedTasks;

    public InMemoryHistoryManager(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;

        addedTasks = new HashMap<>();
        tail = new LinkedListNode<>(null);

        head = tail;
    }

    @Override
    public void addTask(Task task) {
        LinkedListNode<Task> newTail = new LinkedListNode<>(task);

        remove(task.getId());

        if (addedTasks.size() == MAX_SIZE) {
            remove(head.getValue().getId());
        }

        tail.setNext(newTail);
        newTail.setPrevious(tail);
        tail = newTail;

        addedTasks.put(task.getId(), tail);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> data = new ArrayList<>();

        LinkedListNode<Task> iterator = head;
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

        if (addedTasks.get(id).getValue() instanceof EpicTask) {
            for (SubTask subTask : ((EpicTask) addedTasks.get(id).getValue()).getSubTasks()) {
                remove(subTask.getId());
            }
        }

        var node = addedTasks.remove(id);

        if (node == head) {
            head = head.getNext();
            head.setPrevious(null);
            return;
        }

        if (node == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
            return;
        }

        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
    }

    @Override
    public void clear() {
        addedTasks.clear();
        tail.setPrevious(null);
        tail.setValue(null);
        head = tail;
    }
}
