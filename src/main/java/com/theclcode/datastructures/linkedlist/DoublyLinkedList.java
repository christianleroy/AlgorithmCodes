package com.theclcode.datastructures.linkedlist;

public class DoublyLinkedList<E> {

    public static void main(String[] args) {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        System.out.println(linkedList.isEmpty());
        linkedList.add("a");
        linkedList.add("b");
    }

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public void add(E value) {
        Node<E> node = new Node<>(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E removeFirst() {
        if (head == null) {
            return null;
        }
        Node<E> node = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return node.getValue();
    }

    public Node<E> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public static class Node<E> {

        private final E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }
    }
}