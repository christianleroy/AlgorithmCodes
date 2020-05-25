package com.theclcode.datastructures;

public class Trie {

    public static void main(String[] args) {
        Node<String> trie = new Node<>();
        trie.put("christian".toCharArray(), "Castillo");
        trie.put("ch".toCharArray(), "bachelor");
        trie.put("can".toCharArray(), "joji");
        trie.put("null".toCharArray(), null);

        System.out.println(trie.get("christian".toCharArray()));
        System.out.println(trie.get("ch".toCharArray()));
        System.out.println(trie.get("can".toCharArray()));
        System.out.println(trie.get("null".toCharArray()));
    }


    static class Node<E>{
        E value;
        Node<E>[] nodes;

        Node(){
            nodes = new Node[26];
        }

        void put(char[] key, E value){
            Node<E> node = createNodes(key);
            node.value = value;
        }

        E get(char[] key){
            Node<E> node = getNode(key);
            return node == null ? null : node.value;
        }

        Node<E> createNodes(char[] key){
            Node<E> node = this;
            for(int i=0; i<key.length; i++){
                int index = key[i]-97;
                if(node.nodes[index] == null){
                    node.nodes[index] = new Node<>();
                }
                node = node.nodes[index];
            }
            return node;
        }

        Node<E> getNode(char[] key){
            Node<E> node = this;
            for(int i=0; i<key.length; i++){
                int index = key[i]-97;
                if(node.nodes[index] == null){
                    return null;
                }
                node = node.nodes[index];
            }
            return node;
        }
    }

}
