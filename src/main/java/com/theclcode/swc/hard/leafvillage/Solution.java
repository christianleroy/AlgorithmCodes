package com.theclcode.swc.hard.leafvillage;

import java.util.Arrays;
import java.util.Scanner;

//Incomplete
public class Solution {

    static int ninjaIndex =1;
    static Ninja[] ninjas = new Ninja[100];

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int testCases = sc.nextInt();
//
//        for(int t=1; t<=testCases; t++){
//            int commands = sc.nextInt();
//            while(commands>0){
//                String action = sc.next();
//                switch(action){
//                    case "D":
//                        int power = sc.nextInt();
//                        int age = sc.nextInt();
//                        addNinja(age, power);
//                        break;
//                    case "P":
//                        findMaster(sc.nextInt());
//                        break;
//                    default:
//                }
//                commands--;
//            }
//        }

        ninjas[(ninjaIndex++)-1] = new Ninja(1,2, 40);
        ninjas[(ninjaIndex++)-1] = new Ninja(2, 1, 30);
        ninjas[(ninjaIndex++)-1] = new Ninja(3, 1, 10);
        ninjas[(ninjaIndex++)-1] = new Ninja(3, 3, 20);
        ninjas[(ninjaIndex++)-1] = new Ninja(3, 5, 50);
        ninjas[(ninjaIndex++)-1] = new Ninja(3, 6, 70);
        ninjas[(ninjaIndex++)-1] = new Ninja(3, 1, 80);
        sortNinjas();
        System.out.println(ninjas);
    }

    private static void expandNinjas() {
        Ninja[] newNinjas = new Ninja[ninjas.length+100];
        for(int i=0; i<ninjas.length; i++){
            newNinjas[i]=ninjas[i];
        }
        ninjas=newNinjas;
    }

    public static void addNinja(int age, int power){
        if(ninjaIndex-1>=ninjas.length){
            expandNinjas();
        }
        Ninja ninja = new Ninja(ninjaIndex, age, power);
        ninjas[ninjaIndex-1]=ninja;
        ninjaIndex++;
    }


    public static void findMaster(int ninjaId){
        if(ninjas[ninjaId-1]==null){
            System.out.print("NE ");
            return;
        }
        Ninja ninja = ninjas[ninjaId-1];
        Ninja masterNinja = null;
        int masterNinjaAgeDifference = Integer.MAX_VALUE;
        int masterNinjaPowerDifference = Integer.MAX_VALUE;

    }

    public static void sortNinjas(){
        for(int i=1; i<ninjaIndex-1; i++){
            Ninja key = ninjas[i];
            int j = i-1;
            int keyAge = key.getAge();
            int keyPower = key.getPower();
            while(j>=0 && keyAge<=ninjas[j].getAge()){
                if(keyAge==ninjas[j].getAge()){
                    if(keyPower<ninjas[j].getPower()){
                        ninjas[j+1]=ninjas[j];
                        ninjas[j]=key;
                    }
                    j--;
                    continue;
                } else {
                    if(keyAge<ninjas[j].getAge()){
                        ninjas[j+1]=ninjas[j];
                    }
                }
                j--;
            }
            ninjas[j+1] = key;
        }
    }

    public static class Ninja {
        private int id;
        private int age;
        private int power;

        public Ninja(int id, int age, int power) {
            this.id=id;
            this.age=age;
            this.power=power;
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public int getPower() {
            return power;
        }
    }

}
