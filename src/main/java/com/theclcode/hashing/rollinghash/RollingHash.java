package com.theclcode.hashing.rollinghash;

//Rabin Karp Rolling Hash
//Unfinished, not yet rolling.
public class RollingHash {

    final static int BASE = 37;
    static int called=0;
    int[] powers = new int[3];
    {
        powers[0] = 1;
        powers[1] = BASE;
        powers[2] = powers[1] * BASE;
    }

    public void run(){
        char[] word = new char[]{'a','a','c','a','c','a','b','c','a','a','a','a','c','a','a'};
        int windowSize = 3;
        char[] find = new char[]{'a','a','c'};
        int[] hashes = new int[3];
        char[] window = new char[windowSize];
        int currentHash = 0;
        for(int i=0; (i+windowSize)-1<word.length; i++){
            if(i==0){
                for(int j=0, k=2; j<windowSize; j++, k--){
                    window[j]=word[j];
                    hashes[j]=hash(window[j], k);
                }
            } else {
                for(int j=1; j<windowSize; j++){
                    window[j-1]=window[j];
                    hashes[j-1]=hashes[j]*BASE;
                }
                window[windowSize-1] = word[i];
                hashes[windowSize-1] = hash(word[i+windowSize-1], 0);
            }
            currentHash = sum(hashes);
            if(currentHash == hash(new String(find))){
                System.out.println("Found at index: " + i);
            }
        }
        System.out.println("Called hash function: "+ called);
    }

    static int sum(int[] arr){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
        }
        return sum;
    }

    public int hash(char letter, int order){
        called++;
        return letter*powers[order];
    }

    public int hash(String word){
        int hash = 0;
        for(int i=0, y=powers.length-1; i<word.length(); i++, y--){
            hash+=word.charAt(i)*powers[y];
        }
        return hash;
    }

    public static void main(String[] args) {
        new RollingHash().run();
    }
}
