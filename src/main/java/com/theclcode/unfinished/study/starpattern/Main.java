package com.theclcode.unfinished.study.starpattern;

import java.util.Scanner;

public class Main {

    /**************** START OF USER SOLUTION ****************/

    static void init(int N, int M, int[][] map) {

    }

	static Result findConstellation(int[][] stars) {
        Result ret = new Result();
        
        return ret;
    }

    /***************** END OF USER SOLUTION *****************/

	private static Scanner sc;
	
	private final static int MAX_N	= 1000;
	private final static int MAX_M	= 20;
	
	private static int Map[][] = new int[MAX_N][MAX_N];
	private static int Stars[][] = new int[MAX_M][MAX_M];
	private static int mSeed;
	
	public static class Result {
		int y, x;
	}

	private static int pseudo_rand()
	{
		mSeed = mSeed * 431345 + 2531999;
		return mSeed & 0x7FFFFFFF;
	}

	private static int run(int Ans)
	{
		int N, M, K;

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		for (int i = 0; i < N; ++i) {
			int num;
			int cnt = N / 30;
			int idx = 0;
			for (int k = 0; k < cnt; ++k) {
				num = sc.nextInt();
				for (int m = 0; m < 30; ++m) {
					Map[i][idx++] = num & 0x01;
					num = num >> 1;
				}
			}
			
			if (N % 30 > 0) {
				num = sc.nextInt();
				for (int m = 0; m < (N % 30); ++m) {
					Map[i][idx++] = num & 0x01;
					num = num >> 1;
				}
			}
		}		
		
		init(N, M, Map);

		for (int t = 0; t < K; ++t) {
			mSeed = sc.nextInt();
			int num = sc.nextInt();

			for (int i = 0; i < M; ++i)
				for (int k = 0; k < M; ++k)
					Stars[i][k] = 0;
			
			int y = pseudo_rand() % M;
			int x = pseudo_rand() % M;
			Stars[y][x] = 9;
			for (int i = 1; i < num; ++i) {
				y = pseudo_rand() % M;
				x = pseudo_rand() % M;
				Stars[y][x] = 1;
			}

			Result answer = findConstellation(Stars);
			System.out.printf("%d %d\n", answer.y, answer.x);
		}

		return Ans;
	}
	
	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);

		int TC = sc.nextInt();
		int Ans = sc.nextInt();
		double before = System.nanoTime();
		for (int testcase = 1; testcase <= TC; ++testcase) {
			System.out.printf("Case #%d:\n", testcase);
			run(Ans);
		}
		//System.out.println((System.nanoTime() - before)/ 1000000000);
		sc.close();
	}
}