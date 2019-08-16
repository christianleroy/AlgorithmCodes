package com.theclcode.newsletter.aug19.treechecker;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for(int h=1; h<=testCases; h++){
            boolean isTree=true;
            int largestVertex = sc.nextInt();
            int edges = sc.nextInt();
            int[][] tree = new int[largestVertex+1][];
            boolean[] kids = new boolean[largestVertex+1];

            for(int i=0; i<edges; i++){
                int parent = sc.nextInt();
                int kid = sc.nextInt();
                if( parent>largestVertex || kid>largestVertex || kids[kid]) {
                    isTree=false;
                }
                if(tree[parent]==null){
                    tree[parent] = new int[largestVertex+1];
                }
                tree[parent][kid] = kid;
                kids[kid] = true;
            }
            if(isTree){
                int roots=0;
                int root=-1;
                for(int j=0; j<tree.length; j++){
                    if(tree[j]!=null && !kids[j]){
                        roots++;
                        root=j;
                    }
                }
                if(roots!=1){
                    isTree = false;
                } else {
                    for(int i=0; i<tree.length; i++){
                        int[] visited = new int[largestVertex+1];
                        if(i!=root && tree[i]!=null && !searchByDfs(root, i, visited, tree)){
                            isTree=false;
                            break;
                        }
                    }
                }
            }
            System.out.println("Case #"+h+": "+(isTree ? "tree" : "not tree"));
        }
    }

    public static boolean searchByDfs(int parent, int lookingForKid, int[] visited, int[][] tree){
        if(parent==lookingForKid){
            return true;
        }
        if(parent>0 && visited[parent]==0){
            visited[parent] = parent;
        } else if(parent==0 && visited[parent]!=-1){
            visited[parent]=-1;
        } else {
            return false;
        }
        if(tree[parent]==null){
            return false;
        }
        for(int i : tree[parent]){
            if(searchByDfs(i, lookingForKid, visited, tree)){
                return true;
            }
        }
        return false;
    }
}
