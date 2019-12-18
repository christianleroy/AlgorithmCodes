package com.theclcode.unfinished.windowexplorer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WindowExplorer {

    /**************** START OF USER SOLUTION ****************/

    final static int SUCCESS = 1;
    final static int FAILURE = 0;
    static Directory root;
    static Window left;
    static Window right;

    static void init() {
        root = new Directory();
        left = new Window(0, root);
        right = new Window(1, root);
    }

    static int mkdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        return window.getLocation().create(name);
    }

    static int chdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        return window.getLocation().change(window, name);
    }

    static int rmdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        return window.getLocation().remove(window, name);
    }

    static int mvdir(int m_window, char name[]) {
        Window window = getWindow(m_window);
        return window.getLocation().move(window, name);
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
        private Directory location;

        public Window(int id, Directory location) {
            this.id = id;
            this.location = location;
        }

        public void setLocation(Directory location) {
            this.location = location;
        }

        public Directory getLocation() {
            return location;
        }
    }

    static class Directory {

        private char[] name;
        private int hash;
        private LinkedList<Directory>[][][] subdirectories = new LinkedList[26][26][26];
        private List<LinkedList<Directory>> subdirectoryList = new ArrayList<>();
        private int numberOfSubdirectories = 0;
        private Directory parent;

        private final static int BASE = 37;
        private final static int[] POWERS = {1, BASE, BASE * BASE};


        public Directory(){
            this.name = new char[]{'/'};
            this.parent = null;
        }

        public Directory(char[] value, Directory parent){
            this.name = new char[value.length];
            for(int i = 0; i<this.name.length && value[i] != '\0'; i++){
                this.name[i] = value[i];
            }
            this.parent = parent;
            setHash();
        }

        public char[] getName() {
            return name;
        }

        public void setParent(Directory parent) {
            this.parent = parent;
        }

        public int create(char[] name){
            LinkedList<Directory> location = subdirectories[name[0]-97][name[1]-97][name[2]-97];
            Directory directory = new Directory(name, this);
            if(location == null){
                location = new LinkedList<>();
                location.add(directory);
                subdirectories[name[0]-97][name[1]-97][name[2]-97] = location;
                numberOfSubdirectories++;
                subdirectoryList.add(location);
                return SUCCESS;
            } else {
                LinkedList.Node<Directory> node = location.getHead();
                while(node != null){
                    if(node.value.isEqual(directory)){
                        return FAILURE;
                    }
                    node = node.next;
                }
                location.add(directory);
                numberOfSubdirectories++;
                return SUCCESS;
            }
        }

        public LinkedList<Directory> getLocation(char[] name){
                LinkedList<Directory> location = null;
                int[] level = new int[3];
                level[0] = name[0]-97;
                if(name[1] != '\0'){
                    level[1] = name[1]-97;
                    if(name[2] != '\0'){
                        level[2] = name[2]-97;
                        if(this.subdirectories[level[0]][level[1]][level[2]] != null
                                && this.subdirectories[level[0]][level[1]][level[2]].size > 0){
                            location = this.subdirectories[level[0]][level[1]][level[2]];
                        }
                    } else {
                        for(int i=0; i<26; i++){
                            if(this.subdirectories[level[0]][level[1]][i] != null
                                    && this.subdirectories[level[0]][level[1]][i].size >0){
                                location = this.subdirectories[level[0]][level[1]][i];
                                break;
                            }
                        }
                    }

                } else {
                    for(int i=0; i<26; i++){
                        for(int j=0; j<26; j++){
                            if(this.subdirectories[level[0]][i][j] != null && this.subdirectories[level[0]][i][j].size > 0){
                                location = this.subdirectories[level[0]][i][j];
                                break;
                            }
                        }
                    }
                }
                return location;
        }

        public List<LinkedList<Directory>> getLocations(char[] name){
            List<LinkedList<Directory>> locations = new ArrayList<>();
            int[] level = new int[3];
            level[0] = name[0]-97;
            if(name[1] != '\0'){
                level[1] = name[1]-97;
                if(name[2] != '\0'){
                    level[2] = name[2]-97;
                    if(this.subdirectories[level[0]][level[1]][level[2]] != null
                            && this.subdirectories[level[0]][level[1]][level[2]].size > 0){
                        locations.add(this.subdirectories[level[0]][level[1]][level[2]]);
                    }
                } else {
                    for(int i=0; i<26; i++){
                        if(this.subdirectories[level[0]][level[1]][i] != null
                                && this.subdirectories[level[0]][level[1]][i].size >0){
                            locations.add(this.subdirectories[level[0]][level[1]][i]);
                        }
                    }
                }

            } else {
                for(int i=0; i<26; i++){
                    for(int j=0; j<26; j++){
                        if(this.subdirectories[level[0]][i][j] != null && this.subdirectories[level[0]][i][j].size > 0){
                            locations.add(this.subdirectories[level[0]][i][j]);
                        }
                    }
                }
            }
            return locations;
        }

        public int change(Window window, char[] name){
            if(name[0] == '/'){
                window.setLocation(root);
                return SUCCESS;
            } else if(name[0] == '.' && name[1] == '.'){
                if(window.getLocation().equals(root)){
                    return FAILURE;
                }
                window.setLocation(window.getLocation().parent);
                return SUCCESS;
            }
            LinkedList<Directory> location = getLocation(name);
            if(location == null || location.size == 0){
                return FAILURE;
            }
            window.setLocation(location.getFirst());
            return SUCCESS;
        }

        public int remove(Window window, char[] name){
            Window otherWindow = getWindow(window.id == 0 ? 1 : 0);
            int deleted = 0;
            List<LinkedList<Directory>> locations = getLocations(name);
            for(LinkedList<Directory> location : locations){
                deleted += location.size;
                LinkedList.Node<Directory> node = location.getHead();
                while(node != null){
                    if(node.value == otherWindow.getLocation()){
                        otherWindow.setLocation(window.getLocation());
                    }
                    deleted = node.value.recursiveDelete(window, deleted);
                    node = node.next;
                }
                numberOfSubdirectories -= location.size;
                location.removeAll();
            }
            return deleted;
        }

        public int move(Window window, char[] name){
            Window otherWindow = getWindow(window.id == 0 ? 1 : 0);
            LinkedList<Directory> location = getLocation(name);
            if(window.getLocation() == otherWindow.getLocation() || location == null
                    || location.size == 0 || otherWindow.getLocation().hasSubdirectory(location.getFirst())
                    || location.getFirst().hasDescendant(otherWindow.getLocation())){
                return FAILURE;
            } else {
                    Directory directoryToMove = location.getFirst();
                    location.remove(location.getFirst());
                    directoryToMove.parent.numberOfSubdirectories--;
                    otherWindow.getLocation().insert(directoryToMove);
            }
            return SUCCESS;
        }

        private void insert(Directory directory){
            LinkedList<Directory> location = getLocation(directory.getName());
            if(location != null){
                location.add(directory);
            } else {
                location = new LinkedList<>();
                location.add(directory);
                subdirectories[directory.getName()[0]-97][directory.getName()[1]-97][directory.getName()[2]-97] = location;
                subdirectoryList.add(location);
            }
            directory.setParent(this);
            numberOfSubdirectories++;
        }

        private boolean hasDescendant(Directory directory){
            boolean found = false;
            for(LinkedList<Directory> directoryLinkedList : subdirectoryList){
                LinkedList.Node<Directory> node = directoryLinkedList.getHead();
                while(node != null){
                    if(node.value == directory){
                        return true;
                    }
                    if(node.value.hasDescendant(directory)){
                        found = true;
                        break;
                    }
                    node = node.next;
                }
            }
            return found;
        }

        private boolean hasSubdirectory(Directory directory){
            for(LinkedList<Directory> directoryLinkedList : subdirectoryList){
                LinkedList.Node<Directory> node = directoryLinkedList.getHead();
                while(node != null){
                    if (node.value.isEqual(directory)) {
                        return true;
                    }
                    node = node.next;
                }
            }
            return false;
        }

        private int recursiveDelete(Window window, int deleted){
            Window otherWindow = getWindow(window.id == 0 ? 1 : 0);
            deleted += this.numberOfSubdirectories;
            for(LinkedList<Directory> subdirectory : subdirectoryList){
                LinkedList.Node<Directory> node = subdirectory.getHead();
                while(node != null){
                    if(node.value == otherWindow.getLocation()){
                        otherWindow.setLocation(window.getLocation());
                    }
                    deleted = node.value.recursiveDelete(window, deleted);
                    node = node.next;
                }
            }
            return deleted;
        }

        private void setHash(){
            for(int i = 0, j = 2; i<this.name.length && this.name[i] != '\0'; i++, j--){
                if(i<3){
                    this.hash += this.name[i] * POWERS[j];
                } else {
                    this.hash += this.name[i];
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
            for(int i = 0; i< directory.getName().length; i++){
                if(directory.getName()[i] == '\0' && this.name[i] == '\0'){
                    break;
                }
                if(directory.getName()[i] != this.getName()[i]){
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
                if(value instanceof Directory){
                    Node<E> existing = getHead();
                    char[] nodeName = ((Directory) value).getName();
                    boolean inserted = false;
                    while(existing != null){
                        char[] existingName = ((Directory) existing.value).getName();
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

        public void removeAll() {
            this.size = 0;
            this.head = null;
            this.tail = null;
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

    public static void main(String[] args) throws IOException {
        sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            System.out.printf("Case #%d:\n", tc);
            run();
        }

//        init();
//        char str[] = new char[100];
//        char[] root = new char[]{'/'};
//        char[] up = new char[]{'.','.'};

//        copy(str, "aaaaa");
//        System.out.println(mkdir(0, str));
//        copy(str, "bbbbb");
//        System.out.println(mkdir(0, str));
//        copy(str, "aaa");
//        System.out.println(chdir(1, str));
//        copy(str, "aaaaa");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbb");
//        System.out.println(chdir(0, str));
//        copy(str, "ccccc");
//        System.out.println(mkdir(0, str));
//        copy(str, "ccc");
//        System.out.println(mvdir(0, str));
//        System.out.println(chdir(0, root));
//        System.out.println(chdir(1, up));
//        copy(str, "aaa");
//        System.out.println(chdir(0, str));
//        copy(str, "ccc");
//        System.out.println(chdir(0, str));
//        copy(str, "ddddd");
//        System.out.println(mkdir(0, str));
//        copy(str, "aaa");
//        System.out.println(rmdir(1, str));
//        copy(str, "abcde");
//        System.out.println(mkdir(1, str));
//        copy(str, "abc");
//        System.out.println(chdir(1, str));
//        copy(str, "defgh");
//        System.out.println(mkdir(1, str));
//        copy(str, "ijklm");
//        System.out.println(mkdir(1, str));
//        copy(str, "i");
//        System.out.println(chdir(1, str));
//        copy(str, "ab");
//        System.out.println(mvdir(0, str));
//        copy(str, "abc");
//        System.out.println(chdir(0, str));
//        System.out.println(chdir(1, up));
//        copy(str, "i");
//        System.out.println(mvdir(0, str));
//        System.out.println(chdir(0, root));
//        System.out.println(chdir(1, root));
//        copy(str, "b");
//        System.out.println(rmdir(0, str));
//        copy(str, "a");
//        System.out.println(rmdir(1, str));
//
//
//        //break
//        System.out.println("===============honest==============");
//
//        copy(str, "aaaaccc");
//        System.out.println(mkdir(0, str));
//        copy(str, "aaaabb");
//        System.out.println(mkdir(0, str));
//        copy(str, "aaaaa");
//        System.out.println(mkdir(0, str));
//        copy(str, "aaa");
//        System.out.println(chdir(1, str));
//        copy(str, "bbbbbbb");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbbbbbbb");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbbbbbbbb");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbbbbbbbaaa");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbbbbbb");
//        System.out.println(mkdir(0, str));
//        copy(str, "bbbbbbb");
//        System.out.println(mkdir(0, str));
//        copy(str, "b");
//        System.out.println(chdir(1, str));
//        System.out.println(chdir(0, str));
//        copy(str, "xxxxx");
//        System.out.println(mkdir(1, str));
//        copy(str, "yyyyy");
//        System.out.println(mkdir(1, str));
//        System.out.println(chdir(0, root));
//        copy(str, "a");
//        System.out.println(rmdir(0, str));
//        System.out.println();

//        copy(str, "aaaaa");
//        System.out.println(mkdir(0, str));
//        copy(str, "bbbbb");
//        System.out.println(mkdir(0, str));
//        copy(str, "b");
//        System.out.println(chdir(1, str));
//        copy(str, "aaaaa");
//        System.out.println(mkdir(1, str));
//        copy(str, "aaa");
//        System.out.println(mvdir(0, str));

//        copy(str, "aaaaa");
//        System.out.println(mkdir(0, str));
//        copy(str, "abaab");
//        System.out.println(mkdir(0, str));
//        copy(str, "acaac");
//        System.out.println(mkdir(0, str));
//        copy(str, "a");
//        System.out.println(chdir(1, str));
//        copy(str, "bbbba");
//        System.out.println(mkdir(1, str));
//        copy(str, "bbbbb");
//        System.out.println(mkdir(1, str));
//        copy(str, "xbbbb");
//        System.out.println(mkdir(1, str));
//        copy(str, "zzzzz");
//        System.out.println(mkdir(1, str));
//        copy(str, "a");
//        System.out.println(rmdir(0, str));
//        System.out.println();



    }

    static void copy(char[] str, String word){
        mstrcpy(str, word);
    }
}
