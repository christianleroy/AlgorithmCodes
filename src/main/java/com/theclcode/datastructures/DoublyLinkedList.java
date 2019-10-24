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
    private int size;

    public void add(E value){
        Node<E> node = new Node(value);
        if(head==null){
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public E remove(){
        if(head==null){
            return null;
        }
        Node<E> node = head;
        head = head.next;
        if(head != null){
            head.prev = null;
        }
        size--;
        return node.getValue();
    }

    public E remove(E value){
        Node<E> existingNode = getHead();
        while(existingNode != null){
            if(value.equals(existingNode.getValue())){
                Node<E> node = existingNode;
                if(node.next != null){
                    node.next.prev=  node.prev;
                }
                if(node.prev != null){
                     node.prev.next = node.next;
                }
                if(node == head){
                    head = node.next;
                }
                if(node == tail){
                    tail = node.prev;
                }
                size--;
                return node.value;
            }
            existingNode = existingNode.next;
        }
        return null;
    }

    public Node<E> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public static class Node<E> {

        private E value;
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