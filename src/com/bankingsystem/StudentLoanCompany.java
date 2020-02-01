package com.bankingsystem;

public class StudentLoanCompany extends Thread {

    /*Contains information for the loan bankingsystem, i.e. the thread group it is in; the student's
    bank account details; the bankingsystem name.*/
    private String companyName;
    private CurrentBankAccount studentBankAcount;


    public StudentLoanCompany(ThreadGroup threadGroup, String threadName, CurrentBankAccount bankAccount){
        super(threadGroup, threadName);
        this.companyName = threadName;
        this.studentBankAcount = bankAccount;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public void run() {
        System.out.println("\n######################### " + this.getCompanyName() + " starting to perform transactions " +
                "#####################\n");

        int numberOfDeposits = 3;

        // Make 3 student loan deposits into the student's bank account
        for (int i = 0; i < numberOfDeposits; i++) {
            Transaction loan = new Transaction(this.getCompanyName(), 50000, Transaction.TransactionTypes.DEPOSIT);
            studentBankAcount.deposit(loan);

            // Sleep for a random amount of time between each transaction
            ThreadManager.sleepThread();
        }

        System.out.println("\n############################# " + this.getCompanyName() + " terminating transactions " +
                "########################\n");
    }
}
