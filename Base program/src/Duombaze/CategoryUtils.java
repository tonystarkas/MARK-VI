package Duombaze;
import SubKlases.Kategorijos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Duombaze.DatabaseUtils.connectToDb;
import static Duombaze.DatabaseUtils.disconnectFromDb;

public class CategoryUtils {

    public static void update(String name, int id) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        String query = "UPDATE kategorija Set pavadinimas = ? WHERE kategorijos_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static void add(String name, String kurejas) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        String query = "insert into kategorija(pavadinimas, kurejas) values(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, kurejas);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        String query = "DELETE FROM kategorija WHERE kategorijos_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static List<Kategorijos> getAllCategories() throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        List<Kategorijos> all = new ArrayList<>();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM kategorija";
        ResultSet cat = stmt.executeQuery(query);
        while (cat.next()) {
            String name = cat.getString("pavadinimas");
            String kurejas = cat.getString("kurejas");
            int categoryId = cat.getInt("kategorijos_id");
            all.add(new Kategorijos(name, kurejas, categoryId));
        }
        disconnectFromDb(connection, stmt);
        return all;
    }
}
