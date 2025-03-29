package com.theclcode.trees.treeheight;

import java.util.ArrayList;
import java.util.List;

public class TreeHeight {

    public static void main(String[] args) {

        Node[] nodes = new Node[12];

        for(int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }

        nodes[1].children.add(nodes[2]);
        nodes[1].children.add(nodes[3]);
        nodes[1].children.add(nodes[4]);

        nodes[2].children.add(nodes[5]);
        nodes[2].children.add(nodes[6]);

        nodes[3].children.add(nodes[7]);

        nodes[4].children.add(nodes[8]);

        nodes[5].children.add(nodes[6]);

        nodes[8].children.add(nodes[10]);
        nodes[8].children.add(nodes[11]);

        System.out.println(dfs(nodes[1]));
    }

    static int dfs(Node node) {
        if(node == null) {
            return 0;
        }
        int max = 0;
        for(Node child: node.children) {
            max = Math.max(max, dfs(child));
        }
        return 1 + max;
    }


    private static class Node {
        int val;
        List<Node> children = new ArrayList<>();

        Node(int val) {
            this.val = val;
        }
    }
}
