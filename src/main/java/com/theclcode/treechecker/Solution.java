package com.theclcode.treechecker;

import java.util.Scanner;

//Unfinished
public class Solution {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        boolean isTree=true;

        for(int h=1; h<=testCases; h++){
            int largestVertex = sc.nextInt();
            int edges = sc.nextInt();
            boolean[] parents = new boolean[largestVertex+1];
            boolean[] kids = new boolean[largestVertex+1];

            for(int i=0; i<edges; i++){
                int parent = sc.nextInt();
                int kid = sc.nextInt();
                if( parent>largestVertex || kid>largestVertex || kids[kid]) {
                    isTree=false;
                    break;
                }
                parents[parent] = true;
                kids[kid] = true;
            }
            if(isTree){
                int roots=0;
                int root=-1;
                for(int j=0; j<parents.length; j++){
                    if(parents[j] && !kids[j]){
                        roots++;
                        root=j;
                    }
                }
                if(roots!=1){
                    isTree = false;
                } else {
                    
                }
            }
            System.out.println("Case #"+h+": "+(isTree ? "tree" : "not tree"));
        }
    }
}
