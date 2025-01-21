package com.theclcode.linkedlist.easy;

//https://leetcode.com/problems/merge-two-sorted-lists
public class MergeTwoSortedList {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);

        ListNode list4 = new ListNode(4);
        ListNode list5 = new ListNode(5);
        ListNode list6 = new ListNode(6);

        list1.next = list2;
        list2.next = list3;

        list4.next = list5;
        list5.next = list6;

        ListNode res = mergeTwoLists(list4, list1);
        System.out.println(res);

    }

    public static ListNode mergeTwoLists(ListNode node1, ListNode node2) {

        ListNode curr = null;
        ListNode head = null;

        while(node1 != null || node2 != null) {
            if(node2 == null || (node1 != null && node1.val <= node2.val)) {
                if(curr == null) {
                    head = curr = node1;
                } else {
                    curr.next = node1;
                    curr = node1;
                }
                node1 = node1.next;
            } else {
                if(curr == null) {
                    head = curr = node2;
                } else {
                    curr.next = node2;
                    curr = node2;
                }
                node2 = node2.next;
            }
            curr.next = null;
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
