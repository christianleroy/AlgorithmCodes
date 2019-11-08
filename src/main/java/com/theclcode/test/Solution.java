package com.theclcode.test;

import java.util.*;
public class Solution {

    public void run(){
        HashTable<String, String> mgaWords = new HashTable<>(10);
        mgaWords.put("Mapa", "Map");
        mgaWords.put("Sansinukob", "Universe");
        mgaWords.put("Daigdig", "Earth");
        mgaWords.put("Daigdig", "World");
        mgaWords.put("Arkamad", "Pluto");
        System.out.println(mgaWords.get("Arkamad"));
        System.out.println(mgaWords.get("Daigdig"));

        HashTable<Double, Double> mgaDouble = new HashTable<>(10);
        mgaDouble.put(1.5, 1.5*2);
        mgaDouble.put(1.75, 1.75*2);
        mgaDouble.put(2.0, 2.0*2);
        mgaDouble.put(2.0, 3.0*2);

        HashTable<Person, Integer> mgaPeople = new HashTable<>(10);
        Person[] people = new Person[]{new Person("A"), new Person("B"), new Person("C")};
        mgaPeople.put(people[0], 25);
        mgaPeople.put(people[1], 20);
        mgaPeople.put(people[2], 25);
        mgaPeople.put(people[2], 26);
        mgaPeople.put(null, null);
        System.out.println(mgaPeople.get(null));
        mgaPeople.put(null, 16);
        System.out.println(mgaPeople.get(null));
        mgaPeople.remove(null);
        System.out.println(mgaPeople.get(null));

        HashTable<String, Integer> map = new HashTable<>(1);
        String name = "Tristan";
        map.put(name, 25);
        map.put("John", 45);
        map.put("Side", 35);
        System.out.println(map.contains("Side"));
        System.out.println(map.get("Side"));
        map.remove("Side");
        System.out.println(map.contains("Side"));
        System.out.println(map.get("Side"));
        System.out.println(map.contains("Tristan"));
        map.put("Christian", 25);
        map.put("Leroy", 25);

        HashTable.Node[] table = map.getTable();

        for(int i=0; i<table.length; i++){
            if(table[i] != null){
                HashTable.Node node = table[i];
                while(node != null){
                    System.out.println(node.key+": "+node.value);
                    node = node.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Solution().run();
    }
}

class HashTable<K, V> {

    private final int capacity;
    private Node<K, V>[] table;
    private static final int BASE = 37;
    private static final int[] POWERS = {1, BASE, BASE*BASE};

    public HashTable(){
        this(1013);
    }

    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Node[capacity];
    }

    public void put(K key, V value){
        int index = getAddress(key);
        if(table[index] == null){
            Node<K, V> node = new Node<>(key, value);
            table[index] = node;
        } else {
            Node<K, V> node = find(key);
            if(node == null){
                node = new Node<>(key, value);
                Node<K, V> existingNode = table[index];
                node.next = existingNode;
                existingNode.prev = node;
                table[index] = node;
            } else {
                node.value = value;
            }
        }
    }

    public V get(K key){
        Node<K, V> node = find(key);
        if(node == null){
            return null;
        }
        return node.value;
    }

    public void remove(K key){
        Node<K, V> node = find(key);
        int index = getAddress(key);
        if(node != null){
            if(node.next != null){
                node.next.prev = node.prev;
            }
            if(node.prev != null){
                node.prev.next = node.next;
            }
            if(node == table[index]){
                table[index] = node.next;
            }
        }
    }

    public boolean contains(K key){
        return find(key) != null;
    }

    public Node<K,V>[] getTable(){
        return table;
    }

    private Node<K, V> find(K key){
        int index = getAddress(key);
        Node<K, V> node = table[index];
        while(node != null){
            if(key instanceof String && node.key != null){
                String _key = key.toString();
                String nodeKey = node.key.toString();
                boolean isEqual = true;
                if(_key.length() == nodeKey.length()){
                    for(int i=0; i<_key.length(); i++){
                        if(_key.charAt(i) != nodeKey.charAt(i)){
                            isEqual = false;
                            break;
                        }
                    }
                    if(isEqual){
                        return node;
                    }
                }
            } else if((key == null && node.key == null) || (node.key != null && node.key.equals(key))){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private int getAddress(K key){
        return key==null ? 0 : hash(key) % capacity;
    }

    private int hash(K key){
        String word = key.toString();
        int hash = 0;
        for(int i=0, y=POWERS.length-1; i<word.length(); i++, y--){
            if(i<3){
                hash+=word.charAt(i)*POWERS[y];
            } else {
                hash+=word.charAt(i);
            }
        }
        return hash;
    }


    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getPrev() {
            return prev;
        }

        public void setPrev(Node<K, V> prev) {
            this.prev = prev;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

}

class Person {
    String name;
    Person(String name){
        this.name = name;
    }
}