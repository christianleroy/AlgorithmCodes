package com.theclcode.datastructures;

public class SinglyLinkedList<E> {

    public static void main(String[] args) {
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        singlyLinkedList.add("a");
        singlyLinkedList.add("b");
        singlyLinkedList.add("c");
        singlyLinkedList.remove();
        Node<String> node = singlyLinkedList.getHead();

        while(node!=null){
            System.out.println(node.getValue());
            node = node.next;
        }
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

    public boolean isEmpty(){
        return head==null;
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

    public Node<E> getHead() {
        return head;
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

