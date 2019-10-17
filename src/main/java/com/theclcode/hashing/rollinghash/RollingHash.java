package com.theclcode.hashing.rollinghash;

//Rabin Karp Rolling Hash
public class RollingHash {

    final static int BASE = 37;
    int[] powers = new int[3];
    {
        powers[0] = 1;
        powers[1] = BASE;
        powers[2] = powers[1] * BASE;
    }

    public void run(){
        char[] word = new char[]{'a','a','c','a','a','b','a','a','c','a','a'};
        int windowSize = 3;
        char[] find = new char[]{'c','a','a'};

        for(int i=0; (i+windowSize)-1<word.length; i++){
            char[] window = new char[windowSize];
            for(int j=i,k=0; j<i+windowSize; j++,k++){
                window[k]=word[j];
            }
            if(hash(new String(window)) == hash(new String(find))){
                System.out.println("Found at index: "+i);
            }

        }
    }

    public int hash(String word){
        int hash = 0;
        for(int i=0, y=2; i<word.length(); i++, y--){
            hash+=word.charAt(i)*powers[y];
        }
        return hash;
    }

    public static void main(String[] args) {
        new RollingHash().run();
    }
}
