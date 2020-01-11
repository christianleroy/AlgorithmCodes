package com.theclcode.unfinished.multifile;

class UserSolution {
	
	
	/*static void mstrcpy(char dst[], char src[])
	{
		int c = 0;
		while((dst[c] = src[c]) != 0) ++c;
	}
	
	int mstrcmp(char str1[], char str2[])
	{
		int c = 0;
		while(str1[c] != 0 && str1[c] == str2[c]) ++c;
		return str1[c] - str2[c];
	}
	*/
	
	static HashTable<char[], User> users;
	static HashTable<char[], Group> groups;
	static Directory root;

	void init()
	{
		root = new Directory(new char[] {'/'}, null, 2);
		users = new HashTable<>();
		groups = new HashTable<>(13);
		
		char[] admin = {'a','d','m','i','n',0};
		Group group = new Group(admin);
		User user = new User(admin, group);
		
		users.put(admin, user);
		groups.put(admin, group);
	}
	
	void createUser(char userName[], char groupName[])
	{
		Group group = groups.get(groupName);
		if(group == null) {
			group = new Group(groupName);
			groups.put(groupName, group);
		}
		User user = new User(userName, group);
		users.put(userName, user);
	}

	int createDirectory(char userName[], char path[], char directoryName[], int permission)
	{
		User user = users.get(userName);
		Directory directory = getDirectory(path);
		if(directory.authLevel == 0 && directory.user != user) {
			return 0;
		} else if(directory.authLevel == 1 && directory.user.group != user.group) {
			return 0;
		} else {
			Directory newDirectory = new Directory(directoryName, user, permission);
			directory.subdirectories.put(directoryName, newDirectory);
			directory.subdirectoriesLinkedList.add(newDirectory);
			return 1;
		}
	}
	
	Directory getDirectory(char[] path) {
		Directory directory = root;
		String str = "";
		boolean beginning = false;
		for(int i=1; i<path.length && path[i] != '\0'; i++) {
			if(path[i] == '/') {
				if(beginning) {
					beginning = false;
				} else {
					char[] dirName = new char[str.length()+1];
					for(int j=0; j<str.length(); j++) {
						dirName[j] = str.charAt(j);
					}
					directory = directory.subdirectories.get(dirName);
					str = "";
					beginning = true;
				}
				continue;
			}
			str += path[i];
		}
		return directory;
	}
	
	int countFiles(User user, char[] path) {
		Directory directory = root;
		String str = "";
		
		LinkedList<Directory> dirs = new LinkedList<>();
		int count=0;
		boolean beginning = false;
		for(int i=1; i<path.length && path[i] != '\0'; i++) {
			if(path[i] == '/') {
				if(beginning) {
					beginning = false;
				} else {
					if(str.charAt(0) == '*') {
						LinkedList.Node<Directory> subdirs = directory.subdirectoriesLinkedList.head;
						while(subdirs != null) {
							dirs.add(subdirs.value);
							subdirs = subdirs.next;
						}
					} else {
						char[] dirName = new char[str.length()+1];
						for(int j=0; j<str.length(); j++) {
							dirName[j] = str.charAt(j);
						}
						directory = directory.subdirectories.get(dirName);
						dirs.add(directory);
						str = "";
						beginning = true;
					}
				}
				continue;
			}
			str += path[i];
		}
		
		while(!dirs.isEmpty()) {
			Directory dir = dirs.removeLast();
			if(directory.authLevel == 0 && directory.user != user) {
				return 0;
			} else if(directory.authLevel == 1 && directory.user.group != user.group) {
				return 0;
			} else {
				count +=dir.files.size;
			}
		}
		return 0;
	}
	

	int createFile(char userName[], char path[], char fileName[], char fileExt[])
	{
		User user = users.get(userName);
		Directory directory = getDirectory(path);
		if(directory.authLevel == 0 && directory != root && directory.user != user) {
			return 0;
		} else if(directory.authLevel == 1 && (directory != root && directory.user.group != user.group)) {
			return 0;
		} else {
			File file = new File(fileName, fileExt);
			directory.files.add(file);
			return 1;
		}
	}

	
	int find(char userName[], char pattern[])
	{
//		User user = users.get(userName);
//		Directory directory = getDirectory(pattern);
//		int authLevel = directory.authLevel;
//		LinkedList.Node<File> node = directory.files.head;
//		int fileCount = 0;
//		
//		while(node != null) {
//			File file = node.value;
//			
//		}
//		
		return 0;
	}
	
	
	static class User {
		char[] userName;
		Group group;
		User(char[] userName, Group group){
			this.userName = new char[userName.length];
			for(int i=0; i<userName.length && userName[i] != '\0'; i++) {
				this.userName[i] = userName[i];
			}
			this.group = group;
		}
	}
	
	
	
	static class Group {
		char[] groupName;
		int hash;
		
		Group(char[] groupName){
			this.groupName = new char[groupName.length];
			for(int i=0, y=2; i<groupName.length && groupName[i] != '\0'; i++, y--) {
				this.groupName[i] = groupName[i];
				if(i < 3) {
					hash += groupName[i] * Hash.POWERS[y];
				} else {
					hash += groupName[i];
				}
			}
		}
		
		boolean same(Group compare) {
			if(compare == this) {
				return true;
			} else {
				if(this.hash == compare.hash) {
					boolean isEqual = true;
					for(int i=0; i<groupName.length; i++) {
						if(groupName[i] != compare.groupName[i]) {
							isEqual = false;
							break;
						}
					}
					if(isEqual) {
						return true;
					}
				}
				return false;
			}
		}
		
		public String toString() {
			return new String(groupName);
		}
	}
	
	static class Directory {
		char[] dirName;
		User user;
		HashTable<char[], Directory> subdirectories = new HashTable<>(23);
		LinkedList<Directory> subdirectoriesLinkedList = new LinkedList<>();
		LinkedList<File> files = new LinkedList<>();
		int authLevel;
		int hash;
		
		Directory(char[] dirName, User user, int authLevel){
			this.dirName = new char[dirName.length];
			for(int i=0, y=2; i<dirName.length && dirName[i] != '\0'; i++, y--) {
				this.dirName[i] = dirName[i];
				if(i < 3) {
					this.hash += dirName[i] * Hash.POWERS[y];
				} else {
					this.hash += dirName[i];
				}
			}
			this.user = user;
			this.authLevel = authLevel;
		}
		
		public String toString() {
			return new String(dirName);
		}
		
		boolean same(Directory compare) {
			if(compare == this) {
				return true;
			} else {
				if(this.hash == compare.hash) {
					boolean isEqual = true;
					for(int i=0; i<dirName.length; i++) {
						if(dirName[i] != compare.dirName[i]) {
							isEqual = false;
							break;
						}
					}
					if(isEqual) {
						return true;
					}
				}
				return false;
			}
		}
	}
	
	static class File {
		char[] fileName;
		char[] fileExt;
		int hash;
		
		File(){}
		File(char[] fileName, char[] fileExt){
			this.fileName = new char[fileName.length];
			this.fileExt = new char[fileExt.length];
			for(int i=0, y=2; i<fileName.length && fileName[i] != '\0'; i++, y--) {
				this.fileName[i] = fileName[i];
				if(i<3) {
					hash += fileName[i] * Hash.POWERS[y];
				} else {
					hash += fileName[i];
				}
			}
			for(int i=0, y=2; i<fileExt.length && fileExt[i] != '\0'; i++, y--) {
				this.fileExt[i] = fileExt[i];
				if(i<3) {
					hash += fileExt[i] * Hash.POWERS[y];
				} else {
					hash += fileExt[i];
				}
			}
		}
		
		boolean sameFileName(char[] fileName) {
			for(int i=0; i<this.fileName.length; i++) {
				if(this.fileName[i] != fileName[i]) {
					return false;
				}
			}
			return true;
		}
		
		boolean sameFileExt(char[] fileExt) {
			for(int i=0; i<this.fileExt.length; i++) {
				if(this.fileExt[i] != fileExt[i]) {
					return false;
				}
			}
			return true;
		}
		
		boolean same(File compare) {
			if(compare == this) {
				return true;
			} else {
				if(this.hash == compare.hash) {
					return this.sameFileName(compare.fileName) 
							&& this.sameFileExt(compare.fileExt);
					
				}
				return false;
			}
		}
		
		public String toString() {
			return new String(fileName) +"."+new String(fileExt);
		}
	}
	
	static class LinkedList<E> {
		int size;
		Node<E> head;
		Node<E> tail;
		
		public void add(E value) {
			Node<E> node = new Node<>(value);
			if(head == null) {
				head = tail = node;
			} else {
				tail.next=  node;
				node.prev = tail;
				tail = node;
			}
			size++;
		}
		
		public E removeLast() {
			if(tail == null) {
				return null;
			}
			Node<E> node = tail;
			tail = tail.next;
			if(tail == null) {
				head = null;
			} else {
				tail.next = null;
			}
			size--;
			return node.value;
		}
		
		public boolean isEmpty() {
			return size == 0;
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
	
	static class HashTable<K, V>{
		
		int capacity;
		Node<K, V>[] table;
		
		public HashTable(){
			this(23);
		}
		
		public HashTable(int capacity) {
			this.capacity = capacity;
			table = new Node[capacity];
		}
		
		public void put(K key, V value) {
			int index = getAddress(key);
			if(table[index] == null) {
				Node<K, V> node = new Node<>(key, value);
				table[index] = node;
			} else {
				Node<K, V> node = find(key);
				if(node == null) {
					node = new Node<>(key, value);
					Node<K, V> existing = table[index];
					node.next = existing;
					existing.prev=  node;
					table[index] = node;
				} else {
					node.value = value;
				}
			}
		}
		
		public V get(K key) {
			Node<K, V> node = find(key);
			if(node == null) {
				return null;
			}
			return node.value;
		}
		
		public boolean has(K key) {
			return find(key) != null;
		}
		
		private Node<K, V> find(K key){
			int index = getAddress(key);
			Node<K, V> node = table[index];
			while(node != null) {
				if(key instanceof char[] && node.key instanceof char[]){
					char[] gk = (char[]) key;
					char[] nk = (char[]) node.key;
					if(gk.length == nk.length) {
						boolean isEqual = true;
						for(int i=0; i<gk.length; i++) {
							if(gk[i] != nk[i]) {
								isEqual = false;
								break;
							}
						}
						if(isEqual) {
							return node;
						}
					}
				}
				node = node.next;
			}
			return null;
		}
		
		private int getAddress(K key) {
			return key == null ? 0 : Hash.hash((char[])key) % capacity;
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
	
	static class Hash {
		final static int BASE = 37;
		final static int[] POWERS = {1, BASE, BASE * BASE};
		
		static int hash(char[] key) {
			int hash = 0;
			for(int i=0, y=2; i<key.length && key[i] != '\0'; i++, y--) {
				if(i<3) {
					hash += key[i] * POWERS[y];
				} else {
					hash += key[i];
				}
			}
			return hash;
		}
	}

}