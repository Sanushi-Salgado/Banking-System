package com.bankingsystem;

public class Grandmother extends Thread {

    /*Contains information for the grandmother, i.e. the thread group it is in; the student's
    bank account details; the grandmother name. */
    private String grandmotherName;
    private CurrentBankAccount studentBankAcount;


    public Grandmother(ThreadGroup threadGroup, String threadName, CurrentBankAccount bankAccount){
        super(threadGroup, threadName);
        this.grandmotherName = threadName;
        this.studentBankAcount = bankAccount;
    }

    public String getGrandmotherName(){
        return this.grandmotherName;
    }

    public void setGrandmotherName(String grandmotherName){
         this.grandmotherName = getGrandmotherName();
    }

    @Override
    public void run() {
        System.out.println("\n###################### " + this.getGrandmotherName() + " starting to perform transactions" +
                "  #######################\n");

        // Make 2 “top-up gifts”, e.g. birthday & Christmas, deposits into their gandchild's bank account.
        Transaction birthdayGift = new Transaction(this.getGrandmotherName(), 10000, Transaction.TransactionTypes.DEPOSIT);
        studentBankAcount.deposit(birthdayGift);

        // Sleep for a random amount of time between each transaction
        ThreadManager.sleepThread();

        Transaction christmasGift = new Transaction(this.getGrandmotherName(), 15000, Transaction.TransactionTypes.DEPOSIT);
        studentBankAcount.deposit(christmasGift);

        System.out.println("\n######################## " + this.getGrandmotherName() + " terminating transactions " +
                "##########################\n");
    }

}
