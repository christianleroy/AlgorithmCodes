package com.theclcode.unfinished.leafvillage;
import java.util.Scanner;

public class TimeOutExceedSolution {

    static HashTable<Integer, DoublyLinkedList<Ninja>> ninjasByAge;
    static HashTable<Integer, Ninja> ninjas;
    static int ninjaId;
    static int oldestNinja;
    static int youngestNinja;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for(int t=1; t<=testCases; t++){
            init();
            System.out.print("#"+t);
            int commands = sc.nextInt();
            while(commands>0){
                String action = sc.next();
                switch(action){
                    case "D":
                        int power = sc.nextInt();
                        int age = sc.nextInt();
                        addNinja(age, power);
                        break;
                    case "P":
                        findMaster(sc.nextInt());
                        break;
                    default:
                }
                commands--;
            }
        }
    }

    static void init(){
        ninjas = new HashTable<>();
        ninjasByAge = new HashTable<>();
        ninjaId = 1;
        oldestNinja = 0;
        youngestNinja = 0;
    }


    public static void addNinja(int age, int power){
        Ninja ninja = new Ninja(ninjaId++, age, power);
        ninjas.put(ninja.getId(), ninja);
        if(ninjasByAge.has(age)){
            DoublyLinkedList<Ninja> ninjaList = ninjasByAge.get(age);
            ninjaList.add(ninja);
        } else {
            DoublyLinkedList<Ninja> ninjaList = new DoublyLinkedList<>();
            ninjaList.add(ninja);
            ninjasByAge.put(age, ninjaList);
        }
        if(ninja.getAge()>oldestNinja){
            oldestNinja = ninja.getAge();
        }
        if(ninja.getAge()<youngestNinja){
            youngestNinja = ninja.getAge();
        }
    }


    public static void findMaster(int ninjaId){
        System.out.print(" ");
        Ninja ninja = ninjas.get(ninjaId);
        if(ninja != null){
            for(int age=ninja.getAge(); age<=oldestNinja; age++){
                if(ninjasByAge.has(age)){
                    DoublyLinkedList<Ninja> ninjasByAgeGroup = ninjasByAge.get(age);
                    DoublyLinkedList.Node<Ninja> node = ninjasByAgeGroup.getHead();
                    int lowestPowerGap = 0;
                    Ninja ninjaLowestPowerGap = null;
                    while(node != null){
                        if(node.getValue() != ninja && node.getValue().getPower() >= ninja.getPower()){
                            System.out.print(node.getValue().getId());
                            return;
                        }
                        if(node.getValue().getPower()>=ninja.getPower() && node.getValue().getPower()-ninja.getPower()<lowestPowerGap && node.getValue() != ninja){
                            lowestPowerGap = node.getValue().getPower()-ninja.getPower();
                            ninjaLowestPowerGap = node.getValue();
                        }
                        node = node.getNext();
                    }
                    if(ninjaLowestPowerGap != null){
                        System.out.print(ninjaLowestPowerGap.getId());
                        return;
                    }
                }
            }
            System.out.print("NE");
        }
    }

    public static class Ninja {
        private int id;
        private int age;
        private int power;

        public Ninja(int id, int age, int power) {
            this.id=id;
            this.age=age;
            this.power=power;
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public int getPower() {
            return power;
        }

    }

}

class DoublyLinkedList<E>{

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void add(E value){
        Node<E> node = new Node(value);
        if(head==null){
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public E remove(){
        if(head==null){
            return null;
        }
        Node<E> node = head;
        head = head.next;
        if(head != null){
            head.prev = null;
        }
        size--;
        return node.getValue();
    }

    public Node<E> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public static class Node<E> {

        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }
    }
}

class HashTable<K, V> {
    int capacity;
    Node<K, V>[] table;
    static final int[] powers = new int[3];
    static final int BASE = 37;

    static {
        powers[0] = 1;
        powers[1] = BASE;
        powers[2] = powers[1] * BASE;
    }

    public HashTable() {
        this(701);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
    }

    public void put(K key, V value) {
        int index = getAddress(key);
        if (table[index] == null) {
            Node<K, V> node = new Node<>(key, value);
            table[index] = node;
        } else {
            Node<K, V> node = find(key);
            if (node == null) {
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

    public V get(K key) {
        Node<K, V> node = find(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node<K, V> find(K key) {
        int index = getAddress(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (key instanceof Integer) {
                int _key = (Integer) key;
                int nodeKey = (Integer) node.key;
                if(_key == nodeKey){
                    return node;
                }
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key) {
        Node<K, V> node = find(key);
        if (node != null) {
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            int index = getAddress(key);
            if (table[index] == node) {
                table[index] = node.next;
            }
        }
    }

    public boolean has(K key) {
        return find(key) != null;
    }

    public Node<K, V>[] getTable() {
        return table;
    }

    private int getAddress(K key) {
        return key == null ? 0 : hash(key) % capacity;
    }

    private int hash(K key) {
        String word = key.toString();
        int length = word.length() - 1;
        int hash = 0;
        for (int x = 0, y = 2; x <= length; x++, y--) {
            if (x < 3) {
                hash += word.charAt(x) * powers[y];
            } else {
                hash += word.charAt(x);
            }
        }
        return hash;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}