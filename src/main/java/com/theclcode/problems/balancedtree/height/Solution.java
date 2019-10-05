package com.theclcode.problems.balancedtree.height;

public class Solution {

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
            int result = findHeight(node, 0);
            if(result==0){
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
            return height;
        }
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);

        one.left=two;
        two.left=three;

        boolean res = new BalancedTree(one).isBalanced();
        System.out.print(res);

    }
}
