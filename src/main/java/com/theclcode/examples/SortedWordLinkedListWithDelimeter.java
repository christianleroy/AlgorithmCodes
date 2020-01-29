package com.theclcode.examples;

public class SortedWordLinkedListWithDelimeter {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(mstrcpy(new char[6], "abc".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "aa".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "zzz".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "zz".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "bbbbb".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "b".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "ccc".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "cccc".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "a".toCharArray()));
        linkedList.add(mstrcpy(new char[6], "zzzzz".toCharArray()));

        LinkedList.Node node = linkedList.getHead();
        while(node != null){
            System.out.println(new String(node.value));
            node = node.next;
        }
    }

    static char[] mstrcpy(char dst[], char src[])
    {
        int c = 0;
        while(c < src.length && (dst[c] = src[c]) != 0) ++c;
        dst[c] = 0;
        return dst;
    }

    static class LinkedList {

        int size;
        Node head;
        Node tail;

        public void add(char[] value){
            Node node = new Node(value);
            if(head == null){
                head = tail = node;
            } else {
                Node existing = head;
                boolean inserted = false;
                while(existing != null){
                    char[] exVal = existing.value;
                    int i=0;
                    boolean equal = true;
                    for(; i < value.length && value[i] != '\0' && exVal[i] != '\0'; i++){
                        if(value[i] == exVal[i]){
                            continue;
                        }
                        equal = false;
                        if(value[i] < exVal[i]){
                            insertNode(node, existing);
                            inserted = true;
                        }
                        break;
                    }
                    if(inserted){
                        break;
                    } else if(equal && value[i] == '\0' && exVal[i] != '\0'){
                        insertNode(node, existing);
                        inserted = true;
                        break;
                    }
                    existing = existing.next;
                }
                if(!inserted){
                    node.prev = tail;
                    tail.next = node;
                    tail = node;
                }
            }
            size++;
        }

        private void insertNode(Node node, Node existing) {
            node.next = existing;
            node.prev = existing.prev;
            if(existing.prev != null){
                existing.prev.next = node;
            }
            existing.prev = node;
            if(existing == head){
                head = node;
            }
        }

        public Node getHead() {
            return head;
        }

        class Node {
            char[] value;
            Node prev;
            Node next;

            Node(char[] value){
                this.value = value;
            }
        }
    }

}
