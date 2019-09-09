package com.theclcode.datastructures;

public class DoublyLinkedList<E>{

    public static void main(String[] args) {
        //Can be improved by allowing LinkedList to insert type of E instead of creating a new Node object every time.
        DoublyLinkedList<String> stringLinkedList = new DoublyLinkedList<>();
        stringLinkedList.add(new Node<>("A"));
        stringLinkedList.add(new Node<>("B"));
        stringLinkedList.add(new Node<>("C"));
        stringLinkedList.add(new Node<>("D"));
        stringLinkedList.add(new Node<>("E"));
        stringLinkedList.add(new Node<>("F"));
        stringLinkedList.add(new Node<>("G"));
        stringLinkedList.print();
        stringLinkedList.removeHead();
        stringLinkedList.print();
    }

    private Node<E> head;
    private Node<E> tail;

    public void add(Node<E> node){
        if(head==null){
            head = node;
            tail = head;
        } else {
           tail.next = node;
           node.prev=tail;
           tail = node;
        }
    }

    public void print(){
        Node node=head;
        while(node != null){
            System.out.print("Prev: "+ (node.getPrev()!=null ? node.getPrev().getValue() : null) + " ");
            System.out.print("Val: " + node.getValue() + " ");
            System.out.print("Next: "+ (node.getNext()!=null ? node.getNext().getValue() : null) + " ");
            System.out.println();
            node=node.next;
        }
        System.out.println();
    }

    public void removeHead(){
        head.next.prev = head.prev;
        head = head.next;
    }

    public void push(Node<E> node){
        node.next = head;
        head = node;
    }

    public static class Node<E> {

        private E value;
        private Node prev;
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

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

