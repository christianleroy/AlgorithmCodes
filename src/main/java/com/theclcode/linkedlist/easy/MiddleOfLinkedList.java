package com.theclcode.linkedlist.easy;

public class MiddleOfLinkedList {

    public static void main(String[] args) {
        int[][] arr = {
                {1}, // 1
                {1, 2}, // 2
                {1, 2, 3, 4, 5}, // 3
                {1, 2, 3, 4, 5, 6} // 4
        };

        for(int[] nums : arr) {
            ListNode node = null;
            ListNode root = null;
            for(int i = 0; i < nums.length; i++) {
                if(node == null) {
                    node = new ListNode(nums[i]);
                    root = node;
                } else {
                    ListNode newNode = new ListNode(nums[i]);
                    node.next = newNode;
                    node = newNode;
                }
            }
            System.out.println(middleNode(root).val);
        }
    }

    public static ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }

    // LeetCode
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
