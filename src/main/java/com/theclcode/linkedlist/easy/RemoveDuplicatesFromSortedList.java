package com.theclcode.linkedlist.easy;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 3, 3};

        ListNode head = null;
        ListNode node = null;
        for (int i = 0; i < arr.length; i++) {
            if (node == null) {
                head = node = new ListNode(arr[i]);
            } else {
                node.next = new ListNode(arr[i]);
                node = node.next;
            }
        }

        node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
        deleteDuplicates(head);
        node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }

    }

    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;
        ListNode next = head.next;

        while (next != null) {
            if (node.val == next.val) {
                next = next.next;
                node.next = next;
            } else {
                node = next;
                next = next.next;
            }
        }
        return head;
    }

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
