package com.theclcode.cracking.linkedlists;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LinkedListInterviewQuestionsA {

    public static void main(String[] args) {
//        removeDuplicate();
//        kthElement();
//        deleteMiddleNode();
        kthElementUnknownSize();
    }

    /**
     * Removes duplicate elements from a linked list
     * This solution can be improved to not create a new linked list,
     * and instead remove duplicate elements from the list as you find it.
     * But to avoid using "advanced" Java data structure or create own
     * implementation, we created a new one.
     * Time: O(N)
     * Space: O(N)
     */
    static void removeDuplicate() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(4);

        Set<Integer> set = new HashSet<>();
        LinkedList<Integer> fin = new LinkedList<>();
        for (Integer i : list) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            fin.add(i);
        }
        System.out.println(list);
        System.out.println(fin);

    }

    /**
     * Returns the kth to the last element of the linked list
     * Time: O(K)
     * Space: O(1)
     */
    private static void kthElement() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.add(6);

        int k = 7;
        int elem;
        if (k > myLinkedList.size()) {
            elem = -1;
        } else {
            int size = myLinkedList.size();
            MyLinkedList.Node<Integer> node = myLinkedList.getHead();
            while (size > k) {
                node = node.next;
                size--;
            }
            elem = node.value;
        }

        System.out.println(elem);
    }

    private static void kthElementUnknownSize() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.add(6);

        int k = 0;
        MyLinkedList.Node<Integer> node = myLinkedList.getHead();
        Object[] obj = dig(node, k);
        System.out.format("Node value: %s%n", ((MyLinkedList.Node) obj[0]).value);
    }

    private static Object[] dig(MyLinkedList.Node<Integer> node, int k) {
        if (node.next == null) {
            return new Object[]{node, 1};
        } else {
            Object[] obj = dig(node.next, k);
            int val = (Integer) obj[1] + 1;
            if (val > k) {
                return obj;
            } else {
                return new Object[]{node, val};
            }
        }
    }

    private static void deleteMiddleNode() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);

        int element = 3;
        MyLinkedList.Node<Integer> node = myLinkedList.getHead();
        while (node != null) {
            if (node.next != null && node.next.value == element) {
                myLinkedList.remove(node);
            }
            node = node.next;
        }
        node = myLinkedList.getHead();
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    private static class MyLinkedList<E> {
        private int size;
        private Node<E> head;
        private Node<E> tail;

        public void add(E value) {
            Node<E> node = new Node<>(value);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public int size() {
            return this.size;
        }

        public Node<E> getHead() {
            return head;
        }

        public static class Node<E> {
            E value;
            Node<E> next;

            Node(E value) {
                this.value = value;
            }

        }

        public void remove(Node<E> prev) {
            if (prev.next != null) {
                prev.next = prev.next.next;
                size--;
            }
        }
    }

}
