package com.theclcode.datastructures;

public class LinkedList<E>{

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

    }

    private Node<E> head;
    private Node<E> tail;

    public void add(E value){
        Node<E> node = new Node(value);
        if(head==null){
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public E remove(){
        if(head==null){
            return null;
        } else {
            Node<E> node = head;
            head = head.next;
            return node.getValue();
        }
    }

    public boolean hasNext(){
        return false;
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

