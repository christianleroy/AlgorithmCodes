package com.theclcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntervals {

    public static void main(String[] args) {
        int[] newInterval = new int[]{2, 5};
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[][] res = insert(intervals, newInterval);
        Arrays.stream(res).forEach(e -> System.out.println(Arrays.toString(e)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        List<int[]> result = new ArrayList<>();
        int i=0;
        for (int[] interval : intervals) {
            if(newEnd < interval[0]) {
                // This marks the end of newInterval position, as we know the current interval is larger than newInterval
                // We add the rest of intervals to our result set here.
                // If newInterval did not overlap with any interval, we simply add it to the result.
                // If we found an overlap, it was processed on the else block, and then finally inserted to the result set here.
                // When we reach here, we're done processing and can return the result.
                result.add(new int[]{newStart, newEnd});
                for(int j = i; j < intervals.length; j++){
                    result.add(intervals[j]);
                }
                return convertToArray(result);
            } else if(newStart > interval[1]) {
                // This rules out lower intervals and add them to the result set.
                // We haven't found the start position of newInterval yet.
                result.add(interval);
            } else {
                // We found an overlap, and the startPosition, here.
                // So we just get the lower start and higher end betwen current interval and newInterval,
                // and adjust newInterval accordingly
                newStart = Math.min(newStart, interval[0]);
                newEnd = Math.max(newEnd, interval[1]);
            }
            i++;
        }

        // If newInterval reaches, or extends beyond, the end of intervals, we add it to the result set here.
        newInterval[0] = newStart;
        newInterval[1] = newEnd;
        result.add(newInterval);
        return convertToArray(result);

    }

    private static int[][] convertToArray(List<int[]> result) {
        int[][] newIntervals = new int[result.size()][2];
        for(int j = 0; j < result.size(); j++) {
            newIntervals[j] = result.get(j);
        }
        return newIntervals;
    }

}
