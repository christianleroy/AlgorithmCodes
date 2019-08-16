package com.theclcode.newsletter.aug19.imdbrecommender;

import java.util.Scanner;

public class Solution {
    public static final int MAX_MOVIE = 5000;
    public static final int MAX_WATCH = 5000;
    public static final int MAX_USER = 1000;

    /**************** START OF USER SOLUTION ****************/

    static void init()
    {

    }

    static void newMovie(int mid)
    {

    }

    static void addUser(int uid)
    {

    }

    static void watchMovie(int uid, int mid)
    {

    }

    static int getRecommendedMovie(int uid)
    {
        return -1;
    }

    /***************** END OF USER SOLUTION *****************/


    public static final int CMD_INIT = 10;
    public static final int CMD_MOVIE = 20;
    public static final int CMD_USER = 30;
    public static final int CMD_WATCH = 40;
    public static final int CMD_RECOMMEND = 50;

    static Scanner sc;

    static void run() {
        int  m, cmd, arg1, arg2, ret;

        m = sc.nextInt();

        while (m-- > 0) {
            cmd = sc.nextInt();

            switch (cmd) {
                case CMD_INIT:
                    init();

                    break;

                case CMD_MOVIE:
                    arg1 = sc.nextInt();
                    newMovie(arg1);

                    break;

                case CMD_USER:
                    arg1 = sc.nextInt();
                    addUser(arg1);

                    break;

                case CMD_WATCH:
                    arg1 = sc.nextInt();
                    arg2 = sc.nextInt();
                    watchMovie(arg1, arg2);

                    break;

                case CMD_RECOMMEND:
                    arg1 = sc.nextInt();
                    ret = getRecommendedMovie(arg1);

                    System.out.printf("%d\n", ret);

                    break;
            }
        }
    }

    public static void main(String[] args) {
        int T, ANS, tc;

        sc = new Scanner(System.in);

        T = sc.nextInt();
        ANS = sc.nextInt();

        for (tc = 1; tc <= T; ++tc) {
            System.out.printf("Case #%d:\n", tc);
            run();
        }
    }
}
