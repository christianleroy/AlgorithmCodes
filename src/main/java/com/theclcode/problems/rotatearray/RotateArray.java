package com.theclcode.problems.rotatearray;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {

        int[][] nums = {{1, 2, 3, 4, 5, 6, 7}, {1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 2, 3, 4}};

        RotateArray rotateArray = new RotateArray();

        rotateArray.rotateArray(nums[0], 3);
        rotateArray.rotateArrayBetter(nums[1], 3);
        rotateArray.rotateArrayBetter(nums[2], 2);

        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }

    }

    private void rotateArray(int[] nums, int k) {
        boolean[] visited = new boolean[nums.length];
        k = k % nums.length;
        if (k > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    int r = i;
                    int curr = nums[r];
                    int next;
                    do {
                        int s = (r + k) % nums.length;
                        visited[s] = true;
                        next = nums[s];
                        nums[s] = curr;
                        curr = next;
                        r = s;
                    } while (r != i);
                }
            }
        }
    }

    private void rotateArrayBetter(int[] nums, int k) {
        int visited = 0;
        k = k % nums.length;
        if (k > 0) {
            for (int i = 0; visited < nums.length; i++) {
                int r = i;
                int curr = nums[r];
                do {
                    int s = (r + k) % nums.length;
                    int temp = nums[s];
                    nums[s] = curr;
                    curr = temp;
                    r = s;
                    visited++;
                } while (r != i);
            }
        }
    }

}
