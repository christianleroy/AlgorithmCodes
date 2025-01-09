package com.theclcode.trees.balancedtree;

// A height-balanced binary tree is defined as a binary tree in which the height of the left and the right subtree of any node differ by not more than 1
public class HeightBalancedTree {

    public static class Node {
        int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static class BalancedTree {
        Node node;
        public BalancedTree(Node node){
            this.node = node;
        }

        public boolean isBalanced(){
            int left = findHeight(node.left, 0);
            int right = findHeight(node.right, 0);
            if(left-right>=-1 && left-right<=1){
                return true;
            }
            return false;
        }

        public int findHeight(Node node, int height) {
            if(node==null){
                return height;
            }
            height++;
            int leftHeight = findHeight(node.left, height);
            int rightHeight = findHeight(node.right, height);
            if(leftHeight>=rightHeight){
                return leftHeight;
            } else {
                return rightHeight;
            }
        }
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);

        one.left=two;
        one.right=three;
        two.left=four;
        two.right=five;
        four.left=six;
        four.right=eight;
        five.right=seven;
        three.right=nine;
        nine.right=ten;
        boolean res = new BalancedTree(one).isBalanced();
        System.out.print(res);
    }
}
