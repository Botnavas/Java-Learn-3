package controller;


import model.Task;

public class TaskListNode {
    private Task value;
    private TaskListNode next;
    private TaskListNode prev;

    public TaskListNode() {
        value = null;
        next = null;
        prev = null;
    }

    public TaskListNode(Task value, TaskListNode next, TaskListNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public Task getValue() {
        return value;
    }

    public void setValue(Task value) {
        this.value = value;
    }

    public TaskListNode getNext() {
        return next;
    }

    public void setNext(TaskListNode next) {
        this.next = next;
    }

    public TaskListNode getPrev() {
        return prev;
    }

    public void setPrev(TaskListNode prev) {
        this.prev = prev;
    }
}
