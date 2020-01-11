package com.theclcode.unfinished.study.nextwordprediction;

import java.util.Scanner;

class NextWordPrediction {

    /**************** START OF USER SOLUTION ****************/

    static HashTable<Word, Integer> searchedWords;
    static Trie root;
    
    static void init() {
		searchedWords = new HashTable<>(1013);
		root = new Trie();
    }

    static void search(char[] words) {
        Word word = new Word(words);
        if(searchedWords.contains(word)){
        	Integer count = searchedWords.get(word);
        	searchedWords.put(word, ++count);
		} else {
        	searchedWords.put(word, 1);
		}
        Trie trie = root;
        for(int i=0; i<words.length && words[i] != '\0'; i++){
			if(trie.node[i-97] == null){
				trie.node[i-97] = new Trie();
			} else {
				trie.node[i-97].count++;
			}
			trie = trie.node[i-97];
        }
    }

    static int autoComplete(char[] prefix, char[][] autoWords) {

        return 0;
    }


	static class Trie {
    	int count;
    	Trie[] node;
		LinkedList<Word> topWords = new LinkedList<>();
    	Trie(){
    		count = 1;
    		node = new Trie[26];
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
				if(value instanceof Word){
					Word word = (Word) value;
					Integer wordCount = searchedWords.get(word);
					Node<E> existing = head;
					while(existing != null){
						Word existingWord = (Word) existing.value;
						Integer existingWordCount = searchedWords.get(existingWord);
						boolean inserted = false;
						if(wordCount == existingWordCount){
							char[] wordVal = word.value;
							char[] exWordVal = existingWord.value;
							for(int i=0; i<wordVal.length && i<exWordVal.length; i++){
								if(wordVal[i] <= exWordVal[i]){
									if(wordVal[i] == exWordVal[i]){
										if((i == wordVal.length-1 || wordVal[i+1] == '\0')
												&& (i < exWordVal.length-1 && exWordVal[i+1] != '\0')){
										} else{
											continue;
										}
									}
									node.next = existing;
									node.prev = existing.prev;
									if(existing.prev != null){
										existing.prev.next = node;
									}
									existing.prev = node;
									if(existing == head){
										head = node;
									}
									inserted = true;
								}
								break;
							}
							if(inserted){
								break;
							}
						} else if(wordCount > existingWordCount){
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

						}
						if(!inserted && existing == tail){
							tail.next = node;
							node.prev = tail;
							tail = node;
							break;
						}
						existing = existing.next;
					}
				} else {
					tail.next = node;
					node.prev = tail;
					tail = node;
				}

			}
			size++;
			if(size > 5){
				tail = tail.prev;
				tail.next = null;
				size--;
			}
		}

		static class Node<E> {
			E value;
			Node<E> prev;
			Node<E> next;

			Node(E value){
				this.value = value;
			}
		}
	}

	public static class HashTable<K, V>{

		int capacity;
		Node<K, V>[] table;
		static final int BASE = 37;
		static final int[] POWERS = {1, BASE, BASE * BASE};

		public HashTable(){
			this(13);
		}

		public HashTable(int capacity){
			this.capacity = capacity;
			table = new Node[capacity];
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
					table[index] = node.next;
					if(node.next != null){
						node.next.prev = null;
					}
				}
			}
		}

		public Node<K, V>[] getTable() {
			return table;
		}

		public V get(K key){
			Node<K, V> node = find(key);
			if(node == null){
				return null;
			}
			return node.value;
		}

		public boolean contains(K key){
			return find(key) != null;
		}

		public void put(K key, V value){
			int index = getAddress(key);
			if(table[index] == null){
				Node<K, V> node = new Node<>(key, value);
				table[index] = node;
			} else {
				Node<K, V> node = find(key);
				if(node == null){
					node = new Node<>(key, value);
					Node<K, V> existing = table[index];
					node.next = existing;
					existing.prev = node;
					table[index] = node;
				} else {
					node.value = value;
				}
			}
		}

		private Node<K, V> find(K key){
			int index = getAddress(key);
			Node<K, V> node = table[index];
			while(node != null){
				if(key instanceof Word && node.key instanceof Word){
					Word keyWord = (Word) key;
					Word nodeWord = (Word) node.key;
					if(keyWord.same(nodeWord)){
						return node;
					}
				}
				else if(key instanceof String && node.key instanceof String){
					String keyWord = key.toString();
					String nodeKey = node.key.toString();
					if(keyWord.length() == nodeKey.length()){
						boolean isEqual = true;
						for(int i=0; i<keyWord.length(); i++){
							if(keyWord.charAt(i) != nodeKey.charAt(i)){
								isEqual = false;
								break;
							}
						}
						if(isEqual){
							return  node;
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
			if(key instanceof Word){
				return ((Word) key).hash;
			}
			String word = key.toString();
			int hash = 0;
			for(int i=0, y=2; i<word.length(); i++, y--){
				if(i<3){
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

			Node(K key, V value){
				this.key = key;
				this.value = value;
			}
		}
	}

	static class Word {
    	char[] value;
    	int hash;
    	static final int BASE = 37;
    	static final int[] POWERS = {1, BASE, BASE * BASE};

    	Word(char[] value){
    		this.value = new char[value.length];
    		for(int i=0; i<value.length && value[i] != '\0'; i++){
				this.value[i] = value[i];
			}
    		setHash();
		}

		public boolean same(Object obj) {
    		Word compare = (Word) obj;
    		if(this.hash == compare.hash){
    			boolean isEqual = true;
    			for(int i=0; i<value.length; i++){
    				if(value[i] == '\0' && compare.value[i] == '\0'){
    					break;
					}
    				if(value[i] != compare.value[i]){
    					isEqual = false;
    					break;
					}
    			}
    			if(isEqual){
    				return true;
				}
			}
			return false;
		}

		void setHash(){
    		int hash = 0;
    		for(int i=0, y=2; i<this.value.length && this.value[i] != '\0'; i++, y--){
    			if(i < 3){
    				hash += this.value[i] * POWERS[y];
				} else {
    				hash += this.value[i];
				}
			}
    		this.hash = hash;
		}
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

//	public static void main(String[] args) throws Exception {
//		int test, T;
//		int n, m, initSearchN;
//
//		Scanner sc = new Scanner(System.in);
//
//		T = sc.nextInt();
//		orgWordsN = sc.nextInt();
//
//		for (int i = 0; i < orgWordsN; ++i) {
//			String inputStr;
//
//			wordsLen[0][i] = sc.nextInt();
//			inputStr = sc.next();
//
//			for (int j = 0; j < inputStr.length(); ++j) {
//				words[0][i][j] = inputStr.charAt(j);
//			}
//		}
//		for (test = 1; test <= T; ++test) {
//
//			seed = sc.nextInt();
//			n = sc.nextInt();
//			m = sc.nextInt();
//			startPt = sc.nextInt();
//			newWordsN = sc.nextInt();
//			initSearchN = sc.nextInt();
//
//			makeWords(startPt, n);
//
//			init();
//
//			while (initSearchN-- > 0) {
//				int idx = getRandomWordIndex(n);
//
//				search(words[idx / INDEX_DIV][idx % INDEX_DIV]);
//			}
//            System.out.printf("Case #%d:\n", test);
//            run(sc, n, m);
//		}
//	}

//	public static void main(String[] args) {
//		Word word = new Word("abc".toCharArray());
//		Word _word = new Word("abcd".toCharArray());
//		HashTable<Word, String> table = new HashTable<>();
//		table.put(word, "Hey");
//		table.put(_word, "Hey din");
//		System.out.println(table.get(word));
//		System.out.println(table.get(_word));
//		table.put(word, "Hey ka rin.");
//		System.out.println(table.get(word));
//	}
}
