package com.theclcode.unfinished.autocorrect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    /**************** START OF USER SOLUTION ****************/
	static DoublyLinkedList<char[]> sentence;

	static void init()
	{
		sentence = new DoublyLinkedList<>();
	}

	static void appendWord(char str[])
	{
		char[] newStr = new char[str.length];
		for(int i=0; i<str.length; i++){
			if(str[i]=='\0'){
				break;
			}
			newStr[i]=str[i];
		}
		sentence.add(newStr);
	}

	static void changeAllWord(char before[], char after[])
	{
		Node<char[]> node = sentence.head;
		while(node!=null){
			if(isEqual(before, node.getValue())){
				char[] replacement = new char[11];
				boolean foundSpace=false;
				for(int i=0; i<after.length; i++){
					if(after[i]=='\0'){
						foundSpace=true;
					}
					if(foundSpace){
						replacement[i]='\0';
					} else {
						replacement[i]=after[i];
					}
				}
				node.setValue(replacement);
			}
			node=node.next;
		}
	}

	static void addAfter(char str[], char new_str[])
	{
		Node<char[]> node = sentence.head;
		while(node!=null){
			if(isEqual(str, node.getValue())){
				char[] newStr = new char[11];
				boolean foundSpace=false;
				for(int i=0; i<new_str.length; i++){
					if(new_str[i]=='\0'){
						foundSpace=true;
					}
					if(foundSpace){
						newStr[i]='\0';
					} else {
						newStr[i]=new_str[i];
					}
				}
				sentence.insertAfter(node, newStr);
			}
			node=node.next;
		}
	}

	static void removeWord(int k)
	{
		sentence.findPositionAndRemove(k);
	}

	static char getLetter(int pos)
	{
		String string = build(sentence);
		char[] s = string.toCharArray();
		return s[pos-1];
	}

	static String build(DoublyLinkedList<char[]> sentence){
		String result="";
		Node<char[]> node = sentence.head;
		while(node!=null){
			for(char c: node.getValue()){
				if(c!='\0') {
					result += c;
				}
			}
			if(node.next!=null){
				result+=" ";
			}
			node = node.next;
		}
		return result;
	}

	static boolean isEqual(char[] a, char[] b){
		if(a.length!=b.length){
			return false;
		}

		for(int i=0; i<a.length; i++){
			if(a[i]=='\0'){
				break;
			}
			if(a[i]!=b[i]){
				return false;
			}
		}
		return true;
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
		System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(stinit.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run();
		}
		
		br.close();
	}

	public static class Node<E> {

		private E value;
		private Node prev;
		private Node next;

		public Node(E value) {
			this.value = value;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	public static class DoublyLinkedList<E>{

		private Node<E> head;
		private Node<E> tail;

		public void add(E value){
			Node<E> node = new Node(value);
			if(head==null){
				head = node;
				tail = head;
			} else {
				tail.next = node;
				node.prev=tail;
				tail = node;
			}
		}

		public void print(){
			Node node=head;
			while(node != null){
				System.out.println(node.getValue());
				System.out.println();
				node=node.next;
			}
			System.out.println();
		}

		public void push(E value){
			Node<E> node = new Node(value);
			node.next = head;
			head.prev = node;
			head = node;
		}

		public void insertAfter(Node<E> node, E value){
			if(node==null){
				return;
			}
			Node<E> newNode = new Node(value);
			newNode.next = node.next;
			if(node.next!=null){
				node.next.prev = newNode;
			}
			node.next = newNode;
			newNode.prev=node;
		}

		public void findPositionAndRemove(int position){
			Node node = head;
			int index = 1;
			while(node!=null){
				if(position==index){
					if(node.next!=null){
						node.next.prev = node.prev;
					}
					if(node.prev!=null) {
						node.prev.next = node.next;
					}
					if(position==1){
						head = node.next;
					}
					break;
				}
				node = node.next;
				index++;
			}
		}
	}
}

