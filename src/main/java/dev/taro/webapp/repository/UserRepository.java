package dev.taro.webapp.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.*;

public class UserRepository {
    private static final String URL = System.getenv("USER_DB_URL");
    private static final String USERNAME = System.getenv("USER_DB_USERNAME");
    private static final String PASSWORD = System.getenv("USER_DB_PASSWORD");
    // Singleton design pattern
    private static final UserRepository instance = new UserRepository();
    // Hikari CP
    private final HikariDataSource ds;

    private UserRepository() {
        ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
    }
    public static UserRepository getInstance() {
        return instance;
    }
    public DataSource getDataSource() {
        return ds;
    }
    /*
        Please make sure to have MySQL database table created in the server like createTable() method
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL)";
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
            e.printStackTrace();
        }
        return false;
    }

    // B-crypt
    private String hashPassword(String plainTextPassword) {
        // default 2^10
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public boolean checkPassword(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }



}
