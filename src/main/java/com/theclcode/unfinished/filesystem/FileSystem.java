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
        Node dirToDelete = node.find(name);
        if(dirToDelete != null){
            return dirToDelete.remove(0);
        }
        return 0;
    }

    public int mvdir(int m_window, char[] name){
        return -1;
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

    public void run(){
        init();
        Node node = getWindow(1).getLocation();
        System.out.println(mkdir(1, new char[]{'a','b','c','\0','\0','\0'}));
        System.out.println(mkdir(1,"a".toCharArray()));
        System.out.println(mkdir(1,"b".toCharArray()));
        System.out.println(mkdir(1,"c".toCharArray()));
        System.out.println(mkdir(1,"a".toCharArray()));
    }

    public static void main(String[] args) {
        new FileSystem().run();
    }

    class Node {
        private char value;
        private int size=0;
        private boolean isDirectory = false;
        private Node[] children = new Node[26];
        private Node parent;
        private char[] name;
        LinkedList<Node> childDirectories = new LinkedList<>();

        Node(){
            this('/', null);
            this.name = new char[]{'/'};
        }

        Node(char value, Node parent){
            this.value = value;
            this.parent = parent;
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
            Node node = this;
            for(int i=0; i<name.length && name[i]!='\0'; i++){
                int index = name[i]-97;
                if(node.children[index] == null){
                    node.children[index] = new Node(name[i], this);
                    node.size++;
                } else {
                    if(i==name.length-1 && node.children[index].isDirectory){
                        return FAILURE;
                    }
                    node.size++;
                }
                node = node.children[index];
                if((i==name.length-1 || name[i+1] == '\0') && !node.isDirectory){
                    node.isDirectory=true;
                    node.name = name;
                    node.parent = this;
                    this.childDirectories.add(node);
                    return SUCCESS;
                }
            }


            return FAILURE;
        }

        public int remove(int deletedDirectories){
            LLNode<Node> llNode = this.childDirectories.head;
            while(llNode != null){
                llNode.value.remove(deletedDirectories);
                llNode = llNode.next;
            }
            Node node = this.parent;
            for(int i=0; i<this.name.length && name[i]!='\0'; i++){
                int index = name[i]-97;
                //Checks node is a directory but it's not the one you're deleting OR it's not a directory but it has more than one children
                if((node.getChildren()[index].isDirectory && i < name.length-1) || node.getChildren()[index].size>1){
                    node.getChildren()[index].size -= 1;
                } else {
                    if(node.getChildren()[index].isDirectory && node.getChildren()[index].size > 0){
                        node.getChildren()[index].isDirectory = false;
                    } else {
                        node.getChildren()[index] = null;
                    }
                    deletedDirectories++;
                }
            }
            return deletedDirectories;
        }

        public Node find(char[] name){
            Node node = this;
            for(int i=0; i<name.length && name[i]!='\0'; i++){
                int index = name[i]-97;
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

    }

    class LinkedList<E> {
        LLNode head;
        LLNode tail;

        void add(E value){
            LLNode node = new LLNode(value);
            if(head == null){
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
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

