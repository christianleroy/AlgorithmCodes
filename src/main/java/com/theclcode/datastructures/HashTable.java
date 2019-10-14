package com.theclcode.datastructures;

import java.util.Scanner;

public class HashTable<K, V> {

    LinkedList<K, V>[] table;
    int capacity;
    static long[] powers = new long[12]; //assuming longest string is 12
    static final int BASE = 37;
    static {
        for(int i=0; i<=11; i++){
            if(i==0){
                powers[0]=1;
            } else if(i==1){
                powers[i] = BASE;
            } else {
                powers[i] = powers[i-1]*BASE;
            }
        }
    }

    public HashTable(int capacity){
        this.capacity = capacity;
        table = new LinkedList[capacity];
    }

    public void put(K key, V value){
        int index = (int)(hash(key.toString()) % capacity);
        index = index < 0 ? index*(-1) : index;
        if(table[index]==null){
            LinkedList linkedList = new LinkedList();
            linkedList.add(new Node(key, value));
            table[index] = linkedList;
        } else {

        }
    }

    public V get(K key){
        int index = (int)(hash(key.toString()) % capacity);
        if(table[index]!=null){
            LinkedList<K, V> linkedList = table[index];
            Node<K, V> node = linkedList.head;
            while(node!=null){
                if(key==node.key){
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    public long hash(String word){
        int length = word.length()-1;
        int hash=0;
        for(int x=0, y=length; x<=length; x++, y--){
            hash+=word.charAt(x)*powers[y];
        }
        return hash;
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(701);

//        boolean cont = true;
//        Scanner sc;
//        while(cont){
//            sc = new Scanner(System.in);
//            System.out.print("Enter word: ");
//            String word = sc.nextLine();
//            hashTable.put(word, word);
//            System.out.print("0 to quit: ");
//            cont = sc.nextInt()==0 ? false : true;
//        }
        hashTable.put("a", 23);
        int x = hashTable.get("a");
        hashTable.put("a", 25);
        System.out.println(x);
    }

    class LinkedList<K, V> {

        private Node<K, V> head;
        private Node<K, V> tail;

        public void add(Node<K, V> node){
            if(head==null){
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        public V remove(){
            if(head==null){
                return null;
            } else {
                V value = head.value;
                head = head.next;
                if(head != null){
                    head.prev = null;
                }
                return value;
            }
        }

        public boolean isEmpty(){
            return head == null;
        }
    }

    class Node<K, V>{
        public K key;
        public V value;
        public Node<K, V> next;
        public Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}



