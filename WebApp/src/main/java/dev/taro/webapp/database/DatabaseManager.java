package dev.taro.webapp.database;

import dev.taro.webapp.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    public DatabaseManager(){
        String url = "jdbc:sqlite:data/todo.db";
        try {
            this.connection = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS todos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "title TEXT NOT NULL," +
                "done INTEGER NOT NULL DEFAULT 0)";
        // default every to-do is initialized with done = false

        try {
            Statement st = this.connection.createStatement();
            st.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // CRUD
    // Create
    public void create(ToDo td) {
        try ( PreparedStatement ps = connection.prepareStatement(
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
    public void updateTitle(int id, String title) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE todos SET title = ? WHERE id = ?")
        ){
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void updateDone(int id ,Boolean done) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE todos SET done = ? WHERE id = ?")
        ){
            ps.setInt(1, (done)?1:0 );
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // Read
    public List<ToDo> readAll() {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        try(Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ToDo td = new ToDo(rs.getInt(1),
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
    public List<ToDo> read(String username) {
        List<ToDo> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM todos WHERE username = ?",
                Statement.RETURN_GENERATED_KEYS)
        ){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ToDo td = new ToDo(rs.getInt(1),
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
    // Delete
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM todos WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
