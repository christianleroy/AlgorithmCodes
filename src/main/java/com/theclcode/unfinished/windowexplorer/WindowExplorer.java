package com.theclcode.unfinished.windowexplorer;

import java.util.Scanner;

public class WindowExplorer {

    /**************** START OF USER SOLUTION ****************/

    private static Root root;
    private static Window left;
    private static Window right;
    final static int SUCCESS = 1;
    final static int FAILURE = 0;

    static void init() {
        root = new Root();
        left = new Window(0, null);
        right = new Window(1, null);
    }

    static int mkdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        if(window.location == null){

        } else {

        }
        return 0;
    }

    static int chdir(int m_window, char name[]) {

        return 0;
    }

    static int rmdir(int m_window, char name[]) {

        return 0;
    }

    static int mvdir(int m_window, char name[]) {

        return 0;
    }

    static Window getWindow(int m_window){
        switch(m_window){
            case 0:
                return left;
            case 1:
                return right;
        }
        return null;
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
                    mstrcpy(str, "src/main");
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

    static class Root {
        private TreeNode[] levels = new TreeNode[26];

        public int add(char[] dirName){
            for(int i=0; i<dirName.length && dirName[i] != '\0'; i++){
                int loc = dirName[i] - 'a';
                if(i<4){

                } else {

                }
            }
            return FAILURE;
        }
    }

    static class TreeNode {
        TreeNode[] nodes = new TreeNode[26];
        LinkedList<Directory> directories;
    }

    static class Directory {
        DirectoryName name;
        LinkedList<Directory> directories;
    }

    static class DirectoryName {
        char[] name;
        int hashCode;
        final static int BASE = 37;
        final int[] POWERS = { 1, BASE, BASE * BASE };

        DirectoryName(char[] name){
            this.name = new char[name.length];
            for(int i=0; i<name.length && name[i]!='\0'; i++){
                this.name[i] = name[i];
            }
            setHashCode();
        }

        public int getHashCode() {
            return hashCode;
        }

        private void setHashCode(){
            int hash = 0;
            for(int i=0, y=2; i<this.name.length && this.name[i] != '\0'; i++, y--){
                if(i<3){
                    hash += this.name[i] * POWERS[y];
                } else {
                    hash += this.name[i];
                }
            }
            this.hashCode = hash;
        }

    }

    static class LinkedList<E> {
        Node<E> head;
        Node<E> tail;
        int size=0;
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

        public void remove(E value){
            Node<E> node = head;
            while(node != null){
                node = node.next;
                if(node.value.equals(value)){
                    if(node.next != null){
                        node.next.prev = node.prev;
                    }
                    if(node.prev != null){
                        node.prev.next = node.next;
                    }
                    if(head == node){
                        head = node.next;
                    }
                    if(tail == node){
                        tail = node.prev;
                    }
                    size--;
                }
            }
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

    static class Window {
        int id;
        Directory location;

        Window(int id, Directory location){
            this.id = id;
            this.location = location;
        }
    }


}
