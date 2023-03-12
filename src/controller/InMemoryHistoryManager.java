package controller;

import model.EpicTask;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryHistoryManager implements HistoryManager {
    private final int historySize;
    private int currentSize;
    private LinkedListNode<Task> head;
    private LinkedListNode<Task> endPointer;
    private HashMap<Integer, LinkedListNode<Task>> addedTasks;

    public InMemoryHistoryManager(int historySize) {
        this.historySize = historySize;
        addedTasks = new HashMap<>();
        head = new LinkedListNode<>(null);
        endPointer = new LinkedListNode<>(null);
        currentSize = 0;
    }

    @Override
    public void addTask(Task task) {
        if (addedTasks.containsKey(task.getId())) {
            removeNode(addedTasks.get(task.getId()));
        }

        if (currentSize == 0) {
            head.setValue(task);
            head.setNext(endPointer);
            endPointer.setPrevious(head);
            addedTasks.put(task.getId(), head);
        } else {
            LinkedListNode newNode = new LinkedListNode<>(task);
            endPointer.getPrevious().setNext(newNode);
            newNode.setPrevious(endPointer.getPrevious());
            newNode.setNext(endPointer);
            endPointer.setPrevious(newNode);
            addedTasks.put(task.getId(), newNode);
        }

        if (currentSize == historySize) {
            addedTasks.remove(head.getValue().getId());
            removeNode(head);
        }
        currentSize++;
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> data = new ArrayList<>();

        LinkedListNode<Task> iterator = head;
        while (iterator.getNext() != null) {
            data.add(iterator.getValue());
            iterator = iterator.getNext();
        }
        return data;
    }

    private void removeNode(LinkedListNode node) {
        if (node.getPrevious() == null) {
            head = head.getNext();
            head.setPrevious(null);
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        currentSize--;
    }

    @Override
    public void remove(int id) {
        if (addedTasks.containsKey(id)) {
            if (addedTasks.get(id).getValue() instanceof EpicTask) {
                for (SubTask subTask : ((EpicTask)addedTasks.get(id).getValue()).getSubTasks()) {
                    if (addedTasks.containsKey(subTask.getId())) {
                        this.remove(subTask.getId());
                    }
                }
                removeNode(addedTasks.get(id));
            } else {
                removeNode(addedTasks.get(id));
            }
            addedTasks.remove(id);
        }
    }

    @Override
    public void clear() {
        addedTasks.clear();
        head.setValue(null);
        head.setNext(null);
        endPointer.setPrevious(null);
    }

}
