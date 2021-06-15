package Duombaze;
import SubKlases.Finansai;

import java.sql.*;
import java.util.ArrayList;

import static Duombaze.DatabaseUtils.connectToDb;
import static Duombaze.DatabaseUtils.disconnectFromDb;

public class FinanceUtils {
    public static ArrayList<Finansai> getFinances(int categoryId) throws SQLException, ClassNotFoundException {
        ArrayList<Finansai> finances = new ArrayList<>();
        Connection connection = connectToDb();
        String query = "SELECT * FROM finansai where kategorijos_id="+categoryId;
        Statement stmt = connection.createStatement();
        ResultSet fin = stmt.executeQuery(query);
        while(fin.next())
        {
            int finansu_id = fin.getInt(3);
            String name = fin.getString(1);
            double amount = fin.getDouble(2);
            int kategorijos_id = fin.getInt(4);
            finances.add(new Finansai(amount, name, kategorijos_id, finansu_id));
        }
        System.out.println(finances);
        return finances;
    }
    public static void add(String name, double amount, int categoryId) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO finansai (pavadinimas, suma, kategorijos_id) VALUES(?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setDouble(2, amount);
        preparedStatement.setInt(3, categoryId);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static void update(String name, double amount, int financeId) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = connectToDb();
        String query = "UPDATE finansai SET pavadinimas = ?, suma = ? where finansu_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setDouble(2, amount);
        preparedStatement.setInt(3, financeId);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        Statement stmt = connection.createStatement();
        String query = "DELETE from finansai where finansu_id=" + id;
        stmt.execute(query);
        disconnectFromDb(connection, stmt);
    }

    public static double getBalanceById(int id) throws SQLException, ClassNotFoundException {
        double balance = 0;
        Connection connection = connectToDb();
        String query = "SELECT * FROM finansai where kategorijos_id="+id;
        Statement stmt = connection.createStatement();
        ResultSet fin = stmt.executeQuery(query);
        while(fin.next())
        {
            double amount = fin.getDouble("suma");
            balance += amount;
        }
        return balance;
    }

    public static double getBalance() throws SQLException, ClassNotFoundException {
        double balance = 0;
        Connection connection = connectToDb();
        String query = "SELECT * FROM finansai";
        Statement stmt = connection.createStatement();
        ResultSet fin = stmt.executeQuery(query);
        while(fin.next())
        {
            double amount = fin.getDouble("suma");
            balance += amount;
        }
        return balance;
    }
}
