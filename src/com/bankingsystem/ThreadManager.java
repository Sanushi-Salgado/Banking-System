package com.bankingsystem;

public class ThreadManager {

    // Sleep the thread for a random amount of time
    public static void sleepThread() {
        try {
            int randomTime = (int)(Math.random()  * 1000);
            Thread.sleep(randomTime);
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!! " + Thread.currentThread().getName() + " thread sleeping " +
                    "for : " + randomTime + " ms !!!!!!!!!!!!!!!!!!!!!!!!!\n");
        } catch (InterruptedException e) {
            System.out.println("Error occured in " + Thread.currentThread().getName() + " thread");
            e.printStackTrace();
        }
    }
}