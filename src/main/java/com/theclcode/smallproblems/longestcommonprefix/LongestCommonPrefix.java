package com.theclcode.smallproblems.longestcommonprefix;

public class LongestCommonPrefix {

    //Can be improved so that the trie will only accommodate prefixes that are present in the 1st string
    public static void main(String[] args) {
        String[][] words = {
                {""}, //
                {"a","b",""}, //
                null, //
                {"abc"}, //abc
                {"a", "b", "c"}, //
                {"a", "aa", "aaa"}, //a
                {"ab", "aba", ""," abaaba"}, //
                {"flight", "fight", "fence"}, //f
                {"flight", "flower", "flow"}, //fl
                {"a", "ab", null, "abc"}, //
                {"a", "ab", "abc"} //a
        };
        for(String[] _words: words){
            System.out.format("Case: %s %n", longestCommonPrefix(_words));
        }

    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        Node root = new Node(0);
        int max = 0;
        String best = "";
        boolean isFirst = true;

        for (String word : strs) {
            if(word == null || word.length() == 0){
                return "";
            }
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                if (i == 0 && !isFirst && node.nodes[word.charAt(i) - 'a'] == null) {
                    return "";
                }
                if (node.nodes[word.charAt(i) - 'a'] == null) {
                    node.nodes[word.charAt(i) - 'a'] = new Node(1);
                } else {
                    node.nodes[word.charAt(i) - 'a'].value++;
                }
                node = node.nodes[word.charAt(i) - 'a'];
                if (node.value > 1 && node.value >= max) {
                    max = node.value;
                    best = word.substring(0, i + 1);
                }
                isFirst = false;
            }
        }

        return best;
    }

    static class Node {
        int value;
        Node[] nodes;

        Node(int value) {
            this.value = value;
            this.nodes = new Node[26];
        }
    }
}
