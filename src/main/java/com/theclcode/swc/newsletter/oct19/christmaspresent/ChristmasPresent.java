package com.theclcode.swc.newsletter.oct19.christmaspresent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChristmasPresent {
    /**************** START OF USER SOLUTION ****************/
    static List<DoublyLinkedList<Integer>> twos;
    static List<DoublyLinkedList<Integer>> threes;
    static List<DoublyLinkedList<Integer>> solo;
    static List<DoublyLinkedList<Integer>> big;

    public static void arrange(int N, int box[], int limit) {

        twos = new ArrayList<>();
        threes = new ArrayList<>();
        solo = new ArrayList<>();
        big = new ArrayList<>();

        int[] gifts = box;
        boolean[] visited = new boolean[N];
        for(int i=0; i<N; i++){
            int index;
            if(!visited[i]){
                DoublyLinkedList<Integer> cycle = new DoublyLinkedList<>();
                index = i;
                visited[i] = true;
                cycle.add(i);
                do{
                    index = gifts[index];
                    visited[index]=true;
                    cycle.add(index);
                } while(index!=i);
                groupCycle(cycle);
            }
        }

        for(DoublyLinkedList<Integer> cycle : big){
            while(cycle.size-1 > 5){
                DoublyLinkedList<Integer> small = new DoublyLinkedList<>();
                small.add(cycle.getHead());
                int i = 4;
                while(i > 0){
                    small.add(cycle.removeFromHead());
                    i--;
                }
                small.add(cycle.getHead());
                solo.add(small);
            }
            groupCycle(cycle);
        }

        for(DoublyLinkedList<Integer> cycle : solo){
            init(cycle);
        }

        for(DoublyLinkedList<Integer> cycle : threes){
            if(!twos.isEmpty()){
                DoublyLinkedList<Integer> two = twos.get(0);
                twos.remove(two);
                initialize( 5, cycle, two);
            } else {
                init(cycle);
            }
        }

        int size = twos.size();
        while(size>0){
            if(size%2 == 0){
                initialize(4, twos.get(0), twos.get(1));
                twos.remove(twos.get(0));
                twos.remove(twos.get(0));
                size-=2;
            } else {
                initialize(2, twos.get(0));
                size--;
            }
        }
    }

    private static void init(DoublyLinkedList<Integer> cycle){
        initialize(cycle.size-1, cycle);
    }

    private static void initialize(int length, DoublyLinkedList<Integer>... cycles) {
        int[] origin = new int[length];
        int[] target = new int[length];
        int index = 0;
        for(int x=0; x<cycles.length; x++){
            origin[index] = cycles[x].getHead();
            int cycleLength = cycles[x].size-1;
            for (int i = index; i < index+cycleLength; i++) {
                target[i] = cycles[x].removeFromHead();
                if (i < target.length - 1) {
                    origin[i + 1] = target[i];
                }
            }
            index=cycleLength;
        }
        swap5(origin.length, origin, target);
    }

    private static void groupCycle(DoublyLinkedList<Integer> cycle) {
        switch (cycle.size - 1) {
            case 1:
                break;
            case 2:
                twos.add(cycle);
                break;
            case 3:
                threes.add(cycle);
                break;
            case 4:
            case 5:
                solo.add(cycle);
                break;
            default:
                big.add(cycle);
        }
    }

    /***************** END OF USER SOLUTION *****************/


    private static final int MAXN = 10000;

    private static int N;
    private static int limit;
    private static int box[] = new int[MAXN];

    private final static int CALL_COUNT_MAX	= 1000000;
    private static int callcount;
    private static boolean okay;

    private static final int que[] = new int[5];

    public static boolean swap5(int K, int origin[], int target[]) {
        if(!okay || callcount >= CALL_COUNT_MAX || K <= 0 || K > 5)
            return okay = false;

        ++callcount;

        for(int i = 0; i < K; ++i) {
            if(origin[i] < 0 || origin[i] >= N || target[i] < 0 || target[i] >= N)
                return okay = false;

            que[i] = box[origin[i]];

            if(box[origin[i]] == -1)
                return okay = false;

            box[origin[i]] = -1;
        }

        for(int i = 0; i < K; ++i) {
            if(box[target[i]] != -1)
                return okay = false;

            box[target[i]] = que[i];
        }

        return true;
    }

    private static boolean check() {
        for(int i = 0; i < N; ++i)
            if(box[i] != i)
                return false;

        return true;
    }

    private static boolean run(BufferedReader br) throws Exception {

        StringTokenizer st;

        int box_t[] = new int[MAXN];

        okay = true;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; ++i) {
            box[i] = Integer.parseInt(st.nextToken());
            box_t[i] = box[i];
        }

        callcount = 0;

        arrange(N, box_t, limit);

        return okay && callcount <= limit && check() && N > 0;
    }

    public static void main(String[] args) throws Exception {
        int TC;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());

        for(int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("Case #" + testcase + ": " + run(br));
        }
    }

    static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;
        Node(E value){
            this.value = value;
        }
    }

    static class DoublyLinkedList<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size;

        public void add(E value){
            Node<E> node = new Node<>(value);
            if(head == null){
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public E getHead(){
            if(head == null){
                return null;
            }
            return head.value;
        }

        public E removeFromHead(){
            if(head == null || head.next == null){
                return null;
            }
            Node<E> node = head.next;
            head.next = node.next;
            if(node.next != null){
                node.next.prev = head;
            }
            size--;
            return node.value;
        }

    }
}
