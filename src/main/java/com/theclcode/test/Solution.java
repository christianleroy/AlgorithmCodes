package com.theclcode.test;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(6);
        Node n8 = new Node(6);
        Node n9 = new Node(6);
        Node n10 = new Node(6);
        n1.adjacent.add(n2);
        n1.adjacent.add(n3);
        n2.adjacent.add(n4);
        n2.adjacent.add(n5);
        n3.adjacent.add(n6);
        n4.adjacent.add(n7);
        n4.adjacent.add(n8);
        n8.adjacent.add(n9);
        n9.adjacent.add(n10);
        System.out.println(n1.dfs());


    }

    static class Node {
        int value;
        List<Node> adjacent = new ArrayList<>();
        public Node(int value) {
            this.value = value;
        }

        int dfs(){
            int size = this.adjacent.size();
            for(Node node : adjacent){
                size += node.dfs();
            }
            return size;
        }



    }
}
