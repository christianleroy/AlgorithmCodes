package com.theclcode.swc.newsletter.dec19.friendlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class FriendList {
	
	/**************** START OF USER SOLUTION ****************/

	static LinkedList<Integer>[] users;
	static boolean[] friends;


	static void init(int N)	{
		users = new LinkedList[N+1];
	}
	
	static void add(int id, int F, int ids[]) {
		if(users[id] == null){
			users[id] = new LinkedList<>();
		}
		for(int i=0; i<F; i++){
			users[id].add(ids[i]);
			if(users[ids[i]] == null){
				users[ids[i]] = new LinkedList<>();
			}
			users[ids[i]].add(id);
		}
	}
	
	static void del(int id1, int id2) {
		users[id1].remove(id2);
		users[id2].remove(id1);
	}
	
	static int recommend(int id, int list[]) {

		if(users[id] == null){
			return 0;
		}

		friends = new boolean[users.length];

		LinkedList.Node node = users[id].getHead();
		LinkedList<int[]> recommendedFriends = new LinkedList<>();

		while(node != null){
			Integer index = (Integer) node.value;
			friends[index] = true;
			node = node.next;
		}

		for(int i=0; i<users.length; i++){
			if(users[i] == null || i == id || friends[i]){
				continue;
			}
			LinkedList.Node otherUserFiends = users[i].getHead();
			int mutuals = 0;
			while(otherUserFiends != null){
				if(friends[(Integer)otherUserFiends.value]){
					mutuals++;
				}
				otherUserFiends = otherUserFiends.next;
			}
			if(mutuals > 0){
				int[] otherUserInfo = {i, mutuals};
				recommendedFriends.sortedAdd(otherUserInfo);
			}
		}

		int i = 0;
		LinkedList.Node recommendedFriend = recommendedFriends.getHead();
		while(recommendedFriend != null && i < 5){
			list[i++] = ((int[])recommendedFriend.value)[0];
			recommendedFriend = recommendedFriend.next;
		}
		return i;
	}

	static class LinkedList<E> {

		int size;
		Node<E> head;
		Node<E> tail;

		public void add(E value){
			Node<E> node = new Node<>(value);
			if(size == 0){
				head = tail = node;
			} else {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
			size++;
		}

		public void sortedAdd(E value){
			Node<E> node = new Node<>(value);
			if(size == 0){
				head = tail = node;
				size++;
			} else {
				if(value instanceof int[]){
					Node<E> existing = head;
					int[] nodeValue = (int[]) node.value;
					while(existing != null){
						int[] exVal = (int[]) existing.value;
						if(nodeValue[1] > exVal[1] || (nodeValue[1] == exVal[1] && nodeValue[0] < exVal[0])){
							insertNode(node, existing);
							break;
						} else if(existing == tail){
							tail.next = node;
							node.prev = tail;
							tail = node;
							break;
						}
						existing = existing.next;
					}
					size++;
				} else {
					add(value);
				}
			}
		}

		private void insertNode(Node<E> node, Node<E> existing){
			node.next = existing;
			node.prev = existing.prev;
			if(existing.prev != null){
				existing.prev.next = node;
			}
			if(existing == head){
				head = node;
			}
			existing.prev = node;
		}


		public E remove(E value){
			Node<E> node = head;
			while (node != null){
				if(node.value == value || node.value.equals(value)){
					if(node.next != null){
						node.next.prev = node.prev;
					}
					if(node.prev != null){
						node.prev.next = node.next;
					}
					if(node == head){
						head = head.next;
						if(head != null){
							head.prev = null;
						} else {
							tail = null;
						}
					}
					if(node == tail){
						tail = tail.prev;
						if(tail != null){
							tail.next = null;
						} else {
							head = null;
						}
					}
					size--;
					return node.value;
				}
				node = node.next;
			}
			return null;
		}

		public Node<E> getHead() {
			return head;
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

	/***************** END OF USER SOLUTION *****************/

	
	private final static int MAXL		= 5;
	private final static int MAXF		= 10;
	
	private final static int INIT		= 1;
	private final static int ADD		= 2;
	private final static int DEL		= 3;
	private final static int RECOMMEND	= 4;
	
	private static int N, M;

	private static void run(BufferedReader br) throws Exception
	{
		int cmd;
		int id, F;
		int ids[] = new int[MAXF];
		int id1, id2;
		int len, len_a;
		int list[] = new int[MAXL];
		int list_a[] = new int[MAXL];
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < M; ++k)
		{
			st = new StringTokenizer(br.readLine(), " ");
			
			cmd = Integer.parseInt(st.nextToken());
			switch(cmd)
			{
			case INIT:
				N = Integer.parseInt(st.nextToken());
				init(N);
				break;
			case ADD:
				id = Integer.parseInt(st.nextToken());
				F = Integer.parseInt(st.nextToken());
				for (int i = 0; i < F; ++i)
					ids[i] = Integer.parseInt(st.nextToken());
				add(id, F, ids);
				break;
			case DEL:
				id1 = Integer.parseInt(st.nextToken());
				id2 = Integer.parseInt(st.nextToken());
				del(id1, id2);
				break;
			case RECOMMEND:
				id = Integer.parseInt(st.nextToken());
				len = recommend(id, list);

				System.out.printf("%d", len);
				for (int i = 0; i < len; i++)
					System.out.printf(" %d", list[i]);
				System.out.println();
				break;
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		int TC;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			System.out.printf("Case #%d:\n", testcase);
			run(br);
		}

	}
}
