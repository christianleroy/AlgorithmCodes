package com.theclcode.linkedlist.easy;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {

    public static void main(String[] args) {
        // [1,2,2,3,4,4,3]

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));

    }

    public static boolean isSymmetric(TreeNode root) {

        if(root == null) {
            return true;
        }

        Deque<TreeNode[]> stack = new LinkedList<>();
        stack.add(new TreeNode[] {root.left, root.right});

        while(!stack.isEmpty()) {
            TreeNode[] tree = stack.pollLast();
            TreeNode left = tree[0];
            TreeNode right = tree[1];

            if(left == null && right == null) {
                continue;
            }

            if(left == null || right == null || left.val != right.val) {
                return false;
            }
            stack.add(new TreeNode[] { left.left, right.right });
            stack.add(new TreeNode[] { left.right, right.left });
        }
        return true;
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
