package com.theclcode.hashing.rollinghash;

//Rabin Karp Rolling Hash
public class RollingHash {

    private static final int BASE = 37;
    private int[] powers = {1, BASE, BASE * BASE}; //Supports window sizes up to 3.
    private char[] word = "be there or be square".toCharArray();
    private char[] wordToFind = "e".toCharArray();
    private char[] window = new char[wordToFind.length];
    private int hashToFind = hash(new String(wordToFind));
    private int[] hashes = new int[window.length];

    public void run(){
        int currentHash = 0;
        for(int i=0; (i+window.length)-1<word.length; i++){
            if(i==0){
                for(int j=0, k=window.length-1; j<window.length; j++, k--){
                    window[j]=word[j];
                    hashes[j]=hash(window[j], k);
                }
                currentHash = sum(hashes);
            } else {
                currentHash -= hashes[0];
                currentHash *= BASE;
                for(int j=1; j<window.length; j++){
                    window[j-1]=window[j];
                    hashes[j-1]=hashes[j]*BASE;
                }
                window[window.length-1] = word[i];
                hashes[window.length-1] = hash(word[i+window.length-1], 0);
                currentHash+=hashes[window.length-1];
            }
            if(currentHash == hashToFind){
                System.out.println("Found at index: " + i);
            }
        }
    }

    private int sum(int[] arr){
        int sum = 0;
        for(int value : arr){
            sum += value;
        }
        return sum;
    }

    private int hash(String word){
        int hash = 0;
        for(int i=0, y=window.length-1; i<word.length(); i++, y--){
            hash+=hash(word.charAt(i), y);
        }
        return hash;
    }

    private int hash(char letter, int order){
        return letter*powers[order];
    }

    public static void main(String[] args) {
        new RollingHash().run();
    }
}
