package com.theclcode.trees.medium;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
public class LowestCommonAncestorOfBinaryTreeIV {
    static TreeNode lca;

    public static void main(String[] args) {

        TreeNode[] nodes = new TreeNode[6];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i + 1);
        }

        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];

        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];

        nodes[2].right = nodes[5];

        TreeNode lca = lowestCommonAncestor(nodes[0], new TreeNode[]{ nodes[3], nodes[4] });
        System.out.println(lca.val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nodes.length; i++) {
            set.add(nodes[i].val);
        }

        dfs(root, set);
        return lca;
    }

    static int dfs(TreeNode root, Set<Integer> set) {

        if (root == null) {
            return 0;
        }

        int left = dfs(root.left, set);
        int right = dfs(root.right, set);
        int found = left + right;

        if (set.contains(root.val)) {
            found++;
        }

        if (found == set.size() && lca == null) {
            lca = root;
        }

        return found;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
