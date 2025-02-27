package com.theclcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/description/
public class MergeInterval {

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        int[][] res = new MergeInterval().merge(intervals);

        Arrays.stream(res).forEach(e -> System.out.print(Arrays.toString(e) + " "));
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0 || intervals.length == 1){
            return intervals;
        }

        mergeSort(intervals, 0, intervals.length - 1);
        List<int[]> result = new ArrayList<>();

        int[] curr = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            if(curr[1] < intervals[i][0]) {
                result.add(curr);
                curr = intervals[i];
            } else {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            }
        }
        result.add(curr);
        return convertToArray(result);

    }

    private static int[][] convertToArray(List<int[]> result) {
        int[][] newIntervals = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++) {
            newIntervals[j] = result.get(j);
        }
        return newIntervals;
    }

    public static void mergeSort(int[][] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[][] arr, int start, int mid, int end) {
        int[][] left = new int[(mid - start) + 1][2];
        int[][] right = new int[(end+1)-(mid+1)][2];

        for(int i = 0; i < left.length; i++) {
            left[i] = arr[i+start];
        }

        for(int j = 0; j < right.length; j++) {
            right[j] = arr[(mid + 1) + j];
        }

        for(int index = start, i=0, j=0; index <= end; index++) {
            if(j == right.length || (i < left.length && left[i][0] <= right[j][0])) {
                arr[index] = left[i++];
            } else{
                arr[index] = right[j++];
            }
        }
    }
}
