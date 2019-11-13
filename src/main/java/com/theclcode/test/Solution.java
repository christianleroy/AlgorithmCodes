package com.theclcode.test;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
//        int node = 4;
//        int res = (node - 1) / 2;
//        System.out.println(res);
        Heap heap = new Heap(5);
        heap.offer(5);
        heap.offer(1);
        heap.offer(6);
        heap.offer(0);
        System.out.println();
    }
}

class Heap {
    int arr[];
    int size;
    int maxsize;
    public Heap(int maxsize) {
        arr = new int[maxsize];
        this.maxsize = maxsize;
        size = 0;
    }
    void offer(int num) {
        arr[size++] = num;
        int node = size - 1;
        while (node != 0) {
            int parent = (node - 1) / 2;
            if (arr[node] < arr[parent]) {
                int temp = arr[node];
                arr[node] = arr[parent];
                arr[parent] = temp;
                node = parent;
            }
            else
                break;
        }
    }
}

    //offer() is defined inside
//Heap class

