package com.theclcode.unfinished.library;

import java.util.Scanner;

class Library
{

	static HashTable<char[], LinkedList<BOOK_INFO>> authors;
	static HashTable<char[], LinkedList<BOOK_INFO>> genre;
	static HashTable<char[], BOOK_INFO> titles;

	static class BOOK_TRANSACTIONS {
		int quantity;
		int sold=0;

		BOOK_TRANSACTIONS(){

		}

		BOOK_TRANSACTIONS(int quantity){
			this.quantity = quantity;
		}

		void sell(){
			quantity--;
			sold++;
		}
	}

	static class BOOK_INFO
	{
		public char title[]  = new char[21];
		public char author[] = new char[11];
		public char genre[]  = new char[11];
		BOOK_TRANSACTIONS transactions = new BOOK_TRANSACTIONS();
	}
	
	/**************** START OF USER SOLUTION ****************/

	static void init()	{
		authors = new HashTable<>(103);
		genre = new HashTable<>(53);
		titles = new HashTable<>(1013);
	}

	static void bringBooks(char ti[], char au[], char gen[], int quan)	{
		BOOK_INFO bookInfo = new BOOK_INFO();
		for(int i=0; i<ti.length && ti[i] != '\0'; i++){
			bookInfo.title[i] = ti[i];
		}
		for(int i=0; i<au.length && au[i] != '\0'; i++){
			bookInfo.author[i] = au[i];
		}
		for(int i=0; i<gen.length && gen[i] != '\0'; i++){
			bookInfo.genre[i] = gen[i];
		}
	}

	static void takeOutBooks(char ti[], int quan) {

	}

	static int checkBooks(int info, char str[]) {

		return 0;
	}

	static BOOK_INFO recommendBook(int info, char str[]) {
		BOOK_INFO result = new BOOK_INFO();
		result.title[0] = 0;
		result.author[0] = 0;
		result.genre[0] = 0;
		return result;
	}

	static class HashTable<K, V>{
		int capacity;
		Node<K, V>[] table;
		final static int BASE = 37;
		final static int[] POWERS = {1, BASE, BASE * BASE};

		public HashTable(int capacity){
			this.capacity = capacity;
			this.table = new Node[capacity];
		}

		public HashTable(){
			this(13);
		}

		public Node<K, V>[] getTable() {
			return table;
		}

		public boolean contains(K key){
			return find(key) != null;
		}

		public void remove(K key){
			Node<K, V> node = find(key);
			if(node != null){
				int index = getAddress(key);
				if(node.next != null){
					node.next.prev = node.prev;
				}
				if(node.prev != null){
					node.prev.next = node.next;
				}
				if(table[index] == node){
					if(node.next != null){
						node.next.prev = null;
					}
					table[index] = node.next;
				}
			}
		}

		public void put(K key, V value){
		    int hash = hash(key);
			int index = hash % capacity;
			if(table[index] == null){
				Node<K, V> node = new Node<>(key, value);
				node.hash = hash;
				table[index] = node;
			} else {
				Node<K, V> node = find(key);
				if(node == null){
					node = new Node<>(key, value);
                    node.hash = hash;
					Node<K, V> existing = table[index];
					node.next = existing;
					existing.prev = node;
					table[index] = node;
				} else {
					node.value = value;
				}
			}
		}

		public V get(K key){
			Node<K, V> node = find(key);
			if(node == null){
				return null;
			}
			return node.value;
		}

		private Node<K, V> find(K key){
			int index = getAddress(key);
			Node<K, V> node = table[index];
			int keyHash = hash(key);
			while(node != null){
				if(key instanceof char[] && node.key instanceof char[]){
				    if(node.hash == keyHash){
                        char[] _key = (char[]) key;
                        char[] _nodeKey = (char[]) node.key;
                        int c = 0;
                        while(_key[c] != '\0' && _key[c] == _nodeKey[c]){
                            c++;
                        }
                        if(_key[c] - _nodeKey[c] == 0){
                            return node;
                        }
                    }
                } else if((key == null && node.key == null) || (node.key != null && node.key.equals(key))){
					return node;
				}
				node = node.next;
			}
			return null;
		}

		private int getAddress(K key){
			return key == null ? 0 : hash(key) % capacity;
		}

		private int hash(K key){
			String word = key.toString();
			int hash = 0;
			for(int i=0, y=2; i<word.length(); i++, y--){
				if(i < 3){
					hash += word.charAt(i) * POWERS[y];
				} else {
					hash += word.charAt(i);
				}
			}
			return hash;
		}

		class Node<K, V>{
			K key;
			V value;
			Node<K, V> prev;
			Node<K, V> next;
			int hash;

			Node(K key, V value){
				this.key = key;
				this.value = value;
			}
		}
	}

	static class LinkedList<E> {

		int size;
		Node<E> head;
		Node<E> tail;

		public void add(E value){
			Node<E> node = new Node<>(value);
			if(head == null){
				head = tail = node;
			} else {
				if(value instanceof char[]){
					Node<E> existing = head;

				}
			}
			size++;
		}

		class Node<E>{
			E value;
			Node<E> prev;
			Node<E> next;

			Node(E value){
				this.value = value;
			}
		}
	}

	/***************** END OF USER SOLUTION *****************/


	private static Scanner sc;
	private static BOOK_INFO book = new BOOK_INFO();
	
	private final static int TITLE				= 0;
	private final static int AUTHOR				= 1;
	private final static int GENRE				= 2;

	private final static int INIT				= 0;
	private final static int BRING				= 1;
	private final static int TAKEOUT			= 2;
	private final static int CHECK				= 3;
	private final static int RECOMMEND			= 4;

	private final static int MAX_TITLE_LENGTH	= 20;
	private final static int MIN_TITLE_LENGTH	= 5;
	private final static int MAX_AUTHOR_LENGTH	= 10;
	private final static int MIN_AUTHOR_LENGTH	= 5;
	private final static int MAX_GENRE_LENGTH	= 10;
	private final static int MIN_GENRE_LENGTH	= 5;

	private static void strCpy(char dest[], char src[]) 
	{
		int idx;
		for (idx = 0; src[idx] != 0; ++idx) {
			dest[idx] = src[idx];
		}
		dest[idx] = 0;
	}
	
	private static int strCmp(char str1[], char str2[]) 
	{
		int idx;
		for (idx = 0; str1[idx] != 0 && str2[idx] != 0; ++idx) {
			if (str1[idx] != str2[idx]) {
				break;
			}	
		}
		return str1[idx] - str2[idx];
	}
	private static int mSeed;
	private static int pseude_rand()
	{
		mSeed = mSeed * 431345 + 2531999;
		return mSeed & 0x7FFFFFFF;
	}
	
	private static void make_title(char str[], int seed)
	{
		mSeed = seed;
		int length = MIN_TITLE_LENGTH + pseude_rand() % (MAX_TITLE_LENGTH + 1 - MIN_TITLE_LENGTH);
		for (int i = 0; i < length; ++i) {
			str[i] = (char)('a' + pseude_rand() % 26);
		}
		str[length] = 0;
	}

	private static void make_author(char str[], int seed) 
	{
		mSeed = seed;
		int length = MIN_AUTHOR_LENGTH + pseude_rand() % (MAX_AUTHOR_LENGTH + 1 - MIN_AUTHOR_LENGTH);
		for (int i = 0; i < length; ++i) {
			str[i] = (char)('a' + pseude_rand() % 26);
		}
		str[length] = 0;
	}

	private static void make_genre(char str[], int seed) 
	{
		mSeed = seed;
		int length = MIN_GENRE_LENGTH + pseude_rand() % (MAX_GENRE_LENGTH + 1 - MIN_GENRE_LENGTH);
		for (int i = 0; i < length; ++i) {
			str[i] = (char)('a' + pseude_rand() % 26);
		}
		str[length] = 0;
	}

	private static char str1[] = new char[21];
	private static char str2[] = new char[11];
	private static char str3[] = new char[11];

	private static void run()
	{
		int ret, p1, p2, p3, p4;
		int N, cmd, flag;
		cmd = sc.nextInt();
		N = sc.nextInt();
		flag = sc.nextInt();
		
		init();

		for (int i = 1; i < N; ++i) {
			cmd = sc.nextInt();

			switch (cmd) {
			case BRING:
				if (flag == 1) {
					String str = sc.next();		
					for (int k = 0; k < str.length(); ++k) {
						str1[k] = str.charAt(k);
					}
					str1[str.length()] = 0;
					str = sc.next();
					for (int k = 0; k < str.length(); ++k) {
						str2[k] = str.charAt(k);
					}
					str2[str.length()] = 0;
					str = sc.next();
					for (int k = 0; k < str.length(); ++k) {
						str3[k] = str.charAt(k);
					}
					str3[str.length()] = 0;
					p4 = sc.nextInt();
				}
				else {
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					p3 = sc.nextInt();
					p4 = sc.nextInt();
					make_title(str1, p1);
					make_author(str2, p2);
					make_genre(str3, p3);
				}
				bringBooks(str1, str2, str3, p4);
				break;

			case TAKEOUT:
				if (flag == 1) {
					String str = sc.next();				
					for (int k = 0; k < str.length(); ++k) {
						str1[k] = str.charAt(k);
					}
					str1[str.length()] = 0;
					p2 = sc.nextInt();
				}
				else {
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					make_title(str1, p1);
				}
				takeOutBooks(str1, p2);
				break;

			case CHECK:
				if (flag == 1) {
					p1 = sc.nextInt();
					String str = sc.next();				
					for (int k = 0; k < str.length(); ++k) {
						str1[k] = str.charAt(k);
					}
					str1[str.length()] = 0;
				}
				else {
					p1 = sc.nextInt();
					p2 = sc.nextInt();
					if (p1 == 0) make_title(str1, p2);
					else if (p1 == 1) make_author(str1, p2);
					else make_genre(str1, p2);
				}
				ret = checkBooks(p1, str1);

				System.out.printf("%d\n", ret);

				break;

			case RECOMMEND:
				if (flag == 1) {
					String str_2, str_3;
					p1 = sc.nextInt();
					String str = sc.next();
					if (p1 == 1) {
						str_2 = str;
						for (int k = 0; k < str.length(); ++k) {
							str2[k] = str.charAt(k);
						}
						str2[str.length()] = 0;
					}
					else {
						str_3 = str;
						for (int k = 0; k < str.length(); ++k) {
							str3[k] = str.charAt(k);
						}
						str3[str.length()] = 0;
					}
				}
				else {
					p1 = sc.nextInt();
					if (p1 == 1) {
						p3 = sc.nextInt();
						make_author(str2, p3);
					}
					else {
						p4 = sc.nextInt();
						make_genre(str3, p4);
					}
				}
				if(p1 == 1) book = recommendBook(p1, str2);
				else book = recommendBook(p1, str3);

				StringBuilder sb = new StringBuilder();
				for (int ctr = 0; book.title[ctr] != 0; ctr++)
					sb.append(book.title[ctr]);
				sb.append(' ');
				for (int ctr = 0; book.author[ctr] != 0; ctr++)
					sb.append(book.author[ctr]);
				sb.append(' ');
				for (int ctr = 0; book.genre[ctr] != 0; ctr++)
					sb.append(book.genre[ctr]);
				System.out.printf("%s\n", sb.toString());

				break;

			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for (int testcase = 1; testcase <= TC; ++testcase) {
			System.out.printf("Case #%d:\n", testcase);
			run();
		}
	}
}
