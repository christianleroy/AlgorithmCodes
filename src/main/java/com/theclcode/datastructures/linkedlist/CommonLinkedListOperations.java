package com.theclcode.datastructures.linkedlist;

public class CommonLinkedListOperations {
    public static void main(String[] args) {

        Node[] arr = new Node[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(i + 1);
        }

        arr[0].next = arr[1];
        arr[1].next = arr[2];
        arr[2].next = arr[3];
        arr[3].next = arr[4];

        reverseRunner(arr);
        removeTailRunner(arr);
    }


    static void reverseRunner(Node[] arr) {

        print(arr[0]);
        reverseLinkedList(arr[0]);
        print(arr[arr.length - 1]);
    }

    static void reverseLinkedList(Node head){

        Node prev;
        Node node = head;
        Node next = node.next;

        while(next != null) {
            prev = node;
            node = next;
            if(prev == head) {
                prev.next = null;
            }
            next = node.next;
            node.next = prev;

        }
    }

    static void removeTailRunner(Node[] arr) {
        removeTail(arr[0]);
    }

    static void removeTail(Node head) {

        Node node = head;
        while(node.next != null) {
            node = node.next;
        }

        System.out.println("The tail is: " + node.value);
    }


    static void print(Node node) {
        while(node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
}
