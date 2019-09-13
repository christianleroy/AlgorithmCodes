package com.theclcode.test;

import java.util.*;

public class Solution {
    static class Node {
        int id;
        List<Node> adjacent = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Node> getAdjacent() {
            return adjacent;
        }

        public void setAdjacent(List<Node> adjacent) {
            this.adjacent = adjacent;
        }
    }

    public static void main(String[] args){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        node1.getAdjacent().add(node2);
        node1.getAdjacent().add(node3);
        node1.getAdjacent().add(node4);

        node2.getAdjacent().add(node5);
        node2.getAdjacent().add(node6);

        node3.getAdjacent().add(node7);
        node3.getAdjacent().add(node6);

        node4.getAdjacent().add(node11);

        node6.getAdjacent().add(node3);

        node7.getAdjacent().add(node8);
        node7.getAdjacent().add(node9);
        node7.getAdjacent().add(node10);

        Node node13 = new Node(13);

        Node toFind = node13;
//
        if(!searchByDfs(node1, toFind, new ArrayList<>())){
            System.out.println("Cannot find node " + toFind.getId());
        }
//
//        searchByBfs(node1, toFind, new ArrayList<>(), new LinkedList<>());

        char[] str = "abc".toCharArray();
//        permute(str, 0, str.length-1);
//        combine(str, 0, 2);

    }



    public static boolean searchByDfs(Node source, Node destination, List<Integer> visited){
        if(visited.contains(source.getId())){
            return false;
        }
        System.out.println("Checking node "+source.getId()+"...");
        if(source.getId()==destination.getId()){
            System.out.println("Found destination "+destination.getId());
            return true;
        }

        visited.add(source.getId());
        System.out.println("Checking children of node "+source.getId());
        for(Node adjacent : source.getAdjacent()){
            if(searchByDfs(adjacent, destination, visited)){
                return true;
            }
        }
        return false;
    }


    public static void swap(char[] str, int a, int b){
      char temp = str[a];
      str[a] = str[b];
      str[b] = temp;
    }

    public static void permute(char[] str, int start, int end){
        if(start==end){
            System.out.println(new String(str));
            return;
        }

        for(int i=start; i<=end; i++){
            swap(str, start, i);
            permute(str, start+1, end);
            swap(str, start, i);
        }
    }

    static int limit = 3;
    static char[] stack = new char[limit];
    static int stackMarker = 0;

    public static void combine(char[] str, int start, int limit){
        if(limit==0){
            System.out.println(new String(stack));
            return;
        }
        for(int i=start; i<=str.length-limit; i++){
           stack[stackMarker++] = str[i];
           combine(str, i+1, limit-1);
           stack[--stackMarker] = '\0';
        }

    }
}
