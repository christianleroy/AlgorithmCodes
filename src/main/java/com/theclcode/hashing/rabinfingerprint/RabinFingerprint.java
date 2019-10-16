package com.theclcode.hashing.rabinfingerprint;

import java.util.Arrays;

public class RabinFingerprint {

    final int BASE; //The range of expected characters. I.E, alphabet will give you BASE of 26.
    final int STRING_LIMIT; //The max length expected of String to be hashed.
    long[] powers;

    public RabinFingerprint(int base, int stringLimit){
        BASE = base;
        STRING_LIMIT = stringLimit;
        powers = new long[STRING_LIMIT];
        initPowers();
    }

    private void initPowers() {
        powers[0] = 1;
        powers[1] = BASE;
        for(int i=2; i<STRING_LIMIT; i++){
            powers[i] = powers[i-1]*BASE;
        }
    }

    public static void main(String[] args) {
        RabinFingerprint rabinFingerprint = new RabinFingerprint(52, 12);
        String[] mgaWords = new String[]{"Sana all", "sana mall", "sana Halls", "Halls sana", "pt", "0123456789AB"};
        Arrays.stream(mgaWords).forEach(
            word -> System.out.println(rabinFingerprint.hash(word))
        );
    }

    public int hash(String word){
        int mod = Integer.MAX_VALUE;
        long hash=0;
        for(int i=0, j=word.length()-1; i<word.length(); i++, j--){
            hash+=(word.charAt(i)*powers[j]);
        }
        return (int)(hash%mod);
    }
}
