package com.redBank.Controller;

import com.redBank.Service.TransactionService;

import java.sql.SQLException;

public class TransactionController {
    TransactionService transactionService;

    public void deposit(String accountNumber, double amount) throws SQLException {
        transactionService = new TransactionService();
        transactionService.deposit(accountNumber, amount);
    }

    public void withdraw(String accountNumber, double amount) throws SQLException {
        transactionService = new TransactionService();
        transactionService.withdraw(accountNumber, amount);
    }

    public void transfer(String srcAccountNumber, String desAccountNumber, double amount) throws SQLException {
        transactionService = new TransactionService();
        transactionService.transfer(srcAccountNumber, desAccountNumber, amount);
    }

    public void buyTopup(String srcAccountNumber, double amount) throws SQLException {
        transactionService = new TransactionService();
        transactionService.buyTopup(srcAccountNumber, amount);
    }
}
