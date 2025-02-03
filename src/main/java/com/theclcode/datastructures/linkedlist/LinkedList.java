package com.theclcode.datastructures.linkedlist;

//Full-featured singly-linked list
public class LinkedList<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("hey");
        linkedList.add("low");
        linkedList.add("bro");


        linkedList.printLinkedList();
        linkedList.removeLast();

        linkedList.add("yow");
        linkedList.printLinkedList();
        linkedList.removeLast();
        linkedList.printLinkedList();
        linkedList.removeLast();
        linkedList.printLinkedList();
        System.out.println(linkedList.size);
        linkedList.removeLast();
        System.out.println(linkedList.size);
        linkedList.printLinkedList();
    }

    private void printLinkedList() {
        Node<E> node = getHead();
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public void add(E value) {
        Node<E> node = new Node<>(value);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void push(E value) {
        Node<E> node = new Node<>(value);
        if (size == 0) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void insert(int index, E value) {
        if ((index - 1) <= 0) {
            push(value);
        } else if (index >= this.size) {
            add(value);
        } else {
            Node<E> existing = traverse(index);
            insertNode(new Node<>(value), existing);
        }
    }

    public E removeFirst(){
        if(head == null){
            return null;
        }
        Node<E> node = head;
        head = node.next;
        if(head == null){
            tail = null;
        }
        size--;
        return node.value;
    }

    public E removeLast(){
        if(tail == null){
            return null;
        }
        Node<E> node;
        if(size == 1){
            node = head;
            head = null;
            tail = null;
        } else {
            Node<E> newTail = traverse(size-1);
            if(newTail != null) {
                node = tail;
                newTail.next = null;
                tail = newTail;
            } else {
                return null;
            }
        }
        size--;
        return node.value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if(index == 0){
            return removeFirst();
        }
        Node<E> node = traverse(index);
        size--;
        return node.value;
    }

    private void insertNode(Node<E> node, Node<E> otherNode) {
        Node<E> temp = otherNode.next;
        otherNode.next = node;
        node.next = temp;
        size++;
    }

    private Node<E> traverse(int index) {
        index -= 1;
        int counter = 0;
        Node<E> node = head;
        while (counter < index) {
            node = node.next;
            counter++;
        }
        return node;
    }

    public Node<E> getHead() {
        return head;
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }


}
