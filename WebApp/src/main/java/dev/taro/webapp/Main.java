package dev.taro.webapp;
import dev.taro.webapp.database.DatabaseManager;
import io.muzoo.ssc.assignment.tracker.SscAssignment;

import java.util.List;

public class Main extends SscAssignment {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        db.createTable();

        List<ToDo> allTodos = db.readAll();
        System.out.println("All todos:");
        for (ToDo td : allTodos) {
            System.out.println(td.getId() + " " + td.getUsername() + " " + td.getTitle() + " " + td.isDone());
        }
    }
}