package com.theclcode.unfinished.windowexplorer;

import java.util.Scanner;

public class WindowExplorer {

    /**************** START OF USER SOLUTION ****************/

    final static int SUCCESS = 1;
    final static int FAILURE = 0;
    static TreeNode[][][] levels;
    static Window left;
    static Window right;

    static void init() {
        left = new Window(0, null);
        right = new Window(1, null);
        levels = new TreeNode[26][26][26];
    }

    static int mkdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        TreeNode directory;
        TreeNode newDirectory;
        if(window.getLocation() == null){
            directory = levels[name[0]-'a'][name[1]-'a'][name[2]-'a'];
            if(directory == null){
                char[] prefix = new char[]{name[0], name[1], name[2]};
                Directory dirDirectory = new Directory(prefix);
                directory = new TreeNode(null, dirDirectory);
                levels[name[0]-'a'][name[1]-'a'][name[2]-'a'] = directory;
            }
            newDirectory = new TreeNode(null, new Directory(name));

        } else {
            directory = window.getLocation();
            newDirectory = new TreeNode(directory, new Directory(name));
        }
        if(directory.containsDirectory(new Directory(name))){
            return FAILURE;
        }
        directory.getSubdirectories().add(newDirectory);
        return SUCCESS;
    }

    static int chdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        if(name[0] == '\\'){
            window.setLocation(null);
            return SUCCESS;
        } else if(name[0] == '.' && name[1] == '.'){
            if(window.getLocation().getParent() == null){
                return FAILURE;
            }
            window.setLocation(window.getLocation().getParent());
            return SUCCESS;
        } else {
            TreeNode directory;
            if(window.getLocation() == null){
                directory = levels[name[0]-97][name[1]-97][name[2]-97];
            } else {
                directory = window.getLocation();
            }
            if(directory == null || directory.getSubdirectories().size == 0){
                return FAILURE;
            }
            window.setLocation(directory.getSubdirectories().getFirst());
            return SUCCESS;
        }
    }

    static int rmdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        return 0;
    }

    static int mvdir(int m_window, char name[]) {

        return 0;
    }

    static Window getWindow(int m_window){
        if(m_window == 0){
            return left;
        } else {
            return right;
        }
    }

    static class Window {
        private int id;
        private TreeNode location;

        public Window(int id, TreeNode location) {
            this.id = id;
            this.location = location;
        }

        public void setLocation(TreeNode location) {
            this.location = location;
        }

        public TreeNode getLocation() {
            return location;
        }
    }

    static class TreeNode {
        private TreeNode parent;
        private Directory directory;
        private LinkedList<TreeNode> subdirectories = new LinkedList<>();

        public TreeNode(TreeNode parent, Directory directory){
            this.parent = parent;
            this.directory = directory;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setDirectory(Directory directory) {
            this.directory = directory;
        }

        public Directory getDirectory() {
            return directory;
        }

        public LinkedList<TreeNode> getSubdirectories() {
            return subdirectories;
        }

        public LinkedList.Node<TreeNode> find(Directory directory){
            LinkedList.Node<TreeNode> node = subdirectories.getHead();
            while(node != null){
                if(node.value.getDirectory().isEqual(directory)){
                    return node;
                }
                node = node.next;
            }
            return null;
        }

        public LinkedList.Node<TreeNode> findByPrefix(char[] prefix){
            LinkedList.Node<TreeNode> node = subdirectories.getHead();
            while(node != null){

                node = node.next;
            }
            return null;
        }

        public boolean containsDirectory(Directory dirDirectory){
            return find(dirDirectory) != null;
        }
    }

    static class Directory {
        private char[] dirName;
        private int hash;
        private final static int BASE = 37;
        private final static int[] POWERS = {1, BASE, BASE * BASE};

        public Directory(char[] value){
            this.dirName = new char[value.length];
            for(int i = 0; i<this.dirName.length && value[i] != '\0'; i++){
                this.dirName[i] = value[i];
            }
            setHash();
        }

        public char[] getDirName() {
            return dirName;
        }

        private void setHash(){
            for(int i = 0, j = 2; i<this.dirName.length && this.dirName[i] != '\0'; i++, j--){
                if(i<3){
                    this.hash += this.dirName[i] * POWERS[j];
                } else {
                    this.hash += this.dirName[i];
                }
            }
        }

        public int getHash(){
            return this.hash;
        }

        public boolean isEqual(Directory directory){
            if(this.hash != directory.getHash()){
                return false;
            }
            boolean isEqual = true;
            for(int i = 0; i< directory.getDirName().length; i++){
                if(directory.getDirName()[i] == '\0' && this.dirName[i] == '\0'){
                    break;
                }
                if(directory.getDirName()[i] != this.getDirName()[i]){
                    isEqual = false;
                    break;
                }
            }
            return isEqual;
        }

    }

    static class LinkedList<E> {
        private int size;
        private Node<E> head;
        private Node<E> tail;

        public E getFirst(){
            if(head == null){
                return null;
            }
            return head.value;
        }

        public void add(E value){
            Node<E> node = new Node<>(value);
            if(head == null){
                head = tail = node;
                size++;
            } else {
                if(value instanceof TreeNode){
                    Node<E> existing = getHead();
                    char[] nodeName = ((TreeNode) value).getDirectory().getDirName();
                    boolean inserted = false;
                    while(existing != null){
                        char[] existingName = ((TreeNode) existing.value).getDirectory().getDirName();
                        for(int i=0; i<nodeName.length && i<existingName.length; i++){
                            if(nodeName[i] <= existingName[i]){
                                if(nodeName[i] == existingName[i]){
                                    if((i == nodeName.length-1 || nodeName[i+1] == '\0')
                                            && (i < existingName.length-1 && existingName[i+1] != '\0')){
                                    } else {
                                        continue;
                                    }
                                }
                                node.next = existing;
                                node.prev = existing.prev;
                                if(existing.prev != null){
                                    existing.prev.next = node;
                                }
                                existing.prev = node;
                                if(existing == getHead()){
                                    head = node;
                                }
                                inserted = true;
                            }
                            break;
                        }
                        if(inserted){
                            break;
                        }
                        existing = existing.next;
                    }
                    if(!inserted){
                        tail.next = node;
                        node.prev = tail;
                        tail = node;
                    }
                    size++;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                    size++;
                }
            }
        }

        public void remove(E value){
            Node<E> node = head;
            while(node != null){
                if(value instanceof Directory){
                    Directory directory = (Directory) value;
                    Directory nodeDirectory = (Directory) node.value;
                    if(directory.isEqual(nodeDirectory)){
                        if(node.next != null){
                            node.next.prev = node.prev;
                        }
                        if(node.prev != null){
                            node.prev.next = node.next;
                        }
                        if(head == node){
                            head = node.next;
                            if(head != null){
                                head.prev = null;
                            }
                        }
                        if(tail == node){
                            tail = node.prev;
                            if(tail != null){
                                tail.next = null;
                            }
                        }
                        size--;
                    }
                }
                node = node.next;
            }

        }

        public Node<E> getHead() {
            return head;
        }

        static class Node<E>  {
            E value;
            Node<E> prev;
            Node<E> next;

            Node(E value){
                this.value = value;
            }
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
//        sc = new Scanner(System.in);
//
//        int T = sc.nextInt();
//
//        for (int tc = 1; tc <= T; tc++) {
//            System.out.printf("Case #%d:\n", tc);
//            run();
//        }

        init();
        char[] c = new char[]{'a','a','a','a',0};
        System.out.println(mkdir(0, c));
        c = new char[]{'a','a','a','a','a'};
        System.out.println(mkdir(0, c));
        c = new char[]{'a','a','a',0,0};
        System.out.println(mkdir(0, c));
        System.out.println(chdir(1, c));
        System.out.println(mkdir(1, c));
        c = new char[]{'z','a','a',0,0};
        System.out.println(mkdir(1, c));
        c = new char[]{'b','a','a',0,0};
        System.out.println(mkdir(1, c));
        System.out.println();
    }
}
