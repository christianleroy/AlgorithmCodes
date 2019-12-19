package com.theclcode.unfinished.study.nextwordprediction;

import java.util.Scanner;

class Main {

    /**************** START OF USER SOLUTION ****************/
    
    static void init() {

    }

    static void search(char[] words) {
        
    }

    static int autoComplete(char[] prefix, char[][] autoWords) {

        return 0;
    }

    /***************** END OF USER SOLUTION *****************/


	private static Scanner sc;
	
	private final static int SEARCHWORD_MINLEN	= 4;
	private final static int SEARCHWORD_MAXLEN	= 12;
	private final static int PREFIX_MINLEN		= 4;
	private final static int PREFIX_MAXLEN		= 8;
	private final static int RANDTB_SIZE		= 15;
	private final static int INDEX_DIV			= 100000;
	
	private final static int AC_RATE			= 40;
	private final static int SEARCH_RATE		= 60;
	private final static int RANDOMPREFIX_RATE	= 5;
	
	private static int seed;
	private static char[][][] words = new char [2][5000][SEARCHWORD_MAXLEN + 1];
	private static int[][] wordsLen = new int [2][5000];
	private static int orgWordsN, newWordsN, startPt;
	private static int[] randTb = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	
	private static int pseudo_rand()
	{
		seed = seed * 431345 + 2531999;
		return seed & 0x7FFFFFFF;
	}
	
	private static int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}

	private static void mstrncpy(char[] dest, char[] src, int len) {
		for (int i = 0; i < len; i++) {
			dest[i] = src[i];
		}
		dest[len] = '\0';
	}
	
	private static  void makeWords(int startPt, int n)
	{
		for (int i = 0; i < newWordsN; ++i) {
			int idx = (startPt + (pseudo_rand() % n)) % orgWordsN;
			int addLen = randTb[pseudo_rand() % RANDTB_SIZE] + 1;

			mstrncpy(words[1][i], words[0][idx], wordsLen[0][idx]);
			wordsLen[1][i] = wordsLen[0][idx];

			while (addLen-- > 0 && wordsLen[1][i] < SEARCHWORD_MAXLEN) {
				words[1][i][wordsLen[1][i]++] = (char) ((pseudo_rand() % 26) + 'a');
			}

			words[1][i][wordsLen[1][i]++] = '\0';
		}
	}

	private static  int getRandomWordIndex(int n)
	{
		int randVal = (pseudo_rand() % (n + newWordsN));
		int idx1, idx2;

		idx1 = randVal >= n ? 1 : 0;
		idx2 = randVal >= n ? randVal - n : randVal;

		if (idx1 == 0) {
			idx2 = (idx2 + startPt) % orgWordsN;
		}

		return idx1 * INDEX_DIV + idx2;
	}

	private static boolean run(Scanner sc, int n, int m) {

		boolean accepted = true;

        StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			if (pseudo_rand() % (AC_RATE + SEARCH_RATE) < AC_RATE) {
				char[][] autoWords = new char [5][SEARCHWORD_MAXLEN + 1];
				char[][] answerWords = new char [5][SEARCHWORD_MAXLEN + 1];
				char[] prefix = new char [SEARCHWORD_MAXLEN + 1];
				int autoWordsN, answerWordsN;
				int idx, prefixLen;

				if (pseudo_rand() % 100 < RANDOMPREFIX_RATE) {
					prefixLen = pseudo_rand() % (PREFIX_MAXLEN - PREFIX_MINLEN + 1) + PREFIX_MINLEN;

					for (int i = 0; i < prefixLen; ++i) {
						prefix[i] = (char) (pseudo_rand() % 26 + 'a');
					}
				}
				else {
					idx = getRandomWordIndex(n);

					prefixLen = wordsLen[idx / INDEX_DIV][idx % INDEX_DIV] - randTb[pseudo_rand() % RANDTB_SIZE];
					prefixLen = (prefixLen < PREFIX_MINLEN ? PREFIX_MINLEN : (prefixLen > PREFIX_MAXLEN ? PREFIX_MAXLEN : prefixLen));

					mstrncpy(prefix, words[idx / INDEX_DIV][idx % INDEX_DIV], prefixLen);
				}

				autoWordsN = autoComplete(prefix, autoWords);

                sb.append(autoWordsN);
                sb.append(" ");
				
				for (int i = 0; i < autoWordsN; ++i) {
					String inputStr;
					
					for (int j = 0; autoWords[i][j] != '\0'; j++)
                        sb.append(autoWords[i][j]);
                    sb.append(" ");
				}
			}
			else {
				int idx = getRandomWordIndex(n);

				search(words[idx / INDEX_DIV][idx % INDEX_DIV]);
			}
		}
        System.out.println(sb.toString().trim());

		return accepted;
	}

	public static void main(String[] args) throws Exception {
		int test, T;
		int n, m, initSearchN;
		
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		orgWordsN = sc.nextInt();

		for (int i = 0; i < orgWordsN; ++i) {
			String inputStr;
			
			wordsLen[0][i] = sc.nextInt();
			inputStr = sc.next();
			
			for (int j = 0; j < inputStr.length(); ++j) {
				words[0][i][j] = inputStr.charAt(j);	
			}
		}
		for (test = 1; test <= T; ++test) {

			seed = sc.nextInt();
			n = sc.nextInt();
			m = sc.nextInt();
			startPt = sc.nextInt();
			newWordsN = sc.nextInt();
			initSearchN = sc.nextInt();

			makeWords(startPt, n);

			init();

			while (initSearchN-- > 0) {
				int idx = getRandomWordIndex(n);

				search(words[idx / INDEX_DIV][idx % INDEX_DIV]);
			}
            System.out.printf("Case #%d:\n", test);
            run(sc, n, m);
		}
	}
}
