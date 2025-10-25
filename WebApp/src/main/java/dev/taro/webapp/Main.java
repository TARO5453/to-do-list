package dev.taro.webapp;
import dev.taro.webapp.database.DatabaseManager;
import io.muzoo.ssc.assignment.tracker.SscAssignment;

import java.util.List;

public class Main extends SscAssignment {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        db.createTable();
        System.out.println("\nAll Todos:");
        List<ToDo> allTodos = db.readAll();
        for (ToDo t : allTodos) {
            System.out.println(t.getId() + " | " + t.getUsername() + " | " + t.getTitle() + " | " + t.isDone());
        }
    }
}