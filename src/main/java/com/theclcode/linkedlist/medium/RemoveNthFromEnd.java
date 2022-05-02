package com.theclcode.linkedlist.medium;

public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode node = root;
        for (int i = 2; i <= 4; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }

        node = removeNthFromEnd(root, 4);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        int k = 1;
        while (fast != null && fast.next != null) {
            if (k >= n) {
                prev = slow;
                slow = slow.next;
            }
            fast = fast.next;
            k++;
        }

        if (prev == slow) {
            head = head.next;
        } else {
            prev.next = slow.next;
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
