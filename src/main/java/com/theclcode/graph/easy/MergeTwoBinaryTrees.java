package com.theclcode.graph.easy;

//
public class MergeTwoBinaryTrees {

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(4);
        tree1.right = new TreeNode(5);
        TreeNode tree2 = new TreeNode(2);
        tree2.right = new TreeNode(1);
        tree2.right.right = new TreeNode(10);

        TreeNode merged = dfs(tree1, tree2);
        print(merged);
    }

    public static void print(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);
            print(node.left);
            print(node.right);
        }

    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    public static TreeNode dfs(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) {
            return null;
        }

        TreeNode merged = null;
        if (root1 != null && root2 != null) {
            merged = new TreeNode(root1.val + root2.val);
        } else if (root1 != null) {
            merged = new TreeNode(root1.val);
        } else if (root2 != null) {
            merged = new TreeNode(root2.val);
        }

        merged.left = dfs(root1 != null ? root1.left : null, root2 != null ? root2.left : null);
        merged.right = dfs(root1 != null ? root1.right : null, root2 != null ? root2.right : null);

        return merged;
    }

    public static class TreeNode {
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
