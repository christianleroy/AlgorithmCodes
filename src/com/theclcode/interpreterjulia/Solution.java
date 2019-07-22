package com.theclcode.interpreterjulia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


//Solved, but try implementing without regex
public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        sc.nextLine();

        for(int testCase = 1; testCase <= T; testCase++)
        {
            String sentences = sc.nextLine();
            String text =sc.nextLine();
            findNames(Integer.parseInt(sentences), text, testCase);
            System.out.println("");
        }
    }

    public static void _findNames(int sentences, String text, int testCase){
        System.out.print("#"+testCase+" ");
        int count=0;
        for(char c: text.toCharArray()){
            boolean newWord=true;
            if(c>=65 && c<=90 && newWord){
                newWord=false;
            } else {

            }
        }
    }

    public static void findNames(int sentences, String text, int testCase){
        System.out.print("#"+testCase+" ");
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(".*?[!?.,]");
        java.util.regex.Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            pattern = java.util.regex.Pattern.compile("\\b[A-Z][a-z]*[!?.,]*\\b");
            text = matcher.group();
            java.util.regex.Matcher _matcher = pattern.matcher(text);
            int count=0;
            while(_matcher.find()){
                count++;
            }
            System.out.print(count+" ");
        }
    }

}
