package com.theclcode.unfinished.leafvillage;

import java.util.Scanner;

//Incomplete
public class Solution {

    public static final String NE = "NE ";
    static int ninjaIndex =1;
    static Ninja[] ninjas = new Ninja[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int t=1; t<=testCases; t++){
            int commands = sc.nextInt();
            while(commands>0){
                String action = sc.next();
                switch(action){
                    case "D":
                        int power = sc.nextInt();
                        int age = sc.nextInt();
                        addNinja(age, power);
                        break;
                    case "P":
                        findMaster(sc.nextInt());
                        break;
                    default:
                }
                commands--;
            }
        }
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
        sortNinjas();
        Ninja ninja=null;
        for(int i=0; i<ninjaIndex-1; i++){
            if(ninjas[i].getId()==ninjaId){
                ninja = ninjas[i];
                if(i>0 && ninjas[i-1].getAge()>=ninja.getAge()){
                    System.out.print(ninjas[i-1].getId()+" ");
                    break;
                } else {
                    while(i<ninjaIndex-1){
                        if(i<ninjaIndex-2){
                            Ninja candidateMasterNinja = ninjas[i+1];
                            if(candidateMasterNinja.getAge()>ninja.getAge() && candidateMasterNinja.getPower()>=ninja.getPower()){
                                System.out.print(candidateMasterNinja.getId()+" ");
                                return;
                            }
                        }
                        i++;
                    }
                }
                System.out.print(NE);
                return;
            }
        }
        if(ninja==null){
            System.out.print(NE);
        }
    }

    public static void sortNinjas(){
        for(int i=1; i<ninjaIndex-1; i++){
            Ninja key = ninjas[i];
            int j = i-1;
            int keyAge = key.getAge();
            int keyPower = key.getPower();
            while(j>=0 && keyAge<=ninjas[j].getAge()){
                if(keyAge==ninjas[j].getAge()){
                    if(keyPower>ninjas[j].getPower()){
                        ninjas[j+1]=ninjas[j];
                        ninjas[j]=key;
                    }
                } else {
                    if(keyAge<ninjas[j].getAge()){
                        ninjas[j+1]=ninjas[j];
                        ninjas[j]=key;
                    }
                }
                j--;
            }
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


