package com.theclcode.datastructures.linkedlist;

public class ReversibleLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.reverse();
        linkedList.print();
        linkedList.add(1);
        linkedList.reverse();
        linkedList.print();
        linkedList.add(2);
        linkedList.add(3);
        linkedList.print();
        linkedList.reverse();
        linkedList.print();
        linkedList.reverse();
        linkedList.print();
        linkedList.add(4);
        linkedList.add(5);
        linkedList.reverse();
        linkedList.print();
    }

    static class LinkedList<E> {

        int size;
        Node<E> head;
        Node<E> tail;

        public void add(E value) {
            Node<E> node = new Node<>(value);
            if (size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public void reverse() {
            if (size <= 1) {
                return;
            }
            Node<E> node = head;
            while (node != null) {
                Node<E> old = node.prev;
                node.prev = node.next;
                node.next = old;
                node = node.prev;
            }
            Node<E> temp = head;
            head = tail;
            tail = temp;
        }

        public void print() {
            Node<E> node = head;
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
        }

        public Node<E> getHead() {
            return head;
        }

        class Node<E> {
            E value;
            Node<E> next;
            Node<E> prev;

            Node(E value) {
                this.value = value;
            }
        }
    }
}
