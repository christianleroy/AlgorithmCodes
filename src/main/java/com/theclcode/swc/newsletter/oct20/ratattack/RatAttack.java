package com.theclcode.swc.newsletter.oct20.ratattack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class RatAttack {

    private int[][] matrix;
    private boolean[][] visited;
    private int radius;
    private int maxKilled;
    private LinkedList maxKilledList;
    private static final int N = 1025;

    public static void main(String[] args) {
        new RatAttack().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
            this.radius = sc.nextInt();
            int rattyCells = sc.nextInt();
            for (int j = 0; j < rattyCells; j++) {
                this.visited = new boolean[N][N];
                int x = sc.nextInt();
                int y = sc.nextInt();
                int rats = sc.nextInt();
                traverse(x, y, rats);
            }
            int[] answer = this.maxKilledList.getTop();
            System.out.format("Case #%s: %s %s %s\n", i, answer[0], answer[1], maxKilled);
        }
    }

    private void init() {
        this.matrix = new int[N][N];
        this.maxKilled = 0;
    }

    private void traverse(int x, int y, int rats) {
        Deque<Integer[]> deq = new ArrayDeque<>();
        Integer[] source = {x , y};
        this.visited[x][y] = true;
        deq.add(new Integer[]{x, y});
        while (!deq.isEmpty()) {
            Integer[] ints = deq.remove();
            x = ints[0];
            y = ints[1];
            this.matrix[x][y] += rats;

            if (this.matrix[x][y] >= maxKilled) {
                if (this.matrix[x][y] > maxKilled) {
                    this.maxKilled = this.matrix[x][y];
                    this.maxKilledList = new LinkedList();
                    maxKilledList.add(new int[]{x, y});
                } else {
                    maxKilledList.add(new int[]{x, y});
                }
            }

            if (x - 1 >= 0 && !this.visited[x - 1][y]) {
                Integer[] location = {x - 1, y};
                addIfReachable(deq, source, location);
            }
            if (y - 1 >= 0 && !this.visited[x][y - 1]) {
                Integer[] location = {x, y - 1};
                addIfReachable(deq, source, location);
            }

            if (x + 1 < N && !this.visited[x + 1][y]) {
                Integer[] location = {x + 1, y};
                addIfReachable(deq, source, location);
            }

            if (y + 1 < N && !this.visited[x][y + 1]) {
                Integer[] location = {x, y + 1};
                addIfReachable(deq, source, location);
            }
        }
    }

    private void addIfReachable(Deque<Integer[]> deq, Integer[] ints, Integer[] location) {
        if (getDistance(ints, location) <= radius) {
            deq.add(location);
            this.visited[location[0]][location[1]] = true;
        }
    }

    private int getDistance(Integer[] from, Integer[] to) {
        int x = Math.abs(from[0] - to[0]);
        int y = Math.abs(from[1] - to[1]);
        return Math.max(x, y);
    }

    private static class LinkedList {
        int size;
        Node head;
        Node tail;

        public void add(int[] value) {
            Node node = new Node(value);
            if (size == 0) {
                head = tail = node;
            } else {
                boolean inserted = false;
                Node existing = head;
                while (existing != null && !inserted) {
                    if (value[0] <= existing.value[0]) {
                        if (value[0] == existing.value[0]) {
                            if (value[1] < existing.value[1]) {
                                insertNode(node, existing);
                                inserted = true;
                            }
                        } else {
                            insertNode(node, existing);
                            inserted = true;
                        }
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

        private int[] getTop() {
            return this.head.value;
        }

        private void insertNode(Node node, Node existing) {
            node.next = existing;
            node.prev = existing.prev;
            if (existing.prev != null) {
                existing.prev.next = node;
            }
            existing.prev = node;
            if (existing == head) {
                head = node;
            }
        }

        class Node {
            int[] value;
            Node prev;
            Node next;

            Node(int[] value) {
                this.value = value;
            }
        }
    }

}
