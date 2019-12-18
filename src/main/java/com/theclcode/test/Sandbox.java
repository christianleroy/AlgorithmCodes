package com.theclcode.test;

import java.util.Arrays;

public class Sandbox {
    public static void main(String[] args) {
        SortedLinkedList sortedLinkedList = new SortedLinkedList();
        sortedLinkedList.add(5);
        sortedLinkedList.add(1);
        sortedLinkedList.add(4);
        sortedLinkedList.add(-4);
        sortedLinkedList.add(3);
        sortedLinkedList.add(9);
        sortedLinkedList.add(0);
        sortedLinkedList.add(1);
        sortedLinkedList.add(190);
        sortedLinkedList.add(189);

        SortedLinkedList.Node node = sortedLinkedList.getHead();
        while(node != null){
            System.out.println(node.value);
            node = node.next;
        }
    }
    static class SortedLinkedList {

        Node head;
        Node tail;
        int size;

        public Node getHead() {
            return head;
        }

        public void add(int value){
            Node node = new Node(value);
            if(head == null){
                head = tail = node;
            } else {
                Node existing = head;
                while(existing != null){
                    if(existing.value > node.value){
                        node.next = existing;
                        node.prev = existing.prev;
                        if(existing.prev != null){
                            existing.prev.next = node;
                        }
                        existing.prev = node;
                        if(existing == head){
                            head = node;
                        }
                        size++;
                        break;
                    } else {
                        if(existing == tail){
                            tail.next = node;
                            node.prev = tail;
                            tail = node;
                            size++;
                            break;
                        }
                    }
                    existing = existing.next;
                }
            }
        }

       static class Node {
            int value;
            Node prev;
            Node next;

            Node(int value){
                this.value = value;
            }
        }
    }
}
