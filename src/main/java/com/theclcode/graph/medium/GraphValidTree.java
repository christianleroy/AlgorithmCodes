package com.theclcode.graph.medium;

import java.util.*;

// https://leetcode.com/problems/graph-valid-tree/description/?envType=problem-list-v2&envId=oizxjoit
public class GraphValidTree {

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println(validTree(5, edges));
    }

    public static boolean validTree(int n, int[][] edges) {
        if(edges == null || edges.length == 0 && edges.length == n - 1) {
            return true;
        }

        Map<Integer, Node> map = new HashMap<>();
        int expectedEdgesCount = n - 1;
        int actualEdgesCount = 0;
        for(int i = 0; i < edges.length; i++) {
            Node left = getOrCreateNode(edges, map, i, 0);
            Node right = getOrCreateNode(edges, map, i, 1);

            left.connections.add(right);
            right.connections.add(left);
            actualEdgesCount++;
        }


        if(actualEdgesCount != expectedEdgesCount) {
            return false;
        }

        Set<Integer> visated = new HashSet<>();
        return map.get(edges[0][0]).dfs(null, visated) && visated.size() == n;
    }

    private static Node getOrCreateNode(int[][] edges, Map<Integer, Node> map, int i, int j) {
        Node node;
        if(map.containsKey(edges[i][j])) {
            node = map.get(edges[i][j]);
        } else {
            node = new Node(edges[i][j]);
            map.put(edges[i][j], node);
        }
        return node;
    }

    static class Node {
        int val;
        List<Node> connections;

        Node(int val) {
            this.val = val;
            this.connections = new ArrayList<>();
        }

        boolean dfs(Node parent, Set<Integer> visited) {
            visited.add(this.val);
            for(Node connection : connections) {
                if(parent == null || parent.val != connection.val) {
                    if(visited.contains(connection.val)) {
                        return false;
                    }
                    connection.dfs(this, visited);
                }
            }

            return true;
        }

    }
}
