package com.theclcode.unfinished.filesystem;

public class FileSystem {

    TreeNode rootDirectory;
    Window left;
    Window right;
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    public void init(){
        rootDirectory = new TreeNode();
        left = new Window(rootDirectory);
        right = new Window(rootDirectory);
    }

    public int mkdir(int m_window, char[] name){
        Window window = getWindow(m_window);
        TreeNode treeNode = window.getLocation();
        return treeNode.add(name);
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
            TreeNode treeNode = window.getLocation();
            TreeNode newLocation = treeNode.find(name);
            if(newLocation != null ){
                window.setLocation(newLocation);
                return SUCCESS;
            }
        }
        return FAILURE;
    }

    public int rmdir(int m_window, char[] name){
        Window window = getWindow(m_window);
        TreeNode treeNode = window.getLocation();
        return treeNode.findAndDelete(name);
    }

    public int mvdir(int m_window, char[] name) {
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
        mkdir(1, "a".toCharArray());
        mkdir(1, "b".toCharArray());

        chdir(1, "a".toCharArray());
        mkdir(1, "c".toCharArray());
        chdir(1, "/".toCharArray());
        chdir(0, "b".toCharArray());

        mvdir(1, "a".toCharArray());
        System.out.println(rmdir(1, "b".toCharArray()));
        System.out.println();
    }

    public static void main(String[] args) {
        new FileSystem().run();
    }

    class TreeNode {
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
                treeNode.parent = this;
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
                treeNode.parent = this;
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
            if(treeNode != null){
                treeNode.remove();
                deletedDirectories = 1;
                Node<TreeNode> child = treeNode.getRoot().childrenDirectories.head;
                while(child != null){
                    deletedDirectories += child.value.countSubdirectories();
                    child = child.next;
                }
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
            while(!treeNode.isDirectory){
                for(int i = 0; i< treeNode.getChildren().length; i++){
                    if(treeNode.getChildren()[i] != null){
                        if(treeNode.getChildren()[i].isDirectory){
                            return treeNode.getChildren()[i];
                        }
                        treeNode = treeNode.getChildren()[i];
                        break;
                    }
                }
                if(treeNode == null){
                    break;
                }
            }
            return null;
        }


    }

    class LinkedList<E> {
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
    class Node<E>{
        E value;
        Node next;
        Node prev;

        Node(E value){
            this.value = value;
        }

    }
    class Window {
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

