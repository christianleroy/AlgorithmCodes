package com.theclcode.swc.newsletter.dec19.cellcombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class CellCombination {

	/**************** START OF USER SOLUTION ****************/

	static Node<Integer>[][] cells;
	static int contentId;
	static Trie root;
	static Content[] words;

	static void init(int R, int C) {
		cells = new Node[R+2][C+2];
		contentId = 1;
		root = new Trie();
		words = new Content[50_000+2];
		words[0] = new Content();
		words[0].word = new char[11];
	}

	static void setContent(int row, int col, char str[]) {
		Trie node = root;
		int i=0;
		for(; i<str.length && str[i] != '\0'; i++){
			if(node.nodes[str[i]-97] == null){
				node.nodes[str[i]-97] = new Trie();
			}
			node = node.nodes[str[i]-97];
		}

		if(node.contentId == 0){
			node.contentId = contentId++;
			Content content = new Content();
			content.word = mstrcpy(str, new char[str.length]);
			words[node.contentId] = content;
		}

		Content content = words[node.contentId];

		Node cell = cells[row][col];
		if(cell == null){
			cell = new Node<>(node.contentId);
			cells[row][col] = cell;
			content.groups += 1;
			content.area += 1;
		} else {
			if(node.contentId != (Integer) cell.getParent().value){
                Content oldContent = words[(Integer)cell.getParent().value];
                cell.getParent().value = node.contentId;
                if(oldContent != words[0]){
                    oldContent.groups -= 1;
                    oldContent.area -= cell.getParent().size;
                }
                words[node.contentId].groups += 1;
                words[node.contentId].area += cell.getParent().size;
            }
		}

	}

	static void getContent(int row, int col, char str[]) {
		Node cell = cells[row][col];
		if(cell == null) {
		    mstrcpy(new char[11], str);
			return;
		}
		int contentId = (Integer) cell.getParent().value;
		char[] content = words[contentId].word;
		mstrcpy(content, str);
	}

	static void mergeCell(int row1, int col1, int row2, int col2, int opt) {

	    Node<Integer> left = cells[row1][col1];
	    Node<Integer> right = cells[row2][col2];

	    if(left == null){
	        left = new Node<>(0);
	        cells[row1][col1] = left;
        }
	    if(right == null){
	        right = new Node<>(0);
	        cells[row2][col2] = right;
        }

	    switch (opt) {
            case 1:
				mergeCells(left, right);
				Content oldContent;
				break;
            case 2:
                mergeCells(right, left);
                left.setParent(right);
                break;
        }
	}

	private static void mergeCells(Node<Integer> left, Node<Integer> right) {
		Node<Integer> leftParent = left.getParent();
		Node<Integer> rightParent = right.getParent();
		Content oldContent = words[rightParent.value];
		if(rightParent.value != leftParent.value){
			rightParent.value =leftParent.value;
			Content content = words[leftParent.value];
			content.area += rightParent.size;
			oldContent.groups -= 1;
			oldContent.area -= rightParent.size;
		} else {
			Content content = words[leftParent.value];
			content.groups -= 1;
		}
		right.setParent(left);
	}

	static int countCell(char str[]) {
        Trie node = root;
        for(int i=0; i<str.length && str[i] != '\0'; i++){
            if(node.nodes[str[i] - 97] == null){
                return 0;
            }
            node = node.nodes[str[i] - 97];
        }
        if(node.contentId == 0){
            return 0;
        }
        Content content = words[node.contentId];
		return content.groups;
	}

	static int countArea(char str[]) {
        Trie node = root;
        for(int i=0; i<str.length && str[i] != '\0'; i++){
            if(node.nodes[str[i] - 97] == null){
                return 0;
            }
            node = node.nodes[str[i] - 97];
        }
        if(node.contentId == 0){
            return 0;
        }
        Content content = words[node.contentId];
		return content.area;
	}

	static class Trie {
		int contentId;
		Trie[] nodes;

		Trie(){
			nodes = new Trie[26];
		}
	}

	static class Content {
	    char[] word;
		int groups;
		int area;
	}

	static class Node<E> {
		E value;
		Node<E> parent;
		int height = 1;
		int size = 1;

		public Node(E value){
			this.value = value;
		}

		public Node<E> getParent(){
			if(parent == null){
				return this;
			}
			return parent.getParent();
		}

		public void setParent(Node<E> node){
			Node<E> nodeParent = node.getParent();
			if(this.getParent() != nodeParent){
				if(this.getParent().height <= nodeParent.height){
					nodeParent.size += this.getParent().size;
					if(this.getParent().height == nodeParent.height){
						nodeParent.height++;
					}
					this.getParent().parent = nodeParent;

				} else {
					this.getParent().size += node.getParent().size;
					node.getParent().parent = this;
				}
			}
		}
	}

	static char[] mstrcpy(char[] src, char[] dst){
		int c = 0;
		while(c < src.length && (dst[c] = src[c]) != 0){
			c++;
		}
		return dst;
	}

	/***************** END OF USER SOLUTION *****************/


	public static final int INIT		 = 0;
	public static final int SETCONTENT	 = 1;
	public static final int GETCONTENT	 = 2;
	public static final int MERGECELL	 = 3;
	public static final int COUNTCELL	 = 4;
	public static final int COUNTAREA	 = 5;

	static void run() throws Exception {
		int N, cmd, p1, p2, p3, p4, p5, ret;
		char str[] = new char[11];
		char buf[] = new char[11];

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case INIT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				init(p1, p2);

				break;
			case SETCONTENT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				String temp_2 = st.nextToken();
				for (int j = 0; j < temp_2.length(); j++)
					buf[j] = temp_2.charAt(j);
				buf[temp_2.length()] = '\0';

				setContent(p1, p2, buf);

				break;
			case GETCONTENT:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				getContent(p1, p2, buf);
				
				StringBuilder sb = new StringBuilder();
				for (int j = 0; buf[j] != '\0'; j++)
					sb.append(buf[j]);
				System.out.printf("%s\n", sb.toString());

				break;
			case MERGECELL:
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				p3 = Integer.parseInt(st.nextToken());
				p4 = Integer.parseInt(st.nextToken());
				p5 = Integer.parseInt(st.nextToken());
				mergeCell(p1, p2, p3, p4, p5);
				
				break;
			case COUNTCELL:
				String temp_4 = st.nextToken();
				for (int j = 0; j < temp_4.length(); j++)
					buf[j] = temp_4.charAt(j);
				buf[temp_4.length()] = '\0';
				ret = countCell(buf);

				System.out.printf("%d\n", ret);

				break;
			case COUNTAREA:
				String temp_5 = st.nextToken();
				for (int j = 0; j < temp_5.length(); j++)
					buf[j] = temp_5.charAt(j);
				buf[temp_5.length()] = '\0';
				ret = countArea(buf);
				
				System.out.printf("%d\n", ret);

				break;
			default:
				break;
			}
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int T;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			System.out.printf("Case #%d:\n", tc);
			run();
		}
	}
}
