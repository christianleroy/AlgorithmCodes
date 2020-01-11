package com.theclcode.test;

import java.util.Arrays;

public class Sandbox {

    public static void main(String[] args) {

        int[] arr = {15,13,1,2,5,13,12,10,9,7,9,8,4,4,3,2,0,1,7,-32, 32,-1, 4, 5,9, 10, 12, -12, -12, -11, 40};
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
//        Word word1 = new Word("abc".toCharArray());
//        Word word2 = new Word("dbc".toCharArray());
//        Word word3 = new Word("d".toCharArray());
//        Word word4 = new Word("bbb".toCharArray());
//        Word word5 = new Word("zzzzz".toCharArray());
//        Word word6 = new Word("z".toCharArray());
//
//        LinkedList<Word> list = new LinkedList<>();
//        list.add(word1);
//        list.add(word2);
//        list.add(word3);
//        list.add(word4);
//        list.add(word5);
//        list.add(word6);
//        word6 = new Word("a".toCharArray());
//        list.add(word6);
//        word6 = new Word("zzzzzzzzzzzzzzzzzzzzzzz".toCharArray());
//        word6.count = 65;
//        list.add(word6);
//        System.out.println();

    }

    static void mergeSort(int[] arr, int start, int end){
        if(start < end){
            int middle = (start+end) / 2;
            mergeSort(arr, start, middle);
            mergeSort(arr, middle+1, end);
            merge(arr, start, middle, end);
        }
    }

    private static void merge(int[] arr, int start, int middle, int end) {
        int[] left = new int[(middle-start) + 1];
        int[] right = new int[(end+1) - (middle+1)];

        for(int i=0; i<left.length; i++){
            left[i] = arr[start+i];
        }
        for(int j=0; j<right.length; j++){
            right[j] = arr[middle+1+j];
        }

        for(int index=start, i=0, j=0; index<=end; index++){
            if(j == right.length || (i < left.length && left[i] <= right[j])){
                arr[index] = left[i++];
            } else {
                arr[index] = right[j++];
            }
        }
    }

    static void insertionSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int currVal = arr[i];
            int prev = i-1;
            while(prev >= 0 && currVal <= arr[prev]){
                arr[prev+1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = currVal;
        }
    }

    static class Word {
        char[] value;
        int hash;
        static final int BASE = 37;
        static final int[] POWERS = {1, BASE, BASE * BASE};
        int count;

        Word(char[] value){
            this.value = new char[value.length];
            for(int i=0; i<value.length && value[i] != '\0'; i++){
                this.value[i] = value[i];
            }
            setHash();
        }

        public boolean same(Object obj) {
            Word compare = (Word) obj;
            if(this.hash == compare.hash){
                boolean isEqual = true;
                for(int i=0; i<value.length; i++){
                    if(value[i] == '\0' && compare.value[i] == '\0'){
                        break;
                    }
                    if(value[i] != compare.value[i]){
                        isEqual = false;
                        break;
                    }
                }
                if(isEqual){
                    return true;
                }
            }
            return false;
        }

        void setHash(){
            int hash = 0;
            for(int i=0, y=2; i<this.value.length && this.value[i] != '\0'; i++, y--){
                if(i < 3){
                    hash += this.value[i] * POWERS[y];
                } else {
                    hash += this.value[i];
                }
            }
            this.hash = hash;
        }
    }

    static class LinkedList<E> {
        int size;
        Node<E> head;
        Node<E> tail;

        public void add(E value){
            Node<E> node = new Node<>(value);
            if(head == null){
                head = tail = node;
            } else {
                if(value instanceof Word){
                    Word word = (Word) value;
                    Integer wordCount = word.count;
                    Node<E> existing = head;
                    while(existing != null){
                        Word existingWord = (Word) existing.value;
                        Integer existingWordCount = existingWord.count;
                        boolean inserted = false;
                        if(wordCount == existingWordCount){
                            char[] wordVal = word.value;
                            char[] exWordVal = existingWord.value;
                            for(int i=0; i<wordVal.length && i<exWordVal.length; i++){
                                if(wordVal[i] <= exWordVal[i]){
                                    if(wordVal[i] == exWordVal[i]){
                                        if((i == wordVal.length-1 || wordVal[i+1] == '\0')
                                                && (i < exWordVal.length-1 && exWordVal[i+1] != '\0')){
                                        } else{
                                            continue;
                                        }
                                    }
                                    node.next = existing;
                                    node.prev = existing.prev;
                                    if(existing.prev != null){
                                        existing.prev.next = node;
                                    }
                                    existing.prev = node;
                                    if(existing == head){
                                        head = node;
                                    }
                                    inserted = true;
                                }
                                break;
                            }
                            if(inserted){
                                break;
                            }
                        } else if(wordCount > existingWordCount){
                            node.next = existing;
                            node.prev = existing.prev;
                            if(existing.prev != null){
                                existing.prev.next = node;
                            }
                            if(existing == head){
                                head = node;
                            }
                            existing.prev = node;
                            break;

                        }
                        if(!inserted && existing == tail){
                            tail.next = node;
                            node.prev = tail;
                            tail = node;
                            break;
                        }
                        existing = existing.next;
                    }
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }

            }
            size++;
            if(size > 5){
                tail = tail.prev;
                tail.next = null;
                size--;
            }
        }


        static class Node<E> {
            E value;
            Node<E> prev;
            Node<E> next;

            Node(E value){
                this.value = value;
            }
        }
    }

}
