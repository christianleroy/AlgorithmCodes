package com.theclcode.datastructures.linkedlist;

public class InsertionSortLinkedList {

    public static void main(String[] args) {
        Node[] arr = new Node[3];

        arr[0] = new Node(4);
        arr[1] = new Node(2);
        arr[2] = new Node(1);

        arr[0].next = arr[1];
        arr[1].next = arr[2];

        System.out.println("Initial Linked List:");
        print(arr[0]);

        System.out.println("Insert 3...");
        insert(arr[0], 3);
        print(arr[0]);

        System.out.println("Insert 5...");
        print(insert(arr[0], 5));

        System.out.println("Insert -1...");
        insert(arr[0], -1);
        print(arr[0]);

        System.out.println("Print from original head (4) only...");
        print(arr[0]);


    }

    static void print(Node head) {
        Node node = head;

        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    static Node insert(Node head, int target) {
        Node prev = null;
        Node node = head;
        Node tNode = new Node(target);
        while (node != null) {
            if (target >= node.val) {
                if (prev != null) {
                    prev.next = tNode;
                }
                tNode.next = node;
                break;
            }
            prev = node;
            node = node.next;
        }
        if (prev != null) {
            // Not inserted
            prev.next = tNode;
        }
        return tNode;

    }

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

}
