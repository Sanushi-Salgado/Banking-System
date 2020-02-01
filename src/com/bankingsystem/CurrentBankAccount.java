package com.bankingsystem;

public class CurrentBankAccount implements BankAccount {

    private String accountHolderName;
    private int accountNumber;
    private volatile int accountBalanace;
    private volatile Statement bankStatement;


    public CurrentBankAccount(String accountHolderName, int accountNumber, int accountBalanace) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.accountBalanace = accountBalanace;
        bankStatement = new Statement(accountHolderName, accountNumber);
    }

    @Override
    public String getAccountHolder() {
        return this.accountHolderName;
    }

    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public synchronized int getAccountBalance() {
        return this.accountBalanace;
    }

    @Override
    public synchronized void deposit(Transaction transaction) {
        if(transaction.getAmount() <= 0){
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx INVALID DEPOSIT AMOUNT xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        else if(transaction.getAmount() > 0) {
            // Update the student's bank account balance when a deposit is being made
            this.accountBalanace += transaction.getAmount();

            // Update the bank statement for the student's bank account
            this.bankStatement.addTransaction(transaction.getCustomerID(), transaction.getAmount(), this.accountBalanace,
                    Transaction.TransactionTypes.DEPOSIT);
            System.out.println("------------------------------- DEPOSIT MADE SUCCESSFULLY ------------------------------\n");

            // Wake up all threads waiting for a deposit to occur
            notifyAll();
        }
    }


    @Override
    public synchronized void withdrawal(Transaction transaction) {

        /* withdrawal transactions are only allowed to take place, when the account has
           sufficient funds to cover the withdrawal; otherwise it must wait until the
           funds are available. */

        if (transaction.getAmount() <= 0) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx INVALID WITHDRAWAL AMOUNT xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }

        while (this.getAccountBalance() < transaction.getAmount()) {
            System.out.println("Insufficient account balance remaining for a withdrawal of " + transaction.getAmount()
                    + " . Waiting for deposit(s)...\n");
            try {
                // Wait until another thread calls the notify() or notifyAll()
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error occured in " + Thread.currentThread().getName() + " thread");
                e.printStackTrace();
            }
        }

        if (this.getAccountBalance() >= transaction.getAmount()) {
            // Update the student's bank account balance when a withdrawal is being made
            this.accountBalanace -= transaction.getAmount();
            this.bankStatement.addTransaction(transaction.getCustomerID(), transaction.getAmount(), this.accountBalanace,
                    Transaction.TransactionTypes.WITHDRAWAL);
            System.out.println("------------------------------ WITHDRAWAL MADE SUCCESSFULLY ----------------------------\n");
        }
    }


    @Override
    public boolean isOverdrawn() {
        /* An overdraft occurs when money is withdrawn from a bank account and
           the available balance goes below zero. */
        return this.getAccountBalance() < 0;
    }


    @Override
    public void printStatement() {
       /* Prints out the transactions performed so far or after all the threads have
         terminated, it prints out the final statement for the
         student's bank account.*/
        this.bankStatement.print();
    }


}