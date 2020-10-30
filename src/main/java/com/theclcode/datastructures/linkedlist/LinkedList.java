package com.theclcode.datastructures.linkedlist;

//Full featured singly-linked list
public class LinkedList<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("b"); // b
        linkedList.insert(-1, "a"); // a b
        linkedList.add("c"); // a b c
        linkedList.insert(-1000, "-a"); //-a a b c
        linkedList.insert(5, "d"); // -a a b c d
        linkedList.insert(2, "aa"); //-a a aa b c d
        linkedList.insert(10, "e"); //-a a aa b c d e
        linkedList.push("alpha"); //alpha -a a aa b c d e
        linkedList.add("omega"); //alpha -a a aa b c d e omega
        System.out.println(linkedList.remove(0)); //-a a aa b c d e omega
        System.out.println(linkedList.remove(0)); //a aa b c d e omega

        LinkedList.Node<String> node = linkedList.getHead();
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
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
        }
        size--;
        return null;
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
