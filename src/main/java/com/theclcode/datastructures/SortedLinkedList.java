package com.theclcode.datastructures;

public class SortedLinkedList {

    Node head;
    Node tail;
    int size = 0;

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

        Node node = sortedLinkedList.getHead();
        while(node != null){
            System.out.println(node.value);
            node = node.next;
        }
    }

    public void add(int value){
        Node node = new Node(value);
        if(head == null){
            head = tail = node;
        } else {
            Node existing = head;
            while(existing != null) {
                if(existing.value > node.value){
                    node.prev = existing.prev;
                    node.next = existing;
                    if(existing.prev != null){
                        existing.prev.next = node;
                    }
                    if(existing == head){
                        head = node;
                    }
                    existing.prev = node;
                    break;
                } else {
                    if(existing == tail){
                        tail.next = node;
                        node.prev = tail;
                        tail = node;
                        break;
                    }
                }
                existing = existing.next;
           }
        }
        size++;
    }

    public Node getHead() {
        return head;
    }

    static class Node {
        int value;
        Node next;
        Node prev;

        Node(Integer value){
            this.value = value;
        }
    }
}
