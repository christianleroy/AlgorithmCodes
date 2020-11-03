package com.theclcode.test;

import java.util.Comparator;

public class Sandbox {


    public static void main(String[] args) {
        BinarySearchTree<Integer> i = new BinarySearchTree<>(Integer::compare);
        i.insert(15);
        i.insert(5);
        i.insert(21);
        i.insert(22);
        i.insert(2);
        i.insert(6);
        i.printList();

        System.out.println(i.find(15));
        System.out.println(i.find(5));
        System.out.println(i.find(21));
        System.out.println(i.find(22));
        System.out.println(i.find(2));
        System.out.println(i.find(6));

        System.out.println(i.find(-2));
        System.out.println(i.find(69));
        System.out.println(i.find(74));
        System.out.println(i.find(12));
        System.out.println(i.find(55));
        System.out.println(i.find(212));

        i.insert(212);
        System.out.println(i.find(212));

    }

    static class BinarySearchTree<E> {

        Node<E> root;
        Comparator<E> comparator;

        BinarySearchTree(Comparator<E> comparator){
            this.comparator = comparator;
        }

        public void insert(E value){
            Node<E> node = new Node<>(value);
            if(this.root == null){
                this.root = node;
            } else {
                this.root.findLocation(node, this.comparator);
            }
        }

        public boolean find(E value){
            return this.root.find(value, this.comparator);
        }

        public void printList(){
            this.root.printList();
        }

        class Node<E>{
            E value;
            Node<E> left;
            Node<E> right;

            Node(E value){
                this.value = value;
            }

            void findLocation(Node<E> node, Comparator<E> comparator){
                if(comparator.compare(this.value, node.value) > 0){
                    if(this.left == null){
                        this.left = node;
                    } else {
                        this.left.findLocation(node, comparator);
                    }
                } else {
                    if(this.right == null){
                        this.right = node;
                    } else {
                        this.right.findLocation(node, comparator);
                    }
                }
            }

            void printList(){
                if(this.left != null){
                    this.left.printList();
                }
                System.out.println(this.value);
                if(this.right != null){
                    this.right.printList();
                }
            }

            boolean find(E value, Comparator<E> comparator){
                if(comparator.compare(this.value, value) == 0){
                   return true;
                } else if(comparator.compare(this.value, value) > 0){
                    return this.left != null && this.left.find(value, comparator);
                } else {
                    return this.right != null && this.right.find(value, comparator);
                }
            }
        }
    }

}
