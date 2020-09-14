package com.theclcode.unfinished.schooltrouble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SchoolTrouble {


    /**************** START OF USER SOLUTION ****************/

    private static Node[] kids;

    private static void init(int N) {
        kids = new Node[N+1];
    }

    private static boolean setFriends(int kid1, int kid2) {
        Node k1 = kids[kid1] != null ? kids[kid1] : (kids[kid1] = new Node(kid1));
        Node k2 = kids[kid2] != null ? kids[kid2] : (kids[kid2] = new Node(kid2));

        if(k1 == k2){
            return true;
        }

        Node k1Parent = k1.getParent();
        Node k2Parent = k2.getParent();

        if(k1Parent != k2Parent && !areEnemies(k1Parent.value, k2Parent.value)){
            if(k1Parent.getEnemy() != null && k2Parent.getEnemy() != null){
                k1Parent.getEnemy().absorb(k2Parent.getEnemy());
                k1Parent.absorb(k2Parent);
            } else if(k1Parent.getEnemy() != null){
                k1Parent.absorb(k2Parent);
            } else {
                k2Parent.absorb(k1Parent);
            }
            return true;
        }
        return false;
    }

    private static boolean setEnemies(int kid1, int kid2) {
        Node k1 = kids[kid1] != null ? kids[kid1] : (kids[kid1] = new Node(kid1));
        Node k2 = kids[kid2] != null ? kids[kid2] : (kids[kid2] = new Node(kid2));

        if(k1 == k2){
            return false;
        }

        Node k1Parent = k1.getParent();
        Node k2Parent = k2.getParent();

        if(k1Parent != k2Parent && !areFriends(k1Parent.value, k2Parent.value)){
            if(k1Parent.getEnemy() != null && k2Parent.getEnemy() != null){
                k1Parent.getEnemy().absorb(k2Parent);
                k2Parent.getEnemy().absorb(k1Parent);
            } else if(k1Parent.getEnemy() != null){
                k1Parent.getEnemy().absorb(k2Parent);
            } else if(k2Parent.getEnemy() != null){
                k2Parent.getEnemy().absorb(k1Parent);
            } else {
                k1Parent.setEnemy(k2Parent);
                k2Parent.setEnemy(k1Parent);
            }
            return true;
        }
        return false;
    }

    private static boolean areFriends(int kid1, int kid2) {
        Node k1 = kids[kid1] != null ? kids[kid1] : (kids[kid1] = new Node(kid1));
        Node k2 = kids[kid2] != null ? kids[kid2] : (kids[kid2] = new Node(kid2));

        if(k1 == k2){
            return true;
        }
        Node k1Parent = k1.getParent();
        Node k2Parent = k2.getParent();

        return k1Parent == k2Parent;
    }

    private static boolean areEnemies(int kid1, int kid2) {
        Node k1 = kids[kid1] != null ? kids[kid1] : (kids[kid1] = new Node(kid1));
        Node k2 = kids[kid2] != null ? kids[kid2] : (kids[kid2] = new Node(kid2));

        if(k1 == k2){
            return false;
        }

        Node k1Parent = k1.getParent();
        Node k2Parent = k2.getParent();

        return k1Parent.getEnemy() == k2Parent || k2Parent.getEnemy() == k1Parent;
    }

    static class Node {
        int value;
        Node parent;
        Node enemy;

        Node(int value){
            this.value = value;
        }

        public Node getParent() {
            if(this.parent == null) {
                return this;
            }
            return this.parent;
        }

        Node getEnemy(){
            return this.getParent().enemy;
        }

        public void setEnemy(Node other){
            this.enemy = other;
        }

        public void absorb(Node other){
            Node thisParent = getParent();
            Node otherParent = other.getParent();
            if(thisParent != otherParent){
                otherParent.parent = thisParent;
            }
        }
    }

    /***************** END OF USER SOLUTION *****************/

    private static final int SET_FRIENDS = 100;
    private static final int SET_ENEMIES = 200;
    private static final int ARE_FRIENDS = 300;
    private static final int ARE_ENEMIES = 400;

    private static void run(BufferedReader br) throws Exception {
        StringTokenizer st;

        int L, N;
        int kid1, kid2;
        int cmd;

        boolean ret = false;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        init(N);

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            kid1 = Integer.parseInt(st.nextToken());
            kid2 = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case SET_FRIENDS:
                    ret = setFriends(kid1, kid2);
                    break;
                case SET_ENEMIES:
                    ret = setEnemies(kid1, kid2);
                    break;
                case ARE_FRIENDS:
                    ret = areFriends(kid1, kid2);
                    break;
                case ARE_ENEMIES:
                    ret = areEnemies(kid1, kid2);
                    break;
            }

            System.out.println(ret);
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("Case #" + testcase + ":");
            run(br);
        }
    }

}
