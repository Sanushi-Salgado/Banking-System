package com.bankingsystem;

import java.util.ArrayList;

public class Student extends Thread{

    /* Contains information for a student, i.e. the thread group its in; their bank account;
       their name. */
    private String studentName;
    private CurrentBankAccount studentBankAcount;


    public Student(ThreadGroup threadGroup, String threadName, CurrentBankAccount bankAccount){
        super(threadGroup, threadName);
        this.studentName = threadName;
        studentBankAcount = bankAccount;
    }

    public String getStudentName(){
        return this.studentName;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    @Override
    public void run() {
        System.out.println("\n######################## " + this.getStudentName() + " starting to perform transactions " +
                "#######################\n");

        // Student transactions
        Transaction winLottery = new Transaction(this.getStudentName(), 1000000, Transaction.TransactionTypes.DEPOSIT);
        Transaction buyPhone = new Transaction(this.getStudentName(), 30000, Transaction.TransactionTypes.WITHDRAWAL);
        Transaction payHouseRent = new Transaction(this.getStudentName(), 10000, Transaction.TransactionTypes.WITHDRAWAL);
        Transaction goShopping = new Transaction(this.getStudentName(), 4000, Transaction.TransactionTypes.WITHDRAWAL);
        Transaction savedPocketMoney = new Transaction(this.getStudentName(), 3000, Transaction.TransactionTypes.DEPOSIT);
        Transaction partTimeJob = new Transaction(this.getStudentName(), 6000, Transaction.TransactionTypes.DEPOSIT);


        ArrayList<Transaction> studentTransactions = new ArrayList();
        studentTransactions.add(winLottery);
        studentTransactions.add(buyPhone);
        studentTransactions.add(payHouseRent);
        studentTransactions.add(goShopping);
        studentTransactions.add(savedPocketMoney);
        studentTransactions.add(partTimeJob);


        for (Transaction transaction: studentTransactions) {
            // If the transaction is a deposit
            if(transaction.getTransactionType() == Transaction.TransactionTypes.DEPOSIT)
                studentBankAcount.deposit( transaction );
            else
                studentBankAcount.withdrawal( transaction );

            // Sleep for a random amount of time between each transaction
            ThreadManager.sleepThread();
        }

        // After completing the transactions, print out the final bank statement.
        studentBankAcount.printStatement();

        System.out.println("\n############################## " + this.getStudentName() + " terminating transactions" +
                " ########################\n");
    }


}