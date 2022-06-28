package com.theclcode.linkedlist.medium;

// https://leetcode.com/problems/swap-nodes-in-pairs/submissions/
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);

        node = head;

        printNodes(node);
        node = swapPairs(head);
        printNodes(node);

    }

    private static void printNodes(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode swapPairs(ListNode head) {

        ListNode node = head;
        ListNode prev = null;
        while (node != null && node.next != null) {
            ListNode curr = node;
            ListNode next = node.next;

            if (head == node) {
                head = node.next;
            }

            curr.next = next.next;
            next.next = curr;

            if (prev != null) {
                prev.next = next;
            }

            prev = curr;
            node = curr.next;
        }

        return head;
    }

    // Definition for singly-linked list. LeetCode
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
