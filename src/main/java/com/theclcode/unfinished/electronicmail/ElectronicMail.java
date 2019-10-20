package com.theclcode.unfinished.electronicmail;

import com.sun.xml.internal.ws.api.model.MEP;

import java.io.*;
import java.util.StringTokenizer;

public class ElectronicMail {
	static final int INIT = 0;
	static final int SENDMAIL = 1;
	static final int GETCOUNT = 2;
	static final int DELETEMAIL = 3;
	static final int SEARCHMAIL = 4;
	static HashTable<Integer, User> users;
	static int inboxCapacity;

	/**************** START OF USER SOLUTION ****************/

	static void init(int N, int K) {
		users = new HashTable<>(307);
		inboxCapacity = K;
	}

	static void sendMail(char[] subject, int uID, int cnt, int[] rIDs) {
		Word word = new Word();
		Message message = new Message();
		DoublyLinkedList<Word> subjectObj = new DoublyLinkedList<>();
		message.setSubject(subjectObj);
		HashTable<Word, Boolean> words = new HashTable<>();
		for(int i=0; i<subject.length; i++){
			if(subject[i]=='\0' || subject[i]==' '){
				if(!words.contains(word)){
					words.put(word, true);
				}
				subjectObj.add(word);
				if(subject[i] == '\0'){
					break;
				}
				word = new Word();
			} else {
				word.add(subject[i]);
			}
		}

		for(int r=0; r<cnt; r++){
			User user;
			HashTable<Word, Boolean> uniqueWordsInSubject = new HashTable<>();
			if(users.contains(rIDs[r])){
				user = users.get(rIDs[r]);
				if(user.getInbox().size>=inboxCapacity){
					user.getInbox().remove();
				}
				user.getInbox().add(message);
			} else {
				user = new User(rIDs[r]);
				DoublyLinkedList<Message> inbox = new DoublyLinkedList<>();
				inbox.add(message);
				user.setInbox(inbox);
				users.put(rIDs[r], user);
			}
			DoublyLinkedList.Node<Word> node = message.getSubject().getHead();
			while(node != null){
				if(uniqueWordsInSubject.contains(node.getValue())){
					node = node.next;
					continue;
				}
				uniqueWordsInSubject.put(node.getValue(), true);
				if(!user.getUniqueWords().contains(node.getValue())){
					user.getUniqueWords().put(node.getValue(), 1);
				} else {
					int count = user.getUniqueWords().get(node.getValue());
					user.getUniqueWords().put(node.getValue(), ++count);
				}
				node = node.next;
			}
		}
	}

	static int getCount(int uID) {
		if(users.contains(uID)){
			User user = users.get(uID);
			return user.getInbox().size;
		}
		return 0;
	}

	static int deleteMail(int uID, char[] subject) {
		DoublyLinkedList<Message> inbox = users.get(uID).getInbox();
		DoublyLinkedList<Word> subjectObj = new DoublyLinkedList<>();
		Word word = new Word();
		int deleted = 0;
		for(int i=0; i<subject.length; i++){
			if(subject[i]=='\0' || subject[i]==' '){
				subjectObj.add(word);
				if(subject[i] == '\0'){
					break;
				}
				word = new Word();
			} else {
				word.add(subject[i]);
			}
		}

		DoublyLinkedList.Node<Message> inboxNode = inbox.getHead();
		while(inboxNode != null){
			Message message = inboxNode.getValue();
			HashTable<Word, Boolean> uniqueWordsInSubject = new HashTable<>();
			if(message.getSubject().getSize() == subjectObj.getSize()){
				DoublyLinkedList<Word> messageSubject = message.getSubject();
				boolean isEqual = true;
				DoublyLinkedList.Node<Word> messageSubjectWordNode = messageSubject.getHead();
				DoublyLinkedList.Node<Word> subjectObjWordNode = subjectObj.getHead();
				while(messageSubjectWordNode != null){
					if(!messageSubjectWordNode.getValue().equals(subjectObjWordNode.getValue())){
						isEqual = false;
						break;
					}
					subjectObjWordNode = subjectObjWordNode.next;
					messageSubjectWordNode = messageSubjectWordNode.next;
				}
				if(isEqual){
					inbox.remove(inboxNode);
					deleted++;
				}
			}
			inboxNode = inboxNode.next;
		}

		return deleted;
	}

	static int searchMail(int uID, char[] text) {
		User user = users.get(uID);
		if(user != null){
			Word word = new Word();
			for(int i=0; i<text.length; i++){
				if(text[i] == '\0'){
					break;
				}
				word.add(text[i]);
			}
			if(user.getUniqueWords().contains(word)){
				return user.getUniqueWords().get(word);
			}
			return 0;
		}
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

	public static class HashTable<K, V> {

		int capacity;
		Node<K, V>[] table;
		static final int BASE = 37;
		static final int[] POWERS = new int[]{BASE * BASE, BASE, 1};

		HashTable(){
			this(1013);
		}

		HashTable(int capacity){
			this.capacity = capacity;
			table = new Node[capacity];
		}

		public void put(K key, V value){
			int index = getAddress(key);
			if(table[index] == null){
				Node<K, V> node = new Node<>(key, value);
				table[index] = node;
			} else {
				Node<K, V> node = find(key);
				if(node == null){
					node = new Node(key, value);
					Node<K, V> existingNode = table[index];
					node.next = existingNode;
					existingNode.prev = node;
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

		public boolean contains(K key){
			return find(key) != null;
		}

		public Node<K, V> find(K key){
			int index = getAddress(key);
			Node<K, V> node = table[index];
			while(node != null){
				if(key instanceof String || key instanceof Word){
					String _key = key.toString();
					String nodeKey = node.key.toString();
					if(_key.length() == nodeKey.length()){
						boolean isEqual = true;
						for(int i=0; i<_key.length(); i++){
							if(_key.charAt(i) != nodeKey.charAt(i)){
								isEqual = false;
								break;
							}
						}
						if(isEqual){
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

		public int getAddress(K key){
			return key == null ? 0 : hash(key) % capacity;
		}

		public int hash(K key){
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

		class Node<K, V> {
			K key;
			V value;
			Node<K, V> next;
			Node<K, V> prev;
			Node(K key, V value){
				this.key = key;
				this.value = value;
			}
		}
	}

	static class DoublyLinkedList<E> {

		Node<E> head;
		Node<E> tail;
		private int size = 0;

		public void add(E value){
			Node<E> node = new Node(value);
			if(head == null){
				head = tail = node;
			} else {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
			size++;
		}

		public E remove(){
			if(head == null){
				return null;
			}
			Node<E> node = head;
			head = head.next;
			if(head != null){
				head.prev = null;
			}
			size--;
			return node.value;
		}

		public E remove(Node<E> node){
			Node<E> existingNode = getHead();
			while(existingNode != null){
				if(node == existingNode){
					if(node.prev != null){
						node.prev.next = node.next;
					}
					if(node.next != null){
						node.next.prev = node.prev;
					}
				}
				existingNode = existingNode.next;
			}
			return node.getValue();
		}

		public Node<E> getHead(){
			if(head == null){
				return null;
			}
			return head;
		}

		public int getSize() {
			return size;
		}

		static class Node<E> {
			E value;
			Node<E> prev;
			Node<E> next;
			Node(E value){
				this.value = value;
			}

			public E getValue() {
				return value;
			}
		}
	}

	static class Message {
		private DoublyLinkedList<Word> subject;

		public DoublyLinkedList<Word> getSubject() {
			return subject;
		}

		public void setSubject(DoublyLinkedList<Word> subject) {
			this.subject = subject;
		}
	}

	static class User {
		int uid;
		HashTable<Word, Integer> uniqueWords = new HashTable<>();
		DoublyLinkedList<Message> inbox;
		User(int uid){
			this.uid = uid;
		}

		public void setInbox(DoublyLinkedList<Message> inbox) {
			this.inbox = inbox;
		}

		public DoublyLinkedList<Message> getInbox() {
			return inbox;
		}

		public HashTable<ElectronicMail.Word, Integer> getUniqueWords() {
			return uniqueWords;
		}

		@Override
		public String toString() {
			return ""+uid;
		}
	}

	static class Word {

		private char[] value = new char[10];
		private int index = 0;

		public void add(char c){
			value[index++] = c;
		}

		@Override
		public String toString() {
			return new String(value);
		}

		@Override
		public boolean equals(Object obj) {
			if(obj.toString().length() == toString().length()){
				String find = obj.toString();
				String value = toString();
				for(int i=0; i<value.length(); i++){
					if(find.charAt(i) != value.charAt(i)){
						return false;
					}
				}
				return true;
			}
			return false;
		}
	}
}
