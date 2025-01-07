package com.theclcode.linkedlist.easy;

// Numbers are processed in reverse. So 911 + 21 would actually be 119 + 12
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode root1 = l1;
        l1.next = new ListNode(1);
        l1 = l1.next;
        l1.next = new ListNode(1);
        l1 = l1.next;
        l1.next = new ListNode(9);
        l1 = l1.next;
        l1.next = new ListNode(9);
        l1 = l1.next;
        l1.next = new ListNode(9);
        l1 = l1.next;
        l1.next = new ListNode(9);


        ListNode l2 = new ListNode(9);
        ListNode root2 = l2;
        l2.next = new ListNode(9);
        l2 = l2.next;
        l2.next = new ListNode(9);
        l2 = l2.next;
        l2.next = new ListNode(9);
        l2 = l2.next;

        ListNode node = addTwoNumbers(root1, root2);
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode node1, ListNode node2) {

        ListNode root = new ListNode(-1);
        ListNode curr = root;

        int rem = 0, sum = 0;

        while (node1 != null || node2 != null) {
            sum = rem;
            if (node1 != null && node2 != null) {
                sum += node1.val + node2.val;
            } else {
                sum += node1 != null ? node1.val : node2.val;
            }

            rem = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            node1 = node1 != null ? node1.next : null;
            node2 = node2 != null ? node2.next : null;
        }

        if (rem != 0) {
            curr.next = new ListNode(rem);
        }

        return root.next;

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
