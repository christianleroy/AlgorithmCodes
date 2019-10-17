package com.theclcode.unfinished.electronicmail;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static final int INIT = 0;
	static final int SENDMAIL = 1;
	static final int GETCOUNT = 2;
	static final int DELETEMAIL = 3;
	static final int SEARCHMAIL = 4;

	/**************** START OF USER SOLUTION ****************/

	static void init(int N, int K) {

	}

	static void sendMail(char[] subject, int uID, int cnt, int[] rIDs) {

	}

	static int getCount(int uID) {

		return 0;
	}

	static int deleteMail(int uID, char[] subject) {

		return 0;
	}

	static int searchMail(int uID, char[] text) {

		return 0;
	}

	/***************** END OF USER SOLUTION *****************/


	static final int MAX_WORD = 10000;
	static char Word[][] = new char[MAX_WORD][11];

	static int N, W, minR, maxR, SW;

	static int mSeed;
	static int pseudo_rand()
	{
		mSeed = mSeed * 214013 + 2531011;
		return (mSeed >> 16) & 0x7FFF;
	}

	static void make_string(int seed)
	{
		mSeed = seed;

		for (int i = 0; i < W; ++i) {
			int length = 5 + pseudo_rand() % 6;
			for (int k = 0; k < length; ++k) {
				Word[i][k] = (char)((int)'a' + (pseudo_rand() % 26));
			}
			Word[i][length] = '\0';
		}
	}

	static void send_mail(int seed)
	{
		char str[] = new char[200];
		int pos = 0;

		mSeed = seed;

		int wcnt = 1 + pseudo_rand() % SW;
		for (int i = 0; i < wcnt; ++i) {
			int widx = pseudo_rand() % W;
			for (int k = 0; k < 10; ++k) {
				if (Word[widx][k] == '\0') break;
				str[pos++] = Word[widx][k];
			}
			str[pos++] = ' ';
		}
		str[pos - 1] = '\0';

		int uid = pseudo_rand() % N;
		int rcnt = minR + pseudo_rand() % (maxR - minR + 1);
		int rids[] = new int[50];
		for (int i = 0; i < rcnt; ++i) {
			rids[i] = pseudo_rand() % N;
		}

		sendMail(str, uid, rcnt, rids);
	}

	static int delete_mail(int uid, int seed)
	{
		char str[] = new char[200];
		int pos = 0;

		mSeed = seed;

		int wcnt = 1 + pseudo_rand() % SW;
		for (int i = 0; i < wcnt; ++i) {
			int widx = pseudo_rand() % W;
			for (int k = 0; k < 10; ++k) {
				if (Word[widx][k] == '\0') break;
				str[pos++] = Word[widx][k];
			}
			str[pos++] = ' ';
		}
		str[pos - 1] = '\0';

		return deleteMail(uid, str);
	}

	static BufferedReader br;
	static StringTokenizer st;

	static int run(int answer) throws Exception
	{
		int Q, K, cmd, sample, seed, param1, param2, ret;
		char str[] = new char[30];

		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		sample = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (sample == 0) {
			W = Integer.parseInt(st.nextToken());
			minR = Integer.parseInt(st.nextToken());
			maxR = Integer.parseInt(st.nextToken());
			SW = Integer.parseInt(st.nextToken());
			seed = Integer.parseInt(st.nextToken());
			make_string(seed);
		}

		init(N, K);
		for (int i = 1; i < Q; ++i) {
			st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case SENDMAIL:
				if (sample == 1) {
					int uid, rcnt;
					int rids[] = new int[10];
					String temp = st.nextToken();
					for (int ctr = 0; ctr < temp.length(); ctr++)
						str[ctr] = temp.charAt(ctr);
					str[temp.length()] = 0;
					uid = Integer.parseInt(st.nextToken());
					rcnt = Integer.parseInt(st.nextToken());
					for (int k = 0; k < rcnt; ++k) rids[k] = Integer.parseInt(st.nextToken());
					
					for (int k = 0; k < 30; ++k) {
						if (str[k] == '\0') break;
						if (str[k] == '_') str[k] = ' ';
					}
					sendMail(str, uid, rcnt, rids);
				}
				else {
					seed = Integer.parseInt(st.nextToken());
					send_mail(seed);
				}
				break;
			case GETCOUNT:
				param1 = Integer.parseInt(st.nextToken());
				ret = getCount(param1);
				System.out.printf("%d\n", ret);
				break;
			case DELETEMAIL:
				if (sample == 1) {
					int uid;
					param1 = Integer.parseInt(st.nextToken());
					String temp = st.nextToken();
					for (int ctr = 0; ctr < temp.length(); ctr++)
						str[ctr] = temp.charAt(ctr);
					str[temp.length()] = 0;
					for (int k = 0; k < 30; ++k) {
						if (str[k] == '\0') break;
						if (str[k] == '_') str[k] = ' ';
					}
					ret = deleteMail(param1, str);
					System.out.printf("%d\n", ret);
				}
				else {
					param1 = Integer.parseInt(st.nextToken());
					seed = Integer.parseInt(st.nextToken());
					ret = delete_mail(param1, seed);
					System.out.printf("%d\n", ret);
				}
				break;
			case SEARCHMAIL:
				param1 = Integer.parseInt(st.nextToken());
				String temp = st.nextToken();
				for (int ctr = 0; ctr < temp.length(); ctr++)
					str[ctr] = temp.charAt(ctr);
				str[temp.length()] = 0;
				ret = searchMail(param1, str);
				System.out.printf("%d\n", ret);
				break;
			default:
				break;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		int T, Mark;
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run(100);
		}
	}
}
