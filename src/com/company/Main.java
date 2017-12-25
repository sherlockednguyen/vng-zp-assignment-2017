package com.company;

import com.company.model.ConcurrentArray;
import com.company.thread.Worker1;
import com.company.thread.Worker2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {

    private static final int NUM_THREAD = 1000;
    private static final int ARRAY_SIZE = 50;
    public static final int PROCESS_TIMES = 50;
    public static final int SLEEP_TIME = 100;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println("Application Started");
        System.out.println("===================");

        runAppUsingJavaAtomicIntegerArray();

//        runAppUsingCustomConcurrentArray();

        long stopTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (stopTime - startTime));
    }

    private static void runAppUsingJavaAtomicIntegerArray() throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(ARRAY_SIZE);
        List<Worker1> workers = new ArrayList<>();

        //Init and start Thread
        for(int i = 0; i < NUM_THREAD; i++){
            Worker1 w = new Worker1("Worker1 " + i, i, atomicIntegerArray);
            w.start();
            workers.add(w);
        }

        //Wait
        for(int i = 0; i < NUM_THREAD; i++){
            workers.get(i).join();
        }

        System.out.println("Array after processed: " + atomicIntegerArray);
    }

    private static void runAppUsingCustomConcurrentArray() throws InterruptedException {
        ConcurrentArray concurrentArray = new ConcurrentArray(ARRAY_SIZE);
        List<Worker2> workers = new ArrayList<>();

        //Init and start Thread
        for(int i = 0; i < NUM_THREAD; i++){
            Worker2 w = new Worker2("Worker1 " + i, i, concurrentArray);
            w.start();
            workers.add(w);
        }

        //Wait
        for(int i = 0; i < NUM_THREAD; i++){
            workers.get(i).join();
        }

        System.out.println("Array after processed: " + concurrentArray);

    }

}
