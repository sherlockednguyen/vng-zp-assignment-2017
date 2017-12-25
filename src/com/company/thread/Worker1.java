package com.company.thread;

import com.company.Main;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Worker1 extends Thread {


    private AtomicIntegerArray array;
    private int seed;

    public Worker1(String name, int seed, AtomicIntegerArray atomicIntegerArray) {
        super(name);
        this.seed = seed;
        this.array = atomicIntegerArray;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Thread " +  this.getName() + " is started");
        for(int i = 0; i < Main.PROCESS_TIMES; i++){
            int randomIndex = generateRandomIndex();
            int randomValue = generateRandomValue(i);
            System.out.println("[" + this.getName() + "]: is putting value " + randomValue + " in " + randomIndex + " at time " + System.currentTimeMillis());
            this.array.set(randomIndex, randomValue);

            try {
                Thread.sleep(Main.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread " +  this.getName() + " is interrupted");
            }
        }

    }

    private int generateRandomValue(int index){
        // Generate Random Integer Value to put to array
        // Currently it base on seed + index for easy to know where's it from
        return this.seed * 100 + index;
    }

    private int generateRandomIndex(){
        // Generate Random Index to use in the array
        return ThreadLocalRandom.current().nextInt(0, this.array.length());
    }
}
