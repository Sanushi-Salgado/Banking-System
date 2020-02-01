package com.bankingsystem;

// Defines the basic interface to a bank account
public interface BankAccount {

    // Returns the Account number
    int getAccountNumber();

    // Returns the Account holder
    String getAccountHolder();

    // Returns the current balance
    int getAccountBalance();

    // Perform a deposit transaction on the bank account
    void deposit( Transaction transaction );

    // Perform a withdrawal transaction on the bank account
    void withdrawal( Transaction transaction );

    // Returns true if overdrawn, returns false otherwise
    boolean isOverdrawn();

    // Prints out the transactions performed so far
    void printStatement();

}