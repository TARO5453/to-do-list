package dev.taro.webapp.repository;

import com.zaxxer.hikari.HikariDataSource;
import dev.taro.webapp.model.Todo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private static final String URL = System.getenv("TODO_DB_URL");
    private static final String USERNAME = System.getenv("TODO_DB_USERNAME");
    private static final String PASSWORD = System.getenv("TODO_DB_PASSWORD");
    // Singleton
    private static final TodoRepository instance =  new TodoRepository();
    // Hikari CP
    private final HikariDataSource ds;

    private TodoRepository() {
        ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
    public static TodoRepository getInstance() {
        return instance;
    }
    public DataSource getDataSource() {
        return ds;
    }
    /*
        Please make sure to have MySQL database table created in the server like createTable() method
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS todos (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(255) NOT NULL," +
                "title VARCHAR(255) NOT NULL," +
                "done INTEGER NOT NULL DEFAULT 0)";
        // default every to-do is initialized with done = false
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)){
            st.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // CRUD
    // Create
    public void create(Todo td) {
        try ( Connection connection = getDataSource().getConnection();
              PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO todos (username, title, done) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        ){
            ps.setString(1, td.getUsername());
            ps.setString(2, td.getTitle());
            ps.setInt(3, td.isDone() ? 1 : 0);
            ps.executeUpdate();
            // Set the generated id to td
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                td.setId(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // Update
    public void updateTitle(String username, String oldTitle, String newTitle) {
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                "UPDATE todos SET title = ? WHERE username = ? AND title = ?;")
        ){
            ps.setString(1, newTitle);
            ps.setString(2, username);
            ps.setString(3, oldTitle);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void updateDone(String username, String title ,Boolean done) {
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                "UPDATE todos SET done = ? WHERE username = ? AND title = ?;")
        ){
            ps.setInt(1, (done)?1:0 );
            ps.setString(2, username);
            ps.setString(3, title);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // Read
    public List<Todo> read(String username) {
        List<Todo> list = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM todos WHERE username = ?")
        ){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Todo td = new Todo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4) == 1);
                list.add(td);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    public Todo read(String username, String title) {
        Todo td = null;
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM todos WHERE username = ? AND title = ?;")
        ){
            ps.setString(1, username);
            ps.setString(2, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                td = new Todo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4) == 1);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return td;
    }
    // Delete
    public void delete(String username, String title) {
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM todos WHERE username = ? AND title = ?;")
        ){
            ps.setString(1, username);
            ps.setString(2, title);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
