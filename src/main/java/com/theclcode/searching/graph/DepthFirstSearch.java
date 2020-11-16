package com.theclcode.searching.graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    public static void main(String[] args) {
        Node _1 = new Node(1);
        Node _2 = new Node(2);
        Node _3 = new Node(3);
        Node _4 = new Node(4);
        Node _5 = new Node(5);
        Node _6 = new Node(6);
        Node _7 = new Node(7);
        Node _8 = new Node(8);
        Node _9 = new Node(9);
        Node _10 = new Node(10);


        _1.getAdjacent().add(_2);
        _1.getAdjacent().add(_3);
        _2.getAdjacent().add(_4);
        _2.getAdjacent().add(_5);
        _3.getAdjacent().add(_6);
        _4.getAdjacent().add(_10);
        _5.getAdjacent().add(_8);
        _6.getAdjacent().add(_7);
        _8.getAdjacent().add(_9);

//        _1.recursiveDfs();
        _1.stackDfs();
    }


    static class Node {
        int value;
        List<Node> adjacent = new ArrayList<>();

        Node(int value){
            this.value = value;
        }

        public void recursiveDfs(){
            System.out.println(value);
            for(Node child : adjacent){
                child.recursiveDfs();
            }
        }

        public void stackDfs(){
            LinkedList<Node> stack = new LinkedList<>();
            stack.add(this);
            while(!stack.isEmpty()){
                Node node = stack.remove();
                System.out.println(node.value);
                for(Node child : node.getAdjacent()){
                    stack.add(child);
                }
            }
        }

        public List<Node> getAdjacent() {
            return adjacent;
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
