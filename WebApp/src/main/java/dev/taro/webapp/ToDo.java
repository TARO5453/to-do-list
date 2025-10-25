package dev.taro.webapp;

public class ToDo {
    private String title;
    private String username;
    private boolean done;
    private int id;

    ToDo(String title, String username, int id) {
        this.title = title;
        this.username = username;
        this.id = id;
        this.done = false;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
