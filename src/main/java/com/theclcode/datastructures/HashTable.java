package com.theclcode.datastructures;

public class HashTable<K, V> {
    int capacity;
    Node<K, V>[] table;
    static final int[] powers = new int[3];
    static final int BASE = 37;
    static {
        powers[0]=1;
        powers[1] = BASE;
        powers[2] = powers[1] * BASE;
    }

    public HashTable(){
        this(701);
    }

    @SuppressWarnings("unchecked")
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

    private Node<K, V> find(K key) {
        int index = getAddress(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (key instanceof String) {
                String _key = (String) key;
                String nodeKey = (String) node.key;
                if (_key.length() == nodeKey.length() && hash(key) == hash(node.key)) {
                    boolean isEqual = true;
                    for (char i = 0; i < _key.length(); i++) {
                        if (_key.charAt(i) != nodeKey.charAt(i)) {
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

    public void remove(K key){
        Node<K, V> node = find(key);
        if(node != null){
            if(node.next != null){
                node.next.prev = node.prev;
            }
            if(node.prev != null){
                node.prev.next = node.next;
            }
            int index = getAddress(key);
            if(table[index] == node){
                table[index] = node.next;
            }
        }
    }

    public boolean contains(K key){
        return find(key) != null;
    }

    private int getAddress(K key){
        return key == null ? 0 : hash(key) % capacity;
    }

    private int hash(K key){
        String word = key.toString();
        int length = word.length()-1;
        int hash=0;
        for(int x=0, y=2; x<=length; x++, y--){
            if(x<3){
                hash+=word.charAt(x)*powers[y];
            } else {
                hash+=word.charAt(x);
            }
        }
        return hash;
    }

    class Node<K, V>{
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
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
        System.out.print(map.contains("Tristan"));
    }

    public static class Person {
        String name;
        Person(String name){
            this.name = name;
        }
    }
}



