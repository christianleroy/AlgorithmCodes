package com.theclcode.trees.medium;

// https://leetcode.com/problems/balanced-binary-tree/description/
public class HeightBalancedTree {
    public static void main(String[] args) {
        TreeNode[] nodes = new TreeNode[5];
        for(int i = 1; i < nodes.length; i ++) {
            nodes[i-1] = new TreeNode(i);
        }

        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];

        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];

        System.out.println(isBalanced(nodes[0]));

    }

    public static boolean isBalanced(TreeNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        // Checks if all subtrees are balanced from root view AND
        // Checks that all subtrees are balanced on their own recursively
        return (Math.abs(dfs(root.left) - dfs(root.right)) < 2 &&
                isBalanced(root.left) &&
                isBalanced(root.right));
    }

    private static int dfs(TreeNode root) {

        if (root == null) {
            return -1;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        return 1 + Math.max(left, right);
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
