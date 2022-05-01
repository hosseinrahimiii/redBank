package com.redBank.Service;

import com.redBank.Repository.TransactionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class TransactionService {

    public void deposit(String accountNumber, double amount) throws SQLException {
        AccountService accountService = new AccountService();
        if (!accountService.isValid(accountNumber)){
            System.out.println("Problem: Account number was not valid!");
        } else {
            TransactionRepository transactionRepository = new TransactionRepository();
            transactionRepository.deposit(accountNumber, amount);
        }
    }

    public void withdraw(String accountNumber, double amount) throws SQLException {
        AccountService accountService = new AccountService();
        if (!accountService.isValid(accountNumber)){
            System.out.println("Problem: Account number was not valid!");
        } else {
            double balance = accountService.getBalance(accountNumber);
            if (balance < amount){
                System.out.println("Problem: Your balance is not enough!");
            } else {
                TransactionRepository transactionRepository = new TransactionRepository();
                transactionRepository.withdraw(accountNumber, amount);
            }
        }
    }

    public void transfer(String srcAccountNumber, String desAccountNumber, double amount) throws SQLException {
        AccountService accountService = new AccountService();
        if (!accountService.isValid(srcAccountNumber)){
            System.out.println("Problem: Source account number was not valid!");
        } else if (!accountService.isValid(desAccountNumber)){
            System.out.println("Problem: Destination account number was not valid!");
        } else {
            double balance = accountService.getBalance(srcAccountNumber);
            if (balance < amount){
                System.out.println("Problem: Your balance is not enough!");
            } else {
                TransactionRepository transactionRepository = new TransactionRepository();
                transactionRepository.transfer(srcAccountNumber, desAccountNumber, amount);
            }
        }
    }

    public void buyTopup(String srcAccountNumber, double amount) throws SQLException {
        AccountService accountService = new AccountService();
        if (!accountService.isValid(srcAccountNumber)){
            System.out.println("Problem: Your account number was not valid!");
        } else {
            double balance = accountService.getBalance(srcAccountNumber);
            if (balance < amount){
                System.out.println("Problem: Your balance is not enough!");
            } else {
                TransactionRepository transactionRepository = new TransactionRepository();
                transactionRepository.buyTopup(srcAccountNumber, amount);
            }
        }
    }

    public String callTopupApi() throws IOException {
        URL url = new URL("http://numbersapi.com/random/math");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            return br.readLine();
        } finally {
            con.disconnect();
        }
    }
}
