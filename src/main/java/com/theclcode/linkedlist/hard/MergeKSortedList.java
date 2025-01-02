package com.theclcode.linkedlist.hard;


//https://leetcode.com/problems/merge-k-sorted-lists/description/

import java.util.List;

public class MergeKSortedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(9);

        ListNode node9 = new ListNode(2);
        ListNode node10 = new ListNode(6);

        ListNode node11 = new ListNode(3);
        ListNode node12 = new ListNode(11);
        ListNode node13 = new ListNode(36);

        ListNode node14 = new ListNode(-5);
        ListNode node15 = new ListNode(5);
        ListNode node16 = new ListNode(35);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        node9.next = node10;

        node11.next = node12;
        node12.next = node13;

        node14.next = node15;
        node15.next = node16;

        ListNode[] arr1 = {node1, node5, node9, node11, node14};
        printListNode(mergeKLists(arr1));
    }


    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return mergeSort(lists, 0, lists.length - 1);
    }

    public static ListNode mergeSort(ListNode[] lists, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            ListNode leftNode = mergeSort(lists, start, mid);
            ListNode rightNode = mergeSort(lists, mid+1, end);
            return merge(leftNode, rightNode);
        }
        return lists[start];
    }

    public static ListNode merge(ListNode left, ListNode right) {

        ListNode node = null;
        ListNode head = null;

        if(left == null && right == null){
            return null;
        }

        while(left != null || right != null) {
            ListNode nodeToAdd = null;
            if(right == null || (left != null && left.val <= right.val)) {
                nodeToAdd = left;
                left = left.next;
            } else {
                nodeToAdd = right;
                right = right.next;
            }
            if(head == null) {
                node = head = nodeToAdd;
            } else {
                node.next = nodeToAdd;
                node = nodeToAdd;
            }
        }
        return head;

    }



    //Primitive
    public static ListNode mergeKListsLinear(ListNode[] lists) {
        ListNode head = null;
        ListNode tail = null;
        for(ListNode list : lists) {
            ListNode node = list;

            while(node != null) {
                ListNode newNode = new ListNode(node.val);
                if (head == null) {
                    head = tail = newNode;
                    node = node.next;
                    continue;
                }

                if(newNode.val <= head.val) {
                    newNode.next = head;
                    head = newNode;
                } else if(newNode.val >= tail.val) {
                    tail.next = newNode;
                    tail = newNode;
                } else {

                    ListNode currNode = head;
                    ListNode prevNode = null;

                    while(newNode.val > currNode.val) {
                        prevNode = currNode;
                        currNode = currNode.next;
                    }

                    prevNode.next = newNode;
                    newNode.next = currNode;

                }
                node = node.next;

            }
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void printListNode(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
