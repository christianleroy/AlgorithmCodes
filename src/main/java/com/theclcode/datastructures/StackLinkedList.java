package com.theclcode.datastructures;

public class StackLinkedList<E> {

    Node<E> head;
    Node<E> tail;
    int size = 0;

    public void add(E value){
        Node<E> node = new Node(value);
        if(tail == null){
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public E remove(){
        if(tail == null){
            return null;
        }
        Node<E> node = tail;
        if(head == tail){
            head = null;
        }
        tail = tail.prev;
        if(tail != null){
            tail.next = null;
        }
        size--;
        return node.value;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(E value){
            this.value = value;
        }
    }
}
