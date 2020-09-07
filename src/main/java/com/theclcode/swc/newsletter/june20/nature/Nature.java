package com.theclcode.swc.newsletter.june20.nature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nature {
    /**************** START OF USER SOLUTION ****************/

    private static int maxSize;
    private static Trie root;

    private static void init(int N) {
        root = new Trie();
        maxSize = 0;
    }

    private static void predation(char[] a, char[] b) {
        Node aNode = retrieve(a);
        Node bNode = retrieve(b);

        if(aNode == null){
            aNode = insert(a);
        }
        if(bNode == null){
            bNode = insert(b);
        }

        if(aNode != bNode){
            aNode.absorb(bNode);
            if(aNode.size > maxSize){
                maxSize = aNode.size;
            }
        }
    }

    private static boolean check(char[] a, char[] b) {
        Node aNode = retrieve(a);
        Node bNode = retrieve(b);
        if(aNode == null){
            aNode = insert(a);
        }
        if(bNode == null){
            bNode = insert(b);
        }

        if(maxSize == 0){
            maxSize = 1;
        }

        return aNode == bNode || aNode.getParent() == bNode.getParent();
    }

    private static int getSizeLargest() {
        return maxSize;
    }

    private static Node retrieve(char[] name) {
        Trie trie = root;
        for(int i=0; i<name.length && name[i] != 0; i++){
            trie = trie.tries[name[i]-97];
            if(trie == null){
                return null;
            }
        }
        return trie.node;
    }

    private static Node insert(char[] name){
        Trie trie = root;
        for(int i=0; i<name.length && name[i] != 0; i++){
            if(trie.tries[name[i]-97] == null){
                trie.tries[name[i]-97] = new Trie();
            }
            trie = trie.tries[name[i]-97];
        }
        trie.node = new Node(mstrcpy(new char[name.length], name));
        return trie.node;
    }

    static class Trie {
        Node node;
        Trie[] tries;

        Trie() {
            this.tries = new Trie[26];
        }
    }

    static class Node {
        int size = 1;
        char[] value;
        Node parent;

        Node(char[] value) {
            this.value = value;
        }

        Node getParent() {
            if (this.parent == null) {
                return this;
            }
            return parent.getParent();
        }

        void absorb(Node other){
            Node thisParent = this.getParent();
            Node otherParent = other.getParent();

            thisParent.size += otherParent.size;
            otherParent.parent = thisParent;
        }

    }

    private static char[] mstrcpy(char[] dst, char[] src){
        int c = 0;
        while(c < src.length && (dst[c] = src[c]) != '\0'){
            c++;
        }
        return dst;
    }

    /***************** END OF USER SOLUTION *****************/


    public static void main(String[] args) throws Exception {
        int numcases;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringBuilder out = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        numcases = Integer.parseInt(st.nextToken());
        for (int casenum = 1; casenum <= numcases; casenum++) {
            out.append(String.format("Case #%d:\n", casenum));
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            init(N);

            int cmd;
            String s_A, s_B;
            char A[];
            char B[];
            String ret;
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                cmd = Integer.parseInt(st.nextToken());

                switch (cmd) {
                    case 1:
                        s_A = st.nextToken();
                        s_B = st.nextToken();
                        A = new char[s_A.length() + 1];
                        for (int j = 0; j < s_A.length(); j++)
                            A[j] = s_A.charAt(j);
                        A[s_A.length()] = '\0';
                        B = new char[s_B.length() + 1];
                        for (int j = 0; j < s_B.length(); j++)
                            B[j] = s_B.charAt(j);
                        B[s_B.length()] = '\0';

                        predation(A, B);
                        break;
                    case 2:
                        s_A = st.nextToken();
                        s_B = st.nextToken();
                        A = new char[s_A.length() + 1];
                        for (int j = 0; j < s_A.length(); j++)
                            A[j] = s_A.charAt(j);
                        A[s_A.length()] = '\0';
                        B = new char[s_B.length() + 1];
                        for (int j = 0; j < s_B.length(); j++)
                            B[j] = s_B.charAt(j);
                        B[s_B.length()] = '\0';

                        ret = check(A, B) ? "true" : "false";
                        out.append(String.format("%s\n", ret));
                        break;
                    case 3:
                        out.append(String.format("%d\n", getSizeLargest()));
                        break;
                }
            }
        }

        System.out.print(out);
    }
}
