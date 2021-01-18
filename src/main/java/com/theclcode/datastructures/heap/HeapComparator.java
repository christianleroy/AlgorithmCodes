package com.theclcode.datastructures.heap;

import org.omg.SendingContext.RunTime;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class HeapComparator<E> {

    public static void main(String[] args) {

        HeapComparator<Integer> heap = new HeapComparator<>(200, Comparator.comparingInt(e -> e));

        heap.insert(5);
        heap.insert(6);
        heap.insert(21);
        heap.insert(3);
        heap.insert(-2);
        heap.insert(-32);
        heap.insert(9);
        heap.insert(0);
        heap.insert(1);
        heap.insert(10);
        heap.insert(143);
        heap.insert(34);
        heap.insert(222);
        heap.insert(312);
        heap.insert(74);
        heap.insert(901);
        heap.insert(1012);
        heap.insert(-607);

        Integer i;
        while((i = heap.delete()) != null){
            System.out.print(i + " ");
        }
    }

    private Node<E>[] arr;
    private int curr;
    private Comparator<E> comparator;

    HeapComparator(int size, Comparator<E> comparator) {
        this.arr = new Node[size];
        this.comparator = comparator;
    }

    public void insert(E value) {
        if(curr == this.arr.length){
            throw new RuntimeException("Heap is full!");
        }
        int parent = ((curr + 1) / 2) - 1;
        int index = curr++;
        this.arr[index] = new Node<>(value);
        while(parent >= 0 && this.comparator.compare(arr[index].value, arr[parent].value) > 0){
            swap(index, parent);
            index = parent;
            parent = ((index + 1) / 2) - 1;
        }
    }

    public E delete(){
        if(this.arr.length == 0 || this.curr == 0){
            return null;
        }
        final int lastIndex = --curr;
        int index = 0;
        swap(index, lastIndex);

        int left = ((index + 1) * 2) - 1;
        int right = ((index + 1) * 2 + 1) - 1;

        boolean inserted = true;
        while((left < lastIndex || right < lastIndex) && inserted){
            inserted = false;
            if(left < lastIndex && right < lastIndex){
                int max = this.comparator.compare(this.arr[left].value, this.arr[right].value) >= 0 ? left : right;
                if(this.comparator.compare(this.arr[max].value, this.arr[index].value) > 0){
                    inserted = true;
                    swap(max, index);
                    index = max;
                }
            } else if(left < lastIndex && this.comparator.compare(this.arr[left].value, this.arr[index].value) > 0) {
                swap(left, index);
                index = left;
                inserted = true;
            } else if(right < lastIndex && this.comparator.compare(this.arr[right].value, this.arr[index].value) > 0) {
                swap(right, index);
                index = right;
                inserted = true;
            }
            left = ((index + 1) * 2) - 1;
            right = ((index + 1) * 2 + 1) - 1;
        }
        E value = this.arr[lastIndex].value;
        this.arr[lastIndex] = null;
        return value;

    }

    private void swap(int left, int right) {
        Node<E> temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

    }

    private class Node<E> {
        E value;

        Node(E value) {
            this.value = value;
        }

    }
}
