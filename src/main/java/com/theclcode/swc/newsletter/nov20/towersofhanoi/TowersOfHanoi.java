package com.theclcode.swc.newsletter.nov20.towersofhanoi;

import java.util.Scanner;

public class TowersOfHanoi {
    LinkedList towerA = new LinkedList('A');
    LinkedList towerB = new LinkedList('B');
    LinkedList towerC = new LinkedList('C');

    public static void main(String[] args) {
        new TowersOfHanoi().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++) {
            int disks = sc.nextInt();
            int moves = sc.nextInt();
            init(disks);
            System.out.format("Case #%s:%n", tc);
            solve(disks, moves);
        }
    }

    private void solve(int disks, int moves) {
        boolean isEven = disks % 2 == 0;
        int moveNumber = 1;
        printTowers();
        for (int i = 0; i < moves; i++) {
            switch (moveNumber) {
                case 1:
                    if (isEven) {
                        move(towerA, towerB);
                    } else {
                        move(towerA, towerC);
                    }
                    break;
                case 2:
                    if (isEven) {
                        move(towerA, towerC);
                    } else {
                        move(towerA, towerB);
                    }
                    break;
                case 3:
                    move(towerB, towerC);
                    moveNumber = 0;
                    break;
            }
            moveNumber++;
        }
    }

    private void printTowers() {
        System.out.println(towerA);
        System.out.println(towerB);
        System.out.println(towerC);
    }

    private void move(LinkedList left, LinkedList right) {
        if (!left.isEmpty() && (right.isEmpty() || (!right.isEmpty() && left.peek() < right.peek()))) {
            right.addLast(left.removeLast());
        } else {
            left.addLast(right.removeLast());
        }
        printTowers();
    }

    private void init(int disks) {
        towerA.clear();
        towerB.clear();
        towerC.clear();
        for (int i = 1; i <= disks; i++) {
            towerA.addFirst(i);
        }
    }

    static class LinkedList {
        int size;
        Node head;
        Node tail;
        char name;

        public LinkedList(char name) {
            this.name = name;
        }

        public void addFirst(Integer value) {
            Node node = new Node(value);
            if (size == 0) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
        }

        public void addLast(Integer value) {
            Node node = new Node(value);
            if (size == 0) {
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public Integer removeLast() {
            if (tail == null) {
                return null;
            }
            Node node = tail;
            tail = tail.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            size--;
            return node.value;
        }

        public Integer peek() {
            if (tail == null) {
                return null;
            }
            return tail.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void clear() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        static class Node {
            Integer value;
            Node next;
            Node prev;

            Node(int value) {
                this.value = value;
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder().append(this.name).append("=>");
            if (head != null) {
                Node node = head;
                while (node != null) {
                    builder.append(" ").append(node.value);
                    node = node.next;
                }
            }
            return builder.toString();
        }
    }
}
