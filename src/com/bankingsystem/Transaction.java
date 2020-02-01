package com.bankingsystem;

public class Transaction {

    // Possible transaction types that can occur to a bank account
    enum TransactionTypes {
        DEPOSIT, WITHDRAWAL
    }

    private final String customerID;
    private final int amount;
    private TransactionTypes transactionType;


    public Transaction( String customerID, int amount, TransactionTypes transactionType )
    {
        this.customerID  = customerID;
        this.amount = amount;
        this.transactionType = transactionType;
        System.out.println("\n" + this.getCustomerID() + " makes a " + this.getTransactionType() + " of: " + this.getAmount());
    }

    // Get the customer id of the customer who did the transaction
    public String getCustomerID() {
        return this.customerID;
    }

    // Get the amount deposit or withdrew via the transaction
    public int getAmount() {
        return this.amount;
    }

    public TransactionTypes getTransactionType(){
        return this.transactionType;
    }

    @Override
    public String toString( )
    {
        return  new String( "[ " + "Customer: " + this.customerID + ", " + "Amount: "   + this.amount + "]") ;
    }

}