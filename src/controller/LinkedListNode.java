package controller;

public class LinkedListNode<T> {
    private T value;
    private LinkedListNode next;
    private LinkedListNode previous;

    public LinkedListNode(T value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedListNode previous) {
        this.previous = previous;
    }
}
