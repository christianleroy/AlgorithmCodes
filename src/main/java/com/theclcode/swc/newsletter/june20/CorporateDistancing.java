package com.theclcode.swc.newsletter.june20;

import java.util.Scanner;

public class CorporateDistancing {

    private int max;
    private int current;
    private int[][] available;
    private boolean[][] grid;

    public static void main(String[] args) {
        CorporateDistancing corporateDistancing = new CorporateDistancing();
        corporateDistancing.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            max = current = 0;

            int xDim = sc.nextInt();
            int yDim = sc.nextInt();
            int availableSeats = sc.nextInt();
            grid = new boolean[xDim][yDim];
            available = new int[availableSeats][];
            for (int j = 0; j < availableSeats; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                available[j] = new int[]{x, y};
            }
            traverse(0, available.length);
            System.out.println(String.format("Case #%s: %s", i, max));

        }
    }

    private void traverse(int start, int end) {
        for (int i = start; i < end; i++) {
            if (valid(i)) {
                pick(i);
                traverse(i + 1, end);
                unpick(i);
            }
        }
    }

    private void pick(int i) {
        grid[available[i][0]][available[i][1]] = true;
        current++;
        if (current > max) {
            max = current;
        }
    }

    private void unpick(int i) {
        grid[available[i][0]][available[i][1]] = false;
        current--;
    }


    private boolean valid(int i) {
        for (int j = 0; j < available.length; j++) {
            if (i == j) {
                continue;
            }
            if (isOccupied(j) && getDistance(i, j) <= 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isOccupied(int i) {
        return grid[available[i][0]][available[i][1]];
    }

    private int getDistance(int i, int j) {
        int x = available[i][0] - available[j][0];
        int y = available[i][1] - available[j][1];
        x = x < 0 ? x * -1 : x;
        y = y < 0 ? y * -1 : y;
        return x >= y ? x : y;
    }
    
}
