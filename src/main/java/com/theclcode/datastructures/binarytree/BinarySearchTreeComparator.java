package com.theclcode.datastructures.binarytree;

import java.util.Comparator;

public class BinarySearchTreeComparator<E> {

    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTreeComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E value) {
        System.out.format("Inserting %s. ", value);
        Node<E> node = new Node<>(value, this.comparator);
        if (this.root == null) {
            this.root = node;
        } else {
            if (!this.find(value)) {
                this.root.findLocation(node);
            }
        }
    }

    public boolean find(E value) {
        return this.root.find(value);
    }

    public void printList() {
        System.out.println();
        if(this.root != null){
            this.root.printList();
        } else {
            System.out.println("The BST is empty!");
        }
        System.out.println();
    }

    public void delete(E value) {
        System.out.format("Deleting %s. ", value);
        if (this.root == null) {
            return;
        }

        Node<E> parent = null;
        Node<E> node = this.root;
        boolean deleted = false;
        while (node != null && !deleted) {
            if (node.value.equals(value)) {
                if (node.left != null && node.right != null) {
                    Node<E> replacement = node.right;
                    Node<E> replacementParent = null;
                    while (replacement.left != null) {
                        replacementParent = replacement;
                        replacement = replacement.left;
                    }
                    replaceChild(parent, value, replacement);
                    if (replacementParent != null) {
                        replacementParent.left = replacement.right;
                        replacement.right = node.right;
                    }
                    replacement.left = node.left;
                    deleted = true;
                } else if (node.left != null) {
                    replaceChild(parent, node.value, node.left);
                    deleted = true;
                } else if (node.right != null) {
                    replaceChild(parent, node.value, node.right);
                    deleted = true;
                } else {
                    replaceChild(parent, node.value, null);
                    deleted = true;
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

    private void replaceChild(Node<E> parent, E value, Node<E> replacement) {
        if (parent != null) {
            if (this.comparator.compare(parent.value, value) > 0) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        } else {
            this.root = replacement;
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
            System.out.print(this.value + " ");
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
        BinarySearchTreeComparator<Integer> bst = new BinarySearchTreeComparator<>(Integer::compare);
        bst.insert(100);
        bst.insert(90);
        bst.insert(110);
        bst.insert(109);
        bst.insert(120);

        bst.printList();
        bst.delete(109);
        bst.printList();
        bst.insert(121);
        bst.printList();
        bst.delete(121);
        bst.printList();
        bst.insert(105);
        bst.printList();
        bst.insert(80);
        bst.insert(95);
        bst.printList();
        bst.insert(80);
        bst.insert(95);
        bst.printList();
        bst.delete(90);
        bst.delete(80);
        bst.printList();
        bst.delete(95);
        bst.delete(100);
        bst.printList();
        bst.delete(105);
        bst.printList();
        bst.delete(110);
        bst.printList();
        bst.delete(120);
        bst.printList();

        bst.insert(100);
        bst.insert(90);
        bst.insert(110);
        bst.insert(109);
        bst.insert(120);
        bst.insert(115);
        bst.insert(125);
        bst.insert(118);
        bst.insert(117);
        bst.insert(119);
        bst.printList();
        bst.delete(110);
        bst.printList();
        bst.delete(118);
        bst.printList();
        bst.delete(120);
        bst.delete(117);
        bst.printList();
    }
}
