package com.theclcode.datastructures;

public class Stack {
    private int arr[];
    private int capacity;
    private int top=-1;

    public Stack(int size){
        arr = new int[size];
        capacity=size;
    }

    public void pop(){
        if(top>=0){
            arr[top]=0;
            top--;
        }
    }

    public void push(int item){
        if(top<capacity){
            top++;
            arr[top] = item;
        }
    }
}