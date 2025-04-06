package com.theclcode.linkedlist.easy;

// https://leetcode.com/problems/reverse-linked-list/submissions/1488751712/
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode trav = node;

        while(trav != null) {
            System.out.println(trav.val);
            trav = trav.next;
        }

        ListNode rev = reverseList(node);

        while(rev != null) {
            System.out.println(rev.val);
            rev = rev.next;
        }
    }

    public static ListNode reverseList(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev;
        ListNode node = head;
        ListNode next = head.next;

        while(next != null) {
            prev = node;
            node = next;

            if(prev == head) {
                prev.next = null;
            }
            next = node.next;
            node.next = prev;
        }
        return node;
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
