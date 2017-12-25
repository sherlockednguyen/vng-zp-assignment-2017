package com.company.model;

import java.util.Arrays;

public class ConcurrentArray {
    private int[] innerArray;
    private Object[] mutexArray;

    public ConcurrentArray(int size) {
        innerArray = new int[size];
        mutexArray = new Object[size];

        //Init mutex array
        for(int i = 0; i < size; i++){
            Object mutex = new Object();
            mutexArray[i] = mutex;
        }
    }

    public void set(int index, int value){
        //Synchronize only mutex at index, not block the whole array
        synchronized (mutexArray[index]){
            innerArray[index] = value;
        }
    }

    public int length(){
        return innerArray.length;
    }

    @Override
    public String toString() {
        return "ConcurrentArray{" +
                "innerArray=" + Arrays.toString(innerArray) +
                '}';
    }
}
