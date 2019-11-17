package com.theclcode.unfinished.filesystem;

public class FileSystem {

    Node rootDirectory;
    Window left;
    Window right;
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    public void init(){
        rootDirectory = new Node();
        left = new Window(rootDirectory);
        right = new Window(rootDirectory);
    }

    public int mkdir(int m_window, char[] name){
        Window window = getWindow(m_window);
        Node node = window.getLocation();
        return node.add(name);
    }

    public int chdir(int m_window, char[] name){
        Window window = getWindow(m_window);
        if(name[0] == '/'){
            window.setLocation(rootDirectory);
            return SUCCESS;
        }else if(name[0] == '.' && name[1] == '.'){
            window.setLocation(window.getLocation().parent);
            return SUCCESS;
        } else {
            Node node = window.getLocation();
            Node newLocation = node.find(name);
            if(newLocation != null ){
                window.setLocation(newLocation);
                return SUCCESS;
            }
        }
        return FAILURE;
    }

    public int rmdir(int m_window, char[] name){
        Window window = getWindow(m_window);
        Node node = window.getLocation();
        return node.findAndDelete(name, 0);
    }

    public int mvdir(int m_window, char[] name) {
        int o_window = m_window == 1 ? 0 : 1;
        Window window = getWindow(m_window);
        Window otherWindow = getWindow(o_window);
        Node node = window.getLocation();
        Node dirToMove = node.find(name);
        if (dirToMove != null) {
            if(node == otherWindow.getLocation()){
                return FAILURE;
            }
            if(mkdir(m_window == 1 ? 0 : 1, dirToMove.name) == 1){
                Node newNode = otherWindow.getLocation().find(dirToMove.name);
                newNode.childrenDirectories = node.childrenDirectories;
                node.remove();
                return SUCCESS;
            }
        }
        return FAILURE;
    }

    Window getWindow(int m_window){
        switch(m_window){
            case 0:
                return right;
            case 1:
                return left;
            default:
                return null;
        }
    }

    void print(int i){
        System.out.println(i);
    }

    public void run(){
        init();
        int result = 0;
        mkdir(1, "a".toCharArray());
        mkdir(1, "b".toCharArray());
        result = chdir(1, "b".toCharArray());
        mkdir(1, "abc".toCharArray());
        result = mkdir(1, "abc".toCharArray());
        result = mkdir(1, "ab".toCharArray());
        result = mkdir(1, "a".toCharArray());
        chdir(1, "/".toCharArray());
        result = rmdir(1, "b".toCharArray());
        print(result);
        System.out.println();
    }

    public static void main(String[] args) {
        new FileSystem().run();
    }

    class Node {
        private char value;
        private int size=0;
        private boolean isDirectory = false;
        private Node[] children = new Node[27];
        private Node parent;
        private char[] name;
        LinkedList<Node> childrenDirectories = new LinkedList<>();

        Node(){
            this('/', null);
            this.name = new char[]{'/'};
        }

        Node(char value, Node parent){
            this.value = value;
            this.parent = parent;
        }

        private Node getRoot() {
            Node node;
            if(this == rootDirectory){
                node = rootDirectory;
            } else {
                if(this.children[26] == null){
                    this.children[26] = new Node();
                }
                node = this.children[26];
            }
            if(this.children[26] == null && this != rootDirectory){
                this.children[26] = new Node();
                node = this.children[26];
            }
            return node;
        }

        public int getSize() {
            return size;
        }

        public Node[] getChildren() {
            return children;
        }

        public boolean isDirectory() {
            return isDirectory;
        }

        public void setDirectory(boolean directory) {
            isDirectory = directory;
        }

        public int add(char[] name){
            Node node = getRoot();
            for(int i=0; i<name.length && name[i]!='\0'; i++){
                int index = name[i]-97;
                if(node.children[index] == null){
                    node.children[index] = new Node(name[i], this);
                    node.size++;
                }
                node = node.children[index];
            }
            if(node.isDirectory){
                return FAILURE;
            } else {
                node.isDirectory = true;
                node.parent = this;
                node.name = name;
                this.childrenDirectories.add(node);
                return SUCCESS;
            }
        }

        private void remove(){
            Node node = parent;
            for(int i=0; i<name.length && name[i] != '\0'; i++){
                int index = name[i] - 97;
                if(i == name.length - 1){
                    if(node.getChildren()[index].size > 0){
                        node.getChildren()[index].isDirectory = false;
                    } else {
                        node.getChildren()[index] = null;
                    }
                } else if(node.getChildren()[index].isDirectory){
                    node.getChildren()[index].size -= 1;
                }
                node = node.getChildren()[index];
            }

        }

        public int findAndDelete(char[] name, int deletedDirectories){
            Node node = find(name);
            if(node != null){
                node.remove();
                deletedDirectories++;
                LLNode<Node> child = this.childrenDirectories.head;
                while(child != null){
                    deletedDirectories += child.value.remove(deletedDirectories);
                    child = child.next;
                }
            }
            return deletedDirectories;
        }

        public Node find(char[] name){
            Node node = getRoot();
            int index = 0;
            for(int i=0; i<name.length && name[i]!='\0'; i++){
                index = name[i]-97;
                if(node.getChildren()[index] == null){
                    return null;
                }
                node = node.getChildren()[index];
            }
            if(node.isDirectory){
                return node;
            }
            while(!node.isDirectory){
                for(int i=0; i<node.getChildren().length; i++){
                    if(node.getChildren()[i] != null){
                        if(node.getChildren()[i].isDirectory){
                            return node.getChildren()[i];
                        }
                        node = node.getChildren()[i];
                        break;
                    }
                }
                if(node == null){
                    break;
                }
            }
            return null;
        }

        public int remove(int deletedDirectories) {
            Node root = getRoot();
            LLNode<Node> childDirectory=  root.childrenDirectories.head;
            while(childDirectory != null){
                deletedDirectories += childDirectory.value.remove(deletedDirectories);
                childDirectory = childDirectory.next;
            }
            return deletedDirectories;

        }
    }

    class LinkedList<E> {
        LLNode head;
        LLNode tail;
        int size;

        void add(E value){
            LLNode node = new LLNode(value);
            if(head == null){
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        void remove(E value){
            LLNode node = head;
            while(node != null){
                if(node.value == value){
                    if(node == head){
                        head = node.next;
                    }
                    if(node == tail){
                        tail = node.prev;
                    }
                    if(node.prev != null){
                        node.prev.next = node.next;
                    }
                    if(node.next != null){
                        node.next.prev = node.prev;
                    }
                    break;
                }
                node = node.next;
            }
        }
    }
    class LLNode<E>{
        E value;
        LLNode next;
        LLNode prev;

        LLNode(E value){
            this.value = value;
        }

    }
    class Window {
        private Node location;

        public Window(Node location){
            this.location = location;
        }

        public Node getLocation() {
            return location;
        }

        public void setLocation(Node location) {
            this.location = location;
        }
    }
}

