package com.theclcode.graph.easy;

// https://leetcode.com/problems/flood-fill/
public class FloodFill {

    public static void main(String[] args) {
        int[][] dim = {
                {0, 0, 0},
                {0, 1, 1}
        };

        floodFill(dim, 1, 1, 1);

        for (int i = 0; i < dim.length; i++) {
            for (int j = 0; j < dim[i].length; j++) {
                System.out.format("%s", dim[i][j]);
            }
            System.out.println();
        }

    }

    public static int[][] floodFill(int[][] image, int i, int j, int newColor) {
        int color = image[i][j];
        if (color == newColor) {
            return image;
        }
        dfs(image, i, j, newColor, color);
        return image;
    }

    public static void dfs(int[][] image, int i, int j, int newColor, int color) {
        if (image[i][j] == color) {
            image[i][j] = newColor;
            if (i > 0) {
                dfs(image, i - 1, j, newColor, color);
            }

            if (j > 0) {
                dfs(image, i, j - 1, newColor, color);
            }

            if (i + 1 < image.length) {
                dfs(image, i + 1, j, newColor, color);
            }

            if (j + 1 < image[0].length) {
                dfs(image, i, j + 1, newColor, color);
            }
        }
    }

    public static int[][] floodFillBfs(int[][] image, int i, int j, int newColor) {

        int x = image.length;
        int y = image[0].length;
        int color = image[i][j];

        if (color == newColor) {
            return image;
        }

        Queue queue = new Queue();
        queue.add(i, j);
        Node node;

        while ((node = queue.remove()) != null) {
            i = node.val[0];
            j = node.val[1];
            image[i][j] = newColor;

            if (i > 0) {
                if (color == image[i - 1][j]) {
                    queue.add(i - 1, j);
                }
            }

            if (j > 0) {
                if (color == image[i][j - 1]) {
                    queue.add(i, j - 1);
                }
            }

            if (i < x - 1) {
                if (color == image[i + 1][j]) {
                    queue.add(i + 1, j);
                }
            }

            if (j < y - 1) {
                if (color == image[i][j + 1]) {
                    queue.add(i, j + 1);
                }
            }
        }

        return image;
    }

    private static class Queue {
        Node head;
        Node tail;

        Node remove() {
            if (head == null) {
                return null;
            }
            Node node = head;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return node;
        }

        void add(int x, int y) {

            Node node = new Node(x, y);

            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

        }
    }

    private static class Node {
        int[] val;
        Node next;

        Node(int x, int y) {
            this.val = new int[]{x, y};
        }
    }
}
