package com.theclcode.unfinished.filesystem;

import java.util.Scanner;

public class Main {

	/**************** START OF USER SOLUTION ****************/

	static TreeNode rootDirectory;
	static Window left;
	static Window right;
	private static final int SUCCESS = 1;
	private static final int FAILURE = 0;

	static void init() {
		rootDirectory = new TreeNode();
		left = new Window(rootDirectory);
		right = new Window(rootDirectory);
	}

	static int mkdir(int m_window, char name[]) {
		Window window = getWindow(m_window);
		TreeNode treeNode = window.getLocation();
		return treeNode.add(name);
	}

	static int chdir(int m_window, char name[]) {
		Window window = getWindow(m_window);
		if(name[0] == '/'){
			window.setLocation(rootDirectory);
			return SUCCESS;
		}else if(name[0] == '.' && name[1] == '.'){
			if(rootDirectory == window.getLocation().parent){
				return FAILURE;
			}
			window.setLocation(window.getLocation().parent);
			return SUCCESS;
		} else {
			TreeNode treeNode = window.getLocation();
			TreeNode newLocation = treeNode.find(name);
			if(newLocation != null ){
				window.setLocation(newLocation);
				return SUCCESS;
			}
		}
		return FAILURE;
	}
	
	static int rmdir(int m_window, char name[]) {
		Window window = getWindow(m_window);
		TreeNode treeNode = window.getLocation();
		return treeNode.findAndDelete(name);
	}
	
	static int mvdir(int m_window, char name[]) {
		int o_window = m_window == 1 ? 0 : 1;
		Window window = getWindow(m_window);
		Window otherWindow = getWindow(o_window);
		TreeNode treeNode = window.getLocation();
		TreeNode dirToMove = treeNode.find(name);
		if (dirToMove != null) {
			if(dirToMove == otherWindow.getLocation()){
				return FAILURE;
			}
			if(otherWindow.getLocation().addWithDirectories(dirToMove.name, dirToMove.getChildren()[26]) == 1){
				dirToMove.remove();
				return SUCCESS;
			}
		}
		return FAILURE;
	}

	static Window getWindow(int m_window){
		switch(m_window){
			case 0:
				return right;
			case 1:
				return left;
			default:
				return null;
		}
	}
	
	/***************** END OF USER SOLUTION *****************/


	static int seed = 5;  // seed can be changed

	static int pseudo_rand()
	{
		seed = seed * 214013 + 2531011;
		return (seed >> 16) & 0x7FFF;
	}

	static void mstrcpy(char dst[], String src) {
		int c;
		for (c = 0; c < src.length(); c++)
			dst[c] = src.charAt(c);
		dst[c] = 0;
	}
	
	static void make_string(char str[], int alpha, int minlength, int maxlength)
	{
		int length = minlength + pseudo_rand() % (maxlength - minlength);
		for (int i = 0; i < length; ++i) {
			str[i] = (char)((int)'a' + (pseudo_rand() % alpha));
		}
		str[length] = 0;
	}

	static Scanner sc;

	static void run()
	{
		char str[] = new char[100];
		int n, alpha;
		int ratio[] = new int[4];
		
		seed = sc.nextInt();
		n = sc.nextInt();
		alpha = sc.nextInt();
		ratio[0] = sc.nextInt();
		ratio[1] = sc.nextInt();
		ratio[2] = sc.nextInt();
		ratio[3] = sc.nextInt();

		init();
	
		for (int i = 0; i < n; ++i)
		{
			int cmd = pseudo_rand() % 100 + 1;
			int ans = 0;
	
			if (cmd <= ratio[0])
			{
				make_string(str, alpha, 5, 10);
				int user_ans = mkdir(pseudo_rand() % 2, str);
	
				System.out.printf("%d\n", user_ans);
			}
			else if (cmd <= ratio[1])
			{
				int cmd2 = pseudo_rand() % 100 + 1;
				if (cmd2 <= 5)
				{
					mstrcpy(str, "/");
				}
				else if (cmd2 <= 10)
				{
					mstrcpy(str, "..");
				}
				else
				{
					make_string(str, alpha, 1, 3);
				}
				int x = pseudo_rand() % 2;
				int user_ans = chdir(x, str);
	
				System.out.printf("%d\n", user_ans);
			}
			else if (cmd <= ratio[2])
			{
				make_string(str, alpha, 1, 3);
	
				int user_ans = rmdir(pseudo_rand() % 2, str);
	
				System.out.printf("%d\n", user_ans);
			}
			else
			{
				make_string(str, alpha, 1, 3);
	
				int user_ans = mvdir(pseudo_rand() % 2, str);
	
				System.out.printf("%d\n", user_ans);
			}
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run();
		}
	}

	static class TreeNode {
		private int size=0;
		private boolean isDirectory = false;
		private TreeNode[] children = new TreeNode[27];
		private TreeNode parent;
		private char[] name;
		LinkedList<TreeNode> childrenDirectories = new LinkedList<>();

		TreeNode(){
			this(null);
			this.name = new char[]{'/'};
		}

		TreeNode(TreeNode parent){
			this.parent = parent;
		}

		private TreeNode getRoot() {
			TreeNode treeNode;
			if(this == rootDirectory){
				return rootDirectory;
			} else {
				if(this.children[26] == null){
					this.children[26] = new TreeNode();
				}
				treeNode = this.children[26];
			}
			return treeNode;
		}

		public int getSize() {
			return size;
		}

		public TreeNode[] getChildren() {
			return children;
		}

		public int add(char[] name){
			TreeNode treeNode = getRoot();
			for(int i=0; i<name.length && name[i]!='\0'; i++){
				int index = name[i]-97;
				if(treeNode.children[index] == null){
					treeNode.children[index] = new TreeNode(this);
					treeNode.size++;
				}
				treeNode = treeNode.children[index];
			}
			if(treeNode.isDirectory){
				return FAILURE;
			} else {
				treeNode.isDirectory = true;
				treeNode.parent = this.getRoot();
				treeNode.name = name;
				getRoot().childrenDirectories.add(treeNode);
				return SUCCESS;
			}
		}

		public int addWithDirectories(char[] name, TreeNode subdirectories){
			TreeNode treeNode = getRoot();
			for(int i=0; i<name.length && name[i]!='\0'; i++){
				int index = name[i]-97;
				if(treeNode.children[index] == null){
					treeNode.children[index] = new TreeNode(this);
					treeNode.size++;
				}
				treeNode = treeNode.children[index];
			}
			if(treeNode.isDirectory){
				return FAILURE;
			} else {
				treeNode.isDirectory = true;
				treeNode.parent = this.getRoot();
				treeNode.name = name;
				treeNode.getChildren()[26] = subdirectories;
				getRoot().childrenDirectories.add(treeNode);
				return SUCCESS;
			}
		}

		private void remove(){
			TreeNode treeNode = parent;
			for(int i=0; i<name.length && name[i] != '\0'; i++){
				int index = name[i] - 97;
				if(i == name.length - 1){
					if(treeNode.getChildren()[index].size > 0){
						treeNode.getChildren()[index].isDirectory = false;
					} else {
						treeNode.getChildren()[index] = null;
					}
				} else if(treeNode.getChildren()[index].isDirectory){
					treeNode.getChildren()[index].size -= 1;
				}
				treeNode = treeNode.getChildren()[index];
			}

		}

		public int findAndDelete(char[] name){
			TreeNode treeNode = find(name);
			int deletedDirectories = 0;
			while(treeNode != null){
				treeNode.remove();
				deletedDirectories += 1;
				Node<TreeNode> child = treeNode.getRoot().childrenDirectories.head;
				while(child != null){
					deletedDirectories += child.value.countSubdirectories();
					child = child.next;
				}
				treeNode.getChildren()[26] = null;
				treeNode = find(name);
			}
			return deletedDirectories;
		}

		public int countSubdirectories() {
			int count = 1;
			if(this.getChildren()[26] != null){
				Node<TreeNode> node = this.getChildren()[26].childrenDirectories.head;
				while(node != null){
					count += node.value.countSubdirectories();
					node = node.next;
				}
			}
			return count;
		}

		public TreeNode find(char[] name){
			TreeNode treeNode = getRoot();
			int index = 0;
			for(int i=0; i<name.length && name[i]!='\0'; i++){
				index = name[i]-97;
				if(treeNode.getChildren()[index] == null){
					return null;
				}
				treeNode = treeNode.getChildren()[index];
			}
			if(treeNode.isDirectory){
				return treeNode;
			}
			TreeNode toFind = treeNode;
			while(!treeNode.isDirectory){
				for(int i = 0; i<treeNode.getChildren().length-1; i++){
					if(treeNode.getChildren()[i] != null){
						if(treeNode.getChildren()[i].isDirectory){
							return treeNode.getChildren()[i];
						}
						treeNode = treeNode.getChildren()[i];
						break;
					}
				}
				if(treeNode == null || treeNode == toFind){
					break;
				}
			}
			return null;
		}


	}

	static class LinkedList<E> {
		Node head;
		Node tail;
		int size;

		void add(E value){
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

	}
	static class Node<E>{
		E value;
		Node next;
		Node prev;

		Node(E value){
			this.value = value;
		}

	}
	static class Window {
		private TreeNode location;

		public Window(TreeNode location){
			this.location = location;
		}

		public TreeNode getLocation() {
			return location;
		}

		public void setLocation(TreeNode location) {
			this.location = location;
		}
	}
}






