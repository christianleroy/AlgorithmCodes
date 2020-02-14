package com.theclcode.datastructures;

public class UnionFind {


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node3.setParent(node1);
        System.out.println(node3.getParent().id); //1
        node5.setParent(node3);
        System.out.println(node5.getParent().id); //1
        System.out.println(node1.size); //3
        node4.setParent(node2);
        node6.setParent(node2);
        node7.setParent(node1);
        System.out.println(node7.size); //1
        System.out.println(node2.size); //3
        System.out.println(node1.size); //4;
        System.out.println(node7.getParent().size); //4
        System.out.println(node4.getParent().id); //2
        System.out.println(node6.getParent().id); //2
        System.out.println(node7.getParent().id); //1
        node7.setParent(node5);
        System.out.println(node7.getParent().id); //1
        node2.setParent(node3);
        System.out.println(node2.getParent().id); //1
        System.out.println(node4.getParent().id); //1
        System.out.println(node6.getParent().id); //1
        System.out.println(node1.getParent().id); //1
        System.out.println(node1.size); //7
        node1.setParent(node1);
        node2.setParent(node1);
        node3.setParent(node1);
        node4.setParent(node1);
        node5.setParent(node1);
        node6.setParent(node1);
        node7.setParent(node1);
        System.out.println(node1.size); //7
    }

    static class Node {
        Node parent;
        int id;
        int size=1;

        Node(int id){
            this.id = id;
        }

        public Node getParent() {
            if(parent == null){
                return this;
            }
            return parent.getParent();
        }

        public void setParent(Node parent) {
            if(getParent() != parent.getParent()){
                this.parent = parent.getParent();
                this.parent.size += this.size;
            }
        }
    }
}
