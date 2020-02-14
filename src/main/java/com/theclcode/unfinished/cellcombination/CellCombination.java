package com.theclcode.unfinished.cellcombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class CellCombination {

	/**************** START OF USER SOLUTION ****************/

	static void init(int R, int C) {

	}

	static void setContent(int row, int col, char str[]) {

	}

	static void getContent(int row, int col, char str[]) {

		str[0] = 0;
	}

	static void mergeCell(int row1, int col1, int row2, int col2, int opt) {

	}

	static int countCell(char str[]) {

		str[0] = 0;
		return 0;
	}

	static int countArea(char str[]) {

		str[0] = 0;
		return 0;
	}

	static class Trie {

	}

	static class Content {
		char[] value;
	}

	static class UnionFindNode {
		UnionFindNode parent;
		int id;
		int size=1;

		UnionFindNode(int id){
			this.id = id;
		}

		public UnionFindNode getParent() {
			if(parent == null){
				return this;
			}
			return parent.getParent();
		}

		public void setParent(UnionFindNode parent) {
			if(getParent() != parent.getParent()){
				this.parent = parent.getParent();
				this.parent.size += this.size;
			}
		}
	}

	/***************** END OF USER SOLUTION *****************/


	public static final int INIT		 = 0;
	public static final int SETCONTENT	 = 1;
	public static final int GETCONTENT	 = 2;
	public static final int MERGECELL	 = 3;
	public static final int COUNTCELL	 = 4;
	public static final int COUNTAREA	 = 5;

	static int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; ++i) if (a[i] != b[i]) return (int)(a[i] - b[i]);
		return (int)(a[i] - b[i]);
	}

	static void run() throws Exception {
		int N, cmd, p1, p2, p3, p4, p5, ret;
		char str[] = new char[11];
		char buf[] = new char[11];

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case INIT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				init(p1, p2);

				break;
			case SETCONTENT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				String temp_2 = st.nextToken();
				for (int j = 0; j < temp_2.length(); j++)
					buf[j] = temp_2.charAt(j);
				buf[temp_2.length()] = '\0';

				setContent(p1, p2, buf);

				break;
			case GETCONTENT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				getContent(p1, p2, buf);
				
				StringBuilder sb = new StringBuilder();
				for (int j = 0; buf[j] != '\0'; j++)
					sb.append(buf[j]);
				System.out.printf("%s\n", sb.toString());

				break;
			case MERGECELL:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				p3 = Integer.parseInt(st.nextToken());
				p4 = Integer.parseInt(st.nextToken());
				p5 = Integer.parseInt(st.nextToken());
				mergeCell(p1, p2, p3, p4, p5);
				
				break;
			case COUNTCELL:
				String temp_4 = st.nextToken();
				for (int j = 0; j < temp_4.length(); j++)
					buf[j] = temp_4.charAt(j);
				buf[temp_4.length()] = '\0';
				ret = countCell(buf);

				System.out.printf("%d\n", ret);

				break;
			case COUNTAREA:
				String temp_5 = st.nextToken();
				for (int j = 0; j < temp_5.length(); j++)
					buf[j] = temp_5.charAt(j);
				buf[temp_5.length()] = '\0';
				ret = countArea(buf);
				
				System.out.printf("%d\n", ret);

				break;
			default:
				break;
			}
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int T;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run();
		}
	}
}
