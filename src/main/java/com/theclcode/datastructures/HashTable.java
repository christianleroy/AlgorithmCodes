package com.theclcode.datastructures;

import java.util.Scanner;

public class HashTable {

    LinkedList<String>[] table;
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

    public void put(String key, String value){
        int index = (int)(hash(key) % capacity);
        if(table[index]==null){
            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.add(value);
            table[index] = linkedList;
        } else {
            if(find(index, value)==null){
                table[index].add(value);
            } else {

            }
        }
    }

    public String find(int index, String value){
        Node<String> node = table[index].head;
        while(node!=null){
            if(node.value.equals(value)){
                return node.value;
            }
            node = node.next;
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
        HashTable hashTable = new HashTable(701);

        boolean cont = true;
        Scanner sc;
        while(cont){
            sc = new Scanner(System.in);
            System.out.print("Enter word: ");
            String word = sc.nextLine();
            hashTable.put(word, word);
            System.out.print("0 to quit: ");
            cont = sc.nextInt()==0 ? false : true;
        }
        System.out.println("test");
    }

    class LinkedList<E>{

        private Node<E> head;
        private Node<E> tail;

        public void add(E value){
            Node<E> node = new Node(value);
            if(head==null){
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        public E remove(){
            if(head==null){
                return null;
            } else {
                E value = head.value;
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

    class Node<E>{
        public E value;
        public Node<E> next;
        public Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }
}



