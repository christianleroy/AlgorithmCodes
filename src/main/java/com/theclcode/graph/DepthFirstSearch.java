package com.theclcode.graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

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

        // Both will be DFS, but stack will process right first and recursive will do left first
        nodes[0].stackDfs();
        System.out.println();
        nodes[0].recursiveDfs();
    }


    static class Node {
        int value;
        List<Node> children = new ArrayList<>();

        Node(int value){
            this.value = value;
        }

        public void recursiveDfs(){
            System.out.print(value + " ");
            for(Node child : children){
                child.recursiveDfs();
            }
        }

        public void stackDfs(){
            LinkedList<Node> stack = new LinkedList<>();
            stack.add(this);
            while(!stack.isEmpty()){
                Node node = stack.remove();
                System.out.print(node.value + " ");
                for(Node child : node.getChildren()){
                    stack.add(child);
                }
            }
        }

        public List<Node> getChildren() {
            return children;
        }
    }

    static class LinkedList<E> {
        Node<E> head;
        Node<E> tail;
        int size = 0;

        public void add(E value){
            Node<E> node = new Node<>(value);
            if(tail == null){
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public E remove(){
           if(tail == null){
               return null;
           }
           Node<E> node = tail;
           tail = tail.prev;
           if(tail == null){
               head = null;
           } else {
               tail.next = null;
           }
           size--;
           return node.value;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        class Node<E> {
            E value;
            Node<E> prev;
            Node<E> next;

            Node(E value){
                this.value = value;
            }
        }
    }
}
