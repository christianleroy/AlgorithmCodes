package com.theclcode.datastructures.linkedlist;

public class StackQueueLinkedList<E> {

    public static void main(String[] args) {

        StackQueueLinkedList<Integer> linkedList = new StackQueueLinkedList<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        while (!linkedList.isEmpty()){
            System.out.println(linkedList.removeFirst());
        }

        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);

        while (!linkedList.isEmpty()){
            System.out.println(linkedList.removeLast());
        }

        System.out.println(linkedList.size);
        linkedList.add(59);
        System.out.println(linkedList.size);
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList.size);
        System.out.println(linkedList.removeFirst());
        linkedList.add(41);
        System.out.println(linkedList.size);
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.size);
        System.out.println(linkedList.removeLast());

        System.out.println();
    }

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public void add(E value){
        Node<E> node = new Node<>(value);
        if(head == null){
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    //Queue
    public E removeFirst(){
        if(head == null){
            return null;
        }
        Node<E> node = head;
        head = node.next;
        if(head == null){
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return node.value;
    }

    //Stack
    public E removeLast(){
        if(tail == null){
            return null;
        }
        Node<E> node = tail;
        tail = tail.prev;
        if(tail == null){
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return node.value;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(E value){
            this.value = value;
        }
    }
}
