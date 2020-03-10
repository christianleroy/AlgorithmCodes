package com.theclcode.swc.newsletter.mar20.minesofmoira;

import java.util.Scanner;

public class MinesOfMoira {

    static int price;
    static int currentPrice;
    static boolean[] purchased;
    static boolean purchasedAll;
    static int[][] minesForSale;
    static int[] minesRequired;

    static void init(){
        price = 0;
        currentPrice = 0;
        purchased = new boolean[50];
        purchasedAll = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int i=0; i<tc; i++){
            init();

            int K = sc.nextInt();
            minesRequired = new int[K];
            for(int j=0; j<K; j++){
                minesRequired[j] = sc.nextInt();
            }
            int minesForSaleSize = sc.nextInt();

            minesForSale = new int[minesForSaleSize][];

            for(int k=0; k<minesForSaleSize; k++){
                int minePrice = sc.nextInt();
                int mineSize = sc.nextInt();

                minesForSale[k] = new int[mineSize+2];
                minesForSale[k][0] = minePrice;
                minesForSale[k][1] = mineSize;

                for(int l=2; l<minesForSale[k].length; l++){
                    minesForSale[k][l] = sc.nextInt();
                }
            }
            traverse(0);
            System.out.println("Case #"+(i+1)+": "+(price == 0 ? -1 : price));
        }

    }

    public static void traverse(int start){
        for(int i=start; i<minesForSale.length; i++){
            LinkedList list = buy(i);
            traverse(i+1);
            remove(i, list);
        }
    }

    public static LinkedList buy(int index){
        LinkedList list = new LinkedList();
        if(!purchasedAll){
            boolean usedAtLeastOneMine = false;
            for(int i=2; i<minesForSale[index].length; i++){
                int mine = minesForSale[index][i];
                if(!purchased[mine]){
                    purchased[mine] = true;
                    usedAtLeastOneMine = true;
                    list.add(mine);
                }
            }
            if(usedAtLeastOneMine){
                currentPrice += minesForSale[index][0];
                setPurchasedAll();
            }
        }
        return list;
    }

    public static void remove(int index, LinkedList list){
        if(list.size > 0){
            LinkedList.Node node = list.getHead();
            while(node != null){
                purchased[node.value] = false;
                node = node.next;
            }
            currentPrice -= minesForSale[index][0];
            setPurchasedAll();
        }
    }

    public static void setPurchasedAll(){
        purchasedAll = true;
        for(int i=0; i<minesRequired.length; i++){
            if(!purchased[minesRequired[i]]){
                purchasedAll = false;
                break;
            }
        }
        if(purchasedAll){
            if(price == 0 || currentPrice < price){
                price = currentPrice;
            }
        }
    }

    static class LinkedList {
        int size;
        Node head;
        Node tail;

        public void add(int value){
            Node node = new Node(value);
            if(size == 0){
                head = tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public Node getHead() {
            return head;
        }

        class Node {
            int value;
            Node prev;
            Node next;

            Node(int value){
                this.value = value;
            }
        }
    }
}
