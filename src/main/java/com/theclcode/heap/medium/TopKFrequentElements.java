package com.theclcode.heap.medium;

import java.util.*;

//https://leetcode.com/problems/top-k-frequent-elements/description/
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] arr = {5,2,5,3,5,3,1,1,3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> theMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            theMap.put(nums[i], theMap.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Integer> theQueue = new PriorityQueue<>(Comparator.comparingInt(theMap::get));
        for (Map.Entry<Integer, Integer> entry : theMap.entrySet()) {
            if (theQueue.size() < k) {
                theQueue.add(entry.getKey());
            } else if (entry.getValue() > theMap.get(theQueue.peek())) {
                theQueue.poll();
                theQueue.offer(entry.getKey());
            }
        }

        int[] result = new int[k];
        int i = 0;
        for (Integer val : theQueue) {
            result[i++] = val;
        }
        return result;
    }


}
