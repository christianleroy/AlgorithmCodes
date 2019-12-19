package com.theclcode.unfinished.study.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    /**************** START OF USER SOLUTION ****************/

    static void init(int N) {

    }
    static void setupTool(int T, int stepNo[], int procTime[]) {

    }
    static void addLot(int time, int number) {

    }
    static int simulate(int time, int wip[]) {

        return 0;
    }

    /***************** END OF USER SOLUTION *****************/

    private static int MAX_N = 100;
    private static int MAX_TOOL = 50;
    private static int ADDLOT = 1;
    private static int SIMUL = 2;

    private static BufferedReader br;
    private static StringBuilder out;

    private static void run() throws Exception
    {

        int totalStep = 0, totalTool = 0;
        int stepID[] = new int[MAX_N * MAX_TOOL];
        int procTime[] = new int[MAX_N * MAX_TOOL];
        int event, time, number;
        int userOut, resultOut;
        int userWip[] = new int[MAX_N];
        int resultWip[] = new int[MAX_N];

        for (int j = 0; j < MAX_N; j++) {
            userWip[j] = resultWip[j] = 0;
        }

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        totalStep = Integer.parseInt(stdin.nextToken());
        totalTool = Integer.parseInt(stdin.nextToken());

        init(totalStep);

        for (int i = 0; i < totalTool; i++) {
            stepID[i] = Integer.parseInt(stdin.nextToken());
            procTime[i] = Integer.parseInt(stdin.nextToken());
        }
        setupTool(totalTool, stepID, procTime);

        while (true) {
            stdin = new StringTokenizer(br.readLine(), " ");
            event = Integer.parseInt(stdin.nextToken());
            if (event==ADDLOT) {
                time = Integer.parseInt(stdin.nextToken());
                number = Integer.parseInt(stdin.nextToken());
                addLot(time, number);
            }
            else if (event==SIMUL) {
                time = Integer.parseInt(stdin.nextToken());
                for (int j = 0; j < totalStep; j++) {
                    userWip[j] = 0;
                }
                userOut   = simulate(time, userWip);

                out.append(String.format("%d", time));
                for (int j = 0; j < totalStep; j++) {
                    out.append(String.format(" %d", userWip[j]));
                }
                out.append(String.format(" %d\n", userOut));
            }
            else {
                break;
            }
        }
    }


    public static void main(String[] args) throws Exception {
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new StringBuilder();

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        int TestCase = Integer.parseInt(stdin.nextToken());

        for (int t = 1; t <= TestCase; t++) {
            out.append(String.format("Case #%d:\n", t));
            run();
        }
        br.close();

        System.out.print(out);
    }
}
