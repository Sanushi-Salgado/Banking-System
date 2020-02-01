package com.bankingsystem;

public class Statement {

    private final int  MAX_TRANS = 20 ;

    private final StatementEntry[] statement = new StatementEntry[ MAX_TRANS ] ;

    private final String accountHolder ;
    private final int accountNumber ;

    private volatile int transactionCount = 0 ;


    public Statement ( String accountHolder, int accountNumber )
    {
        this.accountHolder = accountHolder ;
        this.accountNumber = accountNumber ;
    }

    public synchronized void addTransaction( String CID, int amount, int balance, Transaction.TransactionTypes transactionType )
    {
        // Create a new statement entry & add to the statement
        statement[ transactionCount ] = new StatementEntry( CID, amount, balance, transactionType ) ;
        transactionCount++ ;
    }


    public void print ( )
    {
        System.out.println("\n---------------------------------------- Final Bank Statement ------------------------------");
        System.out.println( "Statement for "  +  accountHolder  + "'s Bank Account - Account Number: "    +  accountNumber ) ;
        System.out.println( "==========================================================================================\n" ) ;
        System.out.format("%1$-20s %2$-15s %3$12s  %4$13s\n", "Customer", "Transaction Type", "Amount", "Balance" ) ;
        System.out.println( "-------------------------------------------------------------------------------------------");

        int accountBalance = 0;
        for ( int tid = 0 ; tid < transactionCount ; tid++ ) {
            System.out.println(statement[tid]);
            accountBalance = statement[tid].getCurrentBalance();
        }

        System.out.println( "------------------------------------------------------------------------------------------");
        System.out.println("\nTotal no. of transactions occured to the account: " + transactionCount +
                           "\nCurrent account balance: Rs. " +  accountBalance);
        System.out.println( "==========================================================================================\n" );
    }

}