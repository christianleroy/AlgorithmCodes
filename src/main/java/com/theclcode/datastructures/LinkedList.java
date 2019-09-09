package com.theclcode.datastructures;

public class LinkedList<E>{

    public static void main(String[] args) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("A");
        stringLinkedList.add("B");
        stringLinkedList.add("C");
        stringLinkedList.print();
        stringLinkedList.push("-A");
        stringLinkedList.print();
        stringLinkedList.add("C");
        stringLinkedList.print();
    }

    private Node<E> head;
    private Node<E> tail;

    public void add(E value){
        Node<E> node = new Node(value);
        if(head==null){
            head = node;
            tail=head;
        } else {
           tail.next = node;
           tail = node;
        }
    }

    public void print(){
        Node node=head;
        while(node != null){
            System.out.print(node.getValue());
            node=node.next;
        }
        System.out.println();
    }

    public void push(E value){
        Node<E> node = new Node(value);
        node.next = head;
        head = node;
    }

    public static class Node<E> {

        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

