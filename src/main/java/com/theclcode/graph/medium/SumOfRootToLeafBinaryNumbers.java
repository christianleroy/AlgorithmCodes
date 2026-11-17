package com.theclcode.graph.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SumOfRootToLeafBinaryNumbers {

    public static void main(String[] args) {


        TreeNode root = new TreeNode(1);
        TreeNode branchL = new TreeNode(0);
        TreeNode branchR = new TreeNode(1);

        root.left = branchL;
        root.right = branchR;

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        System.out.println("Best: " + dfs(root, 0));

        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> sums = new ArrayList<>();
        digIn(root, stack, sums);


        System.out.println("Total sum: " + sums.stream().mapToInt(Integer::intValue).sum());

    }

//  best
    private static int dfs(TreeNode root, int sum) {
        if(root == null) return 0;
        sum = sum * 2 + root.val;
        return root.left == root.right ? sum : dfs(root.left, sum) + dfs(root.right, sum);
    }

    private static void digIn(TreeNode node, LinkedList<TreeNode> stack, List<Integer> sums){
        if(node == null) {
            print(stack, sums);
            return;
        }

        stack.add(node);

        if(node.left == null && node.right == null){
            print(stack, sums);
        } else {
            if(node.left != null){
                digIn(node.left, stack, sums);
            }
            if(node.right != null){
                digIn(node.right, stack, sums);
            }
        }
        stack.removeLast();
    }

    private static void print(LinkedList<TreeNode> stack, List<Integer> sums) {
        int pow = stack.size() - 1;
        int sum = 0;
        for(TreeNode node1 : stack){
            if(node1.val != 0) {
                sum += (int) Math.pow(2, pow);
            }
            pow--;
            System.out.print(node1.val);
        }
        System.out.println(" = " + sum);
        sums.add(sum);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
