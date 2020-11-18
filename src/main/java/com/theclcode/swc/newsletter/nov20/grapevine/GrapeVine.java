package com.theclcode.swc.newsletter.nov20.grapevine;

import java.util.Scanner;

public class GrapeVine {

    private int max;
    private int grid[][];
    private int x;
    private int y;

    public static void main(String[] args) {
        new GrapeVine().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++) {
            this.x = sc.nextInt();
            this.y = sc.nextInt();
            init(sc);
            System.out.format("Case #%s:%n", tc);
            seekAnswers(sc);
        }
    }

    private void init(Scanner sc) {
        this.grid = new int[x][y];
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                this.grid[i][j] = sc.nextInt();
            }
        }
    }

    private void seekAnswers(Scanner sc) {
        int queries = sc.nextInt();
        for (int i = 0; i < queries; i++) {
            this.max = 0;
            int lowerBound = sc.nextInt();
            int upperBound = sc.nextInt();
            seekAnswer(lowerBound, upperBound);
        }
    }

    private void seekAnswer(int lowerBound, int upperBound) {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                if (this.grid[i][j] >= lowerBound && this.grid[i][j] <= upperBound) {
                    if (this.max == 0) {
                        this.max = 1;
                    }
                    traverse(i, j, upperBound, 1);
                    break;
                }
            }
        }
        System.out.println(this.max);
    }

    private void traverse(int x, int y, int upperBound, int total) {
        if (x + 1 < this.x && y + 1 < this.y) {
            if (this.grid[x + 1][y + 1] <= upperBound) {
                total++;
                if (total > this.max) {
                    this.max = total;
                }
                traverse(x + 1, y + 1, upperBound, total);
            }
        }
    }

}
