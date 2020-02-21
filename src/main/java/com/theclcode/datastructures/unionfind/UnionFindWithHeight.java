package com.theclcode.datastructures.unionfind;

public class UnionFindWithHeight {

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);

        node2.setParent(node1);
        node4.setParent(node3);

        System.out.println(node2.getParent().value); //1
        System.out.println(node4.getParent().value); //3
        System.out.println(node1.height); //2
        System.out.println(node3.height); //2
        node1.setParent(node4);
        System.out.println(node1.getParent().value); //3
        System.out.println(node2.getParent().value); //3
        System.out.println(node3.getParent().value); //3
        System.out.println(node4.getParent().value); //3
        System.out.println(node3.getParent().height); //3

        node6.setParent(node5);
        System.out.println(node5.getParent().value); //5
        System.out.println(node6.getParent().value); //5

        node1.setParent(node5);
        System.out.println(node1.getParent().value); //3

    }

    static class Node<E> {
        E value;
        Node<E> parent;
        int height = 1;
        int size = 1;

        public Node(E value){
            this.value = value;
        }

        public Node<E> getParent(){
            if(parent == null){
                return this;
            }
            return parent.getParent();
        }

        public void setParent(Node<E> node){
            Node<E> nodeParent = node.getParent();
            Node<E> thisParent = getParent();
            if(thisParent != nodeParent){
                if(thisParent.height <= nodeParent.height){
                    nodeParent.size += thisParent.size;
                    if(thisParent.height == nodeParent.height){
                        nodeParent.height++;
                    }
                    thisParent.parent = nodeParent;

                } else {
                    node.setParent(this);
                }
            }
        }
    }
}
