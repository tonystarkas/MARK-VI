package Duombaze;
import SubKlases.Klientas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Duombaze.DatabaseUtils.connectToDb;
import static Duombaze.DatabaseUtils.disconnectFromDb;

public class UserUtils {
    public static boolean success = false;
    public static void createUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = connectToDb();
        String insert = "INSERT INTO klientas (VARDAS, SLAPTAZODIS) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        disconnectFromDb(connection, preparedStatement);
    }
    public static Klientas empLogin(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet user = null;
        try {
            connection = connectToDb();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String query = "SELECT * from klientas where vardas=? and slaptazodis=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            user = preparedStatement.executeQuery();
        } catch (Exception e) {
            disconnectFromDb(connection, preparedStatement);
            e.printStackTrace();
            return null;
        }

        if (user.next()){
            String name = user.getString(1);
            String pass = user.getString(2);
            success = true;
            return new Klientas(name, pass);
        }
        else{
            success = false;
            return null;
        }
    }
    public static boolean returnSuccess(){
        return success;
    }
}
