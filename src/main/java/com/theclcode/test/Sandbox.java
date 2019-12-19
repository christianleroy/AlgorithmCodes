package com.theclcode.test;

import java.util.Arrays;

public class Sandbox {

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
        System.out.println();
    }

   static class StackQueueLinkedList<E> {

       int size;
       Node<E> head;
       Node<E> tail;

       public boolean isEmpty(){
           return size == 0;
       }

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

       public E removeFirst(){
           if(head == null){
               return null;
           }
           Node<E> node = head;
           head = node.next;
           if(head != null){
               head.prev = null;
           } else {
               tail = null;
           }
           size--;
           return node.value;
       }

       public E removeLast(){
           if(tail == null){
                return null;
           }
           Node<E> node = tail;
           tail = node.prev;
           if(tail != null){
               tail.next = null;
           } else {
               head = null;
           }
           size--;
           return node.value;
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
}
