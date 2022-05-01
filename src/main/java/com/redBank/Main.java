package com.redBank;

import com.redBank.Controller.AccountController;
import com.redBank.Controller.TransactionController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static TransactionController transactionController;
    static AccountController accountController;
    static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("---| Welcome to redBank |---");
        initialize();
    }

    public static void initialize() {
        String[] options = {
                "1- Deposit",
                "2- Withdraw",
                "3- Transfer",
                "4- Mobile top up",
                "5- Balance",
                "6- Exit"
        };
        scanner = new Scanner(System.in);

        int option = 0;
        while (option != 6){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        initDeposit();
                        break;
                    case 2:
                        initWithdraw();
                        break;
                    case 3:
                        initTransfer();
                        break;
                    case 4:
                        initTopup();
                        break;
                    case 5:
                        initBalance();
                        break;
                }
            } catch (InputMismatchException ex){
                System.out.println("Please enter a valid input!");
            } catch (Exception ex){
                System.out.println("An unexpected error happened. Please try again");
            }
        }
    }

    public static void initDeposit() throws SQLException {
        String srcAccountNumber;
        double amount;
        transactionController = new TransactionController();
        System.out.println("Please enter your account number: ");
        srcAccountNumber = scanner.next();
        System.out.println("Please enter the amount: ");
        amount = scanner.nextDouble();
        transactionController.deposit(srcAccountNumber, amount);
    }

    public static void initWithdraw() throws SQLException {
        String srcAccountNumber;
        double amount;
        transactionController = new TransactionController();
        System.out.println("Please enter your account number: ");
        srcAccountNumber = scanner.next();
        System.out.println("Please enter the amount: ");
        amount = scanner.nextDouble();
        transactionController.withdraw(srcAccountNumber, amount);
    }

    public static void initTransfer() throws SQLException {
        String srcAccountNumber, desAccountNumber;
        double amount;
        transactionController = new TransactionController();
        System.out.println("Please enter Source account number: ");
        srcAccountNumber = scanner.next();
        System.out.println("Please enter Destination account number: ");
        desAccountNumber = scanner.next();
        System.out.println("Please enter the amount: ");
        amount = scanner.nextDouble();
        transactionController.transfer(srcAccountNumber, desAccountNumber, amount);
    }

    public static void initTopup() throws SQLException {
        String srcAccountNumber;
        double amount;
        transactionController = new TransactionController();
        System.out.println("Please enter your account number: ");
        srcAccountNumber = scanner.next();
        System.out.println("Please the amount of top up: ");
        amount = scanner.nextDouble();
        transactionController.buyTopup(srcAccountNumber, amount);
    }

    public static void initBalance() throws SQLException {
        String srcAccountNumber;
        System.out.println("Please enter your account number: ");
        srcAccountNumber = scanner.next();
        accountController = new AccountController();
        accountController.getBalance(srcAccountNumber);
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
}
