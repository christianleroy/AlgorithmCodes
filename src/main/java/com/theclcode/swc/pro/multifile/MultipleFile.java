package com.theclcode.swc.pro.multifile;

class MultipleFile {
	
	static void mstrcpy(char dst[], char src[])
	{
		int c = 0;
		while((dst[c] = src[c]) != 0) ++c;
	}

	static int mstrcmp(char str1[], char str2[])
	{
		int c = 0;
		while(str1[c] != 0 && str1[c] == str2[c]) ++c;
		return str1[c] - str2[c];
	}

	public static User[] users;
	public static int userMarker;
	public static Directory root;

	void init()
	{
		users = new User[20];
		userMarker = 0;
		root = new Directory();
		root.directoryName = new char[]{'/', 0};
		root.permission = 2;

		User user = new User();
		user.userName = new char[]{'a','d','m','i','n',0};
		user.group = new char[]{'a','d','m','i','n',0};
		users[userMarker++] = user;
	}
	
	void createUser(char userName[], char groupName[])
	{
		User user = new User();
		user.userName = new char[userName.length];
		user.group = new char[groupName.length];
		mstrcpy(user.userName, userName);
		mstrcpy(user.group, groupName);

		users[userMarker++] = user;
	}

	int createDirectory(char userName[], char path[], char directoryName[], int permission)
	{
		User user = getUser(userName);
		Directory directory = root;
		char[] _directoryName = new char[10];
		int index=0;
		for(int i=1; i<path.length && path[i] != '\0'; i++){
			if(path[i] == '/'){
				directory = directory.subdirectories.get(_directoryName);
				_directoryName = new char[10];
				index = 0;
				continue;
			}
			_directoryName[index++] = path[i];
		}

		if(directory.permission == 0 && mstrcmp(directory.userName, userName) != 0){
			return 0;
		} else if(directory.permission == 1 && directory != root && mstrcmp(directory.groupName, user.group) != 0){
			return 0;
		} else {
			Directory newDirectory = new Directory();

			newDirectory.directoryName = new char[directoryName.length];
			newDirectory.userName = new char[userName.length];
			newDirectory.groupName = new char[user.group.length];

			mstrcpy(newDirectory.directoryName, directoryName);
			mstrcpy(newDirectory.userName, userName);
			mstrcpy(newDirectory.groupName, user.group);

			newDirectory.permission = permission;
			directory.subdirectories.put(directoryName, newDirectory);
			return 1;
		}
	}

	int createFile(char userName[], char path[], char fileName[], char fileExt[])
	{
		User user = getUser(userName);
		Directory directory = root;
		char[] _directoryName = new char[10];
		int index=0;
		for(int i=1; i<path.length && path[i] != '\0'; i++){
			if(path[i] == '/'){
				directory = directory.subdirectories.get(_directoryName);

				_directoryName = new char[10];
				index = 0;
				continue;
			}
			_directoryName[index++] = path[i];
		}

		if(directory.permission == 0 && mstrcmp(directory.userName, userName) != 0){
			return 0;
		} else if(directory.permission == 1 && directory != root && mstrcmp(directory.groupName, user.group) != 0){
			return 0;
		} else {

			File file = new File();
			file.fileName = new char[fileName.length];
			file.fileExt = new char[fileExt.length];

			mstrcpy(file.fileName, fileName);
			mstrcpy(file.fileExt, fileExt);

			directory.files.add(file);

			return 1;
		}
	}

	
	int find(char userName[], char pattern[])
	{
		User user = getUser(userName);

		char[] dirFileName = new char[10];
		char[] fileExt = new char[10];
		char[] fileName = new char[12];
		int index = 0;

		LinkedList<Directory> stack = new LinkedList<>();
		stack.add(root);
		Directory directory = null;
		int i=1;
		for(; i<pattern.length; i++){
			if(pattern[i] == '/'){
				LinkedList<Directory> newStack = new LinkedList<>();
				while(!stack.isEmpty()){
					directory = stack.removeLast();
					if(dirFileName[0] == '*'){
						for(int j=0; j<directory.subdirectories.getTable().length; j++){
							HashTable.Node node = directory.subdirectories.getTable()[j];
							while(node != null){
								if(hasAccess(user, (Directory) node.value)){
									newStack.add((Directory)node.value);
								}
								node = node.next;
							}
						}
					} else {
						Directory nextDirectory = directory.subdirectories.get(dirFileName);
						if(nextDirectory != null && hasAccess(user, nextDirectory)){
							newStack.add(nextDirectory);
						}
					}
				}
				stack = newStack;
				dirFileName = new char[10];
				index=0;
			} else if(pattern[i] == '.'){
				mstrcpy(fileName, dirFileName);
				break;
			} else {
				dirFileName[index++] = pattern[i];
			}

		}

		for(int x=i+1, y=0; x<pattern.length; x++, y++){
			fileExt[y] = pattern[x];
		}

		int filesMatched = 0;

		while(!stack.isEmpty()){
			directory = stack.removeLast();
			LinkedList.Node fileNode = directory.files.tail;
			while(fileNode != null){
				File file = (File) fileNode.value;
				if(fileName[0] == '*' || mstrcmp(fileName, file.fileName) == 0){
					if(fileExt[0] == '*' || mstrcmp(fileExt, file.fileExt) == 0){
						filesMatched++;
					}
				}
				fileNode = fileNode.prev;
			}
		}

		return filesMatched;
	}

	boolean hasAccess(User user, Directory directory){
		if((directory.permission == 2)
				|| (directory.permission == 1 && mstrcmp(directory.groupName, user.group) == 0)
				|| (directory.permission == 0 && directory != root && mstrcmp(directory.userName, user.userName)==0)) {
			return true;
		}
		return false;
	}

	User getUser(char[] userName){
		User user = null;
		for(int i=0; i<userMarker; i++){
			if(mstrcmp(users[i].userName, userName) == 0){
				user = users[i];
				break;
			}
		}
		return user;
	}

	class User {
		char[] userName;
		char[] group;
	}

	class Directory {
		char[] directoryName;
		int permission;
		char[] userName;
		char[] groupName;
		HashTable<char[], Directory> subdirectories = new HashTable<>();
		LinkedList<File> files = new LinkedList<>();

	}

	class File {
		char[] fileName;
		char[] fileExt;
	}

	static class HashTable<K, V> {

		int capacity;
		Node<K, V>[] table;
		static final int BASE = 37;
		static final int[] POWERS = {1, BASE, BASE * BASE};

		public HashTable(){
			this(17);
		}

		public HashTable(int capacity){
			this.capacity = capacity;
			this.table = new Node[capacity];
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
				if(key instanceof char[] && node.key instanceof char[]){
					char[] _key = (char[]) key;
					char[] _nodeKey = (char[]) node.key;
					if(mstrcmp(_key, _nodeKey) == 0){
						return node;
					}
				}
				node = node.next;
			}
			return null;
		}

		private int getAddress(K key){
			return key == null ? 0 : hash(key) % capacity;
		}

		private int hash(K key){
			String word = new String((char[])key);
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

			Node<K, V> prev;
			Node<K, V> next;

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
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
			size++;
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

		public boolean isEmpty(){
			return size == 0;
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


}