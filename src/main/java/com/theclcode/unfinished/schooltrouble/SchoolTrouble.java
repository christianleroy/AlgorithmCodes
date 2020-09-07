package com.theclcode.unfinished.schooltrouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SchoolTrouble {


    /**************** START OF USER SOLUTION ****************/

    static void init(int N) {

    }

    static boolean setFriends(int kid1, int kid2) {

        return false;
    }

    static boolean setEnemies(int kid1, int kid2) {

        return false;
    }

    static boolean areFriends(int kid1, int kid2) {

        return false;
    }

    static boolean areEnemies(int kid1, int kid2) {

        return false;
    }

    /***************** END OF USER SOLUTION *****************/

    private final static int SET_FRIENDS = 100;
    private final static int SET_ENEMIES = 200;
    private final static int ARE_FRIENDS = 300;
    private final static int ARE_ENEMIES = 400;

    private static void run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int L, N;
        int kid1, kid2;
        int cmd;

        boolean ret = false;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        init(N);

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            kid1 = Integer.parseInt(st.nextToken());
            kid2 = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case SET_FRIENDS:
                    ret = setFriends(kid1, kid2);
                    break;
                case SET_ENEMIES:
                    ret = setEnemies(kid1, kid2);
                    break;
                case ARE_FRIENDS:
                    ret = areFriends(kid1, kid2);
                    break;
                case ARE_ENEMIES:
                    ret = areEnemies(kid1, kid2);
                    break;
            }

            System.out.println(ret);
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("Case #" + testcase + ":");
            run(br);
        }
    }

}
