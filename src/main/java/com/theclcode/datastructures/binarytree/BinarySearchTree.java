package com.theclcode.datastructures.binarytree;

public class BinarySearchTree {

    public static void main(String[] args) {
        new BinarySearchTree().run();
    }

    public void run(){
        Node node = new Node(1);
        node.insert(6);
        node.insert(4);
        node.insert(7);
        node.insert(5);
        node.insert(2);
        node.insert(8);
        node.insert(9);
        node.insert(3);
        node.printInOrder();
    }

    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public void insert(int value){
            Node node = new Node(value);
            if(value <= this.value){
                if(left == null){
                    left = node;
                } else {
                    left.insert(value);
                }
            } else {
                if(right == null){
                    right = node;
                } else {
                    right.insert(value);
                }
            }
        }

        public void printInOrder(){
            if(left != null){
                left.printInOrder();
            }
            System.out.println(value);
            if(right != null){
                right.printInOrder();
            }
        }
    }
}

