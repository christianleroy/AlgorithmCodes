package com.theclcode.datastructures;

public class DoublyLinkedList<E>{

    public static void main(String[] args) {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        System.out.println(linkedList.isEmpty());
        linkedList.add("a");
        linkedList.add("b");
        System.out.println(linkedList.isEmpty());
        linkedList.remove();
        linkedList.remove();
        System.out.println(linkedList.isEmpty());
    }

    private Node<E> head;
    private Node<E> tail;

    public void add(E value){
        Node<E> node = new Node(value);
        if(head==null){
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
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
            if(head != null){
                head.prev = null;
            }
            return node.getValue();
        }
    }

    public Node<E> getHead() {
        return head;
    }

    public static class Node<E> {

        private E value;
        private Node next;
        private Node prev;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

    }
}