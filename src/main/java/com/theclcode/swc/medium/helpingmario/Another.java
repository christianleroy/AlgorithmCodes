package com.theclcode.swc.medium.helpingmario;

import java.util.Scanner;

public class Another {
    static int M;
    static int tick;
    static int board[][];
    static boolean visited[][];
    static boolean pathFound;

    static boolean hasPath(int i, int j) {
        if (i == M-1) {
            pathFound = true;
            return true;
        }

        visited[i][j] = true;

        // UP
        if(i-1 >= 0 && !visited[i-1][j] && board[i-1][j] == 1) {
            hasPath(i-1, j);
        }

        // DOWN
        if(i+1 < M && !visited[i+1][j] && board[i+1][j] == 1) {
            hasPath(i+1, j);
        }

        // LEFT
        if(j-1 >= 0 && !visited[i][j-1] && board[i][j-1] == 1) {
            hasPath(i, j-1);
        }

        // RIGHT
        if(j+1 < M && !visited[i][j+1] && board[i][j+1] == 1) {
            hasPath(i, j+1);
        }

        return false;
    }


    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            M = sc.nextInt();
            board = new int[M][M];
            pathFound = false;
            tick = 0;

            for(int i=0; i<M*M ; i++) {
                visited = new boolean[M][M];
                board[sc.nextInt()-1][sc.nextInt()-1] = 1;
                if(!pathFound) tick = i;
                if(tick >= M) {
                    for(int j=0; j<M && !pathFound; j++) {
                        if(board[0][j] == 1) hasPath(0, j);
                    }
                }
            }

            System.out.println("#" + test_case + " " + (tick+1));
        }
    }

}
