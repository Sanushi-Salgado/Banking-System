package com.bankingsystem;

public class BankingSystem {

    // Initial account balance of student's bank account
    int iniitialAccountBalance = 2000;
    CurrentBankAccount bankAccount;

    ThreadGroup threadgroup1;
    ThreadGroup threadGroup2;

    Thread student;
    Thread grandmother;
    Thread loanCompany;
    Thread university;


    public BankingSystem() {

        // Create stdent bank account
        System.out.println("------------------ Creating bank account ---------------");
        bankAccount = new CurrentBankAccount("Sanushi", 11111, iniitialAccountBalance);
        System.out.println("Created bank account with: \n initial balanace: Rs." + bankAccount.getAccountBalance() + "\n " +
                "account holder: " + bankAccount.getAccountHolder() +
                "\n account number: " + bankAccount.getAccountNumber());
        System.out.println("-------------------------------------------------------\n\n");


        // Create thread groups
        System.out.println("------------------ Creating thread groups ---------------");
        threadgroup1 = new ThreadGroup("Thread Group 1(Humans)");
        threadGroup2 = new ThreadGroup("Thread Group 2(Loan Company & University)");
        System.out.println("Created thread groups: \n" + threadgroup1.getName() + "\n" + threadGroup2.getName());
        System.out.println("-------------------------------------------------------\n\n");


        // Create thread for student
        System.out.println("----------------- Creating student thread ---------------");
        student = new Student(threadgroup1, "Student", bankAccount);
        System.out.println("Created student thread");
        System.out.println(student.getName() + " Thread State: " + student.getState());
        System.out.println("-------------------------------------------------------\n\n");


        // Create thread for grandmother
        System.out.println("----------------- Creating grandmother thread ---------------");
        grandmother = new Grandmother(threadgroup1, "Grandmother", bankAccount);
        System.out.println("Created grandmother thread");
        System.out.println(grandmother.getName() + " Thread State: " + grandmother.getState());
        System.out.println("-------------------------------------------------------\n\n");


        // Create thread for student loan bankingsystem
        System.out.println("----------------- Creating loan bankingsystem thread ---------------");
        loanCompany = new StudentLoanCompany(threadGroup2, "Loan Company", bankAccount);
        System.out.println("Created loan bankingsystem thread");
        System.out.println(loanCompany.getName() + " Thread State: " + loanCompany.getState());
        System.out.println("-------------------------------------------------------\n\n");


        // Create thread for university
        System.out.println("----------------- Creating university thread ---------------");
        university = new University(threadGroup2, "University", bankAccount);
        System.out.println("Created university thread");
        System.out.println(university.getName() + " Thread State: " + student.getState());
        System.out.println("-------------------------------------------------------\n\n");

    }


    public static void main(String[] args) {

        System.out.println("-------------------------------------- BANKING SYSTEM --------------------------------------\n\n");

        BankingSystem bankingSystem = new BankingSystem();

        // Start all threads
        bankingSystem.student.start();
        bankingSystem.grandmother.start();
        bankingSystem.loanCompany.start();
        bankingSystem.university.start();


        // Wait until all threads terminate
        try {
            bankingSystem.student.join();
            bankingSystem.grandmother.join();
            bankingSystem.loanCompany.join();
            bankingSystem.university.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n############################### All threads are terminated #################################\n\n");

        // Print the final bank account statement
        bankingSystem.bankAccount.printStatement();

    }

}