package com.company.thread;

import com.company.Main;
import com.company.model.ConcurrentArray;

import java.util.concurrent.ThreadLocalRandom;

public class Worker2 extends Thread {

    private ConcurrentArray array;
    private int seed;

    public Worker2(String name, int seed, ConcurrentArray concurrentArray) {
        super(name);
        this.seed = seed;
        this.array = concurrentArray;
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
