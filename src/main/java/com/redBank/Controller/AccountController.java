package com.redBank.Controller;

import com.redBank.Service.AccountService;

import java.sql.SQLException;

public class AccountController {

    public void getBalance(String accountNumber) throws SQLException {
        AccountService accountService = new AccountService();
        System.out.println("Your balance is : " + accountService.getBalance(accountNumber));
    }
}
