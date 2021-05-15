package com.theclcode.smallproblems.validparenthesis;

public class ValidParenthesis {

    public static void main(String[] args) {
        String[] strings = {"{}()[]", "({}{})", "[{]}", "[]({{}})[]", "(){}[]{"}; //true true false true false
        for (String string : strings) {
            boolean test = isValid(string);
            System.out.println(test);
        }
    }

    private static boolean isValid(String string) {
        LinkedList stack = new LinkedList();
        for (int i = 0; i < string.length(); i++) {
            char curr = string.charAt(i);
            if (curr == '(' || curr == '{' || curr == '[') {
                stack.push(curr);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char match = getMatch(curr);
                if (stack.pop() != match) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static char getMatch(char c) {
        switch (c) {
            case '}':
                return '{';
            case ')':
                return '(';
            case ']':
                return '[';
        }
        return 0;
    }

    private static class LinkedList {
        private Node head;
        private int size;

        public void push(char value) {
            Node node = new Node(value);
            if (size == 0) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
            size++;
        }

        public char pop() {
            if (size == 0) {
                return (char) -1;
            }
            Node node = head;
            head = node.next;
            size--;
            return node.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private static class Node {
        private final char value;
        private Node next;

        Node(char value) {
            this.value = value;
        }
    }
}
