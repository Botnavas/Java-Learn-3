package controller;

import java.util.ArrayList;

public class CustomLinkedList<T> {
    private LinkedListNode head;
    private LinkedListNode tail;
    private int size;

    public CustomLinkedList() {
        head = new LinkedListNode<>(null);
        tail = new LinkedListNode<>(null);
        tail.setPrevious(head);
        size = 0;
    }

    public void addAtEnd(T data) {
        if (size == 0) {
            head.setValue(data);
        } else {
            LinkedListNode lastNode = new LinkedListNode<>(data);

            tail.getPrevious().setNext(lastNode);
            lastNode.setPrevious(tail.getPrevious());
            lastNode.setNext(tail);
            tail.setPrevious(lastNode);
        }
        size++;
    }

    public void remove(LinkedListNode node) {
        if (size == 1) {
            this.removeHead();
            return;
        }

        if (node == head) {
            head.getNext().setPrevious(null);
            head = head.getNext();
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;
    }

    public void removeHead() {
        if (size == 1) {
            head.setValue(null);
            size --;
        } else {
            head.getNext().setPrevious(null);
            head = head.getNext();
        }
    }

    public ArrayList getAllData() {
        ArrayList<T> data = new ArrayList<>();

        LinkedListNode iterator = head;
        while (iterator.getNext() != null) {
            data.add((T)iterator.getValue());
            iterator = iterator.getNext();
        }
        return data;
    }

    public void clear() {
        head = new LinkedListNode<>(null);
        tail.setPrevious(head);
        size = 0;
    }

    public LinkedListNode getHead() {
        return head;
    }

    public LinkedListNode getLastNode() {
        return tail.getPrevious();
    }

    public int getSize() {
        return size;
    }
}
