package com.theclcode.examples;

import java.util.ArrayList;
import java.util.List;

public class SortedWordLinkedList {

    public static void main(String[] args) {

        LinkedList<Word> words = new LinkedList<>();
        words.add(new Word("abc".toCharArray()));
        words.add(new Word("zzz".toCharArray()));

        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("aac".toCharArray()));
        wordList.add(new Word("ab".toCharArray()));
        wordList.add(new Word("aaccccc".toCharArray()));
        wordList.add(new Word("ccc".toCharArray()));
        wordList.add(new Word("cc".toCharArray()));
        wordList.add(new Word("aaz".toCharArray()));
        wordList.add(new Word("daa".toCharArray()));
        wordList.add(new Word("evv".toCharArray()));
        wordList.add(new Word("aa".toCharArray()));
        wordList.add(new Word("vaz".toCharArray()));
        wordList.add(new Word("aaaa".toCharArray()));
        wordList.add(new Word("aaaaa".toCharArray()));
        wordList.add(new Word("aaaa".toCharArray()));
        wordList.add(new Word("zz".toCharArray()));

        for(Word word : wordList) {
            LinkedList.Node<Word> existing = words.getHead();
            LinkedList.Node<Word> newWord = new LinkedList.Node<>(word);
            boolean inserted = false;
            while (existing != null) {
                char[] existingArr = existing.value.getValue();
                char[] newArr = word.getValue();
                for(int i=0; i<newArr.length && i<existingArr.length; i++){
                    if(newArr[i] <= existingArr[i]){
                        if(newArr[i] == existingArr[i]){
                            if((i == newArr.length-1 || newArr[i+1] == '\0')
                                    && (i < existingArr.length-1 && existingArr[i+1] != '\0')){
                            } else {
                                continue;
                            }
                        }
                        newWord.next = existing;
                        newWord.prev = existing.prev;
                        if(existing.prev != null){
                            existing.prev.next = newWord;
                        }
                        existing.prev = newWord;
                        if(existing == words.getHead()){
                            words.setHead(newWord);
                        }
                        inserted = true;
                    }
                    break;
                }
                if(inserted){
                    break;
                }
                existing = existing.next;
            }
            if(!inserted){
                words.getTail().next = newWord;
                newWord.prev = words.getTail();
                words.setTail(newWord);
            }
            words.increment();
        }

        while(!words.isEmpty()){
            System.out.println(words.remove());
        }

        System.out.println();
    }

    static class LinkedList<E> {

        Node<E> head;
        Node<E> tail;
        int size;

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

        public void setHead(Node<E> head) {
            this.head = head;
        }

        public Node<E> getHead() {
            return head;
        }

        public void setTail(Node<E> tail) {
            this.tail = tail;
        }

        public Node<E> getTail() {
            return tail;
        }

        public void increment(){
            size++;
        }

        public E remove(){
            if(head == null){
                return null;
            }
            Node<E> node = head;
            head = head.next;
            if(head != null){
                head.prev = null;
            }
            size--;
            return node.value;
        }

        public boolean isEmpty(){
            return size == 0;
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

    static class Word {
        char[] value;

        Word(char[] value){
            this.value = new char[value.length];
            for(int i=0; i<value.length && value[i] != '\0'; i++){
                this.value[i] = value[i];
            }
        }

        public char[] getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new String(value);
        }
    }

}
