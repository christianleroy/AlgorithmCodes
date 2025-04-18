package com.theclcode.trees.medium;

// LeetCode easy, but medium for me!!
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
public class ConvertSortedArrayToBST {

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode node = sortedArrayToBST(nums);
        System.out.println(node.val);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    public static TreeNode helper(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            TreeNode node = new TreeNode(nums[mid]);

            node.left = helper(nums, start, mid);
            node.right = helper(nums, mid + 1, end);

            return node;
        } else {
            return null;
        }
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
