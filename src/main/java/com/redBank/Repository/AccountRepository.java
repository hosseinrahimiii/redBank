package com.redBank.Repository;

import com.redBank.Util.JDBCUtil;
import java.sql.*;

public class AccountRepository {

    public double getBalance(String accountNumber) throws SQLException {
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select balance from Account a where a.accountnumber =" + accountNumber);
                rs.next();
                return rs.getDouble("BALANCE");
            } catch(Exception e){
                System.out.println(e);
                throw e;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public boolean isValid(String accountNumber) throws SQLException {
        try(Connection con = JDBCUtil.getConnection()){
            try(Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT CASE WHEN MAX(accountNumber) IS NULL THEN 'false' ELSE 'true' END isValid " +
                                                    "FROM Account " +
                                                    "WHERE accountNumber = " + accountNumber);
                rs.next();
                return Boolean.parseBoolean(rs.getString("isValid"));
            } catch(Exception e){
                System.out.println(e);
                throw e;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
}
