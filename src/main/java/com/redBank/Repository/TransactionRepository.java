package com.redBank.Repository;

import com.redBank.Model.TransactionType;
import com.redBank.Service.TransactionService;
import com.redBank.Util.JDBCUtil;
import java.sql.*;
import java.util.Date;

public class TransactionRepository {

    public void deposit(String accountNumber, double amount){
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + accountNumber);
                rs.next();
                double newBalance = rs.getDouble("BALANCE") + amount;
                stmt.executeQuery("UPDATE Account a " +
                                     "SET balance = " + newBalance +
                                     "WHERE a.accountnumber = " + accountNumber);
                saveTransaction(con, accountNumber, null, amount, new Date(), true, "", TransactionType.DEPOSIT);
                con.commit();
                System.out.println("*** Successful Transaction ***");
            } catch(Exception e){
                con.rollback();
                System.out.println(e);
                System.out.println("*** Failed Transaction ***");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void withdraw(String accountNumber, double amount){
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + accountNumber);
                rs.next();
                double newBalance = rs.getDouble("BALANCE") - amount;
                stmt.executeQuery("UPDATE Account a " +
                                     "SET balance = " + newBalance +
                                     "WHERE a.accountnumber = " + accountNumber);
                saveTransaction(con, accountNumber, null, amount, new Date(), true, "", TransactionType.WITHDRAW);
                con.commit();
                System.out.println("*** Successful Transaction ***");
            } catch(Exception e){
                con.rollback();
                System.out.println(e);
                System.out.println("*** Failed Transaction ***");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void transfer(String srcAccountNumber, String desAccountNumber, double amount){
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + srcAccountNumber);
                rs.next();
                double newBalance = rs.getDouble("BALANCE") - amount;
                stmt.executeQuery("UPDATE Account a " +
                                     "SET balance = " + newBalance +
                                     "WHERE a.accountnumber = " + srcAccountNumber);

                rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + desAccountNumber);
                rs.next();
                newBalance = rs.getDouble("BALANCE") + amount;
                stmt.executeQuery("UPDATE Account a " +
                                     "SET balance = " + newBalance +
                                     "WHERE a.accountnumber = " + desAccountNumber);
                saveTransaction(con, srcAccountNumber, desAccountNumber, amount, new Date(), true, "", TransactionType.TRANSFER);
                con.commit();
                System.out.println("*** Successful Transaction ***");
            } catch(Exception e){
                con.rollback();
                System.out.println(e);
                System.out.println("*** Failed Transaction ***");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void buyTopup(String accountNumber, double amount){
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + accountNumber);
                rs.next();
                double newBalance = rs.getDouble("BALANCE") - amount;
                stmt.executeQuery("UPDATE Account a " +
                                     "SET balance = " + newBalance +
                                     "WHERE a.accountnumber = " + accountNumber);
                saveTransaction(con, accountNumber, null, amount, new Date(), true, "", TransactionType.TOP_UP);
                TransactionService transactionService = new TransactionService();
                System.out.println("*** The top up code is ---> " + transactionService.callTopupApi());
                con.commit();
            } catch(Exception e){
                con.rollback();
                System.out.println(e);
                System.out.println("*** Failed Transaction ***");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void saveTransaction(Connection con, String srcAccountNumber, String desAccountNumber, double amount, Date date, boolean status, String description, TransactionType type) throws SQLException {
        String query = "INSERT INTO Transaction VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, srcAccountNumber);
            ps.setString(2, desAccountNumber);
            ps.setDouble(3, amount);
            ps.setDate(4, new java.sql.Date(date.getTime()));
            ps.setBoolean(5, status);
            ps.setString(6, description);
            ps.setString(7, type.toString());
            ps.execute();
        } catch(Exception e){
            System.out.println(e);
            con.rollback();
            throw e;
        }
    }
}
