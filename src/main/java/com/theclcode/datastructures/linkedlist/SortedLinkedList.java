package com.theclcode.datastructures.linkedlist;

public class SortedLinkedList {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(5);
        linkedList.add(1);
        linkedList.add(4);
        linkedList.add(-4);
        linkedList.add(3);
        linkedList.add(9);
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(1010);
        linkedList.add(-51);
        linkedList.add(35);
        linkedList.add(36);
        linkedList.add(35);
        linkedList.add(239);
        linkedList.add(139);
        linkedList.add(-189);
        linkedList.add(87);
        linkedList.add(-23);
        linkedList.add(-200);

        LinkedList.Node node = linkedList.getHead();
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }

    static class LinkedList {
        Node head;
        Node tail;
        int size;

        public Node getHead() {
            return head;
        }

        public void add(int value) {
            Node node = new Node(value);
            if (size == 0) {
                head = tail = node;
            } else {
                Node existing = head;
                boolean inserted = false;
                while (existing != null && !inserted) {
                    if (value <= existing.value) {
                        node.next = existing;
                        node.prev = existing.prev;
                        if (existing.prev != null) {
                            existing.prev.next = node;
                        }
                        existing.prev = node;
                        if (existing == head) {
                            head = node;
                        }
                        inserted = true;
                    }
                    existing = existing.next;
                }
                if (!inserted) {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            }
            size++;
        }

        class Node {
            int value;
            Node prev;
            Node next;

            Node(int value) {
                this.value = value;
            }
        }
    }
}
