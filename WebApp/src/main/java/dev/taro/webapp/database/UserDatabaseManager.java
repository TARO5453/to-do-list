package dev.taro.webapp.database;

import com.zaxxer.hikari.HikariDataSource;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.*;

public class UserDatabaseManager {
    private static final String URL = "jdbc:sqlite:data/users.db";
    private static final HikariDataSource ds = new HikariDataSource();
    static {
        ds.setJdbcUrl(URL);
    }
    public static DataSource getDataSource() {
        return ds;
    }
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL)";
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)){
            st.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /*
    Return true if successfully created a new account
     */
    public boolean createNewAccount(String username, String password) {
        if (username == null || password == null ||
                username.isEmpty() || password.isEmpty() || exists(username)) {
            return false;
        }
        try ( Connection connection = getDataSource().getConnection();
              PreparedStatement ps = connection.prepareStatement(
                      "INSERT INTO users (username, password) VALUES (?, ?)")
        ){
            ps.setString(1, username);
            ps.setString(2, hashPassword(password));
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    public String findUserByUsername(String username) {
        if (username == null) {
            return null;
        }
        try ( Connection connection = getDataSource().getConnection();
              PreparedStatement ps = connection.prepareStatement(
                      "SELECT * FROM users WHERE username = ?")
        ){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // return hashed password
               return  rs.getString("password");
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        return null;
    }
    public boolean exists(String username) {
        if (username == null) {
            return false;
        }
        try ( Connection connection = getDataSource().getConnection();
              PreparedStatement ps = connection.prepareStatement(
                      "SELECT * FROM users WHERE username = ?")
        ){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    // B-crypt
    private String hashPassword(String plainTextPassword) {
        // default 2^10
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    private boolean checkPassword(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }



}
