package com.bankingsystem;

public class StatementEntry {

    private final String customerID ;
    private final int amount ;
    private final int currentBalance ;
    private Transaction.TransactionTypes transactionType;

    public StatementEntry( String customerID, int amount, int currentBalance, Transaction.TransactionTypes transactionType)
    {
        this.customerID = customerID ;
        this.amount     = amount;
        this.currentBalance = currentBalance;
        this.transactionType = transactionType;
    }

    public String getCustomerId() {
        return this.customerID;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getCurrentBalance() {
        return this.currentBalance;
    }

    public String getTransactionType(){
        return this.transactionType + "";
    }

    @Override
    public String toString( )
    {
         String statementEntry =  String.format( "%1$-20s %2$-20s %3$-13d %4$10d", this.getCustomerId(),
                this.getTransactionType(), this.getAmount(), this.getCurrentBalance());
         return  statementEntry;
    }

}
