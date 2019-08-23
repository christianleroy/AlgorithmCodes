package com.theclcode.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args){
       Map<String, Integer> test = new HashMap<>();
       Integer num = test.get("a");
       if(num!=null)
       test.put("a", num+1);
       System.out.println(test.get("a"));
    }
}
