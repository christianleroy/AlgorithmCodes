package com.theclcode.searching.graph;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        Node node = new Node(1);
        node.neighbors.add(new Node(2));
        node.neighbors.add(new Node(3));

        Node node2 = new Node(4);
        node2.neighbors.add(new Node(5));
        node2.neighbors.add(new Node(6));

        node.neighbors.add(node2);

        Node nodeCopy = bfsCopy(node);

        nodePrintBfs(nodeCopy);
    }
    public static Node bfsCopy(Node src) {
        Node nodeCopy = new Node(src.value);
        for(Node child: src.neighbors) {
            Node childCopy = bfsCopy(child);
            nodeCopy.neighbors.add(childCopy);
        }
        return nodeCopy;
    }



    public static void nodePrintBfs(Node node) {
        System.out.println(node.value);
        for(Node child : node.neighbors) {
            nodePrintBfs(child);
        }
    }

    static class Node {

        int value;
        List<Node> neighbors = new ArrayList<>();

        Node(){

        }
        Node(int value) {
            this.value = value;
        }
    }
}
