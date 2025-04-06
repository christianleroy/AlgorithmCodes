package com.theclcode.graph.medium;

import java.util.*;

public class CourseSchedule {

    public static void main(String[] args) {

        int[][] courses = {
                {1, 7},
                {7, 0},
                {1, 0},
                {0, 5}
        };

        System.out.println(canFinish(8, courses));

//        canFinish(8, courses);
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, Node> map = new HashMap<>();

        for(int i = 0; i < numCourses; i ++){
            map.put(i, new Node(i));
        }

        for(int i = 0; i < prerequisites.length; i++) {
            Node node = map.get(prerequisites[i][0]);
            Node prereq = map.get(prerequisites[i][1]);
            node.addPrereq(prereq);
        }

        Set<Integer> explored = new HashSet<>();
        for(Map.Entry<Integer, Node> entry: map.entrySet()) {
            if(!explored.contains(entry.getKey()) &&
                    hasCycle(entry.getValue(), explored, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasCycle(Node node, Set<Integer> visited, Set<Integer> recursionPath) {

        if(recursionPath.contains(node.value)){
            return true; // already in the current path, can confirm cycle exists
        }

        if(visited.contains(node.value)) {
            return false; // explored, no cycle
        }

        recursionPath.add(node.value);
        visited.add(node.value);

        for(Node prereq : node.prereqs) {
            if(hasCycle(prereq, visited, recursionPath)) {
                return true;
            }
        }

        recursionPath.remove(node.value);
        return false;

    }

    static class Node {
        int value;
        private List<Node> prereqs;

        Node(int value) {
            this.value = value;
            this.prereqs = new ArrayList<>();
        }

        void addPrereq(Node node) {
            prereqs.add(node);
        }

    }
}
