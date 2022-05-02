package com.theclcode.searching.graph.medium;

//https://leetcode.com/problems/max-area-of-island/
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int curr, max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    curr = dfs(grid, i, j, 0);
                    if (curr > max) {
                        max = curr;
                    }
                }
            }
        }
        return max;
    }

    public static int dfs(int[][] grid, int x, int y, int curr) {
        if (grid[x][y] == 1) {
            curr++;
            grid[x][y]++;
            if (x > 0) {
                curr = dfs(grid, x - 1, y, curr);
            }
            if (y > 0) {
                curr = dfs(grid, x, y - 1, curr);
            }

            if (x + 1 < grid.length) {
                curr = dfs(grid, x + 1, y, curr);
            }

            if (y + 1 < grid[0].length) {
                curr = dfs(grid, x, y + 1, curr);
            }
        }
        return curr;
    }
}
