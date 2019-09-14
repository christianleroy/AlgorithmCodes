package com.theclcode.swc.medium.helpingmario;

import java.util.Scanner;

public class Solution {

    static boolean hasFoundPath;
    static boolean[][] grid;
    static boolean[][] visited;
    static boolean topRowOpen;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int i=1; i<=testCases; i++){
            max = sc.nextInt();
            grid=new boolean[max][max];
            topRowOpen=false;
            hasFoundPath=false;
            int x, y, tick=0;
            for(int j=0; j<max*max; j++){
                tick = !hasFoundPath ? j : tick;
                grid[x=sc.nextInt()-1][y=sc.nextInt()-1]=true;
                topRowOpen = x==0 ? true : topRowOpen;
                if(tick>=max && topRowOpen && !hasFoundPath){
                    visited=new boolean[max][max];
                    for(int k=0; k<max; k++){
                        search(0, k);
                    }
                }

            }
            System.out.println("#"+i+" "+(tick+1));
        }
    }

    public static void search(int x, int y){
        if(x==grid.length-1){
            hasFoundPath=true;
            return;
        }
        if(!hasFoundPath && x<grid.length-1 && grid[x+1][y] && !visited[x+1][y]){
            visited[x+1][y]=true;
            search(x+1, y);
        }
        if(!hasFoundPath && y<grid.length-1 && grid[x][y+1] && !visited[x][y+1]){
            visited[x][y+1]=true;
            search(x,y+1);
        }
        if(!hasFoundPath && y>0 && grid[x][y-1] && !visited[x][y-1]){
            visited[x][y-1]=true;
            search(x,y-1);
        }
        if(!hasFoundPath && x>0 && grid[x-1][y] && !visited[x-1][y]){
            visited[x-1][y]=true;
            search(x-1,y);
        }

    }
}
