package com.theclcode.newsletter.sep19.autocorrect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {



    /**************** START OF USER SOLUTION ****************/

	static void init()
	{
		
	}

	static void appendWord(char str[])
	{ 

	}

	static void changeAllWord(char before[], char after[])
	{

	}

	static void addAfter(char str[], char new_str[])
	{

	}

	static void removeWord(int k)
	{

	}

	static char getLetter(int pos)
	{
		return '\0';
	}
    
	/***************** END OF USER SOLUTION *****************/
	

	private static BufferedReader br;
	
	private final static int APPEND     = 0;
	private final static int CHANGE     = 1;
	private final static int ADDAFTER   = 2;
	private final static int REMOVE     = 3;
	private final static int GETLETTER  = 4;
	
	private static int run() throws Exception {
		int ret_val = 100;

		int N, cmd, param1, param2;
		char buf1[] = new char [11];
		char buf2[] = new char [11];
		char answer, result;
		String inputStr;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());

		init();

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case APPEND:
				inputStr = st.nextToken();	
				for (int k = 0; k < inputStr.length(); ++k) {
					buf1[k] = inputStr.charAt(k);	
				}
				buf1[inputStr.length()] = '\0';
				appendWord(buf1);
				break;
			case CHANGE:
				inputStr = st.nextToken();	
				for (int k = 0; k < inputStr.length(); ++k) {
					buf1[k] = inputStr.charAt(k);	
				}
				buf1[inputStr.length()] = '\0';
				inputStr = st.nextToken();	
				for (int k = 0; k < inputStr.length(); ++k) {
					buf2[k] = inputStr.charAt(k);	
				}
				buf2[inputStr.length()] = '\0';
				changeAllWord(buf1, buf2);
				break;
			case ADDAFTER:
				inputStr = st.nextToken();	
				for (int k = 0; k < inputStr.length(); ++k) {
					buf1[k] = inputStr.charAt(k);	
				}
				buf1[inputStr.length()] = '\0';
				inputStr = st.nextToken();	
				for (int k = 0; k < inputStr.length(); ++k) {
					buf2[k] = inputStr.charAt(k);	
				}
				buf2[inputStr.length()] = '\0';
				addAfter(buf1, buf2);
				break;
			case REMOVE:
				param1 = Integer.parseInt(st.nextToken());
				removeWord(param1);
				break;
			case GETLETTER:
				param1 = Integer.parseInt(st.nextToken());
				result = getLetter(param1);
				
				if (result == ' ')
					result = 26;
				else if (result >= 'a' && result <= 'z')
					result -= 'a';
				System.out.printf("%d\n", (int)result);

				break;
			default:
				break;
			}
		}

		return ret_val;
	}

	public static void main(String[] args) throws Exception {
		int T;
		double start = System.currentTimeMillis();
		System.setIn(new java.io.FileInputStream("sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(stinit.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run();
		}
		
		br.close();
	}
}

