package com.theclcode.array.medium;

// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class MaximumPointsFromCards {

    public static void main(String[] args) {

        int[][] arr = {
                {11, 49, 100, 20, 86, 29, 72}, // 232
                {2, 2, 2}, // 6
                {3, 3, 3, 4, 3, 3}, // 10
                {5, 1, 3, 9, 8, 7} // 24
        };

        int[] k = {4, 3, 3, 3};

        for (int i = 0; i < arr.length; i++) {
            System.out.println(maxScore(arr[i], k[i]));
        }
    }

    public static int maxScore(int[] cardPoints, int k) {

        int max = 0;
        if (cardPoints.length == k) {
            for (int i = 0; i < cardPoints.length; i++) {
                max += cardPoints[i];
            }
        } else {
            int left = 0, right = 0;
            int i = 0, j = cardPoints.length - 1;

            // Get sums of left and right windows
            for (; i < k; i++, j--) {
                left += cardPoints[i];
                right += cardPoints[j];
            }
            i = 0;
            j = cardPoints.length - 1;

            while (k > 0) {
                // Left window sum > Right window sum
                if (left > right) {
                    max += cardPoints[i]; // Pick left card
                    left -= cardPoints[i]; // Shorten window length and update left sum
                    right -= cardPoints[j - (k - 1)]; // Shorten window length and update right sum
                    i++; // Move left
                } else {
                    max += cardPoints[j]; // Pick right card
                    right -= cardPoints[j]; // Shorten window length and update right sum
                    left -= cardPoints[i + (k - 1)]; // Shorten window length and update left sum
                    j--; // Move right
                }
                k--;
            }
        }
        return max;
    }
}
