package com.theclcode.swc.newsletter.oct20.numbermaze;

import java.util.Scanner;

//Uses heap and dijkstra
public class NumberMaze {

    private Node[][] grid;
    private int N;
    private int M;

    public static void main(String[] args) {
        new NumberMaze().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init(sc);
            traverse();
            System.out.format("Case #%s: %s\n", i, this.grid[N-1][M-1]);
        }
    }

    private void traverse() {
        Heap heap = new Heap(N * M);
        heap.insert(this.grid[0][0]);
        while (!heap.isEmpty()) {
            Node node = heap.delete();
            node.setVisited(true);
            int x = node.x;
            int y = node.y;

            Node adjacent = null;
            if (y - 1 >= 0) {
                adjacent = this.grid[x][y - 1];
                checkAdjacent(heap, node, adjacent);
            }
            if (x - 1 >= 0) {
                adjacent = this.grid[x - 1][y];
                checkAdjacent(heap, node, adjacent);
            }
            if (y + 1 < M) {
                adjacent = this.grid[x][y + 1];
                checkAdjacent(heap, node, adjacent);
            }
            if (x + 1 < N) {
                adjacent = this.grid[x + 1][y];
                checkAdjacent(heap, node, adjacent);
            }
        }
    }

    private void checkAdjacent(Heap heap, Node node, Node adjacent) {
        if (!adjacent.isVisited) {
            adjacent.setVisited(true);
            adjacent.bestCost += node.bestCost;
            heap.insert(adjacent);
        } else {
            if (node.bestCost + adjacent.bestCost < adjacent.bestCost) {
                adjacent.bestCost = node.bestCost + adjacent.bestCost;
            }
        }
    }

    private void init(Scanner sc) {
        this.N = sc.nextInt();
        this.M = sc.nextInt();
        this.grid = new Node[N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                this.grid[x][y] = new Node(x, y, sc.nextInt());
            }
        }
    }

    private static class Heap {
        Node[] arr;
        int marker;

        Heap(int size) {
            this.arr = new Node[size];
        }

        void insert(Node node) {
            int index = marker++;
            arr[index] = node;
            int parent = ((index + 1) / 2) - 1;
            while (parent >= 0 && arr[index].bestCost < arr[parent].bestCost) {
                swap(index, parent);
                index = parent;
                parent = ((index + 1) / 2) - 1;
            }
        }

        Node delete() {
            if (marker == 0) {
                return null;
            }
            int index = 0;
            final int lastIndex = --marker;
            swap(index, lastIndex);
            int left = ((index + 1) * 2) - 1;
            int right = ((index + 1) * 2 + 1) - 1;
            boolean inserted = true;
            while (inserted && (left < lastIndex || right < lastIndex)) {
                inserted = false;
                if (left < lastIndex && right < lastIndex) {
                    int min = arr[left].bestCost <= arr[right].bestCost ? left : right;
                    if (arr[min].bestCost < arr[index].bestCost) {
                        swap(index, min);
                        index = min;
                        inserted = true;
                    }
                } else if (left < lastIndex && arr[left].bestCost < arr[index].bestCost) {
                    swap(index, left);
                    index = left;
                    inserted = true;
                } else if (right < lastIndex && arr[right].bestCost < lastIndex) {
                    swap(index, right);
                    index = right;
                    inserted = true;
                }
                left = ((index + 1) * 2) - 1;
                right = ((index + 1) * 2 + 1) - 1;
            }
            return arr[lastIndex];
        }

        void swap(int left, int right) {
            Node temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        boolean isEmpty() {
            return marker == 0;
        }
    }

    private static class Node {
        final int x;
        final int y;
        int initialCost;
        boolean isVisited;
        int bestCost;

        Node(int x, int y, int initialCost) {
            this.x = x;
            this.y = y;
            this.initialCost = initialCost;
            this.bestCost = initialCost;
        }

        public void setBestCost(int bestCost) {
            this.bestCost = bestCost;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        @Override
        public String toString() {
            return String.valueOf(bestCost);
        }
    }
}
