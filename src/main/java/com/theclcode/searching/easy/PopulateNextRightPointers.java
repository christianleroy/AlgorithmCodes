package com.theclcode.searching.easy;

import java.util.ArrayDeque;
import java.util.Queue;

public class PopulateNextRightPointers {
    
    public static void main(String[] args) {

        Node node = new Node(1);
        Node root = node;
        node.left = new Node(2);
        node.right = new Node(3);

        node = node.left;
        node.left = new Node(4);
        node.right = new Node(5);

        node = root.right;
        node.left = new Node(6);
        node.right = new Node(7);


        connect(root);
        connect(null);
    }

    public static Node connectQueue(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        Node node;
        while ((node = queue.poll()) != null) {
            if (node.left != null) {
                node.left.next = node.right;
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
            }
        }

        return root;
    }

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Node node = root;

        while (node.left != null) {
            Node curr = node;

            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                curr = curr.next;
            }
            node = node.left;
        }

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
