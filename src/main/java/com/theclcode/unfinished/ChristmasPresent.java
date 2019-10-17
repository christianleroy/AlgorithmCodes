package com.theclcode.unfinished;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChristmasPresent {
    /**************** START OF USER SOLUTION ****************/

    public static void arrange(int N, int box[], int limit) {

    }

    /***************** END OF USER SOLUTION *****************/


    private final static int MAXN = 10000;

    private static int N;
    private static int limit;
    private static int box[] = new int[MAXN];

    private final static int CALL_COUNT_MAX	= 1000000;
    private static int callcount;
    private static boolean okay;

    private static final int que[] = new int[5];

    public static boolean swap5(int K, int origin[], int target[]) {
        if(!okay || callcount >= CALL_COUNT_MAX || K <= 0 || K > 5)
            return okay = false;

        ++callcount;

        for(int i = 0; i < K; ++i) {
            if(origin[i] < 0 || origin[i] >= N || target[i] < 0 || target[i] >= N)
                return okay = false;

            que[i] = box[origin[i]];

            if(box[origin[i]] == -1)
                return okay = false;

            box[origin[i]] = -1;
        }

        for(int i = 0; i < K; ++i) {
            if(box[target[i]] != -1)
                return okay = false;

            box[target[i]] = que[i];
        }

        return true;
    }

    private static boolean check() {
        for(int i = 0; i < N; ++i)
            if(box[i] != i)
                return false;

        return true;
    }

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int box_t[] = new int[MAXN];

        okay = true;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; ++i) {
            box[i] = Integer.parseInt(st.nextToken());
            box_t[i] = box[i];
        }

        callcount = 0;

        arrange(N, box_t, limit);

        return okay && callcount <= limit && check() && N > 0;
    }

//    public static void main(String[] args) throws Exception {
//        int TC;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//        TC = Integer.parseInt(st.nextToken());
//
//        for(int testcase = 1; testcase <= TC; ++testcase) {
//            System.out.println("Case #" + testcase + ": " + run(br));
//        }
//    }

    static class Node {
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] gifts = new int[]{3,5,1,6,7,4,0,2};
        boolean[] visited = new boolean[gifts.length];
        for(int i=0; i<gifts.length; i++){
            int index;

            if(!visited[i]){
                index = gifts[i];
                visited[i] = true;
                System.out.print(index+" ");
                do {
                    index = gifts[index];
                    visited[index]=true;
                    System.out.print(index+" ");
                }while(index!=gifts[i]);
            }
            System.out.println();

        }
    }
}
