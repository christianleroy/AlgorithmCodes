package com.theclcode.swc.hard.leafvillage;

import java.util.Scanner;


//Timeout Exceeded
public class TimeOutExceedSolution {

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
        if(ninjas[ninjaId-1]==null){
            System.out.print("NE ");
            return;
        }
        Ninja ninja = ninjas[ninjaId-1];
        Ninja masterNinja = null;
        int masterNinjaAgeDifference = Integer.MAX_VALUE;
        int masterNinjaPowerDifference = Integer.MAX_VALUE;
        for(int i=0; i<ninjaIndex-1; i++){
            Ninja candidateMasterNinja = ninjas[i];
            if(candidateMasterNinja==ninja
                    || candidateMasterNinja.getAge()<ninja.getAge()
                    || candidateMasterNinja.getPower()<ninja.getPower()){
                continue;
            }
            if(masterNinja==null){
                masterNinja=candidateMasterNinja;
                masterNinjaAgeDifference = candidateMasterNinja.getAge()-ninja.getAge();
                masterNinjaPowerDifference = candidateMasterNinja.getPower()-ninja.getPower();
            } else {
                if(candidateMasterNinja.getAge()-ninja.getAge()<masterNinjaAgeDifference){
                    masterNinja=candidateMasterNinja;
                    masterNinjaAgeDifference = candidateMasterNinja.getAge()-ninja.getAge();
                    masterNinjaPowerDifference = candidateMasterNinja.getPower()-ninja.getPower();
                } else if(candidateMasterNinja.getAge()-ninja.getAge()==masterNinjaAgeDifference){
                    if(candidateMasterNinja.getPower()-ninja.getPower()<masterNinjaPowerDifference){
                        masterNinja=candidateMasterNinja;
                        masterNinjaAgeDifference = candidateMasterNinja.getAge()-ninja.getAge();
                        masterNinjaPowerDifference = candidateMasterNinja.getPower()-ninja.getPower();
                    }
                }
            }
        }
        if(masterNinja==null){
            System.out.print("NE ");
        } else {
            System.out.print(masterNinja.getId()+" ");
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
