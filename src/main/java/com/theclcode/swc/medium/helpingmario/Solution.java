package com.theclcode.swc.medium.helpingmario;

import java.util.Scanner;

public class Solution {

    static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int i=1; i<=testCases; i++){
            found = false;
            int gridSize = sc.nextInt();
            boolean[][] grid = new boolean[gridSize][gridSize];
            int ticks=0;
            for(int j=0; j<gridSize*gridSize; j++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                grid[x][y] = true;

                if(!found){ ticks=j; }

                if(ticks>=gridSize){
                    for(int k=0; k<gridSize && !found; k++){
                        if(grid[0][k]){
                            search(grid, new boolean[gridSize][gridSize], 0, k);
                        }
                    }
                }
            }
            System.out.println("#"+i+" "+(ticks+1));
        }
    }

    private static boolean search(boolean[][] grid, boolean[][] visited, int x, int y) {
        if(x==grid.length-1){
            found = true;
            return true;
        }
        visited[x][y]=true;
        if(x<grid.length-1 && grid[x+1][y] && !visited[x+1][y]){
            search(grid, visited, x+1, y);
        }
        if(y<grid.length-1 && grid[x][y+1] && !visited[x][y+1]){
            search(grid, visited, x, y+1);
        }
        if(y>0 && grid[x][y-1] && !visited[x][y-1]){
            search(grid, visited, x, y-1);
        }
        if(x>0 && grid[x-1][y] && !visited[x-1][y]){
            search(grid, visited, x-1, y);
        }
        return false;
    }
}
