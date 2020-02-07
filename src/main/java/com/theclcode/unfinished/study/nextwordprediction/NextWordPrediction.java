package com.theclcode.unfinished.study.nextwordprediction;

import java.util.Scanner;

class NextWordPrediction {

    /**************** START OF USER SOLUTION ****************/

    static HashTable<char[], Word> searchedWords;

    static Trie root;
    
    static void init() {
		searchedWords = new HashTable<>(1013);
		root = new Trie();
    }

    static void search(char[] words) {
		Word word = null;
		boolean newWord = false;
		if(searchedWords.contains(words)){
			word = searchedWords.get(words);
			word.increaseCount();
		} else {
			word = new Word(words);
			newWord = true;
			searchedWords.put(words, word);
		}

        Trie trie = root;
        for(int i=0; i<words.length && words[i] != '\0'; i++){
			if(trie.node[words[i]-97] == null){
				trie.node[words[i]-97] = new Trie();
			}
			if(newWord){
				trie.node[words[i]-97].count++;
			}
			trie.topWords.add(word);
			trie = trie.node[words[i]-97];
        }
		trie.topWords.add(word);
    }

    static int autoComplete(char[] prefix, char[][] autoWords) {
		Trie trie = root;
		for(int i=0; i<prefix.length && prefix[i] != '\0'; i++){
			trie = trie.node[prefix[i]-97];
			if(trie == null){
				return 0;
			}
		}

		LinkedList.Node node = trie.topWords.getHead();
		int j = 0;
		while(node != null){
			char[] word = ((Word) node.value).value;
			for(int i=0; word[i] != '\0'; i++){
				autoWords[j][i] = word[i];
			}
			j++;
			node = node.next;
		}
		return trie.count;
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
					removeIfExists((Word) value);
					Node<E> existing = head;
					char[] nodeWord = ((Word) node.value).value;
					boolean inserted = false;
					while(existing != null) {
						if(((Word) node.value).count > ((Word) existing.value).count){
							insertNode(node, existing);
							inserted = true;
							break;
						} else if(((Word) node.value).count == ((Word) existing.value).count){
							char[] existingWord = ((Word) existing.value).value;
							int i=0;
							boolean equal = true;
							for(; i<nodeWord.length && i<existingWord.length && nodeWord[i] != '\0'
								&& existingWord[i] != '\0'; i++){
								if(nodeWord[i] == existingWord[i]){
									continue;
								}
								equal = false;
								if(nodeWord[i] < existingWord[i]){
									insertNode(node, existing);
									inserted = true;
								}
								break;
							}
							if(inserted){
								break;
							} else if(equal && nodeWord[i] < existingWord[i]) {
								insertNode(node, existing);
								inserted = true;
								break;
							}

						} else {
							if(existing == tail){
								tail.next = node;
								node.prev = tail;
								tail = node;
								inserted = true;
								break;
							}
						}
						existing = existing.next;
					}
					if(!inserted){
						if(head == null){
							head = tail = node;
						} else {
							node.prev = tail;
							tail.next = node;
							tail = node;
						}
					}
				} else {
					tail.next = node;
					node.prev = tail;
					tail = node;
				}
			}
    		size++;
    		if(size > 5){
    			removeLast();
			}
		}

		public Node<E> getHead() {
			return head;
		}

		private void removeIfExists(Word word){
    		Node<E> node = head;
    		while(node != null){
    			Word exWord = (Word)node.value;
    			if(exWord.same(word)){
    				if(node.next != null){
    					node.next.prev = node.prev;
					}
    				if(node.prev != null){
    					node.prev.next = node.next;
					}
					if(node == head){
						head = node.next;
						if(head == null){
							tail = null;
						} else {
							head.prev = null;
						}
					}
					if(node == tail){
						tail = node.prev;
						if(tail == null){
							head = null;
						} else {
							tail.next = null;
						}
					}
					size--;
					break;
				}
    			node = node.next;
			}
		}

		private void insertNode(Node<E> node, Node<E> existing) {
			node.prev = existing.prev;
			node.next = existing;
			if(existing.prev != null){
				existing.prev.next = node;
			}
			if(existing == head){
				head = node;
			}
			existing.prev = node;
		}

		public E removeLast(){
    		if(tail == null){
    			return null;
			}
    		Node<E> node = tail;
    		tail = tail.prev;
    		if(tail == null){
    			head = null;
			} else {
    			tail.next = null;
			}
    		size--;
    		return node.value;
		}



    	class Node<E> {
    		E value;
    		Node<E> prev;
    		Node<E> next;

    		Node(E value){
    			this.value = value;
			}
		}
	}

	static class Word {
    	char[] value;
    	int hash = 0;
    	int count;
    	final static int BASE = 37;
    	final static int[] POWERS = {1, BASE, BASE * BASE};

    	Word(char[] value){
    		this.value = value;
    		this.count = 1;
    		setHash();
    	}

    	private void increaseCount(){
    		this.count++;
		}

		private void setHash(){
    		for(int i=0, y=2; i<this.value.length && this.value[i] != '\0'; i++){
				if(i < 3){
					this.hash += this.value[i] * POWERS[y--];
				} else {
					this.hash += this.value[i];
				}
			}
		}

    	public boolean same(Word word){
			if(word.hash == this.hash){
				int i=0;
				for(; this.value[i] != '\0' && word.value[i] != '\0'; i++){
					if(this.value[i] != word.value[i]){
						return false;
					}
				}
				return this.value[i] == word.value[i];
			}
			return false;
		}
	}

	static class Trie {
    	int count;
    	Trie[] node;
		LinkedList<Word> topWords = new LinkedList<>();
    	Trie(){
    		node = new Trie[26];
		}
	}

	static class HashTable<K, V>{

		int capacity;
		Node<K, V>[] table;
		final static int BASE = 37;
		final static int[] POWERS = {1, BASE, BASE * BASE};

		public HashTable(){
			this(13);
		}

		public HashTable(int capacity){
			this.capacity = capacity;
			this.table = new Node[capacity];
		}

		public boolean contains(K key){
			return find(key) != null;
		}

		public Node<K, V>[] getTable() {
			return table;
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
				if(node == table[index]){
					table[index] = node.next;
					if(node.next != null){
						node.next.prev = null;
					}
				}
			}
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
			while(node != null){
				if(key instanceof char[] && node.key instanceof char[]){
					if(mstrcmp((char[]) key, (char[]) node.key) == 0){
						return node;
					}
				}
				node = node.next;
			}
			return null;
		}

		int getAddress(K key){
			return key == null ? 0 : hash(key) % capacity;
		}

		int hash(K key){
			char[] word = (char[]) key;
			int hash = 0;
			for(int i=0, y=2; i<word.length; i++, y--){
				if(i < 3){
					hash += word[i] * POWERS[y];
				} else {
					hash += word[i];
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
						try {

							for (int j = 0; autoWords[i][j] != '\0'; j++)
								sb.append(autoWords[i][j]);
							sb.append(" ");
						} catch (Exception e){
							System.out.println();
						}
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

//	public static void main(String[] args) {
//		init();
//		search(mstrcpy(new char[6], "abc".toCharArray()));
//		search(mstrcpy(new char[6], "abc".toCharArray()));
//		search(mstrcpy(new char[6], "abcd".toCharArray()));
//		search(mstrcpy(new char[6], "abcde".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		search(mstrcpy(new char[6], "azzzz".toCharArray()));
//		System.out.println(autoComplete(mstrcpy(new char[13], "a".toCharArray()), new char[5][13]));
//		System.out.println();
//	}

	static char[] mstrcpy(char dst[], char src[])
	{
		int c = 0;
		while(c < src.length && (dst[c] = src[c]) != 0) ++c;
		dst[c] = 0;
		return dst;
	}
}
