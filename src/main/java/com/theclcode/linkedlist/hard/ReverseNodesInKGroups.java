package com.theclcode.linkedlist.hard;

//https://leetcode.com/problems/reverse-nodes-in-k-group/description/
public class ReverseNodesInKGroups {

    public static void main(String[] args) {
        ListNode[] arr = new ListNode[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode(i + 1);
            if (i > 0) {
                arr[i - 1].next = arr[i];
            }
        }
        print(reverseNodes(arr[0], 3));
    }

    public static ListNode reverseNodes(ListNode head, int limit) {

        ListNode fast = head;
        ListNode slow = head;
        ListNode result = null;
        ListNode prev = null;

        while (fast != null) {
            int k = 1;
            while(fast != null && k < limit) {
                fast = fast.next;
                k++;
            }
            if(fast != null) {
                if(result == null) {
                    result = fast;
                }
                if(prev != null) {
                    prev.next = fast;
                }
                ListNode fastNext = fast.next;
                ListNode node = slow;

                while( node != null && node != fastNext) {
                    ListNode next = node.next;
                    node.next = prev;
                    prev = node;
                    node = next;
                }

                prev = slow;
                slow.next = fastNext;
                slow = fast = fastNext;
            }
        }
        return result;
    }

    static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

}
