package com.theclcode.datastructures.binarytree;

import java.util.Comparator;

//Incomplete
public class BinarySearchTreeComparator<E> {

    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTreeComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E value) {
        Node<E> node = new Node<>(value, this.comparator);
        if (this.root == null) {
            this.root = node;
        } else {
            if(!this.find(value)){
                this.root.findLocation(node);
            }
        }
    }

    public boolean find(E value) {
        return this.root.find(value);
    }

    public void printList() {
        this.root.printList();
    }

    public void delete(E value) {
            /*
            1. If  no children, just derefence.
            2. If has one child, the child takes old parent's spot
            3. If two children, go right, then find lowest value
             */
        if (this.root == null) {
            return;
        }

        Node<E> parent = null;
        Node<E> node = this.root;
        while (node != null) {
            if (node.value.equals(value)) {
                if (parent != null) {
                    boolean isLeft = this.comparator.compare(parent.value, value) > 0;
                    if (node.left != null && node.right != null) {

                    } else if (node.left != null) {

                    } else if(node.right != null){

                    } else {

                    }
                }
            } else {
                parent = node;
                if (this.comparator.compare(node.value, value) > 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }

    }

    static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;
        Comparator<E> comparator;

        Node(E value, Comparator<E> comparator) {
            this.value = value;
            this.comparator = comparator;
        }

        void findLocation(Node<E> node) {
            if (this.comparator.compare(this.value, node.value) > 0) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.findLocation(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.findLocation(node);
                }
            }
        }

        void printList() {
            if (this.left != null) {
                this.left.printList();
            }
            System.out.println(this.value);
            if (this.right != null) {
                this.right.printList();
            }
        }

        boolean find(E value) {
            if (this.comparator.compare(this.value, value) == 0) {
                return true;
            } else if (this.comparator.compare(this.value, value) > 0) {
                return this.left != null && this.left.find(value);
            } else {
                return this.right != null && this.right.find(value);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeComparator<Integer> i = new BinarySearchTreeComparator<>(Integer::compare);
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
}
