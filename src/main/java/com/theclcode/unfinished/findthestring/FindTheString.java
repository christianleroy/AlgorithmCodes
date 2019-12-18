package com.theclcode.unfinished.findthestring;

import java.util.Scanner;

class FindTheString {
	
	/**************** START OF USER SOLUTION ****************/

	static char[] full_string;
	static LinkedList[][][] trie;

	static void init(int N, char init_string[]) {
		trie = new LinkedList[26][26][26];
		full_string = new char[N];
		for(int i=0; i<N; i++){
			full_string[i] = init_string[i];
			if(i<=N-3){
				LinkedList linkedList = trie[init_string[i]-97][init_string[i+1]-97][init_string[i+2]-97];
				if(linkedList == null){
					linkedList = new LinkedList();
					linkedList.add(i);
					trie[init_string[i]-97][init_string[i+1]-97][init_string[i+2]-97] = linkedList;
				} else {
					linkedList.add(i);
				}
			}

		}
	}

	static int change(char string_A[], char string_B[]) {
		
		return 0;
	}
	
	static void result(char ret[]) {
		
	}

	static class LinkedList {

		Node head;
		Node tail;
		int size;

		public void add(int value){
			Node node = new Node(value);
			if(head == null){
				head = tail = node;
			} else {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
			size++;
		}

		public int remove(int value){
			Node node = head;
			while(node != null){
				if(node.value == value){
					if(node.next != null){
						node.next.prev = node.prev;
					}
					if(node.prev != null){
						node.prev.next = node.next;
					}
					if(head == node){
						head = node.next;
						if(head != null){
							head.prev = null;
						}
					}
					if(tail == node){
						tail = node.prev;
						if(tail != null){
							tail.next = null;
						}
					}
					size--;
					break;
				}
				node = node.next;
			}
			return -1;
		}

		public Node getHead() {
			return head;
		}

		class Node {
			int value;
			Node prev;
			Node next;

			Node(int value){
				this.value = value;
			}
		}
	}

	/***************** END OF USER SOLUTION *****************/


	private static int mSeed = 5;
	
	private static final int MAXL = 50005;
	private static final int DUMMY_LEN = 5959;
	private static int score = 0;
	private static int total_score = 0;
	
	private static Scanner sc;

	
	private static char dummy1[] = new char[DUMMY_LEN];
	private static char init_string[] = new char[MAXL];
	private static char dummy2[] = new char[DUMMY_LEN];
	private static char result_string[] = new char[MAXL];
	private static char dummy3[] = new char[DUMMY_LEN];
	private static char user_ans_string[] = new char[MAXL];
	private static char dummy4[] = new char[DUMMY_LEN];
	private static char string_A[] = new char[4];
	private static char string_B[] = new char[4];
	private static int init_string_len = 0;
	private static int char_type = 0;
	private static int query_cnt = 0;
	
	private static int pseudo_rand()
	{
		mSeed = mSeed * 214013 + 2531011;
		return (mSeed >> 16) & 0x7FFF;
	}

//	public static void main(String[] args) throws Exception {
//		sc = new Scanner(System.in);
//
//		int T = sc.nextInt();
//
//		total_score = 0;
//		for (int TC = 1; TC <= T; TC++) {
//			System.out.printf("Case #%d:\n", TC);
//
//			score = 100;
//			mSeed = sc.nextInt();
//			init_string_len = sc.nextInt();
//			char_type = sc.nextInt();
//			query_cnt = sc.nextInt();
//
//			for (int i = 0; i < init_string_len; i++)
//			{
//				init_string[i] = (char)(pseudo_rand() % char_type + 'a');
//			}
//			init_string[init_string_len] = '\0';
//
//			init(init_string_len, init_string);
//
//			string_A[3] = string_B[3] = '\0';
//			for (int i = 0; i < query_cnt; i++)
//			{
//				for (int k = 0; k < 3; k++)
//				{
//					string_A[k] = (char) ((pseudo_rand() % char_type) + 'a');
//					string_B[k] = (char) ((pseudo_rand() % char_type) + 'a');
//				}
//				int user_ans = change(string_A, string_B);
//				System.out.printf("%d\n", user_ans);
//			}
//
//			for (int i = 0; i < init_string_len; i++)
//				user_ans_string[i] = ' ';
//			result(user_ans_string);
//			for (int i = 0; i < init_string_len; i++)
//				System.out.printf("%c", user_ans_string[i]);
//			System.out.println();
//		}
//	}

	public static void main(String[] args) {
		char[] init_string = "aaaaaaaab".toCharArray();
		init(9, init_string);
		System.out.println();
	}
}
