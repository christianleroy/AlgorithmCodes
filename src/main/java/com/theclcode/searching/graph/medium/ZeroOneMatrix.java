package com.theclcode.searching.graph.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ZeroOneMatrix {

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        updateMatrix(mat);
    }

    public static int[][] updateMatrix(int[][] mat) {

        if (mat == null || mat.length == 0) {
            return mat;
        }

        int rows = mat.length;
        int cols = mat[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = Integer.MAX_VALUE - 10000;
            }
        }

        //First pass: check for left and top
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    if (i > 0) {
                        result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                }
            }
        }

        //Second pass: check for bottom and right
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i + 1 < rows) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }

                if (j + 1 < cols) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }

        return result;

    }

    public static int[][] updateMatrixBfs(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return mat;
        }
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] result = new int[rows][cols];

        Queue<Integer[]> queue = new ArrayDeque<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < rows; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.add(new Integer[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            Integer[] pop = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int currRow = pop[0];
                int currCol = pop[1];
                int newRow = currRow + directions[i][0];
                int newCol = currCol + directions[i][1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (result[newRow][newCol] > result[currRow][currCol] + 1) {
                        result[newRow][newCol] = result[currRow][currCol] + 1;
                        queue.add(new Integer[]{newRow, newCol});
                    }
                }
            }
        }

        return result;
    }
}
