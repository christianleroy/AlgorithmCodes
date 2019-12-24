package com.theclcode.swc.newsletter.nov19.findthestring;

import java.util.Scanner;

class FindTheString {
	
	/**************** START OF USER SOLUTION ****************/

	static char[] full_string;
	static LinkedList[][][] trie;
	static int LENGTH;

	static void init(int N, char init_string[]) {
		trie = new LinkedList[26][26][26];
		full_string = new char[N];
		LENGTH = N;
		for(int i=0; i<N; i++){
			full_string[i] = init_string[i];
			if(i <= N-3){
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
		LinkedList linkedList = trie[string_A[0]-97][string_A[1]-97][string_A[2]-97];
		if(linkedList != null){
			LinkedList.Node node = linkedList.getHead();
			int lastMatched = -1;
			int replaced = 0;
			while(node != null){
				if(lastMatched != -1 && node.value < lastMatched+3){
					node = node.next;
					continue;
				}
				lastMatched = node.value;
				remove(node.value-2, node.value+2, LENGTH);
				for(int i=node.value, j=0; i<node.value+3; i++, j++){
					full_string[i] = string_B[j];
				}
				add(node.value-2, node.value+2, LENGTH);
				replaced++;
				node = node.next;
			}
			return replaced;
		}
		return 0;
	}
	
	static void result(char ret[]) {
		for(int i=0; i < full_string.length; i++){
			ret[i] = full_string[i];
		}
	}

	static void add(int start, int end, int stringLength){
		if(start < 0){
			add(start+1, end, stringLength);
		} else {
			if(start > stringLength-3 || start > end){
				return;
			}
			LinkedList linkedList = trie[full_string[start]-97][full_string[start+1]-97][full_string[start+2]-97];
			if(linkedList != null){
				linkedList.add(start);
			} else {
				linkedList = new LinkedList();
				linkedList.add(start);
				trie[full_string[start]-97][full_string[start+1]-97][full_string[start+2]-97] = linkedList;
			}
			add(start+1, end, stringLength);

		}
	}

	static void remove(int start, int end, int stringLength){
		if(start < 0){
			remove(start+1, end, stringLength);
		} else {
			if(start > stringLength-3 || start > end){
				return;
			}
			LinkedList linkedList = trie[full_string[start]-97][full_string[start+1]-97][full_string[start+2]-97];
			if(linkedList != null){
				linkedList.remove(start);
			}
			remove(start+1, end, stringLength);
		}
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
				Node existing = head;
				while(node != null){
					if(node.value < existing.value){
						node.next = existing;
						node.prev = existing.prev;
						if(existing.prev != null){
							existing.prev.next = node;
						}
						if(existing == head){
							head = node;
						}
						existing.prev = node;
						break;
					} else {
						if(existing == tail){
							tail.next = node;
							node.prev = tail;
							tail = node;
							break;
						}
					}
					existing = existing.next;
				}
			}
			size++;
		}

		public void remove(int value){
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
		}

		public Node getHead() {
			return head;
		}

		static class Node {
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

	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		total_score = 0;
		for (int TC = 1; TC <= T; TC++) {
			System.out.printf("Case #%d:\n", TC);

			score = 100;
			mSeed = sc.nextInt();
			init_string_len = sc.nextInt();
			char_type = sc.nextInt();
			query_cnt = sc.nextInt();

			for (int i = 0; i < init_string_len; i++)
			{
				init_string[i] = (char)(pseudo_rand() % char_type + 'a');
			}
			init_string[init_string_len] = '\0';

			init(init_string_len, init_string);

			string_A[3] = string_B[3] = '\0';
			for (int i = 0; i < query_cnt; i++)
			{
				for (int k = 0; k < 3; k++)
				{
					string_A[k] = (char) ((pseudo_rand() % char_type) + 'a');
					string_B[k] = (char) ((pseudo_rand() % char_type) + 'a');
				}
				int user_ans = change(string_A, string_B);
				System.out.printf("%d\n", user_ans);
			}

			for (int i = 0; i < init_string_len; i++)
				user_ans_string[i] = ' ';
			result(user_ans_string);
			for (int i = 0; i < init_string_len; i++)
				System.out.printf("%c", user_ans_string[i]);
			System.out.println();
		}
	}

}
