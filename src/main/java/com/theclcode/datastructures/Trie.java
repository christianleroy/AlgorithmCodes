package com.theclcode.datastructures;

public class Trie {

    Node root = new Node();

    public void add(char[] string){

    }

    public boolean isMember(char[] value){
        Node node = root;
        for(int i=0; i<value.length; i++){
            if(node.children[value[i]-'a'] != null){
                node = node.children[value[i]-'a'];
            } else {
                return false;
            }
        }
        return true;
    }

    public void remove(char[] string){

    }


    class Node {
        Node[] children;
        boolean isWord;

        Node(){
            children = new Node[26];
        }
    }

}
