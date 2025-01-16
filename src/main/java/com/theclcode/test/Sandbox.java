package com.theclcode.test;

import java.util.*;

public class Sandbox {

    public static void main(String[] args) {

//        {1,5},{6,9}
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval = {2,5};

//         {1,2},{3,10},{12,16}
//        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newInterval = {4,8};

//        newInterval within or beyond last intervals element
//        {3,6},{7,11},{12,13}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {12,13};

//        {3,6},{7,13}
        int[][] intervals = {{3,6},{7,11}};
        int[] newInterval = {10,13};

//        newInterval within or before the first intervals element
//        {1,6},{7,11}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {1,4};

//        {1,2},{3,6},{7,11}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {1,2};

        // Beyond range
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {1,15};

//        {2,15}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {2,15};

//        {3,15}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {5,15};

//        {1,11}
//        int[][] intervals = {{3,6},{7,11}};
//        int[] newInterval = {1,10};


        int[][] res = insert(intervals, newInterval);

        for(int i = 0; i < res.length; i++) {
            System.out.println("{" + res[i][0] + "," + res[i][1] + "}");
        }
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int startIdx = -1;
        int endIdx = -1;
        int marker = -1;

        List<Integer[]> ints = new ArrayList<>();

        if(newStart < intervals[0][0] && newEnd > intervals[intervals.length-1][1]) {
            return new int[][]{{newStart, newEnd}};
        }

        if(newEnd < intervals[0][0]) {
            ints.add(new Integer[]{newStart, newEnd});
            marker = 0;
        } else if(newStart > intervals[intervals.length-1][1]) {
            marker = 0;
            endIdx = intervals.length;
        } else {
            for(int i = 0; i < intervals.length; i++) {
                if(startIdx == -1) {
                    if(newStart >= intervals[i][0] && newStart <= intervals[i][1]) {
                        startIdx = i;
                        marker = i + 1;
                        intervals[startIdx][0] = Math.min(intervals[startIdx][0], newStart);
                    } else {
                        Integer[] interval = new Integer[]{intervals[i][0], intervals[i][1]};
                        ints.add(interval);
                    }
                } else if(endIdx == -1){
                    if(newEnd < intervals[i][0]) {
                        Integer[] interval = new Integer[]{intervals[startIdx][0], Math.max(newEnd, intervals[i - 1][1])};
                        ints.add(interval);
                        marker = endIdx = i;
                    } else if(newEnd >= intervals[i][0] && newEnd <= intervals[i][1]) {
                        Integer[] interval = new Integer[]{intervals[startIdx][0], Math.max(newEnd, intervals[i][1])};
                        ints.add(interval);
                        marker = endIdx = i+1;
                    }
                }
            }

        }

        for(int j = marker; j < intervals.length; j++) {
            ints.add(new Integer[]{intervals[j][0], intervals[j][1]});
        }

        if(endIdx == intervals.length) {
            ints.add(new Integer[]{newStart, Math.max(intervals[intervals.length-1][1],newEnd)});
        }

        int[][] result = new int[ints.size()][2];

        for(int i = 0; i < result.length; i++) {
            result[i][0] = ints.get(i)[0];
            result[i][1] = ints.get(i)[1];
        }

        return result;
    }
}
