package com.theclcode.datastructures.linkedlist;

public class CommonLinkedListOperations {
    public static void main(String[] args) {

        Node[] arr = new Node[15];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(i + 1);
            if(i > 0) {
                arr[i - 1].next = arr[i];
            }
        }

        removeTailRunner(arr);
//        reverseRunner(arr);
    }


    static void reverseRunner(Node[] arr) {
        print(arr[0]);
        reverseLinkedList(arr[0]);
        print(arr[arr.length - 1]);
    }

    static void reverseLinkedList(Node head){
        Node prev = null;
        Node node = head;

        while(node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
    }

    static void removeTailRunner(Node[] arr) {
        print(arr[0]);
        removeTail(arr[0]);
        print(arr[0]);
    }

    static void removeTail(Node head) {

        Node prev = null;
        Node node = head;

        while(node.next != null) {
            prev = node;
            node = node.next;
        }

        if(prev != null) {
            prev.next = null;
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
