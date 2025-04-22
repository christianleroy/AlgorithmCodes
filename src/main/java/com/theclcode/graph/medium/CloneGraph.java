package com.theclcode.graph.medium;

import java.util.*;

// https://leetcode.com/problems/clone-graph/description/?envType=problem-list-v2&envId=oizxjoit
public class CloneGraph {

    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        for(int i = 1; i <= nodes.length; i++) {
            nodes[i-1] = new Node(i);
        }

        nodes[0].neighbors.add(nodes[1]);
        nodes[0].neighbors.add(nodes[3]);

        nodes[1].neighbors.add(nodes[0]);
        nodes[1].neighbors.add(nodes[2]);

        nodes[2].neighbors.add(nodes[1]);
        nodes[2].neighbors.add(nodes[3]);

        nodes[3].neighbors.add(nodes[0]);
        nodes[3].neighbors.add(nodes[2]);

        Node node = cloneGraph(nodes[0]);
        print(node);
    }

    public static Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            Node _node = queue.poll();
            Node nodeCopy;

            if(map.containsKey(_node)) {
                nodeCopy = map.get(_node);
            } else {
                nodeCopy = new Node(_node.val);
                map.put(_node, nodeCopy);
            }

            for (Node neighbor : _node.neighbors) {
                Node neighborCopy;
                if(map.containsKey(neighbor)) {
                    neighborCopy = map.get(neighbor);
                } else {
                    neighborCopy = new Node(neighbor.val);
                    map.put(neighbor, neighborCopy);
                    queue.offer(neighbor);
                }
                nodeCopy.neighbors.add(neighborCopy);
            }

        }
        return map.get(node);
    }

    public static void print(Node node) {

        LinkedList<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(node);
        visited.add(node.val);

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();

            System.out.println("Node " + currNode.val);
            System.out.print("Neighbors: ");
            for (Node neighbor : currNode.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.contains(neighbor.val)) {
                    visited.add(neighbor.val);
                    queue.offer(neighbor);
                }
            }
            System.out.println();

        }
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
