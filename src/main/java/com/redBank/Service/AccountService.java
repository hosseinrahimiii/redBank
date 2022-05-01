package com.redBank.Service;

import com.redBank.Repository.AccountRepository;

import java.sql.SQLException;

public class AccountService {

    public double getBalance(String accountNumber) throws SQLException {
        AccountRepository accountRepository = new AccountRepository();
        return accountRepository.getBalance(accountNumber);
    }

    public boolean isValid(String accountNumber) throws SQLException {
        if (accountNumber.length() != 5 || !isNumeric(accountNumber))
            return false;
        else {
            AccountRepository accountRepository = new AccountRepository();
            return accountRepository.isValid(accountNumber);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
}
