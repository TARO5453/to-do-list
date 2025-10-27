package dev.taro.webapp;
import dev.taro.webapp.database.DatabaseManager;
import dev.taro.webapp.database.UserDatabaseManager;
import io.muzoo.ssc.assignment.tracker.SscAssignment;

import java.util.List;

public class Main extends SscAssignment {
    public static void main(String[] args) {
        UserDatabaseManager userDB = new UserDatabaseManager();

        userDB.createTable();
        userDB.createNewAccount("alice", "mypassword");
        userDB.createNewAccount("bob", "bob");
    }
}