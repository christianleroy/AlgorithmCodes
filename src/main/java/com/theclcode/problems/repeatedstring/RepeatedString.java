package com.theclcode.problems.repeatedstring;

//linked lists
public class RepeatedString {

    public static void main(String[] args) {
        System.out.println(repeatedString("abcac", 10));
        System.out.println(repeatedString("aba", 10));
        System.out.println(repeatedString("a", 1000000000000l));
    }

    private static long repeatedString(String s, long n) {
        LinkedList ll = new LinkedList();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                ll.add(i);
            }
        }
        long div = (n / s.length()) * ll.size;
        long mod = n % s.length();
        if (n % s.length() == 0) {
            return div;
        } else {
            LinkedList.Node node = ll.head;
            while (node != null && node.value < mod) {
                div++;
                node = node.next;
            }
        }
        return div;
    }

    private static class LinkedList {

        private int size;
        private Node head;
        private Node tail;

        public void add(int value) {
            Node node = new Node(value);
            if (size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        static class Node {
            private int value;
            private Node next;

            Node(int value) {
                this.value = value;
            }
        }
    }

}
