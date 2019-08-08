package com.theclcode.parser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Non algo
public class Solution {

    public static void main(String[] args){
        int cont=1;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter string: ");
            String input = scanner.nextLine();
            Pattern pattern = Pattern.compile("\\b[A-Za-z0-9=,._-]*[,\\]]*\\b");
            Matcher matcher = pattern.matcher(input);
            Map<String, String> json = new HashMap<>();

            while(matcher.find()){
//                System.out.println(matcher.group()+" ");
                pattern = Pattern.compile("\\b[A-Za-z0-9,._-]*[^=]\\b");
                Matcher _matcher = pattern.matcher(matcher.group());
                boolean isKey = true;
                String key="", value="";
                while(_matcher.find()){
                    if(isKey){
                        key = _matcher.group();
                        isKey = false;
                    } else {
                        value = _matcher.group();
                        isKey = true;
                    }
                }
                json.put(key, value);
            }
            JSONObject obj = new JSONObject(json);
            System.out.println(obj);
            System.out.print("1 to continue: ");
            cont = scanner.nextInt();

        }while(cont==1);

        String input = "[name=packet_count_ulflow, table=summary.voma_cellcall_wise-agg-3600-timeline, isPk=false, isTimestamp=false, units=null, isSubscriberId=false, isMetric=true, isDimension=false, isDrillable=false, isTime=false, isVisible=true, isSyntheticBase=false]";


    }
}
