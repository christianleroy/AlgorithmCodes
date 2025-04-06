package com.theclcode.graph;

import java.util.*;

public class BreadthFirstSearch {

    public static void main(String[] args) {

        Node[] nodes = new Node[10];

        for(int i = 1; i <= 10; i++) {
            nodes[i-1] = new Node(i);
        }

        nodes[0].children.add(nodes[1]);
        nodes[0].children.add(nodes[2]);
        nodes[0].children.add(nodes[3]);

        nodes[1].children.add(nodes[4]);
        nodes[1].children.add(nodes[5]);

        nodes[2].children.add(nodes[6]);
        nodes[3].children.add(nodes[7]);
        nodes[4].children.add(nodes[8]);

        nodes[7].children.add(nodes[9]);

        nodePrintBfs(nodes[0]);
    }

    public static void nodePrintBfs(Node node) {

        Queue<Node> stack = new ArrayDeque<>();
        stack.offer(node);

        while(!stack.isEmpty()) {
            node = stack.poll();
            System.out.println(node.value);

            for(Node child : node.children) {
                stack.offer(child);
            }
        }

    }

    static class Node {

        int value;
        List<Node> children = new ArrayList<>();

        Node(){

        }
        Node(int value) {
            this.value = value;
        }
    }
}
