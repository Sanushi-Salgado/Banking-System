package com.bankingsystem;

public class University extends Thread {

    /*Contains information for the University, i.e. the thread group it is in; student's bank
      account details; the University name.*/
    private String universityName;
    private CurrentBankAccount studentBankAcount;


    public University(ThreadGroup threadGroup, String threadName, CurrentBankAccount bankAccount){
        super(threadGroup, threadName);
        this.universityName = threadName;
        this.studentBankAcount = bankAccount;
    }

    public String getUniversityName(){
        return this.universityName;
    }

    public void setUniversityName(String universityName){
        this.universityName = universityName;
    }

    @Override
    public void run() {
        System.out.println("\n########################## " + this.getUniversityName() + " starting to perform transactions" +
                " ######################\n");

        int numberOfWithdrawals = 3;

        // Make 3 appropriate course fee withdrawals from student's account.
        for (int i = 0; i < numberOfWithdrawals; i++) {
            Transaction courseFee = new Transaction(this.getUniversityName(), 10000, Transaction.TransactionTypes.WITHDRAWAL);
            studentBankAcount.withdrawal(courseFee);

            // Sleep for a random amount of time between each transaction
            ThreadManager.sleepThread();
        }

        System.out.println("\n############################ " + this.getUniversityName() + " terminating transactions " +
                "############################\n");
    }

}
